package co.edu.usbcali.HollowBank.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usua_id", referencedColumnName = "id",nullable = false)
    private Usuario usuario;

    @Column(length = 19, precision = 2)
    private BigDecimal saldo;

    @Column(nullable = false, length = 15)
    private String tipo;


    /*
    @OneToMany(mappedBy = "idcuentaBancaria")
    private List<Transaccion> transacciones;
*/
}
