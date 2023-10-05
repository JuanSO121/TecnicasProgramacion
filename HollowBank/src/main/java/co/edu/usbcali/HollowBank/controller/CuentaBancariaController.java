package co.edu.usbcali.HollowBank.controller;

import co.edu.usbcali.HollowBank.domain.CuentaBancaria;
import co.edu.usbcali.HollowBank.dto.CuentaBancariaDTO;
import co.edu.usbcali.HollowBank.repository.CuentaBancariaRepository;
import co.edu.usbcali.HollowBank.service.CuentaBancariaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentaBancaria")

public class CuentaBancariaController {

    private final CuentaBancariaRepository cuentaBancariaRepository;
    private final CuentaBancariaService cuentaBancariaService;

    public CuentaBancariaController(CuentaBancariaService cuentaBancariaService,CuentaBancariaRepository cuentaBancariaRepository) {
        this.cuentaBancariaService = cuentaBancariaService;
        this.cuentaBancariaRepository = cuentaBancariaRepository;

    }


    @GetMapping("/validar")
    public String validarController() {
        return "Controlador Correcto";
    }

    @GetMapping("/obtenerTodos")
    //@ResponseBody
    //Spring Boot automáticamente serializará la lista de objetos Usuario en formato JSON y los enviará como respuesta
    public List<CuentaBancaria> obtenerTodos() {
        return cuentaBancariaRepository.findAll();
    }

    @PostMapping("/guardar")
    public ResponseEntity<CuentaBancariaDTO> guardarCuentaBancaria(@RequestBody CuentaBancariaDTO cuentaBancariaDTO) throws Exception {

        CuentaBancariaDTO cuentaBancariaDTO1 = cuentaBancariaService.guardarNuevaCuentaBancaria(cuentaBancariaDTO);

        return new ResponseEntity<>(cuentaBancariaDTO1, HttpStatus.OK);
    }

}


