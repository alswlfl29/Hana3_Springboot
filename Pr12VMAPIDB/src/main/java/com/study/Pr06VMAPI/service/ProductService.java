package com.study.Pr06VMAPI.service;

import com.study.Pr06VMAPI.entity.ProductEntity;
import com.study.Pr06VMAPI.entity.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductEntity> getProducts (){
        return productRepository.findAll();
    }

    public ProductEntity getProduct(Long id){
        return productRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public void saveProduct(ProductEntity productEntity){
        productEntity.setLimitDate();
        productRepository.save(productEntity);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
