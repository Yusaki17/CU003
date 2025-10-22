package com.example.CU03.controller.general;
import com.example.CU03.dto.RegistroDTO;
import com.example.CU03.service.service.RegistroService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/registro")
public class RegistroController {
    private final RegistroService registroService;

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }
    @GetMapping
    public ResponseEntity<List<RegistroDTO>> listAll() throws ServiceException {

        return ResponseEntity.ok(registroService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RegistroDTO> read(@PathVariable Long id) throws ServiceException {
        RegistroDTO registroDTO = registroService.findById(id);
        return ResponseEntity.ok(registroDTO);
    }
    @PostMapping
    public ResponseEntity<RegistroDTO> create(@RequestBody RegistroDTO registroDTO) throws ServiceException {
        RegistroDTO registroDTO1 = registroService.create(registroDTO);
        return new ResponseEntity<>(registroDTO1,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RegistroDTO> update(@PathVariable Long id, @RequestBody RegistroDTO registroDTO) throws ServiceException {
        RegistroDTO registroDTO1 = registroService.update(id,registroDTO);
        return ResponseEntity.ok(registroDTO1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        registroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}