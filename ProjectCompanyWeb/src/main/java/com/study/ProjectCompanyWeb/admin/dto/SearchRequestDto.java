package com.study.ProjectCompanyWeb.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
    private String search_select;
    private String search_keyword;
    private String order_select;
    private String page_select;
}
