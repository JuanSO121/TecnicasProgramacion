package co.edu.usbcali.HollowBank.mapper;

import co.edu.usbcali.HollowBank.domain.PagoServicio;
import co.edu.usbcali.HollowBank.dto.PagoServicioDTO;

import java.util.List;

public class PagoServicioMapper {
    public static PagoServicio dtoToDomain(PagoServicioDTO pagoServicioDTO){
        return PagoServicio.builder()
                .id(pagoServicioDTO.getId())
                .servicio(pagoServicioDTO.getServicio())
                .monto(pagoServicioDTO.getMonto())
                .fecha(pagoServicioDTO.getFecha())
                .referencia(pagoServicioDTO.getReferencia())
                .build();
    }

    public static PagoServicioDTO domainToDto(PagoServicio pagoServicio){
        return PagoServicioDTO.builder()
                .id(pagoServicio.getId())
                .usuarioId((pagoServicio.getUsuario() == null) ? null :
                        pagoServicio.getUsuario().getId())
                .servicio(pagoServicio.getServicio())
                .monto(pagoServicio.getMonto())
                .fecha(pagoServicio.getFecha())
                .referencia(pagoServicio.getReferencia())
                .build();
    }

    public static List<PagoServicioDTO> domainToDtoList(List<PagoServicio> pagoServicios){
        return pagoServicios.stream().map(PagoServicioMapper::domainToDto).toList();
    }
}
