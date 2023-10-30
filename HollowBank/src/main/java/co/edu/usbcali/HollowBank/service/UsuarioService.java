package co.edu.usbcali.HollowBank.service;

import co.edu.usbcali.HollowBank.dto.UsuarioDTO;
import co.edu.usbcali.HollowBank.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    public UsuarioDTO guardarNuevoUsuario(UsuarioDTO usuarioDTO) throws Exception;

    List<UsuarioDTO> buscarTodos();

    void eliminarUsuarioPorId(Integer id) throws Exception;

}
