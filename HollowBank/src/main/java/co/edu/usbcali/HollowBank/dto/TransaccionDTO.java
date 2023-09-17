package co.edu.usbcali.HollowBank.dto;


import co.edu.usbcali.HollowBank.domain.CuentaBancaria;
import co.edu.usbcali.HollowBank.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionDTO {
    private Integer id;

    private Integer cuentaBancariaId;

    private Integer usuarioId;

    private BigDecimal monto;

    private Timestamp fecha;

    private String tipo;

    private String estado;

}
