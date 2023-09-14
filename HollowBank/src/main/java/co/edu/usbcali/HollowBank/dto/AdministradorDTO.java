package co.edu.usbcali.HollowBank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorDTO {
    private Integer idAdministrador;
    private String nomAdmin;
    private String apeAdmin;
    private Integer salarioAdmin;
    private String telAdministrador;
}

