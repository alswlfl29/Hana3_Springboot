package com.study.Pr07LoginJoin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class MainService {
    private  final MainRepository mainRepository;

    public void create(ReqDto reqDto){
        Member member = new Member();
        member.setUsername(reqDto.getInputName());
        member.setEmail(reqDto.getInputEmail());
        member.setPassword(reqDto.getInputPw());
        member.setJoinDate(LocalDate.now());
        this.mainRepository.save(member);
    }
}
