package co.edu.usbcali.HollowBank.service.impl;

import co.edu.usbcali.HollowBank.domain.Usuario;
import co.edu.usbcali.HollowBank.dto.UsuarioDTO;
import co.edu.usbcali.HollowBank.dto.UsuarioDTO;
import co.edu.usbcali.HollowBank.mapper.UsuarioMapper;
import co.edu.usbcali.HollowBank.mapper.UsuarioMapper;
import co.edu.usbcali.HollowBank.repository.UsuarioRepository;
import co.edu.usbcali.HollowBank.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioSericeImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioSericeImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDTO guardarNuevoUsuario(UsuarioDTO usuarioDTO) throws Exception {
        //1. validar datos usuario
        if (usuarioDTO == null){
            throw new Exception("El usuario es Nulo");
        }

        if(usuarioDTO.getNombre() == null || usuarioDTO.getNombre().trim().isEmpty()){
            throw new Exception("Debe ingresar el nombre del usuario");
        }

        if(usuarioDTO.getApellido() == null || usuarioDTO.getApellido().trim().isEmpty()){
            throw new Exception("Debe ingresar el Apellido del usuario");
        }

        if(usuarioDTO.getDireccion() == null || usuarioDTO.getDireccion().trim().isEmpty()){
            throw new Exception("Direccion Vacia");
        }

        if(usuarioDTO.getTelefono() == null || usuarioDTO.getTelefono().trim().isEmpty()){
            throw new Exception("Telefono Vacio");
        }

        if(usuarioDTO.getPassword() == null || usuarioDTO.getPassword().trim().isEmpty()){
            throw new Exception("Ingresa la contraseña del usuario");
        }

        Optional<Usuario> usuarioPorNombre = usuarioRepository.findUsuarioByNombre(usuarioDTO.getNombre());
        if(usuarioPorNombre.isPresent()){
            throw new Exception(String.format("El usuario con el nombre %s ya se encuentra registrado",
                    usuarioDTO.getNombre()));
        }
        //2. registrar el usuario en DB Mapeando desde DTO hacia DOMAIN
        // convertir el usuario dto a un objeto del dominio
        Usuario usuario = UsuarioMapper.dtoToDomain(usuarioDTO);

        usuario = usuarioRepository.save(usuario);

        //3. retornar usuario mapeado en DTO
        usuarioDTO = UsuarioMapper.domainToDto(usuario);

        return usuarioDTO;


    }
    public List<UsuarioDTO> buscarTodos() {
        return UsuarioMapper.domainToDtoList(usuarioRepository.findAll());
    }

    @Override
    public void eliminarUsuarioPorId(Integer id) throws Exception {

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new Exception("El usuario con el ID " + id + " no se encuentra registrado.");
        }

        usuarioRepository.deleteById(id);
    }


}
