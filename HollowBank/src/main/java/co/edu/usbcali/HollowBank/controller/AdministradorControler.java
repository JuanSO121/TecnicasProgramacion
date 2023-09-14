package co.edu.usbcali.HollowBank.controller;
import co.edu.usbcali.HollowBank.domain.Administrador;
import co.edu.usbcali.HollowBank.repository.AdministradorRepository;
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

    public List<Administrador> obtenerTodos() {
        List<Administrador> administradores = administradorRepository.findAll();
        return administradores;
    }

}
