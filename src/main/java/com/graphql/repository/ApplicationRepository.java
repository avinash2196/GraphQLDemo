package com.graphql.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.graphql.entity.Application;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends CosmosRepository<Application, Long> {

}
