package co.edu.usbcali.HollowBank.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionDTO {
    private Integer idTransaccion;

    private Integer CuentaBancariaId;

    private Integer UsuarioId;

    private Integer monto;

    private Timestamp fechaTransaccion;

    private String tipotransaccion;

    private String estadotransaccion;

}
