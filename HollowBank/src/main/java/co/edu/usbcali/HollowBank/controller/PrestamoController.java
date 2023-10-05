package co.edu.usbcali.HollowBank.controller;

import co.edu.usbcali.HollowBank.dto.PrestamoDTO;
import co.edu.usbcali.HollowBank.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamo")

public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping("/buscarTodos")
    public ResponseEntity<List<PrestamoDTO>> buscarTodos(){
        return new ResponseEntity<>(prestamoService.buscarTodos(), HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<PrestamoDTO> guardarPrestamo(@RequestBody PrestamoDTO prestamoDTO) throws Exception{
        PrestamoDTO prestamoDTO1 = prestamoService.guardarNuevoPrestamo(prestamoDTO);
        return new ResponseEntity<>(prestamoDTO1, HttpStatus.OK);
    }

    @GetMapping("/validar")
    public String validarController() {
        return "Controlador Correcto";
    }

}
