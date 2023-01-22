package com.example.demo2.mapper;

import com.example.demo2.dto.ProductCreateDto;
import com.example.demo2.dto.ProductResponseDto;
import com.example.demo2.dto.ProductUpdateDto;
import com.example.demo2.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    Product productCreateDtoToProduct(ProductCreateDto productCreateDto);

    ProductResponseDto productToProductResponseDto(Product product);

    void ProductUpdateDtoToExistProduct(ProductUpdateDto productUpdateDto, @MappingTarget Product product);
}
