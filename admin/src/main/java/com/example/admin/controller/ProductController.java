package com.example.admin.controller;


import com.example.admin.service.ProductService;
import com.example.commonmodel.dto.DataTableRequestDTO;
import com.example.commonmodel.dto.DataTableResponseDTO;
import com.example.commonmodel.dto.ProductDTO;
import com.example.commonmodel.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CommonsLog
@RestController
@AllArgsConstructor
@RequestMapping("v1/product")
public class ProductController {

    private ProductService productService;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseDTO<ProductDTO> createProduct(@RequestBody ProductDTO requestDTO) {
        return productService.createProduct(requestDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseDTO<ProductDTO> removeProduct(@RequestBody ProductDTO requestDTO) {
        return productService.removeProduct(requestDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public DataTableResponseDTO<ProductDTO, List<ProductDTO>> searchProduct(@RequestBody DataTableRequestDTO<ProductDTO> requestDTO) {
        return productService.searchProduct(requestDTO);
    }
}
