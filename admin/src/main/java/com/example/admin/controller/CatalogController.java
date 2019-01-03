package com.example.admin.controller;


import com.example.admin.service.CatalogService;
import com.example.commonmodel.dto.CatalogDTO;
import com.example.commonmodel.dto.DataTableRequestDTO;
import com.example.commonmodel.dto.DataTableResponseDTO;
import com.example.commonmodel.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CommonsLog
@RestController
@AllArgsConstructor
@RequestMapping("v1/catalog")
public class CatalogController {

    private CatalogService catalogService;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseDTO<CatalogDTO> createCatalog(@RequestBody CatalogDTO requestDTO) {
        return catalogService.createCatalog(requestDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> searchCatalog(@RequestBody DataTableRequestDTO<CatalogDTO> requestDTO) {
        return catalogService.searchCatalog(requestDTO);
    }
}
