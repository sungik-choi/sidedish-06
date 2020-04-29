package com.codesquad.sidedish06.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseOverview {

    private int menuIndex;

    private String menuType;

    private String menuTypeTitle;

    private List<ResponseOverviewData> data;
}
