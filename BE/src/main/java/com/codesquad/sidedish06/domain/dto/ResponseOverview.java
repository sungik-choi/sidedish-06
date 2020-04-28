package com.codesquad.sidedish06.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseOverview {

    private int menuIndex;

    private String menuType;

    private String menuTypeTitle;

    private List<ResponseOverviewData> data;
}
