package co.edu.usbcali.HollowBank.mapper;

import co.edu.usbcali.HollowBank.domain.Prestamo;
import co.edu.usbcali.HollowBank.dto.PrestamoDTO;

import java.util.List;

public class PrestamoMapper {
    public static Prestamo dtoToDomain(PrestamoDTO prestamoDTO){
        return Prestamo.builder()
                .id(prestamoDTO.getId())
                .monto(prestamoDTO.getMonto())
                .fecha(prestamoDTO.getFecha())
                .estado(prestamoDTO.getEstado())
                .referencia(prestamoDTO.getReferencia())
                .build();
    }

    public static PrestamoDTO domainToDto(Prestamo prestamo){
        return PrestamoDTO.builder()
                .id(prestamo.getId())
                .usuarioId((prestamo.getUsuario() == null) ? null :
                        prestamo.getUsuario().getId())
                .estado(prestamo.getEstado())
                .monto(prestamo.getMonto())
                .fecha(prestamo.getFecha())
                .referencia(prestamo.getReferencia())
                .build();
    }

    public static List<PrestamoDTO> domainToDtoList(List<Prestamo> prestamos){
        return prestamos.stream().map(PrestamoMapper::domainToDto).toList();
    }
}
