package co.edu.usbcali.HollowBank.service;

import co.edu.usbcali.HollowBank.dto.PrestamoDTO;

public interface PrestamoService {
    PrestamoDTO guardarNuevoPrestamo(PrestamoDTO prestamoDTO) throws Exception;
}
