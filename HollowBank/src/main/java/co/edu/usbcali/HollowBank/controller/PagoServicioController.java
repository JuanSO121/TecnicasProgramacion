package co.edu.usbcali.HollowBank.controller;

import co.edu.usbcali.HollowBank.dto.PagoServicioDTO;
import co.edu.usbcali.HollowBank.service.PagoServicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pago-servicio")

public class PagoServicioController {

    private final PagoServicioService pagoServicioService;

    public PagoServicioController(PagoServicioService pagoServicioService) {
        this.pagoServicioService = pagoServicioService;
    }

    @PostMapping("/buscarTodos")
    public ResponseEntity<List<PagoServicioDTO>>buscarTodos(){
        return new ResponseEntity<>(pagoServicioService.buscarTodos(),HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<PagoServicioDTO> guardarPagoServicio(@RequestBody PagoServicioDTO pagoServicioDTO) throws Exception{
        PagoServicioDTO pagoServicioDTO1 = pagoServicioService.guardarNuevoPagoServicio(pagoServicioDTO);
        return new ResponseEntity<>(pagoServicioDTO1, HttpStatus.OK);
    }

}
