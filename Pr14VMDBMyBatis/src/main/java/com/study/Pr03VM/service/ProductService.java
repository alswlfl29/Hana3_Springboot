package com.study.Pr03VM.service;

import com.study.Pr03VM.dao.IProductDao;
import com.study.Pr03VM.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final IProductDao productDao;

    public List<ProductDto> getProducts(){
        return productDao.list();
    }

    public ProductDto getProduct(Long id){
        return productDao.findById(id).orElseThrow(NullPointerException::new);
    }

    public int countProducts(){
        return productDao.count();
    }

    public int insertProduct(ProductDto productDto){
        if(productDto.getLimitDate() == null){
            productDto.setLimitDate(LocalDate.now());
        }
        return productDao.insert(productDto);
    }

    public int updateProduct(ProductDto productDto){
        return productDao.update(productDto);
    }

    public int deleteProduct(Long id){
        return productDao.delete(id);
    }

}
