package com.codesquad.sidedish06.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDetail {

    private String hash;

    private String title;

    private String top_image;

    private List<String> thumb_images;

    private String description;

    private String point;

    private String delivery_info;

    private String delivery_fee;

    private String originPrice;

    private String salePrice;

    private List<String> detail_section;
}
