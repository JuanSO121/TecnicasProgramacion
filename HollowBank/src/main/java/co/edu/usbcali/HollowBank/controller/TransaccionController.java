package co.edu.usbcali.HollowBank.controller;

import co.edu.usbcali.HollowBank.domain.Transaccion;
import co.edu.usbcali.HollowBank.dto.TransaccionDTO;
import co.edu.usbcali.HollowBank.repository.TransaccionRepository;
import co.edu.usbcali.HollowBank.service.TransaccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaccion")

public class TransaccionController {
    private final TransaccionRepository transaccionRepository;
    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService,TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
        this.transaccionService = transaccionService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<TransaccionDTO> guardarTransaccion(@RequestBody TransaccionDTO transaccionDTO) throws Exception {
        TransaccionDTO transaccionDTO1 = transaccionService.guardarNuevaTransaccion(transaccionDTO);

        return new ResponseEntity<>(transaccionDTO1, HttpStatus.OK);
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
