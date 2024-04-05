package com.study.Pr06VMAPI.controller;

import com.study.Pr06VMAPI.dto.CUDResDto;
import com.study.Pr06VMAPI.dto.ProductSaveDto;
import com.study.Pr06VMAPI.dto.ReadDataResDto;
import com.study.Pr06VMAPI.dto.ReadListResDto;
import com.study.Pr06VMAPI.entity.ProductEntity;
import com.study.Pr06VMAPI.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiController {
    private final ProductService productService;

    @GetMapping("/products")
    public ReadListResDto<ProductEntity> products(){
        List<ProductEntity> products;
        try {
            products = productService.getProducts();
        }catch (Exception e){
            e.printStackTrace();
            return new ReadListResDto<>(null,"fail","전체 상품 조회를 실패하였습니다.");
        }
        return new ReadListResDto<>(products,"success","전체 상품 조회 성공!");
    }

    @PostMapping("/addProduct")
    public CUDResDto addProduct(@RequestBody ProductSaveDto productSaveDto){
        System.out.println("product>>"+productSaveDto.getName());
        try {
            ProductEntity product = productSaveDto.toSaveEntity();
            productService.saveProduct(product);
        }catch (Exception e){
            e.printStackTrace();
            return new CUDResDto("fail", "상품 등록을 실패하였습니다.");
        }
        return new CUDResDto("success", "상품을 등록하였습니다.");
    }

    @GetMapping("/product")
    public ReadDataResDto<ProductEntity> product(@RequestParam Long id){
            ProductEntity product;
            try {
                product = productService.getProduct(id);
            }catch (NullPointerException e){
                e.printStackTrace();
                return new ReadDataResDto<>(null,"fail","상품 조회를 실패하였습니다.");
            }
            return new ReadDataResDto<>(product,"success","상품 조회 성공!");
    }

    @PutMapping("/updateProduct")
    public CUDResDto updateProduct(@RequestBody ProductSaveDto productSaveDto){
        try{
            ProductEntity product = productSaveDto.toUpdateEntity();
            productService.saveProduct(product);
        }catch (Exception e){
            e.printStackTrace();
            return new CUDResDto("fail","상품 수정을 실패하였습니다.");
        }
        return new CUDResDto("success","상품 정보가 수정되었습니다.");
    }

    @GetMapping("/deleteProduct")
    public CUDResDto deleteProduct(@RequestParam Long id){
        try{
            ProductEntity product = productService.getProduct(id);
            productService.deleteProduct(product.getId());
        }catch (NullPointerException e){
            e.printStackTrace();
            return new CUDResDto("fail","상품 조회를 실패하였습니다.");
        }catch (Exception e){
            e.printStackTrace();
            return new CUDResDto("fail","상품 삭제를 실패하였습니다.");
        }
        return new CUDResDto("success","상품이 삭제되었습니다.");
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
