package com.codesquad.sidedish06.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseOverview {

    private String menuType;

    private String menuTypeTitle;

    private ResponseOverviewData data;
}
