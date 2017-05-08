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
    private int totalAmount;

    /**
     * 中奖率
     *
     * @mbg.generated
     */
    private String winningRate;

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

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getWinningRate() {
        return winningRate;
    }

    public void setWinningRate(String winningRate) {
        this.winningRate = winningRate;
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
        sb.append("]");
        return sb.toString();
    }
}