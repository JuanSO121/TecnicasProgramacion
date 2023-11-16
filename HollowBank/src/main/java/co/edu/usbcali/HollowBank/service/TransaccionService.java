package co.edu.usbcali.HollowBank.service;

import co.edu.usbcali.HollowBank.dto.TransaccionDTO;

import java.math.BigDecimal;
import java.util.List;

public interface TransaccionService {
    TransaccionDTO guardarNuevaTransaccion(TransaccionDTO transaccionDTO) throws Exception;
    void realizarTransferenciaPrueba(Integer cuentaOrigenId, Integer cuentaDestinoId, BigDecimal monto) throws Exception;
    List<TransaccionDTO> buscarTodas();

    TransaccionDTO transferencia(Integer cuentaOrigenId, BigDecimal monto, Integer cuentaDestinoId) throws Exception;
}

