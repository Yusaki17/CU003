package com.example.CU03.service.impl;

import com.example.CU03.controller.exceptions.ResourceNotFoundException;
import com.example.CU03.dto.RegistroDTO;
import com.example.CU03.entity.Conductor;
import com.example.CU03.entity.Registro;
import com.example.CU03.entity.UbicacionGPS;
import com.example.CU03.mapper.RegistroMapper;
import com.example.CU03.repository.ConductorRepository;
import com.example.CU03.repository.RegistroRepository;
import com.example.CU03.repository.UbicacionGPSRepository;
import com.example.CU03.service.service.RegistroService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegistroServiceImpl implements RegistroService {
    private final RegistroRepository registroRepository;
    private final RegistroMapper registroMapper;
    private final ConductorRepository conductorRepository;
    private final UbicacionGPSRepository ubicacionGPSRepository;

    public RegistroServiceImpl(RegistroRepository registroRepository, RegistroMapper registroMapper, ConductorRepository conductorRepository, UbicacionGPSRepository ubicacionGPSRepository) {
        this.registroRepository = registroRepository;
        this.registroMapper = registroMapper;
        this.conductorRepository = conductorRepository;
        this.ubicacionGPSRepository = ubicacionGPSRepository;
    }

    @Override
    public RegistroDTO create(RegistroDTO registroDTO) throws ServiceException {
        if(registroDTO==null){
            throw new IllegalArgumentException("El Registro no puede ser nulo.");
        }
        try {
            Registro registro = registroMapper.toEntity(registroDTO);
            return registroMapper.toDTO(registroRepository.save(registro));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RegistroDTO update(Long aLong, RegistroDTO registroDTO) throws ServiceException {
        if (aLong == null || registroDTO == null) {
            throw new IllegalArgumentException("El ID y el Registro no pueden ser nulos");
        }
        Registro existente = registroRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Registro no encontrado con ID: " + aLong));


        try {
            if(registroDTO.getConductorId()!=null){
                Conductor conductor = conductorRepository.findById(registroDTO.getConductorId())
                        .orElseThrow(()-> new ResourceNotFoundException("Conductores no encontrado con ID "+aLong));
                existente.setConductorId(conductor);
            }
            if(registroDTO.getUbicacionGPSId()!=null){
                UbicacionGPS ubicacionGPS = ubicacionGPSRepository.findById(registroDTO.getUbicacionGPSId())
                        .orElseThrow(()-> new ResourceNotFoundException("UbicacionGPS no encontrado con ID "+aLong));
                existente.setUbicacionGPSId(ubicacionGPS);

            }

            Registro actualizado = registroRepository.save(existente);
            return registroMapper.toDTO(actualizado);

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RegistroDTO findById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulos");
        }
        Registro existente = registroRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Registro no encontrado con ID: " + aLong));
        try {
            return registroMapper.toDTO(existente);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        Registro existente = registroRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Registro no encontrado con ID: " + aLong));
        try{
            registroRepository.delete(existente);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<RegistroDTO> findAll() throws ServiceException {
        List<Registro> registro = registroRepository.findAll();
        if (registro.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron registros ");
        }

        return registro.stream()
                .map(registroMapper::toDTO)
                .toList();
    }
}
