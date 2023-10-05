package co.edu.usbcali.HollowBank.service.impl;

import co.edu.usbcali.HollowBank.domain.Transaccion;
import co.edu.usbcali.HollowBank.dto.TransaccionDTO;
import co.edu.usbcali.HollowBank.mapper.TransaccionMapper;
import co.edu.usbcali.HollowBank.repository.TransaccionRepository;
import co.edu.usbcali.HollowBank.service.TransaccionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service

public class TransaccionSericeImpl implements TransaccionService {
    private final TransaccionRepository transaccionRepository;

    public TransaccionSericeImpl(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    @Override
    public TransaccionDTO guardarNuevaTransaccion(TransaccionDTO transaccionDTO) throws Exception {
        //1. validar datos transaccion
        if (transaccionDTO == null){
            throw new Exception("La cuenta Bancaria es Nula");
        }

        if(transaccionDTO.getMonto() == null || transaccionDTO.getMonto().compareTo(BigDecimal.ZERO) == 0){
            throw new Exception("Monto Vacio");
        }


        if(transaccionDTO.getTipo() == null || transaccionDTO.getTipo().trim().isEmpty()){
            throw new Exception("Tipo Vacio");
        }

        if (transaccionDTO.getEstado() == null || transaccionDTO.getEstado().trim().isEmpty()) {
            throw new Exception("Estado vacío");
        }


        //2. registrar el transaccion en DB Mapeando desde DTO hacia DOMAIN
        // convertir el transaccion dto a un objeto del dominio
        Transaccion transaccion = TransaccionMapper.dtoToDomain(transaccionDTO);

        transaccion = transaccionRepository.save(transaccion);

        //3. retornar transaccion mapeado en DTO
        transaccionDTO = TransaccionMapper.domainToDto(transaccion);

        return transaccionDTO;
    }


}
