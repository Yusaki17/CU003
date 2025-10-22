package com.example.CU03.service.impl;

import com.example.CU03.controller.exceptions.ResourceNotFoundException;
import com.example.CU03.dto.ReporteDTO;
import com.example.CU03.entity.Registro;
import com.example.CU03.entity.Reporte;
import com.example.CU03.mapper.ReporteMapper;
import com.example.CU03.repository.RegistroRepository;
import com.example.CU03.repository.ReporteRepository;
import com.example.CU03.service.service.ReporteService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReporteServiceImpl implements ReporteService {
    private final ReporteRepository reporteRepository;
    private final ReporteMapper reporteMapper;
    private final RegistroRepository registroRepository;

    public ReporteServiceImpl(ReporteRepository reporteRepository, ReporteMapper reporteMapper, RegistroRepository registroRepository) {
        this.reporteRepository = reporteRepository;
        this.reporteMapper = reporteMapper;
        this.registroRepository = registroRepository;
    }

    @Override
    public ReporteDTO create(ReporteDTO reporteDTO) throws ServiceException {
        if(reporteDTO==null){
            throw new IllegalArgumentException("El reporte no puede ser nulo.");
        }
        try {
            Reporte reporte = reporteMapper.toEntity(reporteDTO);
            return reporteMapper.toDTO(reporteRepository.save(reporte));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ReporteDTO update(Long aLong, ReporteDTO reporteDTO) throws ServiceException {
        if (aLong == null || reporteDTO == null) {
            throw new IllegalArgumentException("El ID y el Reporte no pueden ser nulos");
        }
        Reporte existente = reporteRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado con ID: " + aLong));


        try {

            existente.setEstado(reporteDTO.getEstado());
            existente.setFechareporte(reporteDTO.getFechareporte());
            existente.setCodigoreporte(reporteDTO.getCodigoreporte());

            if(reporteDTO.getRegistroId()!=null){
                Registro registro = registroRepository.findById(reporteDTO.getRegistroId())
                        .orElseThrow(()-> new ResourceNotFoundException("Registro no encontrado con ID "+aLong));
                existente.setRegistroId(registro);

            }

            Reporte actualizado = reporteRepository.save(existente);
            return reporteMapper.toDTO(actualizado);

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ReporteDTO findById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulos");
        }
        Reporte existente = reporteRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado con ID: " + aLong));
        try {
            return reporteMapper.toDTO(existente);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        Reporte existente = reporteRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("reporte no encontrado con ID: " + aLong));
        try{
            reporteRepository.delete(existente);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ReporteDTO> findAll() throws ServiceException {
        List<Reporte> reporte = reporteRepository.findAll();
        if (reporte.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron reportes registrados");
        }

        return reporte.stream()
                .map(reporteMapper::toDTO)
                .toList();
    }
}
