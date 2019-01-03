package com.example.rest.service.impl;

import com.example.commonmodel.dto.CatalogDTO;
import com.example.commonmodel.dto.DataTableRequestDTO;
import com.example.commonmodel.dto.DataTableResponseDTO;
import com.example.commonmodel.dto.ResponseDTO;
import com.example.commonmodel.service.MessageHelperServiceImpl;
import com.example.dao.databinder.CatalogDataBinder;
import com.example.dao.entity.Catalog;
import com.example.dao.repoService.CatalogRepoService;
import com.example.rest.service.CatalogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private CatalogRepoService catalogRepoService;
    private MessageHelperServiceImpl messageSource;

    @Override
    public ResponseDTO<CatalogDTO> createCatalog(CatalogDTO requestDTO) {
        ResponseDTO<CatalogDTO> responseDTO = new ResponseDTO<CatalogDTO>(Boolean.FALSE, messageSource.getMessage("unable.to.save.record"), requestDTO);
        //TODO:- Validate Catalog eg check for duplicate catalog etc
        Catalog catalog = CatalogDataBinder.bind(requestDTO);
        try {
            catalog = catalogRepoService.save(catalog);
            requestDTO.setId(catalog.getId());
            responseDTO.setStatus(Boolean.TRUE);
            responseDTO.setMessage(messageSource.getMessage("record.successfully.saved"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDTO;
    }

    public DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> searchCatalog(DataTableRequestDTO<CatalogDTO> requestDTO) {
        return catalogRepoService.searchCatalog(requestDTO);
    }
}
