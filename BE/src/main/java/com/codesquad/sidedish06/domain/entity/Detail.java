package com.codesquad.sidedish06.domain.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Detail {

    private String top_image;

    private List<ThumbImage> thumb_images;

    private String product_description;

    private String point;

    private String delivery_info;

    private String delivery_fee;

    private List<Price> prices;

    private List<DetailSection> detail_section;
}
