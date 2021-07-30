package com.michal.openfda.entity.application;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String applicationNumber;
    private String manufacturerName;
    private String substanceName;
    @ElementCollection
    private List<String> productNumbers;
}
