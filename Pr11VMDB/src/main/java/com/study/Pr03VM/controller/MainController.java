package com.study.Pr03VM.controller;

import com.study.Pr03VM.dto.ProductSaveDto;
import com.study.Pr03VM.entity.ProductEntity;
import com.study.Pr03VM.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<ProductEntity> products = productService.getProducts();

        model.addAttribute("products",products);
        model.addAttribute("productCnt",products.size());
        return "index";
    }

    @GetMapping("/addForm")
    public String addForm(){
        return "addItem";
    }

    @PostMapping("/addProduct")
    @ResponseBody
    public String addProduct(@ModelAttribute ProductSaveDto productSaveDto){
        try{
            ProductEntity productEntity = productSaveDto.toSaveEntity();
            productService.saveProduct(productEntity);
        }catch (Exception e){
            e.printStackTrace();
            return "<script>alert('상품 추가를 실패하였습니다.');history.back();</script>";
        }
        return "<script>alert('상품이 추가되었습니다.');location.href='/list';</script>";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam Long id, Model model){
        try{
            ProductEntity product = productService.getProduct(id);
            model.addAttribute("id",product.getId());
            model.addAttribute("product",product);
        }catch (NullPointerException e){
            return "redirect:/list";
        }
        return "updateItem";
    }

    @PostMapping("/updateAction")
    @ResponseBody
    public String updateAction(@ModelAttribute ProductSaveDto productSaveDto){
        System.out.println(productSaveDto.getName());
        try{
            ProductEntity productEntity = productSaveDto.toUpdateEntity();
            productService.saveProduct(productEntity);
        }catch (NullPointerException e){
            return "<script>alert('상품 정보 수정을 실패하였습니다.');history.back();</script>";
        }
        return "<script>alert('상품 정보를 수정하였습니다.');location.href='/';</script>";
    }

    @GetMapping("/deleteProduct")
    @ResponseBody
    public String deleteProduct(@RequestParam Long id){
        try{
            ProductEntity product = productService.getProduct(id);
            productService.deleteProduct(product.getId());
        } catch (NullPointerException e){
            return "<script>alert('상품 정보가 존재하지 않습니다.');history.back();</script>";
        } catch (Exception e){
            e.printStackTrace();
            return "<script>alert('상품이 삭제를 실패하였습니다.');history.back();</script>";
        }
        return "<script>alert('상품이 삭제되었습니다.');location.href='/list';</script>";
    }
}
