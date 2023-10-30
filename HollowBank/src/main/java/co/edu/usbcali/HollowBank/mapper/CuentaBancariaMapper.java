package co.edu.usbcali.HollowBank.mapper;

import co.edu.usbcali.HollowBank.domain.CuentaBancaria;
import co.edu.usbcali.HollowBank.dto.CuentaBancariaDTO;

import java.util.List;

public class CuentaBancariaMapper {
    public static CuentaBancaria dtoToDomain(CuentaBancariaDTO cuentaBancariaDTO){
        return CuentaBancaria.builder()
                .id(cuentaBancariaDTO.getId())
                //.usuario(cuentaBancariaDTO.getUsuario())
                .tipo(cuentaBancariaDTO.getTipo())
                .saldo(cuentaBancariaDTO.getSaldo())
                .build();
    }

    public static CuentaBancariaDTO domainToDto(CuentaBancaria cuentaBancaria){
        return CuentaBancariaDTO.builder()
                .id(cuentaBancaria.getId())
                .usuarioId((cuentaBancaria.getUsuario() == null) ? null :
                        cuentaBancaria.getUsuario().getId())
                .tipo(cuentaBancaria.getTipo())
                .saldo(cuentaBancaria.getSaldo())
                .build();
    }

    public static List<CuentaBancariaDTO> domainToDtoList(List<CuentaBancaria> cuentaBancarias){
        return cuentaBancarias.stream().map(CuentaBancariaMapper::domainToDto).toList();
    }
}
