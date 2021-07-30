package com.michal.openfda.dto.application;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CreateApplicationRequest {

    @NotNull
    private String applicationNumber;
    @NotNull
    private String manufacturerName;
    @NotNull
    private String substanceName;
    @NotEmpty
    private List<String> productNumbers;
}
