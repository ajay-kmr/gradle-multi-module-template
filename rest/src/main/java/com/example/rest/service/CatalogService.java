package com.example.rest.service;

import com.example.commonmodel.dto.CatalogDTO;
import com.example.commonmodel.dto.DataTableRequestDTO;
import com.example.commonmodel.dto.DataTableResponseDTO;
import com.example.commonmodel.dto.ResponseDTO;

import java.util.List;

public interface CatalogService {

    ResponseDTO<CatalogDTO> createCatalog(CatalogDTO requestDTO);

    DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> searchCatalog(DataTableRequestDTO<CatalogDTO> requestDTO);
}
