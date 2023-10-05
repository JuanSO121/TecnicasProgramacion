package co.edu.usbcali.HollowBank.service;

import co.edu.usbcali.HollowBank.dto.PagoServicioDTO;
import co.edu.usbcali.HollowBank.dto.TransaccionDTO;

import java.util.List;

public interface TransaccionService {
    public TransaccionDTO guardarNuevaTransaccion(TransaccionDTO transaccionDTO)throws Exception;
    List<TransaccionDTO> buscarTodos();
}
