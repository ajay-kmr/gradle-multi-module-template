package com.example.rest.service.impl;

import com.example.commonmodel.dto.DataTableRequestDTO;
import com.example.commonmodel.dto.DataTableResponseDTO;
import com.example.commonmodel.dto.ProductDTO;
import com.example.commonmodel.dto.ResponseDTO;
import com.example.commonmodel.service.MessageHelperServiceImpl;
import com.example.dao.databinder.ProductDataBinder;
import com.example.dao.entity.Catalog;
import com.example.dao.entity.Product;
import com.example.dao.repoService.CatalogRepoService;
import com.example.dao.repoService.ProductRepoService;
import com.example.rest.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepoService productRepoService;

    private CatalogRepoService catalogRepoService;

    private MessageHelperServiceImpl messageSource;

    @Override
    public ResponseDTO<ProductDTO> createProduct(ProductDTO requestDTO) {
        ResponseDTO<ProductDTO> responseDTO = new ResponseDTO<ProductDTO>(Boolean.FALSE, messageSource.getMessage("unable.to.save.record"), requestDTO);
        //TODO:- Validate Product eg check for duplicate product etc
        Optional<Catalog> catalog = catalogRepoService.findById(requestDTO.getCatalogId());
        if (!catalog.isPresent()) {
            responseDTO.setMessage(messageSource.getMessage("invalid.catalog.id"));
        } else {
            Product product = ProductDataBinder.bind(requestDTO, catalog.get());
            try {
                product = productRepoService.save(product);
                requestDTO.setId(product.getId());
                responseDTO.setMessage(messageSource.getMessage("record.successfully.saved"));
                responseDTO.setStatus(Boolean.TRUE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO<ProductDTO> removeProduct(ProductDTO requestDTO) {
        ResponseDTO<ProductDTO> responseDTO = new ResponseDTO<ProductDTO>(Boolean.FALSE, messageSource.getMessage("unable.to.remove.catalog.product"), requestDTO);
        //TODO:- Validate Product eg check for duplicate product etc
        Optional<Product> productOptional = productRepoService.findById(requestDTO.getId());
        if (!productOptional.isPresent()) {
            responseDTO.setMessage(messageSource.getMessage("invalid.product.id"));
        } else {
            try {
                Product product = productOptional.get();
                product.setDeleted(Boolean.TRUE);
                product = productRepoService.save(product);
                responseDTO.setMessage(messageSource.getMessage("record.deleted.successfully"));
                responseDTO.setStatus(Boolean.TRUE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return responseDTO;
    }

    public DataTableResponseDTO<ProductDTO, List<ProductDTO>> searchProduct(DataTableRequestDTO<ProductDTO> requestDTO) {
        return productRepoService.searchProduct(requestDTO);
    }
}
