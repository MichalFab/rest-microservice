package com.michal.openfda.service;

import com.michal.openfda.dto.application.CreateApplicationRequest;
import com.michal.openfda.entity.application.Application;
import com.michal.openfda.repository.ApplicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Application create(CreateApplicationRequest createApplicationRequest) {
        Application application = modelMapper.map(createApplicationRequest, Application.class);
        return applicationRepository.save(application);
    }

    @Override
    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Override
    public Optional<Application> findById(Long id) {
        return applicationRepository.findById(id);
    }

    @Override
    public List<Application> findByApplicationNumber(String name) {
        return applicationRepository.findByApplicationNumber(name);
    }
}
