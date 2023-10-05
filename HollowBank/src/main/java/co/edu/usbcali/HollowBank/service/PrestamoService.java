package co.edu.usbcali.HollowBank.service;

import co.edu.usbcali.HollowBank.dto.PagoServicioDTO;
import co.edu.usbcali.HollowBank.dto.PrestamoDTO;

import java.util.List;

public interface PrestamoService {
    PrestamoDTO guardarNuevoPrestamo(PrestamoDTO prestamoDTO) throws Exception;
    List<PrestamoDTO> buscarTodos();
}
