package com.study.ProjectCompanyWeb.customer.dto;

import com.study.ProjectCompanyWeb.customer.domain.One2One;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class One2OneRequestDto {
    private String name;
    private String tel;
    private String email;
    private String address1;
    private String address2;
    private String title;
    private String desc;

    public One2One toEntity(){
        return One2One.builder()
                .one2oneName(name)
                .one2onePhone(tel)
                .one2oneEmail(email)
                .one2oneAddress(address1+" "+address2)
                .one2oneTitle(title)
                .one2oneContent(desc)
                .build();
    }
}
