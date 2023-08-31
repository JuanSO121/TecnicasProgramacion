package co.edu.usbcali.HollowBank.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(nullable = false, length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false, length = 50)
    private String nomUsuario;

    @Column(nullable = false, length = 50)
    private String apeUsuario;

    @Column(nullable = false, length = 50)
    private String direUsuario;

    @Column(nullable = false, length = 10)
    private String telUsuario;
/*
    @OneToMany(mappedBy = "usuario")
    private List<CuentaBancaria> cuentasBancarias;

    @OneToMany(mappedBy = "usuario")
    private List<Transaccion> transacciones;

 */
}
