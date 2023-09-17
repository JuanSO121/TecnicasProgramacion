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
@Table(name = "administradores")

public class Administrador {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column
    private BigDecimal salario;

    @Column(nullable = false, length = 10)
    private String telefono;
/*
    @OneToMany(mappedBy = "idadministrador")
    private List<CuentaBancaria> cuentasBancarias;
  */
}
