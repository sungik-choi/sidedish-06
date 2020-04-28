package com.codesquad.sidedish06.domain.dto;

import com.codesquad.sidedish06.domain.entity.Badge;
import com.codesquad.sidedish06.domain.entity.Delivery;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestOverview {

    @JsonProperty
    private String detail_hash;

    @JsonProperty
    private String image;

    @JsonProperty
    private String alt;

    private List<Delivery> delivery_type;

    @JsonProperty
    private String title;

    @JsonProperty
    private String description;

    @JsonProperty
    private String n_price;

    @JsonProperty
    private String s_price;

    private List<Badge> badge;
}
