package com.example.CU03.service.impl;

import com.example.CU03.controller.exceptions.ResourceNotFoundException;
import com.example.CU03.dto.ConductorDTO;
import com.example.CU03.dto.RegistroDTO;
import com.example.CU03.entity.Conductor;
import com.example.CU03.entity.Registro;
import com.example.CU03.entity.UbicacionGPS;
import com.example.CU03.mapper.ConductorMapper;
import com.example.CU03.repository.ConductorRepository;
import com.example.CU03.repository.RegistroRepository;
import com.example.CU03.repository.UbicacionGPSRepository;
import com.example.CU03.service.service.ConductorService;
import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConductorServiceImpl implements ConductorService {
    private ConductorRepository conductorRepository;
    private ConductorMapper conductorMapper;
    private RegistroRepository registroRepository;
    private UbicacionGPSRepository ubicacionGPSRepository;

    public ConductorServiceImpl(ConductorMapper conductorMapper, ConductorRepository conductorRepository,
                                RegistroRepository registroRepository, UbicacionGPSRepository ubicacionGPSRepository){
        this.conductorMapper = conductorMapper;
        this.conductorRepository = conductorRepository;
        this.registroRepository = registroRepository;
        this.ubicacionGPSRepository = ubicacionGPSRepository;
    }

    @Override
    @Transactional
    public ConductorDTO create(ConductorDTO conductorDTO) throws ServiceException {
        if(conductorDTO == null){
            throw new IllegalArgumentException("El Conductor no puede ser nulo.");
        }

        try {
            // Convertir DTO a entidad (sin registros)
            Conductor conductor = conductorMapper.toEntity(conductorDTO);

            // Guardar el conductor primero
            conductor = conductorRepository.save(conductor);

            // Procesar los registros si existen
            if(conductorDTO.getRegistro() != null && !conductorDTO.getRegistro().isEmpty()) {
                for(RegistroDTO registroDTO : conductorDTO.getRegistro()) {
                    Registro registro = new Registro();

                    // Asignar el conductor ya guardado
                    registro.setConductorId(conductor);

                    // Mapear campos del registro desde el DTO
                    registro.setFechahoraingreso(registroDTO.getFechahoraingreso());
                    registro.setEstado(registroDTO.getEstado());
                    registro.setObservaciones(registroDTO.getObservaciones());

                    // Asignar ubicación GPS si existe
                    if(registroDTO.getUbicacionGPSId() != null) {
                        UbicacionGPS ubicacionGPS = ubicacionGPSRepository.findById(registroDTO.getUbicacionGPSId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                        "Ubicación GPS no encontrada con ID: " + registroDTO.getUbicacionGPSId()));
                        registro.setUbicacionGPSId(ubicacionGPS);
                    }

                    conductor.getRegistro().add(registro);
                }

                // Guardar con los registros (cascade los persistirá)
                conductor = conductorRepository.save(conductor);
            }

            return conductorMapper.toDTO(conductor);

        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear conductor: " + e.getMessage(), e);
        }
    }
    @Override
    @Transactional
    public ConductorDTO update(Long aLong, ConductorDTO conductorDTO) throws ServiceException {
        if (aLong == null || conductorDTO == null) {
            throw new IllegalArgumentException("El ID y el Conductor no pueden ser nulos");
        }

        Conductor existente = conductorRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Conductor no encontrado con ID: " + aLong));

        try {
            // Actualizar campos del conductor
            existente.setNombre(conductorDTO.getNombre());
            existente.setApellido(conductorDTO.getApellido());
            existente.setTelefono(conductorDTO.getTelefono());
            existente.setLicencia(conductorDTO.getLicencia());

            // Manejo de la lista de registros
            if(conductorDTO.getRegistro() != null && !conductorDTO.getRegistro().isEmpty()) {

                for(RegistroDTO registroDTO : conductorDTO.getRegistro()) {
                    Registro registro;

                    // Si tiene ID, buscar el existente y ACTUALIZARLO (NO ELIMINARLO)
                    if(registroDTO.getId() != null) {
                        registro = registroRepository.findById(registroDTO.getId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                        "Registro no encontrado con ID: " + registroDTO.getId()));

                        // Actualizar campos del registro existente
                        registro.setFechahoraingreso(registroDTO.getFechahoraingreso());
                        registro.setEstado(registroDTO.getEstado());
                        registro.setObservaciones(registroDTO.getObservaciones());

                    } else {
                        // Si NO tiene ID, crear uno nuevo
                        registro = new Registro();
                        registro.setFechahoraingreso(registroDTO.getFechahoraingreso());
                        registro.setEstado(registroDTO.getEstado());
                        registro.setObservaciones(registroDTO.getObservaciones());
                        registro.setConductorId(existente);
                    }

                    // Actualizar la ubicación GPS si existe
                    if(registroDTO.getUbicacionGPSId() != null) {
                        UbicacionGPS ubicacionGPS = ubicacionGPSRepository.findById(registroDTO.getUbicacionGPSId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                        "Ubicación GPS no encontrada con ID: " + registroDTO.getUbicacionGPSId()));
                        registro.setUbicacionGPSId(ubicacionGPS);
                    }

                    // Solo agregar si es nuevo (si no estaba en la lista)
                    if(registroDTO.getId() == null) {
                        existente.getRegistro().add(registro);
                    }
                    // Si tiene ID, ya está en la lista, solo se actualizó
                }
            }

            Conductor actualizado = conductorRepository.save(existente);
            return conductorMapper.toDTO(actualizado);

        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar conductor: " + e.getMessage(), e);
        }
    }
    @Override
    public ConductorDTO findById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        Conductor existente = conductorRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Conductor no encontrado con ID: " + aLong));
        try {
            return conductorMapper.toDTO(existente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        Conductor existente = conductorRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Conductor no encontrado con ID: " + aLong));
        try {
            conductorRepository.delete(existente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ConductorDTO> findAll() throws ServiceException {
        List<Conductor> conductores = conductorRepository.findAll();
        if (conductores.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron Conductores registrados");
        }

        return conductores.stream()
                .map(conductorMapper::toDTO)
                .toList();
    }
}