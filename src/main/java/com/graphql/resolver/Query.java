package com.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphql.entity.Application;
import com.graphql.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class Query implements GraphQLQueryResolver {
    private ApplicationRepository applicationRepository;

    public Iterable<Application> findAllApplications() {
        return applicationRepository.findAll();
    }

    public long countApplications() {
        return applicationRepository.count();
    }
    public Iterable<Application> findById(Long id) {
        if(id==null)
            return applicationRepository.findAll();
        else
            return Arrays.asList(applicationRepository.findById(id).get());
    }
}
