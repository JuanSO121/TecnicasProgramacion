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
@Table(name = "cuentasbancarias")

public class CuentaBancaria {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCuentaBancaria;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario",nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 50)
    private String nomUsuario;

    @Column(nullable = false, length = 50)
    private String apeUsuario;

    @Column
    private Integer saldo;
/*
    @OneToMany(mappedBy = "idcuentaBancaria")
    private List<Transaccion> transacciones;
*/
    @ManyToOne
    @JoinColumn(name = "idAdministrador", referencedColumnName = "idAdministrador",nullable = false)
    private Administrador administrador;
}
