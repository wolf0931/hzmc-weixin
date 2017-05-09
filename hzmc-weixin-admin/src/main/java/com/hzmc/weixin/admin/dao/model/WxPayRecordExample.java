package com.hzmc.weixin.admin.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WxPayRecordExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public WxPayRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMchBillnoIsNull() {
            addCriterion("mch_billno is null");
            return (Criteria) this;
        }

        public Criteria andMchBillnoIsNotNull() {
            addCriterion("mch_billno is not null");
            return (Criteria) this;
        }

        public Criteria andMchBillnoEqualTo(String value) {
            addCriterion("mch_billno =", value, "mchBillno");
            return (Criteria) this;
        }

        public Criteria andMchBillnoNotEqualTo(String value) {
            addCriterion("mch_billno <>", value, "mchBillno");
            return (Criteria) this;
        }

        public Criteria andMchBillnoGreaterThan(String value) {
            addCriterion("mch_billno >", value, "mchBillno");
            return (Criteria) this;
        }

        public Criteria andMchBillnoGreaterThanOrEqualTo(String value) {
            addCriterion("mch_billno >=", value, "mchBillno");
            return (Criteria) this;
        }

        public Criteria andMchBillnoLessThan(String value) {
            addCriterion("mch_billno <", value, "mchBillno");
            return (Criteria) this;
        }

        public Criteria andMchBillnoLessThanOrEqualTo(String value) {
            addCriterion("mch_billno <=", value, "mchBillno");
            return (Criteria) this;
        }

        public Criteria andMchBillnoLike(String value) {
            addCriterion("mch_billno like", value, "mchBillno");
            return (Criteria) this;
        }

        public Criteria andMchBillnoNotLike(String value) {
            addCriterion("mch_billno not like", value, "mchBillno");
            return (Criteria) this;
        }

        public Criteria andMchBillnoIn(List<String> values) {
            addCriterion("mch_billno in", values, "mchBillno");
            return (Criteria) this;
        }

        public Criteria andMchBillnoNotIn(List<String> values) {
            addCriterion("mch_billno not in", values, "mchBillno");
            return (Criteria) this;
        }

        public Criteria andMchBillnoBetween(String value1, String value2) {
            addCriterion("mch_billno between", value1, value2, "mchBillno");
            return (Criteria) this;
        }

        public Criteria andMchBillnoNotBetween(String value1, String value2) {
            addCriterion("mch_billno not between", value1, value2, "mchBillno");
            return (Criteria) this;
        }

        public Criteria andWxappidIsNull() {
            addCriterion("wxappid is null");
            return (Criteria) this;
        }

        public Criteria andWxappidIsNotNull() {
            addCriterion("wxappid is not null");
            return (Criteria) this;
        }

        public Criteria andWxappidEqualTo(String value) {
            addCriterion("wxappid =", value, "wxappid");
            return (Criteria) this;
        }

        public Criteria andWxappidNotEqualTo(String value) {
            addCriterion("wxappid <>", value, "wxappid");
            return (Criteria) this;
        }

        public Criteria andWxappidGreaterThan(String value) {
            addCriterion("wxappid >", value, "wxappid");
            return (Criteria) this;
        }

        public Criteria andWxappidGreaterThanOrEqualTo(String value) {
            addCriterion("wxappid >=", value, "wxappid");
            return (Criteria) this;
        }

        public Criteria andWxappidLessThan(String value) {
            addCriterion("wxappid <", value, "wxappid");
            return (Criteria) this;
        }

        public Criteria andWxappidLessThanOrEqualTo(String value) {
            addCriterion("wxappid <=", value, "wxappid");
            return (Criteria) this;
        }

        public Criteria andWxappidLike(String value) {
            addCriterion("wxappid like", value, "wxappid");
            return (Criteria) this;
        }

        public Criteria andWxappidNotLike(String value) {
            addCriterion("wxappid not like", value, "wxappid");
            return (Criteria) this;
        }

        public Criteria andWxappidIn(List<String> values) {
            addCriterion("wxappid in", values, "wxappid");
            return (Criteria) this;
        }

        public Criteria andWxappidNotIn(List<String> values) {
            addCriterion("wxappid not in", values, "wxappid");
            return (Criteria) this;
        }

        public Criteria andWxappidBetween(String value1, String value2) {
            addCriterion("wxappid between", value1, value2, "wxappid");
            return (Criteria) this;
        }

        public Criteria andWxappidNotBetween(String value1, String value2) {
            addCriterion("wxappid not between", value1, value2, "wxappid");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNull() {
            addCriterion("total_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountEqualTo(Integer value) {
            addCriterion("total_amount =", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotEqualTo(Integer value) {
            addCriterion("total_amount <>", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThan(Integer value) {
            addCriterion("total_amount >", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_amount >=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThan(Integer value) {
            addCriterion("total_amount <", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThanOrEqualTo(Integer value) {
            addCriterion("total_amount <=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIn(List<Integer> values) {
            addCriterion("total_amount in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotIn(List<Integer> values) {
            addCriterion("total_amount not in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountBetween(Integer value1, Integer value2) {
            addCriterion("total_amount between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("total_amount not between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNull() {
            addCriterion("mch_id is null");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNotNull() {
            addCriterion("mch_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchIdEqualTo(String value) {
            addCriterion("mch_id =", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotEqualTo(String value) {
            addCriterion("mch_id <>", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThan(String value) {
            addCriterion("mch_id >", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThanOrEqualTo(String value) {
            addCriterion("mch_id >=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThan(String value) {
            addCriterion("mch_id <", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThanOrEqualTo(String value) {
            addCriterion("mch_id <=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLike(String value) {
            addCriterion("mch_id like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotLike(String value) {
            addCriterion("mch_id not like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdIn(List<String> values) {
            addCriterion("mch_id in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotIn(List<String> values) {
            addCriterion("mch_id not in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdBetween(String value1, String value2) {
            addCriterion("mch_id between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotBetween(String value1, String value2) {
            addCriterion("mch_id not between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andSendListidIsNull() {
            addCriterion("send_listid is null");
            return (Criteria) this;
        }

        public Criteria andSendListidIsNotNull() {
            addCriterion("send_listid is not null");
            return (Criteria) this;
        }

        public Criteria andSendListidEqualTo(String value) {
            addCriterion("send_listid =", value, "sendListid");
            return (Criteria) this;
        }

        public Criteria andSendListidNotEqualTo(String value) {
            addCriterion("send_listid <>", value, "sendListid");
            return (Criteria) this;
        }

        public Criteria andSendListidGreaterThan(String value) {
            addCriterion("send_listid >", value, "sendListid");
            return (Criteria) this;
        }

        public Criteria andSendListidGreaterThanOrEqualTo(String value) {
            addCriterion("send_listid >=", value, "sendListid");
            return (Criteria) this;
        }

        public Criteria andSendListidLessThan(String value) {
            addCriterion("send_listid <", value, "sendListid");
            return (Criteria) this;
        }

        public Criteria andSendListidLessThanOrEqualTo(String value) {
            addCriterion("send_listid <=", value, "sendListid");
            return (Criteria) this;
        }

        public Criteria andSendListidLike(String value) {
            addCriterion("send_listid like", value, "sendListid");
            return (Criteria) this;
        }

        public Criteria andSendListidNotLike(String value) {
            addCriterion("send_listid not like", value, "sendListid");
            return (Criteria) this;
        }

        public Criteria andSendListidIn(List<String> values) {
            addCriterion("send_listid in", values, "sendListid");
            return (Criteria) this;
        }

        public Criteria andSendListidNotIn(List<String> values) {
            addCriterion("send_listid not in", values, "sendListid");
            return (Criteria) this;
        }

        public Criteria andSendListidBetween(String value1, String value2) {
            addCriterion("send_listid between", value1, value2, "sendListid");
            return (Criteria) this;
        }

        public Criteria andSendListidNotBetween(String value1, String value2) {
            addCriterion("send_listid not between", value1, value2, "sendListid");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNull() {
            addCriterion("ctime is null");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNotNull() {
            addCriterion("ctime is not null");
            return (Criteria) this;
        }

        public Criteria andCtimeEqualTo(String value) {
            addCriterion("ctime =", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotEqualTo(String value) {
            addCriterion("ctime <>", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThan(String value) {
            addCriterion("ctime >", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThanOrEqualTo(String value) {
            addCriterion("ctime >=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThan(String value) {
            addCriterion("ctime <", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThanOrEqualTo(String value) {
            addCriterion("ctime <=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLike(String value) {
            addCriterion("ctime like", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotLike(String value) {
            addCriterion("ctime not like", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeIn(List<String> values) {
            addCriterion("ctime in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotIn(List<String> values) {
            addCriterion("ctime not in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeBetween(String value1, String value2) {
            addCriterion("ctime between", value1, value2, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotBetween(String value1, String value2) {
            addCriterion("ctime not between", value1, value2, "ctime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}