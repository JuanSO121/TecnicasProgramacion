package co.edu.usbcali.HollowBank.service.impl;

import co.edu.usbcali.HollowBank.domain.CuentaBancaria;
import co.edu.usbcali.HollowBank.dto.CuentaBancariaDTO;
import co.edu.usbcali.HollowBank.mapper.CuentaBancariaMapper;
import co.edu.usbcali.HollowBank.repository.CuentaBancariaRepository;
import co.edu.usbcali.HollowBank.service.CuentaBancariaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaBancariaServiceImpl implements CuentaBancariaService {

    private final CuentaBancariaRepository cuentaBancariaRepository;

    public CuentaBancariaServiceImpl(CuentaBancariaRepository cuentaBancariaRepository) {
        this.cuentaBancariaRepository = cuentaBancariaRepository;
    }

    @Override
    public CuentaBancariaDTO guardarNuevaCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO) throws Exception {
        // 1. Validar datos de la cuenta bancaria
        if (cuentaBancariaDTO == null) {
            throw new Exception("La cuenta bancaria es nula");
        }
        if (cuentaBancariaDTO.getNombre() == null || cuentaBancariaDTO.getNombre().trim().isEmpty()) {
            throw new Exception("Nombre vacío");
        }
        if (cuentaBancariaDTO.getApellido() == null || cuentaBancariaDTO.getApellido().trim().isEmpty()) {
            throw new Exception("Apellido vacío");
        }
        if (cuentaBancariaDTO.getSaldo() == null || cuentaBancariaDTO.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
            throw new Exception("Salario vacío");
        }

        // Registrar la cuenta bancaria en la BD mapeando desde DTO hacia DOMINIO
        CuentaBancaria cuentaBancaria = CuentaBancariaMapper.dtoToDomain(cuentaBancariaDTO);
        cuentaBancaria = cuentaBancariaRepository.save(cuentaBancaria);

        // Retornar la cuenta bancaria mapeada a DTO
        cuentaBancariaDTO = CuentaBancariaMapper.domainToDto(cuentaBancaria);

        return cuentaBancariaDTO;
    }
}

