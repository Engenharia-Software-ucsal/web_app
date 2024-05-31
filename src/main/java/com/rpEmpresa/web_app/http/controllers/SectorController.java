package com.rpEmpresa.web_app.http.controllers;


import com.rpEmpresa.web_app.services.dto.CreateSectorDto;
import com.rpEmpresa.web_app.services.SectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sector")
public class SectorController {

    private  final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }


    @PostMapping
    public @ResponseBody ResponseEntity<String> createSector(@RequestBody @Validated CreateSectorDto dto) {
        try {
            this.sectorService.createSector(dto);

            return ResponseEntity.ok().body("sector created");

        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
