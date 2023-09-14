package co.edu.usbcali.HollowBank.mapper;

import co.edu.usbcali.HollowBank.domain.CuentaBancaria;
import co.edu.usbcali.HollowBank.dto.CuentaBancariaDTO;

import java.util.List;

public class CuentaBancariaMapper {
    public static CuentaBancaria dtoToDomain(CuentaBancariaDTO cuentaBancariaDTO){
        return CuentaBancaria.builder()
                .idCuentaBancaria(cuentaBancariaDTO.getIdCuentaBancaria())
                .usuario(cuentaBancariaDTO.getUsuario())
                .nomUsuario(cuentaBancariaDTO.getNomUsuario())
                .apeUsuario(cuentaBancariaDTO.getApeUsuario())
                .saldo(cuentaBancariaDTO.getSaldo())
                .build();
    }

    public static CuentaBancariaDTO domainToDto(CuentaBancaria cuentaBancaria){
        return CuentaBancariaDTO.builder()
                .idCuentaBancaria(cuentaBancaria.getIdCuentaBancaria())
                .usuario(cuentaBancaria.getUsuario())
                .nomUsuario(cuentaBancaria.getNomUsuario())
                .apeUsuario(cuentaBancaria.getApeUsuario())
                .saldo(cuentaBancaria.getSaldo())
                .build();
    }

    public static List<CuentaBancaria> dtoToDomainList(List<CuentaBancariaDTO> cuentaBancariaDTOS){
        return cuentaBancariaDTOS.stream().map(CuentaBancariaMapper::dtoToDomain).toList();
    }

    public static List<CuentaBancariaDTO> domainToDtoList(List<CuentaBancaria> cuentaBancarias){
        return cuentaBancarias.stream().map(CuentaBancariaMapper::domainToDto).toList();
    }
}
