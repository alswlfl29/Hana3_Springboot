package com.study.Pr06VMAPI;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private static List<Product> productList = new ArrayList<>();
    @GetMapping("/products")
    public List<Product> products(){
        return productList;
    }
    @PostMapping("/add")
    public ResDto productAdd(@RequestBody ReqDto reqDto){
        Product product = new Product();
        product.setName(reqDto.getName());
        product.setPrice(reqDto.getPrice());
        product.setLimit_date(reqDto.getLimit_date());
        productList.add(product);

        ResDto resDto = new ResDto();
        resDto.setStatus("ok");
        resDto.setMessage("상품이 추가되었습니다.");
        return resDto;
    }

    @GetMapping("/update/product")
    public Product productUpdate(@RequestParam int index){
        return productList.get(index);
    }
    @PutMapping("/update")
    public ResDto updateAction(@RequestParam int index, @RequestBody ReqDto reqDto){
        Product product = productList.get(index);
        product.setName(reqDto.getName());
        product.setPrice(reqDto.getPrice());
        product.setLimit_date(reqDto.getLimit_date());
        productList.set(index, product);

        ResDto resDto = new ResDto();
        resDto.setStatus("ok");
        resDto.setMessage("상품이 수정되었습니다.");
        return resDto;
    }

    @GetMapping("/delete")
    @ResponseBody
    public ResDto delete(@RequestParam int index){
        productList.remove(index);

        ResDto resDto = new ResDto();
        resDto.setStatus("ok");
        resDto.setMessage("상품이 삭제되었습니다.");
        return resDto;
    }

    @GetMapping("/changeLocale")
    public String changeLocale(HttpSession session){
        Locale locale = (Locale) session.getAttribute(
                SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if(Locale.KOREA.equals(locale)){
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.ENGLISH);
        }else{
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.KOREA);
        }
        return session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME).toString();
    }
}
