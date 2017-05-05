package com.hzmc.weixin.admin.util;

import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hzmc.weixin.admin.util.StringUtil.lineToHump;


/**
 * 代码生成类
 */
public class MybatisGeneratorUtil {

	// generatorConfig模板路径
	private static String generatorConfig_vm = "hzmc-weixin-admin/src/main/resources/template/generatorConfig.vm";
	// Service模板路径
	private static String service_vm = "hzmc-weixin-admin/src/main/resources/template/Service.vm";
	// ServiceMock模板路径
	private static String serviceMock_vm = "hzmc-weixin-admin/src/main/resources/template/ServiceMock.vm";
	// ServiceImpl模板路径
	private static String serviceImpl_vm = "hzmc-weixin-admin/src/main/resources/template/ServiceImpl.vm";

	// Controller 模板路径
	private static String Controller_vm = "hzmc-weixin-admin/src/main/resources/template/Controller.vm";

	/**
	 * 根据模板生成generatorConfig.xml文件
	 *
	 * @param jdbc_driver   驱动路径
	 * @param jdbc_url      链接
	 * @param jdbc_username 帐号
	 * @param jdbc_password 密码
	 * @param module        项目模块
	 * @param database      数据库
	 * @param table_prefix  表前缀
	 * @param package_name  包名
	 */
	public static void generator(
			String jdbc_driver,
			String jdbc_url,
			String jdbc_username,
			String jdbc_password,
			String module,
			String database,
			String table_prefix,
			String package_name,
			Map<String, String> last_insert_id_tables) throws Exception {

		//String targetProject = module + "/" + module + "-dao";
		//String module_path = module + "/" + module + "-dao/src/main/resources/generatorConfig.xml";
		String targetProject = module;
		String module_path = module + "/src/main/resources/generatorConfig.xml";
		String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '"
				+ database + "' AND table_name LIKE '" + table_prefix + "_%';";

		System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
		List<Map<String, Object>> tables = new ArrayList<>();
		try {
			VelocityContext context = new VelocityContext();
			Map<String, Object> table;

			// 查询定制前缀项目的所有表
			JdbcUtil jdbcUtil = new JdbcUtil(jdbc_driver, jdbc_url, jdbc_username, AESUtil.AESDecode(jdbc_password));
			List<Map> result = jdbcUtil.selectByParams(sql, null);
			for (Map map : result) {
				System.out.println(map.get("TABLE_NAME"));
				table = new HashMap<>();
				table.put("table_name", map.get("TABLE_NAME"));
				table.put("model_name", lineToHump(ObjectUtils.toString(map.get("TABLE_NAME"))));
				tables.add(table);
			}
			jdbcUtil.release();

			//String targetProject_sqlMap = module + "/" + module + "service";
			String targetProject_sqlMap = module;
			context.put("tables", tables);
			context.put("generator_javaModelGenerator_targetPackage", package_name + ".dao.model");
			context.put("generator_sqlMapGenerator_targetPackage", package_name + ".dao.mapper");
			context.put("generator_javaClientGenerator_targetPackage", package_name + ".dao.mapper");
			context.put("targetProject", targetProject);
			context.put("targetProject_sqlMap", targetProject_sqlMap);
			context.put("generator_jdbc_password", AESUtil.AESDecode(jdbc_password));
			context.put("last_insert_id_tables", last_insert_id_tables);
			VelocityUtil.generate(generatorConfig_vm, module_path, context);
			// 删除旧代码
			deleteDir(new File(targetProject + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/model"));
			deleteDir(new File(targetProject + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/mapper"));
			deleteDir(new File(targetProject_sqlMap + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/mapper"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("========== 结束生成generatorConfig.xml文件 ==========");

		System.out.println("========== 开始运行MybatisGenerator ==========");
		List<String> warnings = new ArrayList<>();
		File configFile = new File(module_path);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		for (String warning : warnings) {
			System.out.println(warning);
		}
		System.out.println("========== 结束运行MybatisGenerator ==========");

		System.out.println("========== 开始生成Service ==========");
		String ctime = new SimpleDateFormat("yyyy/M/d").format(new Date());
		//String servicePath = module + "/" + module + "-rpc-api" + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/rpc/api";
		//String serviceImplPath = module + "/" + module + "-rpc-service" + "/src/main/java/"
		// + package_name.replaceAll("\\.", "/") + "/rpc/service/impl";

		String servicePath = module + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/service";
		String serviceImplPath = module + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/service/impl";
		String controllerPath = module + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/controller";
		for (int i = 0; i < tables.size(); i++) {
			String model = lineToHump(ObjectUtils.toString(tables.get(i).get("table_name")));
			String service = servicePath + "/" + model + "Service.java";
			String serviceMock = servicePath + "/" + model + "ServiceMock.java";
			String serviceImpl = serviceImplPath + "/" + model + "ServiceImpl.java";
			String controller = controllerPath + "/" + model + "Controller.java";
			// 生成service
			File serviceFile = new File(service);
			if (!serviceFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("ctime", ctime);
				VelocityUtil.generate(service_vm, service, context);
				System.out.println(service);
			}
			// 生成serviceMock
		/*	File serviceMockFile = new File(serviceMock);
			if (!serviceMockFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("ctime", ctime);
				VelocityUtil.generate(serviceMock_vm, serviceMock, context);
				System.out.println(serviceMock);
			}*/
			// 生成serviceImpl
			File serviceImplFile = new File(serviceImpl);
			if (!serviceImplFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("mapper", StringUtil.toLowerCaseFirstOne(model));
				context.put("ctime", ctime);
				VelocityUtil.generate(serviceImpl_vm, serviceImpl, context);
				System.out.println(serviceImpl);
			}

			// 生成serviceImpl
			File controllerFile = new File(controller);
			if (!controllerFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", model);
				//context.put("mapper", StringUtil.toLowerCaseFirstOne(model));
				context.put("ctime", ctime);
				VelocityUtil.generate(Controller_vm, controller, context);
				System.out.println(controllerFile);
			}
		}
		System.out.println("========== 结束生成Service ==========");

		System.out.println("========== 开始生成Controller ==========");
		System.out.println("========== 结束生成Controller ==========");
	}

	// 递归删除非空文件夹
	public static void deleteDir(File dir) {
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteDir(files[i]);
			}
		}
		dir.delete();
	}

}
