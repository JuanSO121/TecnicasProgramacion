package co.edu.usbcali.HollowBank.service.impl;

import co.edu.usbcali.HollowBank.domain.PagoServicio;
import co.edu.usbcali.HollowBank.dto.PagoServicioDTO;
import co.edu.usbcali.HollowBank.mapper.PagoServicioMapper;
import co.edu.usbcali.HollowBank.repository.PagoServicioRepository;
import co.edu.usbcali.HollowBank.service.PagoServicioService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
public class PagoServiciosSericeImpl implements PagoServicioService {

    private final PagoServicioRepository pagoServicioRepository;

    public PagoServiciosSericeImpl(PagoServicioRepository pagoServicioRepository) {
        this.pagoServicioRepository = pagoServicioRepository;
    }

    @Override
    public PagoServicioDTO guardarNuevoPagoServicio(PagoServicioDTO pagoServicioDTO) throws Exception {
        //1. validar datos pagoServicio
        if (pagoServicioDTO == null){
            throw new Exception("El pagoServicio es Nulo");
        }

        if(pagoServicioDTO.getServicio() == null || pagoServicioDTO.getServicio().trim().isEmpty()){
            throw new Exception("Servicio Vacio");
        }

        Timestamp fechaTimestamp = pagoServicioDTO.getFecha();

        if (fechaTimestamp == null) {
            throw new Exception("Fecha nula");
        }

// Comprobar si es un Timestamp válido (por ejemplo, no es negativo)
        if (fechaTimestamp.getTime() < 0) {
            throw new Exception("Fecha no válida");
        }

        if (pagoServicioDTO.getMonto() == null || pagoServicioDTO.getMonto().compareTo(BigDecimal.ZERO) == 0) {
            throw new Exception("Monto vacío");
        }

        //2. registrar el pagoServicio en DB Mapeando desde DTO hacia DOMAIN
        // convertir el pagoServicio dto a un objeto del dominio
        PagoServicio pagoServicio = PagoServicioMapper.dtoToDomain(pagoServicioDTO);

        pagoServicio = pagoServicioRepository.save(pagoServicio);

        //3. retornar pagoServicio mapeado en DTO
        pagoServicioDTO = PagoServicioMapper.domainToDto(pagoServicio);

        return pagoServicioDTO;


    }
}
