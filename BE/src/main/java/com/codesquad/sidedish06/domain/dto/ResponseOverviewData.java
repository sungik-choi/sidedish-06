package com.codesquad.sidedish06.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseOverviewData {

    private String hash;

    private String image;

    private String alt;

    private List<String> delivery_type;

    private String title;

    private String description;

    private String originPrice;

    private String salePrice;

    private List<String> badge;

}
