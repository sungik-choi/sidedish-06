package com.codesquad.sidedish06.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseOverviewData {

    private String hash;

    private String image;

    private String alt;

    private List<String> delivery_type;

    private String title;

    private String description;

    private String originPrice;

    private String salePrice;

    private List<ResponseBadge> badge;

}
