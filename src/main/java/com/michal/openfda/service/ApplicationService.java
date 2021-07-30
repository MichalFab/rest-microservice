package com.michal.openfda.service;

import com.michal.openfda.dto.application.CreateApplicationRequest;
import com.michal.openfda.entity.application.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {

    Application create(CreateApplicationRequest createApplicationRequest);

    List<Application> findAll();

    Optional<Application> findById(Long id);

    List<Application> findByApplicationNumber(String name);
}
