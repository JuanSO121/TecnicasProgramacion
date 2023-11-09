package co.edu.usbcali.HollowBank.controller;

import co.edu.usbcali.HollowBank.dto.TransaccionDTO;
import co.edu.usbcali.HollowBank.service.TransaccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {
    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @PostMapping("/buscarTodos")
    public ResponseEntity<List<TransaccionDTO>> buscarTodos() {
        return new ResponseEntity<>(transaccionService.buscarTodas(), HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<TransaccionDTO> guardarTransaccion(@RequestBody TransaccionDTO transaccionDTO) throws Exception {
        TransaccionDTO transaccionDTO1 = transaccionService.guardarNuevaTransaccion(transaccionDTO);
        return new ResponseEntity<>(transaccionDTO1, HttpStatus.OK);
    }

    @PostMapping("/transferir/{cuentaBancariaId}/{destinatarioId}/{monto}")
    public ResponseEntity<String> realizarTransferencia(
            @PathVariable Integer cuentaBancariaId,
            @PathVariable Integer destinatarioId,
            @PathVariable BigDecimal monto
    ) {
        try {
            transaccionService.realizarTransferencia(cuentaBancariaId, destinatarioId, monto);
            return ResponseEntity.ok("Transferencia realizada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al realizar la transferencia: " + e.getMessage());
        }
    }


    @GetMapping("/validar")
    public String validarController() {
        return "Controlador Correcto";
    }
}
