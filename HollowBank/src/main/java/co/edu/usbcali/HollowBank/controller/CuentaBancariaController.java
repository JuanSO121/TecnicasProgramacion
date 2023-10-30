package co.edu.usbcali.HollowBank.controller;

import co.edu.usbcali.HollowBank.dto.CuentaBancariaDTO;
import co.edu.usbcali.HollowBank.service.CuentaBancariaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentaBancaria")

public class CuentaBancariaController {
    private final CuentaBancariaService cuentaBancariaService;

    public CuentaBancariaController(CuentaBancariaService cuentaBancariaService) {
        this.cuentaBancariaService = cuentaBancariaService;
    }

    @PostMapping("/buscarTodas")
    public ResponseEntity<List<CuentaBancariaDTO>> buscarTodas(){
        return new ResponseEntity<>(cuentaBancariaService.buscarTodas(), HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<CuentaBancariaDTO> guardarCuentaBancaria(@RequestBody CuentaBancariaDTO cuentaBancariaDTO) throws Exception{
        CuentaBancariaDTO cuentaBancariaDTO1 = cuentaBancariaService.guardarNuevaCuentaBancaria(cuentaBancariaDTO);
        return new ResponseEntity<>(cuentaBancariaDTO1, HttpStatus.OK);
    }

    @GetMapping("/validar")
    public String validarController() {
        return "Controlador Correcto";
    }



}
