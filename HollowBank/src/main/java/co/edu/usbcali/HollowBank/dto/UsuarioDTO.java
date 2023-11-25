package co.edu.usbcali.HollowBank.dto;

import lombok.*;
import co.edu.usbcali.HollowBank.domain.CuentaBancaria;
import java.util.List;

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

    private String password;

    private String newDireccion;
    private String newTel;
    private String newPassword;
    // Getter y Setter para cuentaActualId
    @Getter
    private Integer cuentaActualId;

    @Getter
    private List<CuentaBancariaDTO> cuentasBancarias;

    // Otros métodos y atributos

    public UsuarioDTO(Integer id, String nombre, String apellido, String direccion, String telefono, String password, String newDireccion, String newTel, String newPassword) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.password = password;
        this.newDireccion = newDireccion;
        this.newTel = newTel;
        this.newPassword = newPassword;
        // No inicializamos cuentaActualId aquí
    }
    public void setCuentasBancarias(List<CuentaBancariaDTO> cuentasBancarias) {
        this.cuentasBancarias = cuentasBancarias;
    }

    public void setCuentaActualId(Integer cuentaActualId) {
        this.cuentaActualId = cuentaActualId;
    }

    public List<CuentaBancariaDTO> getCuentasBancarias() {
        return cuentasBancarias;
    }

}
