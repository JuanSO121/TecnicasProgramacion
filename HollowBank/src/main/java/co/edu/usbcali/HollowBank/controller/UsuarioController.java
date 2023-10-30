package co.edu.usbcali.HollowBank.controller;

import co.edu.usbcali.HollowBank.domain.Usuario;
import co.edu.usbcali.HollowBank.dto.UsuarioDTO;
import co.edu.usbcali.HollowBank.mapper.UsuarioMapper;
import co.edu.usbcali.HollowBank.repository.UsuarioRepository;
import co.edu.usbcali.HollowBank.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")

public class UsuarioController {
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<UsuarioDTO>> buscarTodos(){
        return new ResponseEntity<>(usuarioService.buscarTodos(), HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<UsuarioDTO> guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception{
        UsuarioDTO usuarioDTO1 = usuarioService.guardarNuevoUsuario(usuarioDTO);
        return new ResponseEntity<>(usuarioDTO1, HttpStatus.OK);
    }

    @GetMapping("/validar")
    public String validarController() {
        return "Controlador Correcto";
    }

    @GetMapping("/porId/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Integer id) throws Exception{

        Usuario usuario = usuarioRepository.getReferenceById(id);
        UsuarioDTO usuarioDTO = UsuarioMapper.domainToDto(usuario);

        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @GetMapping("/buscarPorIdNew/{id}")
    public ResponseEntity<String> buscarPorIdNew(@PathVariable Integer id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        UsuarioDTO usuarioDTO = UsuarioMapper.domainToDto(usuario);

        String respuesta =
                "Nombre: " + usuarioDTO.getNombre() + ".\n" +
                        "Id: " + usuarioDTO.getId() + ".\n" +
                        "Apellido: " + usuarioDTO.getApellido() + ".\n" +
                        "Dirección: " + usuarioDTO.getDireccion() + ".\n" +
                        "Teléfono: " + usuarioDTO.getTelefono() + ".\n";

        return ResponseEntity.ok(respuesta);

    }

    @DeleteMapping("/eliminarPorId/{id}")
    public ResponseEntity<String> eliminarUsuarioPorId(@PathVariable Integer id) throws Exception {
        usuarioService.eliminarUsuarioPorId(id);
        return ResponseEntity.ok("Usuario con ID " + id + " eliminado correctamente.");
    }

}
