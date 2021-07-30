package com.michal.openfda.service;

import com.michal.openfda.dto.application.CreateApplicationRequest;
import com.michal.openfda.entity.application.Application;
import com.michal.openfda.repository.ApplicationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;
    @InjectMocks
    private ApplicationServiceImpl applicationService;
    @Captor
    ArgumentCaptor<Application> applicationCaptor;

    @Test
    @DisplayName("It should map and save the data correctly")
    void shouldMapAndSaveApplication() {
//        given
        String applicationNumber = "APP_NUM";
        String manufacturerName = "MANUFACTURER_NAME";
        String substanceName = "SUBSTANCE_NAME";
        CreateApplicationRequest createApplicationRequest = new CreateApplicationRequest();
        createApplicationRequest.setApplicationNumber(applicationNumber);
        createApplicationRequest.setManufacturerName(manufacturerName);
        createApplicationRequest.setSubstanceName(substanceName);
        createApplicationRequest.setProductNumbers(emptyList());
//        when
        applicationService.create(createApplicationRequest);
//        then
        verify(applicationRepository).save(applicationCaptor.capture());
        Application application = applicationCaptor.getValue();

        assertThat(application.getApplicationNumber()).isEqualTo(applicationNumber);
        assertThat(application.getManufacturerName()).isEqualTo(manufacturerName);
        assertThat(application.getSubstanceName()).isEqualTo(substanceName);
        assertThat(application.getProductNumbers()).isEmpty();
    }
}