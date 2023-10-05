package co.edu.usbcali.HollowBank.controller;

import co.edu.usbcali.HollowBank.domain.Prestamo;
import co.edu.usbcali.HollowBank.repository.PrestamoRepository;
import co.edu.usbcali.HollowBank.service.PrestamoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prestamo")

public class PrestamoController {

    private final PrestamoRepository prestamoRepository;
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoRepository prestamoRepository, PrestamoService prestamoService) {
        this.prestamoRepository = prestamoRepository;
        this.prestamoService = prestamoService;
    }


    @GetMapping("/validar")
    public String validarController() {
        return "Controlador Correcto";
    }

    @GetMapping("/obtenerTodos")
    //@ResponseBody
    //Spring Boot automáticamente serializará la lista de objetos Usuario en formato JSON y los enviará como respuesta
    public List<Prestamo> obtenerTodos(){
        return prestamoRepository.findAll();
    }

}
