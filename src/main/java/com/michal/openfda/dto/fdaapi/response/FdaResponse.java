package com.michal.openfda.dto.fdaapi.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FdaResponse {
    private Meta meta;
    private List<Result> results;
}

