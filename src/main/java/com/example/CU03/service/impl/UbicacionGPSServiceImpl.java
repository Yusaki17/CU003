package com.example.CU03.service.impl;

import com.example.CU03.controller.exceptions.ResourceNotFoundException;
import com.example.CU03.dto.UbicacionGPSDTO;
import com.example.CU03.entity.UbicacionGPS;
import com.example.CU03.mapper.UbicacionGPSMapper;
import com.example.CU03.repository.UbicacionGPSRepository;
import com.example.CU03.service.service.UbicacionGPSService;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public class UbicacionGPSServiceImpl implements UbicacionGPSService  {
    private UbicacionGPSRepository ubicacionGPSRepository;
    private UbicacionGPSMapper ubicacionGPSMapper;

    public UbicacionGPSServiceImpl(UbicacionGPSRepository ubicacionGPSRepository,UbicacionGPSMapper ubicacionGPSMapper){
    this.ubicacionGPSMapper = ubicacionGPSMapper;
    this.ubicacionGPSRepository = ubicacionGPSRepository;
    }

    @Override
    public UbicacionGPSDTO create(UbicacionGPSDTO ubicacionGPSDTO) throws ServiceException {
        try {
            UbicacionGPS ubicacionGPS = ubicacionGPSMapper.toEntity(ubicacionGPSDTO);
            UbicacionGPS ubicacionGPSSaved = ubicacionGPSRepository.save(ubicacionGPS);
            return ubicacionGPSMapper.toDTO(ubicacionGPSSaved);
        } catch (Exception e) {
            throw new ServiceException("Error al crear ubicacionGPS", e);
        }
    }

    @Override
    public UbicacionGPSDTO update(Long aLong, UbicacionGPSDTO ubicacionGPSDTO) throws ServiceException {
        try {
            UbicacionGPS ubicacionGPS = ubicacionGPSRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("UbicacionGPS no encontrada"));
            ubicacionGPS.setDistrito(ubicacionGPSDTO.getDistrito());
            UbicacionGPS ubicacionGPSUpdate = ubicacionGPSRepository.save(ubicacionGPS);
            return ubicacionGPSMapper.toDTO(ubicacionGPSUpdate);
        } catch (ResourceNotFoundException e) {
            throw (e);
        }catch (Exception e) {
            throw new ServiceException("Error al actualizar UbicacionGPS",e);
        }
    }

    @Override
    public UbicacionGPSDTO findById(Long aLong) throws ServiceException {
        try {
            UbicacionGPS materias = materiasRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("UbicacionGPS con ID "+aLong+" no fue encontrada"));
            return materiasMapper.toDTO(materias);
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al leer el UbicacionGPS con id " + aLong, e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        try {
            if(!materiasRepository.findById(aLong).isPresent()){
                throw new ResourceNotFoundException("UbicacionGPS con ID "+aLong+" no fue encontrada");
            }
            materiasRepository.deleteById(aLong);
        }catch (ResourceNotFoundException e) {
            throw (e);
        }catch (Exception e) {
            throw new ServiceException("Error al eliminar  UbicacionGPS con id " + aLong, e);
        }
    }

    @Override
    public List<UbicacionGPSDTO> findAll() throws ServiceException {
        try {
            List<UbicacionGPS> materias = materiasRepository.findAll();
            return materiasMapper.toDTOs(materias);
        }catch (Exception e) {
            throw new ServiceException("Error al listar los UbicacionGPS",e);
        }
    }
}
