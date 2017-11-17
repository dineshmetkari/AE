package com.resultDisplay.ResultDisplay.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.resultDisplay.ResultDisplay.domain.ResultBean;

public interface ResultRepository extends MongoRepository<ResultBean, String>{

}
