package co.edu.usbcali.HollowBank.mapper;

import co.edu.usbcali.HollowBank.domain.Transaccion;
import co.edu.usbcali.HollowBank.dto.TransaccionDTO;

import java.util.List;

public class TransaccionMapper {
    public static Transaccion dtoToDomain(TransaccionDTO transaccionDTO){
        return Transaccion.builder()
                .id(transaccionDTO.getId())
                .monto(transaccionDTO.getMonto())
                .fecha(transaccionDTO.getFecha())
                .tipo(transaccionDTO.getTipo())
                .estado(transaccionDTO.getEstado())
                .referencia(transaccionDTO.getReferencia())
                .build();
    }

    public static TransaccionDTO domainToDto(Transaccion transaccion){
        return TransaccionDTO.builder()
                .id(transaccion.getId())
                .cuentaBancariaId((transaccion.getCuentaBancaria() == null) ? null :
                        transaccion.getCuentaBancaria().getId())
                .usuarioId((transaccion.getUsuario() == null) ? null :
                        transaccion.getUsuario().getId())
                .destinatarioId((transaccion.getDestinatario() == null) ? null :
                        transaccion.getDestinatario().getId())
                .monto(transaccion.getMonto())
                .fecha(transaccion.getFecha())
                .tipo(transaccion.getTipo())
                .estado(transaccion.getEstado())
                .referencia(transaccion.getReferencia())
                .build();
    }

    public static List<TransaccionDTO> domainToDtoList(List<Transaccion> transaccions){
        return transaccions.stream().map(TransaccionMapper::domainToDto).toList();
    }
}
