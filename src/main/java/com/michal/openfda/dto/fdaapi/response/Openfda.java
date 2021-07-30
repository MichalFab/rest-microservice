package com.michal.openfda.dto.fdaapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Openfda {
    @JsonProperty("application_number")
    private List<String> applicationNumber = null;
    @JsonProperty("brand_name")
    private List<String> brandName = null;
    @JsonProperty("generic_name")
    private List<String> genericName = null;
    @JsonProperty("manufacturer_name")
    private List<String> manufacturerName = null;
    @JsonProperty("product_ndc")
    private List<String> productNdc = null;
    @JsonProperty("product_type")
    private List<String> productType = null;
    private List<String> route = null;
    @JsonProperty("substance_name")
    private List<String> substanceName = null;
    private List<String> rxcui = null;
    @JsonProperty("spl_id")
    private List<String> splId = null;
    @JsonProperty("spl_set_id")
    private List<String> splSetId = null;
    @JsonProperty("package_ndc")
    private List<String> packageNdc = null;
    private List<String> nui = null;
    @JsonProperty("pharm_class_epc")
    private List<String> pharmClassEpc = null;
    @JsonProperty("pharm_class_moa")
    private List<String> pharmClassMoa = null;
    @JsonProperty("pharm_class_cs")
    private List<String> pharmClassCs = null;
    private List<String> unii = null;
}
