package com.michal.openfda.dto.fdaapi.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Results {
    private int skip;
    private int limit;
    private int total;
}
