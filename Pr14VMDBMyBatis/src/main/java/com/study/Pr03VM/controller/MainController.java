package com.study.Pr03VM.controller;

import com.study.Pr03VM.dto.ProductDto;
import com.study.Pr03VM.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;

    @GetMapping("/")
    public String index(){
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<ProductDto> products = productService.getProducts();

        model.addAttribute("products",products);
        model.addAttribute("productCnt",productService.countProducts());
        return "index";
    }

    @GetMapping("/addForm")
    public String addForm(){
        return "addItem";
    }

    @PostMapping("/addProduct")
    @ResponseBody
    public String addProduct(@ModelAttribute ProductDto productDto){
        try{
            int result = productService.insertProduct(productDto);
            if(result != 1){
                return "<script>alert('상품 추가를 실패하였습니다.');history.back();</script>";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "<script>alert('상품 추가를 실패하였습니다.');history.back();</script>";
        }
        return "<script>alert('상품이 추가되었습니다.');location.href='/list';</script>";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam Long id, Model model){
        try{
            ProductDto product = productService.getProduct(id);
            model.addAttribute("id",product.getId());
            model.addAttribute("product",product);
        }catch (NullPointerException e){
            e.printStackTrace();
            return "redirect:/list";
        }
        return "updateItem";
    }

    @PostMapping("/updateAction")
    @ResponseBody
    public String updateAction(@ModelAttribute ProductDto productDto){
        try{
            int result = productService.updateProduct(productDto);
            if(result != 1){
                return "<script>alert('상품 정보 수정을 실패하였습니다.');history.back();</script>";
            }
        }catch (Exception e){
            return "<script>alert('상품 정보 수정을 실패하였습니다.');history.back();</script>";
        }
        return "<script>alert('상품 정보를 수정하였습니다.');location.href='/';</script>";
    }

    @GetMapping("/deleteProduct")
    @ResponseBody
    public String deleteProduct(@RequestParam Long id){
        try{
            int result = productService.deleteProduct(id);
            if(result != 1){
                return "<script>alert('상품이 삭제를 실패하였습니다.');history.back();</script>";
            }
        } catch (Exception e){
            e.printStackTrace();
            return "<script>alert('상품이 삭제를 실패하였습니다.');history.back();</script>";
        }
        return "<script>alert('상품이 삭제되었습니다.');location.href='/list';</script>";
    }
}
