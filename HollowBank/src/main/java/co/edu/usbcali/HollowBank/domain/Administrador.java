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
@Table(name = "administradores")
public class Administrador {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idAdministrador;

    @Column(nullable = false, length = 50)
    private String nomAdmin;

    @Column(nullable = false, length = 50)
    private String apeAdmin;

    @Column
    private Integer salarioAdmin;

    @Column(nullable = false, length = 10)
    private String telAdministrador;
/*
    @OneToMany(mappedBy = "idadministrador")
    private List<CuentaBancaria> cuentasBancarias;
  */
}
