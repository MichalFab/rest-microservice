package com.michal.openfda.controllers;

import com.michal.openfda.dto.fdaapi.response.Result;
import com.michal.openfda.service.fda.FdaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.SneakyThrows;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fda")
public class FdaController {

    private final FdaService fdaService;

    public FdaController(FdaService fdaService) {
        this.fdaService = fdaService;
    }

    @SneakyThrows
    @GetMapping(value = "/applications", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Search for drug record applications submitted to FDA")
    @PageableAsQueryParam
    public Page<Result> findApplications(@RequestParam(name = "manufacturer_name") String manufacturerName,
                                         @RequestParam(name = "brand_name", required = false) String brandName,
                                         Pageable pageable) {

        return fdaService.findApplications(manufacturerName, brandName, pageable);
    }
}
