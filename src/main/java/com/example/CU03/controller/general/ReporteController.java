package com.example.CU03.controller.general;
import com.example.CU03.dto.ReporteDTO;
import com.example.CU03.service.service.ReporteService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/reporte")
public class ReporteController {
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }
    @GetMapping
    public ResponseEntity<List<ReporteDTO>> listAll() throws ServiceException {

        return ResponseEntity.ok(reporteService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> read(@PathVariable Long id) throws ServiceException {
        ReporteDTO reporteDTO = reporteService.findById(id);
        return ResponseEntity.ok(reporteDTO);
    }
    @PostMapping
    public ResponseEntity<ReporteDTO> create(@RequestBody ReporteDTO reporteDTO) throws ServiceException {
        ReporteDTO reporteDTO1 = reporteService.create(reporteDTO);
        return new ResponseEntity<>(reporteDTO1,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReporteDTO> update(@PathVariable Long id, @RequestBody ReporteDTO reporteDTO) throws ServiceException {
        ReporteDTO reporteDTO1 = reporteService.update(id,reporteDTO);
        return ResponseEntity.ok(reporteDTO1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        reporteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
