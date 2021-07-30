package com.michal.openfda.controllers;

import com.michal.openfda.entity.application.Application;
import com.michal.openfda.service.ApplicationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ApplicationsController.class)
class ApplicationsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ApplicationService applicationService;

    @Test
    @DisplayName("It should return all applications from the database")
    void shouldReturnAllApplications() throws Exception {
//      given
        Application application = new Application();
        application.setApplicationNumber("Test");
        application.setId(1L);
        List<Application> applications = new ArrayList<>();
        applications.add(application);
//        when
        when(applicationService.findAll()).thenReturn(applications);
//        then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/applications/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[:1].id").exists());
    }

    @Test
    @DisplayName("It should return response with 404 status code when there is no applications")
    void shouldReturnNotFound() throws Exception {
//        when
        when(applicationService.findAll()).thenReturn(emptyList());
//        then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/applications/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}