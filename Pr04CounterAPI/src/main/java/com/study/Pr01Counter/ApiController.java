package com.study.Pr01Counter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiController {
    public final Counter counter;
    @GetMapping("/plus")

    public ResDto plus(){
        counter.setCount(counter.getCount()+1);

        ResDto resDto = new ResDto();
        resDto.setResult(counter.getCount());
        return resDto;
    }
    @GetMapping("/minus")
    public ResDto minus(){
        counter.setCount(counter.getCount()-1);

        ResDto resDto = new ResDto();
        resDto.setResult(counter.getCount());
        return resDto;
    }
}
