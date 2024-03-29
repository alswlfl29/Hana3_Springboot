package com.study.Pr02Calc;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiController {
    final Calc calc;

    @PostMapping("/calc")
    public ResDto calc(@RequestBody ReqDto reqDto){
        double result = calc.calculation(reqDto);
        ResDto resDto = new ResDto();
        resDto.setResult(result);

        return resDto;
    }
}
