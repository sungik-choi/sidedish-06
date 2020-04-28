package com.codesquad.sidedish06.domain.entity;

import com.codesquad.sidedish06.domain.dto.ResponseOverviewData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodType {

    private Integer menuIndex;

    private String type;

    private String subTitle;

    private String mainTitle;

    private List<ResponseOverviewData> babchans;
}
