package com.michal.openfda.dto.fdaapi.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FdaResponse {
    private Meta meta;
    private List<Result> results;
}

