package co.edu.usbcali.HollowBank.service;

import co.edu.usbcali.HollowBank.dto.TransaccionDTO;

public interface TransaccionService {
    public TransaccionDTO guardarNuevaTransaccion(TransaccionDTO transaccionDTO)throws Exception;
}
