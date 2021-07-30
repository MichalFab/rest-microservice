package com.michal.openfda.repository;

import com.michal.openfda.entity.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByApplicationNumber(String applicationNumber);
}
