package co.edu.usbcali.HollowBank.mapper;

import co.edu.usbcali.HollowBank.domain.Prestamo;
import co.edu.usbcali.HollowBank.dto.PrestamoDTO;

import java.util.List;

public class PrestamoMapper {
    public static Prestamo dtoToDomain(PrestamoDTO prestamoDTO){
        return Prestamo.builder()
                .id(prestamoDTO.getId())
                .servicio(prestamoDTO.getServicio())
                .monto(prestamoDTO.getMonto())
                .fecha(prestamoDTO.getFecha())
                .build();
    }

    public static PrestamoDTO domainToDto(Prestamo prestamo){
        return PrestamoDTO.builder()
                .id(prestamo.getId())
                .usuarioId((prestamo.getUsuario() == null) ? null :
                        prestamo.getUsuario().getId())
                .servicio(prestamo.getServicio())
                .monto(prestamo.getMonto())
                .fecha(prestamo.getFecha())
                .build();
    }

    public static List<Prestamo> dtoToDomainList(List<PrestamoDTO> prestamoDTOS){
        return prestamoDTOS.stream().map(PrestamoMapper::dtoToDomain).toList();
    }

    public static List<PrestamoDTO> domainToDtoList(List<Prestamo> prestamos){
        return prestamos.stream().map(PrestamoMapper::domainToDto).toList();
    }
}
