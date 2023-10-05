package co.edu.usbcali.HollowBank.domain;

import jakarta.persistence.*;
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
@Entity
@Table(name = "transacciones")

public class Transaccion {

    @Id
    @Column(nullable = false, length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cuent_id", referencedColumnName = "id",nullable = false)
    private CuentaBancaria cuentaBancaria;

    @ManyToOne
    @JoinColumn(name = "usua_id", referencedColumnName = "id",nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", referencedColumnName = "id",nullable = false)
    private Usuario destinatario;

    @Column(length = 19, precision = 2, nullable = false)
    private BigDecimal monto;

    @Column(length = 10, nullable = false)
    private Timestamp fecha;

    @Column(length = 10, nullable = false)
    private String tipo;

    @Column(length = 10, nullable = false)
    private String estado;
}
