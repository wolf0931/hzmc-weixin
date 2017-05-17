package com.hzmc.weixin.admin.dao.model;

import java.io.Serializable;

public class WxRedpackTemplet implements Serializable {
    private Integer id;

    /**
     * 活动名称
     *
     * @mbg.generated
     */
    private String actName;

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    private String sendName;

    private String wishing;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * 红包发放总人数
     *
     * @mbg.generated
     */
    private Integer totalNum;

    /**
     * 付款总金额
     *
     * @mbg.generated
     */
    private String totalAmount;

    /**
     * 中奖率
     *
     * @mbg.generated
     */
    private String winningRate;

    private String startTime;

    private String endTime;

    private String minAmount;

    private String maxAmount;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getWinningRate() {
        return winningRate;
    }

    public void setWinningRate(String winningRate) {
        this.winningRate = winningRate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", actName=").append(actName);
        sb.append(", sendName=").append(sendName);
        sb.append(", wishing=").append(wishing);
        sb.append(", remark=").append(remark);
        sb.append(", totalNum=").append(totalNum);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", winningRate=").append(winningRate);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", minAmount=").append(minAmount);
        sb.append(", maxAmount=").append(maxAmount);
        sb.append("]");
        return sb.toString();
    }
}