package com.hzmc.weixin.admin.dao.model;

import java.io.Serializable;

public class WxPayRecord implements Serializable {
	private Integer id;

	/**
	 * 商户订单号
	 *
	 * @mbg.generated
	 */
	private String mchBillno;

	/**
	 * 公众账号appid
	 *
	 * @mbg.generated
	 */
	private String wxappid;

	/**
	 * 红包模板Id
	 */
	private int redpacktemid;

	/**
	 * 用户openid
	 *
	 * @mbg.generated
	 */
	private String openid;

	/**
	 * 付款金额
	 *
	 * @mbg.generated
	 */
	private Integer totalAmount;

	/**
	 * 商户号
	 *
	 * @mbg.generated
	 */
	private String mchId;

	private String sendListid;

	private String ctime;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMchBillno() {
		return mchBillno;
	}

	public void setMchBillno(String mchBillno) {
		this.mchBillno = mchBillno;
	}

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}

	public int getRedpacktemid() {
		return redpacktemid;
	}

	public void setRedpacktemid(int redpacktemid) {
		this.redpacktemid = redpacktemid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getSendListid() {
		return sendListid;
	}

	public void setSendListid(String sendListid) {
		this.sendListid = sendListid;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", mchBillno=").append(mchBillno);
		sb.append(", redpacktemid=").append(redpacktemid);
		sb.append(", wxappid=").append(wxappid);
		sb.append(", openid=").append(openid);
		sb.append(", totalAmount=").append(totalAmount);
		sb.append(", mchId=").append(mchId);
		sb.append(", sendListid=").append(sendListid);
		sb.append(", ctime=").append(ctime);
		sb.append("]");
		return sb.toString();
	}
}