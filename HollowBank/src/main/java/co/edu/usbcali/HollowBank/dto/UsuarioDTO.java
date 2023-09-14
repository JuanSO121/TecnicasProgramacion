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
    private Integer idUsuario;

    private String nomUsuario;

    private String apeUsuario;

    private String direUsuario;

    private String telUsuario;

}
