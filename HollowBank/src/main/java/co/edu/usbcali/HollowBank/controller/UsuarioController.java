package co.edu.usbcali.HollowBank.controller;

import co.edu.usbcali.HollowBank.domain.Usuario;
import co.edu.usbcali.HollowBank.dto.UsuarioDTO;
import co.edu.usbcali.HollowBank.mapper.UsuarioMapper;
import co.edu.usbcali.HollowBank.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    //@ResponseBody
    //Spring Boot automáticamente serializará la lista de objetos Usuario en formato JSON y los enviará como respuesta
    public List<Usuario> obtenerTodos(){
        return usuarioRepository.findAll();
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
}
