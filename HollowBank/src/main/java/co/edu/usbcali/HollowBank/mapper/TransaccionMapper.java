package co.edu.usbcali.HollowBank.mapper;

import co.edu.usbcali.HollowBank.domain.Transaccion;
import co.edu.usbcali.HollowBank.dto.TransaccionDTO;

import java.util.List;

public class TransaccionMapper {
    public static Transaccion dtoToDomain(TransaccionDTO transaccionDTO){
        return Transaccion.builder()
                .idTransaccion(transaccionDTO.getIdTransaccion())
                //.cuentaBancaria(transaccionDTO.getCuentaBancariaId())
                //.usuario(transaccionDTO.getUsuarioId())
                .monto(transaccionDTO.getMonto())
                .fechaTransaccion(transaccionDTO.getFechaTransaccion())
                .tipotransaccion(transaccionDTO.getTipotransaccion())
                .estadotransaccion(transaccionDTO.getEstadotransaccion())
                .build();
    }

    public static TransaccionDTO domainToDto(Transaccion transaccion){
        return TransaccionDTO.builder()
                .idTransaccion(transaccion.getIdTransaccion())
                //.cuentaBancaria(transaccion.getCuentaBancariaId())
                //.usuario(transaccion.getUsuarioId())
                .monto(transaccion.getMonto())
                .fechaTransaccion(transaccion.getFechaTransaccion())
                .tipotransaccion(transaccion.getTipotransaccion())
                .estadotransaccion(transaccion.getEstadotransaccion())
                .build();
    }

    public static List<Transaccion> dtoToDomainList(List<TransaccionDTO> transaccionDTOS){
        return transaccionDTOS.stream().map(TransaccionMapper::dtoToDomain).toList();
    }

    public static List<TransaccionDTO> domainToDtoList(List<Transaccion> transaccions){
        return transaccions.stream().map(TransaccionMapper::domainToDto).toList();
    }
}
