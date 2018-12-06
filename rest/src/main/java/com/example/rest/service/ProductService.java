package com.example.rest.service;

import com.example.commonmodel.dto.DataTableRequestDTO;
import com.example.commonmodel.dto.DataTableResponseDTO;
import com.example.commonmodel.dto.ProductDTO;
import com.example.commonmodel.dto.ResponseDTO;

import java.util.List;

public interface ProductService {

    ResponseDTO<ProductDTO> createProduct(ProductDTO requestDTO);

    ResponseDTO<ProductDTO> removeProduct(ProductDTO requestDTO);

    DataTableResponseDTO<ProductDTO, List<ProductDTO>> searchProduct(DataTableRequestDTO<ProductDTO> requestDTO);
}
