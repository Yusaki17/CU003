package com.example.CU03.controller.general;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.CU03.dto.ConductorDTO;
import com.example.CU03.service.service.ConductorService;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/conductor")
public class ConductorController {
    private final ConductorService conductorService;

    public ConductorController(ConductorService conductorService) {
        this.conductorService = conductorService;
    }
    @GetMapping
    public ResponseEntity<List<ConductorDTO>> listAll() throws ServiceException {

        return ResponseEntity.ok(conductorService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ConductorDTO> read(@PathVariable Long id) throws ServiceException {
        ConductorDTO conductorDTO = conductorService.findById(id);
        return ResponseEntity.ok(conductorDTO);
    }
    @PostMapping
    public ResponseEntity<ConductorDTO> create(@RequestBody ConductorDTO conductorDTO) throws ServiceException {
        ConductorDTO conductorDTO1 = conductorService.create(conductorDTO);
        return new ResponseEntity<>(conductorDTO1,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ConductorDTO> update(@PathVariable Long id, @RequestBody ConductorDTO conductorDTO) throws ServiceException {
        ConductorDTO conductorDTO1 = conductorService.update(id,conductorDTO);
        return ResponseEntity.ok(conductorDTO1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        conductorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}