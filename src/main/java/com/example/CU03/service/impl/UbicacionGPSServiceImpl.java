package com.example.CU03.service.impl;

import com.example.CU03.controller.exceptions.ResourceNotFoundException;
import com.example.CU03.dto.UbicacionGPSDTO;
import com.example.CU03.entity.UbicacionGPS;
import com.example.CU03.mapper.UbicacionGPSMapper;
import com.example.CU03.repository.UbicacionGPSRepository;
import com.example.CU03.service.service.UbicacionGPSService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
            ubicacionGPS.setCordenadas(ubicacionGPSDTO.getCordenadas());
            ubicacionGPS.setDireccion(ubicacionGPSDTO.getDireccion());
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
            UbicacionGPS ubicacionGPS = ubicacionGPSRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("UbicacionGPS con ID "+aLong+" no fue encontrada"));
            return ubicacionGPSMapper.toDTO(ubicacionGPS);
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al leer el UbicacionGPS con id " + aLong, e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            // 1️⃣ Verificar si existe
            if (!ubicacionGPSRepository.existsById(id)) {
                throw new ResourceNotFoundException("Ubicación GPS con ID " + id + " no fue encontrada.");
            }

            // 2️⃣ Intentar eliminar por ID
            ubicacionGPSRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            // 3️⃣ Error de integridad referencial (por ejemplo, está usada en 'Registro')
            throw new ServiceException(
                    "No se puede eliminar la Ubicación GPS con ID " + id +
                            " porque está relacionada con otros registros (p. ej., en TBL_REGISTRO_LLEGADA).", e
            );
        } catch (ResourceNotFoundException e) {
            throw e; // repropaga tal cual
        } catch (Exception e) {
            // 4️⃣ Cualquier otro error inesperado
            throw new ServiceException("Error al eliminar la Ubicación GPS con ID " + id, e);
        }
    }
    @Override
    public List<UbicacionGPSDTO> findAll() throws ServiceException {
        try {
            List<UbicacionGPS> ubicacionGPS = ubicacionGPSRepository.findAll();
            return ubicacionGPSMapper.toDTOs(ubicacionGPS);
        }catch (Exception e) {
            throw new ServiceException("Error al listar los UbicacionGPS",e);
        }
    }
}
