package com.example.CU03.controller.general;
import com.example.CU03.dto.UbicacionGPSDTO;
import com.example.CU03.service.service.UbicacionGPSService;
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
@RequestMapping("/api/v1/ubicacionGPS")
public class UbicacionGPSController {
    private final UbicacionGPSService ubicacionGPSService;

    public UbicacionGPSController(UbicacionGPSService ubicacionGPSService) {
        this.ubicacionGPSService = ubicacionGPSService;
    }
    @GetMapping
    public ResponseEntity<List<UbicacionGPSDTO>> listAll() throws ServiceException {

        return ResponseEntity.ok(ubicacionGPSService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UbicacionGPSDTO> read(@PathVariable Long id) throws ServiceException {
        UbicacionGPSDTO ubicacionGPSDTO = ubicacionGPSService.findById(id);
        return ResponseEntity.ok(ubicacionGPSDTO);
    }
    @PostMapping
    public ResponseEntity<UbicacionGPSDTO> create(@RequestBody UbicacionGPSDTO ubicacionGPSDTO) throws ServiceException {
        UbicacionGPSDTO ubicacionGPSDTO1 = ubicacionGPSService.create(ubicacionGPSDTO);
        return new ResponseEntity<>(ubicacionGPSDTO1,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UbicacionGPSDTO> update(@PathVariable Long id, @RequestBody UbicacionGPSDTO ubicacionGPSDTO) throws ServiceException {
        UbicacionGPSDTO ubicacionGPSDTO1 = ubicacionGPSService.update(id,ubicacionGPSDTO);
        return ResponseEntity.ok(ubicacionGPSDTO1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        ubicacionGPSService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}