package co.edu.usbcali.HollowBank.service;

import co.edu.usbcali.HollowBank.dto.CuentaBancariaDTO;

import java.util.List;

public interface CuentaBancariaService {
    CuentaBancariaDTO guardarNuevaCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO) throws Exception;

    List<CuentaBancariaDTO> buscarTodas();
    void eliminarCuentasPorUsuario(Integer userId) throws Exception;

    boolean cuentaPerteneceUsuario(Integer usuarioId, Integer cuentaId);

    List<CuentaBancariaDTO> obtenerCuentasPorUsuario(Integer id);

    CuentaBancariaDTO obtenerCuentaBancariaPorId(Integer cuentaId) throws Exception;

}
