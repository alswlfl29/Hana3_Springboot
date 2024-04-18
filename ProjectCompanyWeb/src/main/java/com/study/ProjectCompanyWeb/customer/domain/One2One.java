package com.study.ProjectCompanyWeb.customer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_one2one")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class One2One {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer one2oneIdx;
    @Column(nullable = false)
    private String one2oneName;
    @Column(nullable = false)
    private String one2onePhone;
    @Column(nullable = false)
    private String one2oneEmail;
    @Column(nullable = false)
    private String one2oneAddress;
    @Column(nullable = false)
    private String one2oneTitle;
    @Column(nullable = false)
    private String one2oneContent;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate one2oneDate = LocalDate.now();

    @Builder
    public One2One(String one2oneName, String one2onePhone, String one2oneEmail, String one2oneAddress, String one2oneTitle, String one2oneContent) {
        this.one2oneName = one2oneName;
        this.one2onePhone = one2onePhone;
        this.one2oneEmail = one2oneEmail;
        this.one2oneAddress = one2oneAddress;
        this.one2oneTitle = one2oneTitle;
        this.one2oneContent = one2oneContent;
    }
}
