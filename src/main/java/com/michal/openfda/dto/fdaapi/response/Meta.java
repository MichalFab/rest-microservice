package com.michal.openfda.dto.fdaapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Meta {
    private String disclaimer;
    private String terms;
    private String license;
    @JsonProperty("last_updated")
    private String lastUpdate;
    private Results results;
}