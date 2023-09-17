package co.edu.usbcali.HollowBank.dto;

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
public class PagoServicioDTO {
    private Integer id;

    private Integer usuarioId;

    private String servicio;

    private BigDecimal monto;

    private Timestamp fecha;
}
