package com.study.Pr03VM.service;

import com.study.Pr03VM.entity.ProductEntity;
import com.study.Pr03VM.entity.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductEntity> getProducts(){
        return productRepository.findAll();
    }

    public ProductEntity getProduct(Long id){
        return productRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public void saveProduct(ProductEntity productEntity){
        productEntity.setCurrentDate();
        productRepository.save(productEntity);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}
