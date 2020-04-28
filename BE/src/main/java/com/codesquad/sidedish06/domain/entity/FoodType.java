package com.codesquad.sidedish06.domain.entity;

import com.codesquad.sidedish06.domain.dto.ResponseOverviewData;
import lombok.Getter;

import java.util.List;

@Getter
public class FoodType {

    private Integer menuIndex;

    private String type;

    private String subTitle;

    private String mainTitle;

    private List<ResponseOverviewData> babchans;
}
