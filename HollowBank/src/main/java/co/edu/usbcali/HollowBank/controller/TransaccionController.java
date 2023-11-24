package co.edu.usbcali.HollowBank.controller;

import co.edu.usbcali.HollowBank.dto.TransaccionDTO;
import co.edu.usbcali.HollowBank.service.TransaccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
            transaccionService.realizarTransferenciaPrueba(cuentaBancariaId, destinatarioId, monto);

            // Mensaje con información sobre la transferencia
            String mensaje = String.format("Transferencia de la cuenta %d a la cuenta %d por un monto de %s realizada correctamente.",
                    cuentaBancariaId, destinatarioId, monto.toString());

            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al realizar la transferencia: " + e.getMessage());
        }
    }


    @PostMapping("/transferencia")
    public ResponseEntity<TransaccionDTO> transferencia(
            @RequestBody Map<String, Object> params) {
        Integer cuentaBancariaId = (Integer) params.get("cuentaBancariaId");
        BigDecimal monto = new BigDecimal(params.get("monto").toString());
        Integer destinatarioId = (Integer) params.get("destinatarioId");

        try {
            TransaccionDTO transaccionDTO = transaccionService.transferencia(cuentaBancariaId, monto, destinatarioId);
            return new ResponseEntity<>(transaccionDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/validar")
    public String validarController() {
        return "Controlador Correcto";
    }
}
