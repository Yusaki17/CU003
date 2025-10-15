package com.example.CU03.service.impl;

import com.example.CU03.controller.exceptions.ResourceNotFoundException;
import com.example.CU03.dto.ConductorDTO;
import com.example.CU03.entity.Conductor;
import com.example.CU03.mapper.ConductorMapper;
import com.example.CU03.repository.ConductorRepository;
import com.example.CU03.repository.RegistroRepository;
import com.example.CU03.repository.UbicacionGPSRepository;
import com.example.CU03.service.service.ConductorService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConductorServiceImpl implements ConductorService {
    private ConductorRepository conductorRepository;
    private ConductorMapper conductorMapper;
    private RegistroRepository registroRepository;
    private UbicacionGPSRepository ubicacionGPSRepository;

    public ConductorServiceImpl(ConductorMapper conductorMapper, ConductorRepository conductorRepository,RegistroRepository registroRepository, UbicacionGPSRepository ubicacionGPSRepository){
        this.conductorMapper = conductorMapper;
        this.conductorRepository = conductorRepository;
        this.registroRepository = registroRepository;
        this.ubicacionGPSRepository = ubicacionGPSRepository;
    }

    @Override
    public ConductorDTO create(ConductorDTO conductorDTO) throws ServiceException {
        try {
            Conductor conductor = conductorMapper.toEntity(conductorDTO);
            Conductor conductorSaved = conductorRepository.save(conductor);
            return conductorMapper.toDTO(conductorSaved);
        } catch (Exception e) {
            throw new ServiceException("Error al crear conductor", e);
        }
    }

    @Override
    public ConductorDTO update(Long aLong, ConductorDTO conductorDTO) throws ServiceException {
        try {
            Conductor conductor = conductorRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("conductor no encontrada"));
            conductor.setMateria(materiasDTO.getMateria());
            Conductor materiasUpdate = materiasRepository.save(materias);
            return materiasMapper.toDTO(materiasUpdate);
        } catch (ResourceNotFoundException e) {
            throw (e);
        }catch (Exception e) {
            throw new ServiceException("Error al actualizar materias",e);
        }
    }

    @Override
    public ConductorDTO findById(Long aLong) throws ServiceException {
        try {
            Conductor materias = materiasRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("materia con ID "+aLong+" no fue encontrada"));
            return materiasMapper.toDTO(materias);
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al leer el materia con id " + aLong, e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        try {
            if(!materiasRepository.findById(aLong).isPresent()){
                throw new ResourceNotFoundException("materia con ID "+aLong+" no fue encontrada");
            }
            materiasRepository.deleteById(aLong);
        }catch (ResourceNotFoundException e) {
            throw (e);
        }catch (Exception e) {
            throw new ServiceException("Error al eliminar  materia con id " + aLong, e);
        }
    }

    @Override
    public List<ConductorDTO> findAll() throws ServiceException {
        try {
            List<Conductor> materias = materiasRepository.findAll();
            return materiasMapper.toDTOs(materias);
        }catch (Exception e) {
            throw new ServiceException("Error al listar los materias",e);
        }
    }
}
}
