package com.study.Pr03VM.dao;

import com.study.Pr03VM.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface IProductDao {
    public List<ProductDto> list();

    public Optional<ProductDto> findById(Long id);

    public int count();

    public int insert(ProductDto productDto);

    public int update(ProductDto productDto);

    public int delete(Long id);
}
