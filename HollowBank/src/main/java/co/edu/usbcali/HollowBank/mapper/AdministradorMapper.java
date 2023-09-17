package co.edu.usbcali.HollowBank.mapper;

import co.edu.usbcali.HollowBank.domain.Administrador;
import co.edu.usbcali.HollowBank.dto.AdministradorDTO;

import java.util.List;

public class AdministradorMapper {
    public static Administrador dtoToDomain(AdministradorDTO administradorDTO){
        return Administrador.builder()
                .id(administradorDTO.getId())
                .nombre(administradorDTO.getNombre())
                .apellido(administradorDTO.getApellido())
                .salario(administradorDTO.getSalario())
                .telefono(administradorDTO.getTelefono())
                .build();
    }

    public static AdministradorDTO domainToDto(Administrador administrador){
        return AdministradorDTO.builder()
                .id(administrador.getId())
                .nombre(administrador.getNombre())
                .apellido(administrador.getApellido())
                .salario(administrador.getSalario())
                .telefono(administrador.getTelefono())
                .build();
    }

    public static List<Administrador> dtoToDomainList(List<AdministradorDTO> administradorDTOS){
        return administradorDTOS.stream().map(AdministradorMapper::dtoToDomain).toList();
    }

    public static List<AdministradorDTO> domainToDtoList(List<Administrador> administradors){
        return administradors.stream().map(AdministradorMapper::domainToDto).toList();
    }
}
