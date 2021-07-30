package com.michal.openfda.controllers;

import com.michal.openfda.dto.fdaapi.response.Result;
import com.michal.openfda.service.fda.FdaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FdaController.class)
class FdaControllerTest {

    @MockBean
    private FdaService fdaService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should return any applications found with the FDA API")
    public void shouldReturnFoundApplications() throws Exception {
//        given
        Result result = new Result();
        result.setApplicationNumber("Test1234");
        List<Result> results = new ArrayList<>();
        results.add(result);
//        when
        when(fdaService.findApplications(anyString(), anyString(), any())).thenReturn(new PageImpl<>(results, Pageable.unpaged(), 0));
//        then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/fda/applications?manufacturer_name=d&brand_name=d&page=0&size=20&sort=string")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content..application_number").exists());
    }
}