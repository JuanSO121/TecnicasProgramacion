package co.edu.usbcali.HollowBank.controller;
import co.edu.usbcali.HollowBank.domain.Administrador;
import co.edu.usbcali.HollowBank.dto.AdministradorDTO;
import co.edu.usbcali.HollowBank.repository.AdministradorRepository;
import co.edu.usbcali.HollowBank.service.AdministradorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrador")

public class AdministradorControler {
    private final AdministradorService administradorService;
    private final AdministradorRepository administradorRepository;

    public AdministradorControler(AdministradorService administradorService, AdministradorRepository administradorRepository) {
        this.administradorService = administradorService;
        this.administradorRepository = administradorRepository;
    }

    @GetMapping("/validar")
    public String validarController() {
        return "Controlador Correcto";
    }

    @GetMapping("/obtenerTodos")
    //@ResponseBody
    //Spring Boot automáticamente serializará la lista de objetos Usuario en formato JSON y los enviará como respuesta
    public List<Administrador> obtenerTodos() {
        return administradorRepository.findAll();
    }

    @PostMapping("/guardar")
    public ResponseEntity<AdministradorDTO> guardarAdministrador(@RequestBody AdministradorDTO administradorDTO) throws Exception {
        AdministradorDTO administradorDTO1 = administradorService.guardarNuevoAdministrador(administradorDTO);

        return new ResponseEntity<>(administradorDTO1, HttpStatus.OK);
        }

    }

