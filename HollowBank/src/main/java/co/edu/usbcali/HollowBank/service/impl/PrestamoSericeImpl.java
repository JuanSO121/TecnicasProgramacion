package co.edu.usbcali.HollowBank.service.impl;

import co.edu.usbcali.HollowBank.domain.Prestamo;
import co.edu.usbcali.HollowBank.dto.PrestamoDTO;
import co.edu.usbcali.HollowBank.mapper.PrestamoMapper;
import co.edu.usbcali.HollowBank.repository.PrestamoRepository;
import co.edu.usbcali.HollowBank.service.PrestamoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
public class PrestamoSericeImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;

    public PrestamoSericeImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public PrestamoDTO guardarNuevoPrestamo(PrestamoDTO prestamoDTO) throws Exception {
        //1. validar datos prestamo
        if (prestamoDTO == null){
            throw new Exception("El prestamo es Nulo");
        }

        if (prestamoDTO.getMonto() == null || prestamoDTO.getMonto().compareTo(BigDecimal.ZERO) == 0) {
            throw new Exception("Monto vacío");
        }

        Timestamp fechaTimestamp = prestamoDTO.getFecha();

        if (fechaTimestamp == null) {
            throw new Exception("Fecha nula");
        }

// Comprobar si es un Timestamp válido (por ejemplo, no es negativo)
        if (fechaTimestamp.getTime() < 0) {
            throw new Exception("Fecha no válida");
        }

        if(prestamoDTO.getEstado() == null || prestamoDTO.getEstado().trim().isEmpty()){
            throw new Exception("Estado Vacio");
        }

        if (prestamoDTO.getReferencia() == null || prestamoDTO.getReferencia().trim().isEmpty()) {
            throw new Exception("Referencia vacía");
        }

        //2. registrar el prestamo en DB Mapeando desde DTO hacia DOMAIN
        // convertir el prestamo dto a un objeto del dominio
        Prestamo prestamo = PrestamoMapper.dtoToDomain(prestamoDTO);

        prestamo = prestamoRepository.save(prestamo);

        //3. retornar prestamo mapeado en DTO
        prestamoDTO = PrestamoMapper.domainToDto(prestamo);

        return prestamoDTO;


    }
}
