package co.edu.usbcali.HollowBank.controller;

import co.edu.usbcali.HollowBank.domain.Usuario;
import co.edu.usbcali.HollowBank.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;


    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/validar")
    public String validarController() {
        return "Controlador Correcto";
    }
    @GetMapping("/obtenerTodos")
    public List<Usuario> obtenerTodos(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

}
