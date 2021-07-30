package com.michal.openfda.dto.fdaapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Result {
    @JsonProperty("application_number")
    private String applicationNumber;
    @JsonProperty("sponsor_name")
    private String sponsorName;
    private Openfda openfda;
    private List<Product> products;
    private List<Submission> submissions;

}