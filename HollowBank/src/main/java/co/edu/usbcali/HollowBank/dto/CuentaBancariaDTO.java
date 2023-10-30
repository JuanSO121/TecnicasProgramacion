package co.edu.usbcali.HollowBank.dto;

import co.edu.usbcali.HollowBank.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaBancariaDTO {
    private Integer id;

    private Integer usuarioId;

    private String tipo;

    private BigDecimal saldo;

    private Integer num_cuenta;

}
