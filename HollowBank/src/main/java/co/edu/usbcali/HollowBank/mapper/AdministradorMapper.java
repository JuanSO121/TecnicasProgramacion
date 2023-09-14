package co.edu.usbcali.HollowBank.mapper;

import co.edu.usbcali.HollowBank.domain.Administrador;
import co.edu.usbcali.HollowBank.dto.AdministradorDTO;

import java.util.List;

public class AdministradorMapper {
    public static Administrador dtoToDomain(AdministradorDTO administradorDTO){
        return Administrador.builder()
                .idAdministrador(administradorDTO.getIdAdministrador())
                .nomAdmin(administradorDTO.getNomAdmin())
                .apeAdmin(administradorDTO.getApeAdmin())
                .salarioAdmin(administradorDTO.getSalarioAdmin())
                .telAdministrador(administradorDTO.getTelAdministrador())
                .build();
    }

    public static AdministradorDTO domainToDto(Administrador administrador){
        return AdministradorDTO.builder()
                .idAdministrador(administrador.getIdAdministrador())
                .nomAdmin(administrador.getNomAdmin())
                .apeAdmin(administrador.getApeAdmin())
                .salarioAdmin(administrador.getSalarioAdmin())
                .telAdministrador(administrador.getTelAdministrador())
                .build();
    }

    public static List<Administrador> dtoToDomainList(List<AdministradorDTO> administradorDTOS){
        return administradorDTOS.stream().map(AdministradorMapper::dtoToDomain).toList();
    }

    public static List<AdministradorDTO> domainToDtoList(List<Administrador> administrador){
        return administrador.stream().map(AdministradorMapper::domainToDto).toList();
    }
}
