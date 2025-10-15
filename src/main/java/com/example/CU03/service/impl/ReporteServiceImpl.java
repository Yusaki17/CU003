package com.example.CU03.service.impl;

import com.example.CU03.dto.ReporteDTO;
import com.example.CU03.service.service.ReporteService;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public class ReporteServiceImpl implements ReporteService {
    @Override
    public ReporteDTO create(ReporteDTO reporteDTO) throws ServiceException {
        return null;
    }

    @Override
    public ReporteDTO update(Long aLong, ReporteDTO reporteDTO) throws ServiceException {
        return null;
    }

    @Override
    public ReporteDTO findById(Long aLong) throws ServiceException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {

    }

    @Override
    public List<ReporteDTO> findAll() throws ServiceException {
        return List.of();
    }
}
