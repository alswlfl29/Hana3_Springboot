package com.study.Pr01Counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    @Autowired
    Counter counter;
    @GetMapping("/plus")
    public int plus(){
        counter.setCount(counter.getCount()+1);
        return counter.getCount();
    }
    @GetMapping("/minus")
    public int minus(){
        counter.setCount(counter.getCount()-1);
        return counter.getCount();
    }
}
