/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 127.0.0.1
 Source Database       : hzmc-weixin

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 05/17/2017 15:21:21 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(32) NOT NULL COMMENT '密码MD5(密码+盐)',
  `type` int(4) NOT NULL DEFAULT '0' COMMENT '用户类型：0-普通用户、1-超级管理员',
  `realname` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `ctime` varchar(255) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'admin', '18E2651FDA42B376B33ADA41870AB740', '0', 'admin', 'dee2cd761f2848088a3cc699fe8ceec2', '1493962563547'), ('15', 'string', 'F04AEF55279EBBDC0447D95FBCDDE358', '0', 'string', '2fbef05d0294408387d00b2b463a6c3a', '1494470030986');
COMMIT;

-- ----------------------------
--  Table structure for `wx_group`
-- ----------------------------
DROP TABLE IF EXISTS `wx_group`;
CREATE TABLE `wx_group` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `wx_pay_record`
-- ----------------------------
DROP TABLE IF EXISTS `wx_pay_record`;
CREATE TABLE `wx_pay_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mch_billno` varchar(255) DEFAULT NULL COMMENT '商户订单号',
  `redpacktemid` int(11) DEFAULT NULL COMMENT '红包模板id',
  `wxappid` varchar(255) DEFAULT NULL COMMENT '公众账号appid',
  `openid` varchar(255) DEFAULT NULL COMMENT '用户openid',
  `total_amount` int(100) DEFAULT NULL COMMENT '付款金额',
  `mch_id` varchar(255) DEFAULT NULL COMMENT '商户号',
  `status` varchar(255) DEFAULT NULL COMMENT '红包状态（SENDING:发放中\nSENT:已发放待领取\nFAILED：发放失败\nRECEIVED:已领取\nRFUND_ING:退款中\nREFUND:已退款 ）',
  `send_listid` varchar(255) DEFAULT NULL,
  `ctime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `wx_redpack_templet`
-- ----------------------------
DROP TABLE IF EXISTS `wx_redpack_templet`;
CREATE TABLE `wx_redpack_templet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `act_name` varchar(255) DEFAULT NULL COMMENT '活动名称',
  `send_name` varchar(255) DEFAULT NULL COMMENT '商户名称',
  `wishing` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `total_num` int(11) DEFAULT NULL COMMENT '红包发放总人数',
  `total_amount` varchar(255) DEFAULT NULL COMMENT '付款总金额',
  `winning_rate` varchar(255) DEFAULT NULL COMMENT '中奖率',
  `startTime` varchar(255) DEFAULT NULL,
  `endTime` varchar(255) DEFAULT NULL,
  `min_amount` varchar(11) DEFAULT NULL,
  `max_amount` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `wx_tag`
-- ----------------------------
DROP TABLE IF EXISTS `wx_tag`;
CREATE TABLE `wx_tag` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `count` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `wx_user`
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sex` int(11) DEFAULT NULL,
  `city` varchar(255)  DEFAULT NULL,
  `country` varchar(255)  DEFAULT NULL,
  `province` varchar(255)  DEFAULT NULL,
  `language` varchar(255)  DEFAULT NULL,
  `remark` varchar(255)  DEFAULT NULL,
  `subscribe` int(11) DEFAULT NULL,
  `openid` varchar(255)  DEFAULT NULL,
  `nickname` varchar(255)  DEFAULT NULL COMMENT '昵称',
  `headimgurl` varchar(255)  DEFAULT NULL,
  `subscribe_time` datetime DEFAULT NULL,
  `unionid` int(11) DEFAULT NULL,
  `groupid` int(11) DEFAULT NULL,
  `tagid_list` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
