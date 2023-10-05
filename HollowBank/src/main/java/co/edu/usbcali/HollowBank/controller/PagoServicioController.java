package co.edu.usbcali.HollowBank.controller;

import co.edu.usbcali.HollowBank.domain.PagoServicio;
import co.edu.usbcali.HollowBank.repository.PagoServicioRepository;
import co.edu.usbcali.HollowBank.service.PagoServicioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pago-servicio")

public class PagoServicioController {

    private final PagoServicioRepository pagoServicioRepository;
    private final PagoServicioService pagoServicioService;

    public PagoServicioController(PagoServicioRepository pagoServicioRepository, PagoServicioService pagoServicioService) {
        this.pagoServicioRepository = pagoServicioRepository;
        this.pagoServicioService = pagoServicioService;
    }


    @GetMapping("/validar")
    public String validarController() {
        return "Controlador Correcto";
    }

    @GetMapping("/obtenerTodos")
    //@ResponseBody
    //Spring Boot automáticamente serializará la lista de objetos Usuario en formato JSON y los enviará como respuesta
    public List<PagoServicio> obtenerTodos(){
        return pagoServicioRepository.findAll();
    }


}
