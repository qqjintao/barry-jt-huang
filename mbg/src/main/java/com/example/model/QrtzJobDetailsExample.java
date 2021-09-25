package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class QrtzJobDetailsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QrtzJobDetailsExample() {
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

    protected abstract static class GeneratedCriteria {
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

        public Criteria andSchedNameIsNull() {
            addCriterion("sched_name is null");
            return (Criteria) this;
        }

        public Criteria andSchedNameIsNotNull() {
            addCriterion("sched_name is not null");
            return (Criteria) this;
        }

        public Criteria andSchedNameEqualTo(String value) {
            addCriterion("sched_name =", value, "schedName");
            return (Criteria) this;
        }

        public Criteria andSchedNameNotEqualTo(String value) {
            addCriterion("sched_name <>", value, "schedName");
            return (Criteria) this;
        }

        public Criteria andSchedNameGreaterThan(String value) {
            addCriterion("sched_name >", value, "schedName");
            return (Criteria) this;
        }

        public Criteria andSchedNameGreaterThanOrEqualTo(String value) {
            addCriterion("sched_name >=", value, "schedName");
            return (Criteria) this;
        }

        public Criteria andSchedNameLessThan(String value) {
            addCriterion("sched_name <", value, "schedName");
            return (Criteria) this;
        }

        public Criteria andSchedNameLessThanOrEqualTo(String value) {
            addCriterion("sched_name <=", value, "schedName");
            return (Criteria) this;
        }

        public Criteria andSchedNameLike(String value) {
            addCriterion("sched_name like", value, "schedName");
            return (Criteria) this;
        }

        public Criteria andSchedNameNotLike(String value) {
            addCriterion("sched_name not like", value, "schedName");
            return (Criteria) this;
        }

        public Criteria andSchedNameIn(List<String> values) {
            addCriterion("sched_name in", values, "schedName");
            return (Criteria) this;
        }

        public Criteria andSchedNameNotIn(List<String> values) {
            addCriterion("sched_name not in", values, "schedName");
            return (Criteria) this;
        }

        public Criteria andSchedNameBetween(String value1, String value2) {
            addCriterion("sched_name between", value1, value2, "schedName");
            return (Criteria) this;
        }

        public Criteria andSchedNameNotBetween(String value1, String value2) {
            addCriterion("sched_name not between", value1, value2, "schedName");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNull() {
            addCriterion("job_name is null");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNotNull() {
            addCriterion("job_name is not null");
            return (Criteria) this;
        }

        public Criteria andJobNameEqualTo(String value) {
            addCriterion("job_name =", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotEqualTo(String value) {
            addCriterion("job_name <>", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThan(String value) {
            addCriterion("job_name >", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThanOrEqualTo(String value) {
            addCriterion("job_name >=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThan(String value) {
            addCriterion("job_name <", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThanOrEqualTo(String value) {
            addCriterion("job_name <=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLike(String value) {
            addCriterion("job_name like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotLike(String value) {
            addCriterion("job_name not like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameIn(List<String> values) {
            addCriterion("job_name in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotIn(List<String> values) {
            addCriterion("job_name not in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameBetween(String value1, String value2) {
            addCriterion("job_name between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotBetween(String value1, String value2) {
            addCriterion("job_name not between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobGroupIsNull() {
            addCriterion("job_group is null");
            return (Criteria) this;
        }

        public Criteria andJobGroupIsNotNull() {
            addCriterion("job_group is not null");
            return (Criteria) this;
        }

        public Criteria andJobGroupEqualTo(String value) {
            addCriterion("job_group =", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupNotEqualTo(String value) {
            addCriterion("job_group <>", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupGreaterThan(String value) {
            addCriterion("job_group >", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupGreaterThanOrEqualTo(String value) {
            addCriterion("job_group >=", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupLessThan(String value) {
            addCriterion("job_group <", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupLessThanOrEqualTo(String value) {
            addCriterion("job_group <=", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupLike(String value) {
            addCriterion("job_group like", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupNotLike(String value) {
            addCriterion("job_group not like", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupIn(List<String> values) {
            addCriterion("job_group in", values, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupNotIn(List<String> values) {
            addCriterion("job_group not in", values, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupBetween(String value1, String value2) {
            addCriterion("job_group between", value1, value2, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupNotBetween(String value1, String value2) {
            addCriterion("job_group not between", value1, value2, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andJobClassNameIsNull() {
            addCriterion("job_class_name is null");
            return (Criteria) this;
        }

        public Criteria andJobClassNameIsNotNull() {
            addCriterion("job_class_name is not null");
            return (Criteria) this;
        }

        public Criteria andJobClassNameEqualTo(String value) {
            addCriterion("job_class_name =", value, "jobClassName");
            return (Criteria) this;
        }

        public Criteria andJobClassNameNotEqualTo(String value) {
            addCriterion("job_class_name <>", value, "jobClassName");
            return (Criteria) this;
        }

        public Criteria andJobClassNameGreaterThan(String value) {
            addCriterion("job_class_name >", value, "jobClassName");
            return (Criteria) this;
        }

        public Criteria andJobClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("job_class_name >=", value, "jobClassName");
            return (Criteria) this;
        }

        public Criteria andJobClassNameLessThan(String value) {
            addCriterion("job_class_name <", value, "jobClassName");
            return (Criteria) this;
        }

        public Criteria andJobClassNameLessThanOrEqualTo(String value) {
            addCriterion("job_class_name <=", value, "jobClassName");
            return (Criteria) this;
        }

        public Criteria andJobClassNameLike(String value) {
            addCriterion("job_class_name like", value, "jobClassName");
            return (Criteria) this;
        }

        public Criteria andJobClassNameNotLike(String value) {
            addCriterion("job_class_name not like", value, "jobClassName");
            return (Criteria) this;
        }

        public Criteria andJobClassNameIn(List<String> values) {
            addCriterion("job_class_name in", values, "jobClassName");
            return (Criteria) this;
        }

        public Criteria andJobClassNameNotIn(List<String> values) {
            addCriterion("job_class_name not in", values, "jobClassName");
            return (Criteria) this;
        }

        public Criteria andJobClassNameBetween(String value1, String value2) {
            addCriterion("job_class_name between", value1, value2, "jobClassName");
            return (Criteria) this;
        }

        public Criteria andJobClassNameNotBetween(String value1, String value2) {
            addCriterion("job_class_name not between", value1, value2, "jobClassName");
            return (Criteria) this;
        }

        public Criteria andIsDurableIsNull() {
            addCriterion("is_durable is null");
            return (Criteria) this;
        }

        public Criteria andIsDurableIsNotNull() {
            addCriterion("is_durable is not null");
            return (Criteria) this;
        }

        public Criteria andIsDurableEqualTo(String value) {
            addCriterion("is_durable =", value, "isDurable");
            return (Criteria) this;
        }

        public Criteria andIsDurableNotEqualTo(String value) {
            addCriterion("is_durable <>", value, "isDurable");
            return (Criteria) this;
        }

        public Criteria andIsDurableGreaterThan(String value) {
            addCriterion("is_durable >", value, "isDurable");
            return (Criteria) this;
        }

        public Criteria andIsDurableGreaterThanOrEqualTo(String value) {
            addCriterion("is_durable >=", value, "isDurable");
            return (Criteria) this;
        }

        public Criteria andIsDurableLessThan(String value) {
            addCriterion("is_durable <", value, "isDurable");
            return (Criteria) this;
        }

        public Criteria andIsDurableLessThanOrEqualTo(String value) {
            addCriterion("is_durable <=", value, "isDurable");
            return (Criteria) this;
        }

        public Criteria andIsDurableLike(String value) {
            addCriterion("is_durable like", value, "isDurable");
            return (Criteria) this;
        }

        public Criteria andIsDurableNotLike(String value) {
            addCriterion("is_durable not like", value, "isDurable");
            return (Criteria) this;
        }

        public Criteria andIsDurableIn(List<String> values) {
            addCriterion("is_durable in", values, "isDurable");
            return (Criteria) this;
        }

        public Criteria andIsDurableNotIn(List<String> values) {
            addCriterion("is_durable not in", values, "isDurable");
            return (Criteria) this;
        }

        public Criteria andIsDurableBetween(String value1, String value2) {
            addCriterion("is_durable between", value1, value2, "isDurable");
            return (Criteria) this;
        }

        public Criteria andIsDurableNotBetween(String value1, String value2) {
            addCriterion("is_durable not between", value1, value2, "isDurable");
            return (Criteria) this;
        }

        public Criteria andIsNonconcurrentIsNull() {
            addCriterion("is_nonconcurrent is null");
            return (Criteria) this;
        }

        public Criteria andIsNonconcurrentIsNotNull() {
            addCriterion("is_nonconcurrent is not null");
            return (Criteria) this;
        }

        public Criteria andIsNonconcurrentEqualTo(String value) {
            addCriterion("is_nonconcurrent =", value, "isNonconcurrent");
            return (Criteria) this;
        }

        public Criteria andIsNonconcurrentNotEqualTo(String value) {
            addCriterion("is_nonconcurrent <>", value, "isNonconcurrent");
            return (Criteria) this;
        }

        public Criteria andIsNonconcurrentGreaterThan(String value) {
            addCriterion("is_nonconcurrent >", value, "isNonconcurrent");
            return (Criteria) this;
        }

        public Criteria andIsNonconcurrentGreaterThanOrEqualTo(String value) {
            addCriterion("is_nonconcurrent >=", value, "isNonconcurrent");
            return (Criteria) this;
        }

        public Criteria andIsNonconcurrentLessThan(String value) {
            addCriterion("is_nonconcurrent <", value, "isNonconcurrent");
            return (Criteria) this;
        }

        public Criteria andIsNonconcurrentLessThanOrEqualTo(String value) {
            addCriterion("is_nonconcurrent <=", value, "isNonconcurrent");
            return (Criteria) this;
        }

        public Criteria andIsNonconcurrentLike(String value) {
            addCriterion("is_nonconcurrent like", value, "isNonconcurrent");
            return (Criteria) this;
        }

        public Criteria andIsNonconcurrentNotLike(String value) {
            addCriterion("is_nonconcurrent not like", value, "isNonconcurrent");
            return (Criteria) this;
        }

        public Criteria andIsNonconcurrentIn(List<String> values) {
            addCriterion("is_nonconcurrent in", values, "isNonconcurrent");
            return (Criteria) this;
        }

        public Criteria andIsNonconcurrentNotIn(List<String> values) {
            addCriterion("is_nonconcurrent not in", values, "isNonconcurrent");
            return (Criteria) this;
        }

        public Criteria andIsNonconcurrentBetween(String value1, String value2) {
            addCriterion("is_nonconcurrent between", value1, value2, "isNonconcurrent");
            return (Criteria) this;
        }

        public Criteria andIsNonconcurrentNotBetween(String value1, String value2) {
            addCriterion("is_nonconcurrent not between", value1, value2, "isNonconcurrent");
            return (Criteria) this;
        }

        public Criteria andIsUpdateDataIsNull() {
            addCriterion("is_update_data is null");
            return (Criteria) this;
        }

        public Criteria andIsUpdateDataIsNotNull() {
            addCriterion("is_update_data is not null");
            return (Criteria) this;
        }

        public Criteria andIsUpdateDataEqualTo(String value) {
            addCriterion("is_update_data =", value, "isUpdateData");
            return (Criteria) this;
        }

        public Criteria andIsUpdateDataNotEqualTo(String value) {
            addCriterion("is_update_data <>", value, "isUpdateData");
            return (Criteria) this;
        }

        public Criteria andIsUpdateDataGreaterThan(String value) {
            addCriterion("is_update_data >", value, "isUpdateData");
            return (Criteria) this;
        }

        public Criteria andIsUpdateDataGreaterThanOrEqualTo(String value) {
            addCriterion("is_update_data >=", value, "isUpdateData");
            return (Criteria) this;
        }

        public Criteria andIsUpdateDataLessThan(String value) {
            addCriterion("is_update_data <", value, "isUpdateData");
            return (Criteria) this;
        }

        public Criteria andIsUpdateDataLessThanOrEqualTo(String value) {
            addCriterion("is_update_data <=", value, "isUpdateData");
            return (Criteria) this;
        }

        public Criteria andIsUpdateDataLike(String value) {
            addCriterion("is_update_data like", value, "isUpdateData");
            return (Criteria) this;
        }

        public Criteria andIsUpdateDataNotLike(String value) {
            addCriterion("is_update_data not like", value, "isUpdateData");
            return (Criteria) this;
        }

        public Criteria andIsUpdateDataIn(List<String> values) {
            addCriterion("is_update_data in", values, "isUpdateData");
            return (Criteria) this;
        }

        public Criteria andIsUpdateDataNotIn(List<String> values) {
            addCriterion("is_update_data not in", values, "isUpdateData");
            return (Criteria) this;
        }

        public Criteria andIsUpdateDataBetween(String value1, String value2) {
            addCriterion("is_update_data between", value1, value2, "isUpdateData");
            return (Criteria) this;
        }

        public Criteria andIsUpdateDataNotBetween(String value1, String value2) {
            addCriterion("is_update_data not between", value1, value2, "isUpdateData");
            return (Criteria) this;
        }

        public Criteria andRequestsRecoveryIsNull() {
            addCriterion("requests_recovery is null");
            return (Criteria) this;
        }

        public Criteria andRequestsRecoveryIsNotNull() {
            addCriterion("requests_recovery is not null");
            return (Criteria) this;
        }

        public Criteria andRequestsRecoveryEqualTo(String value) {
            addCriterion("requests_recovery =", value, "requestsRecovery");
            return (Criteria) this;
        }

        public Criteria andRequestsRecoveryNotEqualTo(String value) {
            addCriterion("requests_recovery <>", value, "requestsRecovery");
            return (Criteria) this;
        }

        public Criteria andRequestsRecoveryGreaterThan(String value) {
            addCriterion("requests_recovery >", value, "requestsRecovery");
            return (Criteria) this;
        }

        public Criteria andRequestsRecoveryGreaterThanOrEqualTo(String value) {
            addCriterion("requests_recovery >=", value, "requestsRecovery");
            return (Criteria) this;
        }

        public Criteria andRequestsRecoveryLessThan(String value) {
            addCriterion("requests_recovery <", value, "requestsRecovery");
            return (Criteria) this;
        }

        public Criteria andRequestsRecoveryLessThanOrEqualTo(String value) {
            addCriterion("requests_recovery <=", value, "requestsRecovery");
            return (Criteria) this;
        }

        public Criteria andRequestsRecoveryLike(String value) {
            addCriterion("requests_recovery like", value, "requestsRecovery");
            return (Criteria) this;
        }

        public Criteria andRequestsRecoveryNotLike(String value) {
            addCriterion("requests_recovery not like", value, "requestsRecovery");
            return (Criteria) this;
        }

        public Criteria andRequestsRecoveryIn(List<String> values) {
            addCriterion("requests_recovery in", values, "requestsRecovery");
            return (Criteria) this;
        }

        public Criteria andRequestsRecoveryNotIn(List<String> values) {
            addCriterion("requests_recovery not in", values, "requestsRecovery");
            return (Criteria) this;
        }

        public Criteria andRequestsRecoveryBetween(String value1, String value2) {
            addCriterion("requests_recovery between", value1, value2, "requestsRecovery");
            return (Criteria) this;
        }

        public Criteria andRequestsRecoveryNotBetween(String value1, String value2) {
            addCriterion("requests_recovery not between", value1, value2, "requestsRecovery");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
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