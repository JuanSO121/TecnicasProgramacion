package co.edu.usbcali.HollowBank.service;

import co.edu.usbcali.HollowBank.dto.PagoServicioDTO;
import co.edu.usbcali.HollowBank.dto.PrestamoDTO;

import java.util.List;

public interface PagoServicioService {
    public PagoServicioDTO guardarNuevoPagoServicio(PagoServicioDTO pagoServicioDTO) throws Exception;

    List<PagoServicioDTO> buscarTodos();

}
