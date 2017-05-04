package com.hzmc.weixin.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hzmc.weixin.pay.util.DateDeserializer;

import java.util.Date;
import java.util.List;

/**
 * Created by wph on 2017/4/24.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedPackResult extends BaseResponse {

	@JsonProperty("mch_billno")
	private String billNumber;

	@JsonProperty("total_amount")
	private int totalAmount;

	@JsonProperty("total_num")
	private int number;

	@JsonProperty("send_type")
	private String sendType;

	@JsonProperty("reason")
	private String reason;

	@JsonProperty("hb_type")
	private String readPackType;

	@JsonProperty("send_time")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date sendTime;

	@JsonProperty("refund_time")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date refundTime;

	@JsonProperty("refund_amount")
	private int refundAmount;


	@JsonProperty("hblist")
	private List<RedPackReport> reports;

	public RedPackResult() {
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReadPackType() {
		return readPackType;
	}

	public void setReadPackType(String readPackType) {
		this.readPackType = readPackType;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public int getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(int refundAmount) {
		this.refundAmount = refundAmount;
	}

	public List<RedPackReport> getReports() {
		return reports;
	}

	public void setReports(List<RedPackReport> reports) {
		this.reports = reports;
	}

	@Override
	public String toString() {
		return "RedPackResult{" +
				"billNumber='" + billNumber + '\'' +
				", totalAmount=" + totalAmount +
				", number=" + number +
				", sendType='" + sendType + '\'' +
				", reason='" + reason + '\'' +
				", readPackType='" + readPackType + '\'' +
				", sendTime=" + sendTime +
				", refundTime=" + refundTime +
				", refundAmount=" + refundAmount +
				", reports=" + reports +
				'}';
	}
}
