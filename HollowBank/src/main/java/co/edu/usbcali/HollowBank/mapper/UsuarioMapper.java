package co.edu.usbcali.HollowBank.mapper;

import co.edu.usbcali.HollowBank.domain.Administrador;
import co.edu.usbcali.HollowBank.domain.Usuario;
import co.edu.usbcali.HollowBank.dto.AdministradorDTO;
import co.edu.usbcali.HollowBank.dto.UsuarioDTO;

import java.util.List;

public class UsuarioMapper {
    public static Usuario dtoToDomain(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .id(usuarioDTO.getId())
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())
                .direccion(usuarioDTO.getDireccion())
                .telefono(usuarioDTO.getTelefono())
                .build();
    }

    public static UsuarioDTO domainToDto(Usuario usuario){
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .direccion(usuario.getDireccion())
                .telefono(usuario.getTelefono())
                .build();
    }

    public static List<Usuario> dtoToDomainList(List<UsuarioDTO> usuarioDTOS){
        return usuarioDTOS.stream().map(UsuarioMapper::dtoToDomain).toList();
    }

    public static List<UsuarioDTO> domainToDtoList(List<Usuario> usuarios){
        return usuarios.stream().map(UsuarioMapper::domainToDto).toList();
    }
}
