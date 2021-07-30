package com.michal.openfda.dto.fdaapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Submission {
    @JsonProperty("submission_type")
    private String submissionType;
    @JsonProperty("submission_number")
    private String submissionNumber;
    @JsonProperty("submission_status")
    private String submissionStatus;
    @JsonProperty("submission_status_date")
    private String submissionStatusDate;
    @JsonProperty("application_docs")
    public List<ApplicationDoc> applicationDocs;
    @JsonProperty("review_priority")
    private String reviewPriority;
    @JsonProperty("submission_class_code")
    private String submissionClassCode;
    @JsonProperty("submission_class_code_description")
    private String submissionClassCodeDescription;

}
