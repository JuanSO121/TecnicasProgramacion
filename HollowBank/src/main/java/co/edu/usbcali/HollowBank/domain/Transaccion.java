package co.edu.usbcali.HollowBank.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer idTransaccion;

    @ManyToOne
    @JoinColumn(name = "idCuentaBancaria", referencedColumnName = "idCuentaBancaria",nullable = false)
    private CuentaBancaria cuentaBancaria;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario",nullable = false)
    private Usuario usuario;

    @Column
    private Integer monto;

    @Column(length = 8)
    private Timestamp fechaTransaccion;

    @Column
    private String tipotransaccion;

    @Column(length = 10)
    private String estadotransaccion;
}
