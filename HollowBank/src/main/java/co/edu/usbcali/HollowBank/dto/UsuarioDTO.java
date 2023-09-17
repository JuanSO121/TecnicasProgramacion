package co.edu.usbcali.HollowBank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Integer id;

    private String nombre;

    private String apellido;

    private String direccion;

    private String telefono;

}
