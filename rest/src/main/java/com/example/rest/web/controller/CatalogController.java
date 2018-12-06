package com.example.rest.web.controller;


import com.example.commonmodel.dto.CatalogDTO;
import com.example.commonmodel.dto.DataTableRequestDTO;
import com.example.commonmodel.dto.DataTableResponseDTO;
import com.example.commonmodel.dto.ResponseDTO;
import com.example.rest.service.CatalogService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/catalog")
@CommonsLog
public class CatalogController extends BaseController {

    @Autowired
    CatalogService catalogService;

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
