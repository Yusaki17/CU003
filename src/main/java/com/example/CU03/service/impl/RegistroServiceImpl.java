package com.example.CU03.service.impl;

import com.example.CU03.dto.RegistroDTO;
import com.example.CU03.service.service.RegistroService;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public class RegistroServiceImpl implements RegistroService {
    @Override
    public RegistroDTO create(RegistroDTO registroDTO) throws ServiceException {
        return null;
    }

    @Override
    public RegistroDTO update(Long aLong, RegistroDTO registroDTO) throws ServiceException {
        return null;
    }

    @Override
    public RegistroDTO findById(Long aLong) throws ServiceException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {

    }

    @Override
    public List<RegistroDTO> findAll() throws ServiceException {
        return List.of();
    }
}
