package com.michal.openfda.service.fda;

import com.michal.openfda.dto.fdaapi.response.FdaResponse;
import com.michal.openfda.dto.fdaapi.response.Result;
import com.michal.openfda.exceptions.FdaConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

@Service
public class FdaServiceImpl implements FdaService {

    private static final Logger log = LoggerFactory.getLogger(FdaServiceImpl.class);
    private static final String MANUFACTURER_URL_PARAM = "openfda.manufacturer_name=\"%s\"";
    private static final String BRAND_URL_PARAM = "+AND+openfda.brand_name=\"%s\"";
    private final String fdaUrl;
    private final RestTemplate restTemplate;

    public FdaServiceImpl(@Value("${fda.url}") String fdaUrl, RestTemplate restTemplate) {
        this.fdaUrl = fdaUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public PageImpl<Result> findApplications(String manufacturerName, String brandName, Pageable pageable) throws FdaConnectionException {
        String url = constructFdaUrl(manufacturerName, brandName, pageable);
        try {
            FdaResponse fdaResponse = restTemplate.getForObject(url, FdaResponse.class);
            return ofNullable(fdaResponse)
                    .map(response -> new PageImpl<>(fdaResponse.getResults(), pageable, fdaResponse.getMeta().getResults().getTotal()))
                    .orElse(new PageImpl<>(new ArrayList<>(), pageable, 0));
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new FdaConnectionException();
        }
    }

    private String constructFdaUrl(String manufacturerName, String brandName, Pageable pageable) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(fdaUrl);
        if (nonNull(brandName)) {
            String searchParam = String.format(MANUFACTURER_URL_PARAM, manufacturerName) + String.format(BRAND_URL_PARAM, brandName);
            uriBuilder.queryParam("search", searchParam);
        } else {
            uriBuilder.queryParam("search", String.format(MANUFACTURER_URL_PARAM, manufacturerName));
        }
        uriBuilder.queryParam("limit", pageable.getPageSize());
        uriBuilder.queryParam("skip", pageable.getOffset());
        return uriBuilder.toUriString();
    }
}
