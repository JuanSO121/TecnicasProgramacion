package co.edu.usbcali.HollowBank.dto;

import co.edu.usbcali.HollowBank.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaBancariaDTO {
    private Integer idCuentaBancaria;
    private String idUsuario;
    private String nomUsuario;
    private String apeUsuario;
    private Integer saldo;

    private Integer AdministradorId;
}
