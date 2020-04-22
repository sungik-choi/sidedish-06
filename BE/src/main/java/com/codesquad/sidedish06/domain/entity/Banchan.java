package com.codesquad.sidedish06.domain.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Banchan {

    private String hash;

    private String food_type;

    private String image;

    private String alt;

    private List<Delivery> delivery_type;

    private String title;

    private String description;

    private String n_price;

    private String s_price;

    private List<Badge> badge;

    private String top_image;

    private List<ThumbImage> thumb_image;

    private String point;

    private String delivery_info;

    private String delivery_fee;

    private List<DetailSection> detail_section;

}
