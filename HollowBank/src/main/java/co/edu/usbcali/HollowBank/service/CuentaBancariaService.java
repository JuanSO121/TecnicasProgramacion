package co.edu.usbcali.HollowBank.service;

import co.edu.usbcali.HollowBank.dto.CuentaBancariaDTO;

import java.util.List;

public interface CuentaBancariaService {
    CuentaBancariaDTO guardarNuevaCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO) throws Exception;

    List<CuentaBancariaDTO> buscarTodas();
}
