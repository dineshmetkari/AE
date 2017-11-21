package com.stackroute.assessmentengine.evaluationmanager.domain;

import java.util.ArrayList;
import java.util.List;

public class UserResultBean {
    
    private String userId;
    private List<QuestionBean> resultBean;
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public List<QuestionBean> getResultBean() {
        return resultBean;
    }
    public void setResultBean(List<QuestionBean> resultBean) {
        this.resultBean = resultBean;
    }
    public UserResultBean() {
//        super();
       this.resultBean=new ArrayList<>();
    }   
}