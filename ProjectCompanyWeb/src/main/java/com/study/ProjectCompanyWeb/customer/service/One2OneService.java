package com.study.ProjectCompanyWeb.customer.service;

import com.study.ProjectCompanyWeb.customer.domain.One2One;
import com.study.ProjectCompanyWeb.customer.domain.One2OneRepository;
import com.study.ProjectCompanyWeb.customer.dto.One2OneRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class One2OneService {
    private final One2OneRepository one2OneRepository;

    @Transactional
    public Integer save(final One2OneRequestDto dto){
        One2One entity = one2OneRepository.save(dto.toEntity());
        return entity.getOne2oneIdx();
    }

    @Transactional(readOnly = true)
    public boolean existsByOne2OneIdx(Integer oneToOneIdx){
        return one2OneRepository.existsByOne2oneIdx(oneToOneIdx);
    }
}
