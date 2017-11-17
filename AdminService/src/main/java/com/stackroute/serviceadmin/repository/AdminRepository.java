package com.stackroute.serviceadmin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.stackroute.serviceadmin.domain.AdminBean;

public interface AdminRepository extends MongoRepository<AdminBean, String>{

}
