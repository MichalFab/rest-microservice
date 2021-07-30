package com.michal.openfda.dto.fdaapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Product {
    @JsonProperty("product_number")
    private String productNumber;
    @JsonProperty("reference_drug")
    private String referenceDrug;
    @JsonProperty("brand_name")
    private String brandName;
    @JsonProperty("active_ingredients")
    private List<ActiveIngredient> activeIngredients;
    @JsonProperty("reference_standard")
    private String referenceStandard;
    @JsonProperty("dosage_form")
    private String dosageForm;
    private String route;
    @JsonProperty("marketing_status")
    private String marketingStatus;
}
