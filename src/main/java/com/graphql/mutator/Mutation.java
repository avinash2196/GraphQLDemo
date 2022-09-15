package com.graphql.mutator;


import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphql.entity.Application;
import com.graphql.exception.ApplicationNotFoundException;
import com.graphql.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class Mutation implements GraphQLMutationResolver {
    private ApplicationRepository applicationRepository;

    public Application newApplication(String name, String owner, String description) {
        Application application = new Application(name, owner, description);
        applicationRepository.save(application);
        return application;
    }

    public boolean deleteApplication(Long id) {
        applicationRepository.deleteById(id);
        return true;
    }

    public Application updateApplicationOwner(String newOwner, Long id) {
        Optional<Application> optionalApplication = applicationRepository.findById(id);
        if (optionalApplication.isPresent()) {
            Application application = optionalApplication.get();
            application.setOwner(newOwner);
            applicationRepository.save(application);
            return application;
        } else {
            throw new ApplicationNotFoundException("App id not Found", id);
        }
    }
}
