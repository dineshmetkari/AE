package com.stackroute.assessmentengine.userresultservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.assessmentengine.userresultservice.domain.UserResultBean;

public interface Resultmongorepository extends MongoRepository<UserResultBean, String> {

}
