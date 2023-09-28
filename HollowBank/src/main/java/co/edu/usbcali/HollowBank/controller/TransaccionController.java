package co.edu.usbcali.HollowBank.controller;

import co.edu.usbcali.HollowBank.domain.Transaccion;
import co.edu.usbcali.HollowBank.repository.TransaccionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaccion")

public class TransaccionController {
    private final TransaccionRepository transaccionRepository;

    public TransaccionController(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }


    @GetMapping("/validar")
    public String validarController() {
        return "Controlador Correcto";
    }

    @GetMapping("/obtenerTodos")
    //@ResponseBody
    //Spring Boot automáticamente serializará la lista de objetos Usuario en formato JSON y los enviará como respuesta
    public List<Transaccion> obtenerTodos() {
        return transaccionRepository.findAll();
    }

}
