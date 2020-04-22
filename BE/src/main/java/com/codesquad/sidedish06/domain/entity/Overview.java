package com.codesquad.sidedish06.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Overview {

    @JsonProperty
    private String detail_hash;

    @JsonProperty
    private String image;

    @JsonProperty
    private String alt;

    @JsonProperty
    private List<Delivery> delivery_type;

    @JsonProperty
    private String title;

    @JsonProperty
    private String description;

    @JsonProperty
    private String n_price;

    @JsonProperty
    private String s_price;

    @JsonProperty
    private List<Badge> badge;
}
