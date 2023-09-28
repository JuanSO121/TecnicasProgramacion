package co.edu.usbcali.HollowBank.controller;
import co.edu.usbcali.HollowBank.domain.Administrador;
import co.edu.usbcali.HollowBank.repository.AdministradorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/administrador")

public class AdministradorControler {
    private final AdministradorRepository administradorRepository;

    public AdministradorControler(AdministradorRepository administradorRepository) {
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

}

