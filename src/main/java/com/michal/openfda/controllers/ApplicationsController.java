package com.michal.openfda.controllers;

import com.michal.openfda.dto.application.CreateApplicationRequest;
import com.michal.openfda.entity.application.Application;
import com.michal.openfda.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/applications")
public class ApplicationsController {

    private final ApplicationService applicationService;

    public ApplicationsController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Application> createApplication(@Valid @RequestBody CreateApplicationRequest createApplicationRequest) {
        Application application = applicationService.create(createApplicationRequest);
        return ResponseEntity.ok(application);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Application> getByApplicationId(@PathVariable("id") Long id) {
        Optional<Application> application = applicationService.findById(id);
        return application.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Application>> getByApplicationNumber(@RequestParam("application_number") String applicationNumber) {
        List<Application> applications = applicationService.findByApplicationNumber(applicationNumber);
        if (!applications.isEmpty()) {
            return new ResponseEntity<>(applications, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> applications = applicationService.findAll();
        if (!applications.isEmpty()) {
            return new ResponseEntity<>(applications, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
