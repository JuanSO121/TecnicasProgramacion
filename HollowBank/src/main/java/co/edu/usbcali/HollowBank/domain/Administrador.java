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

    private String nomAdmin;
    private String apeAdmin;
    private Integer salarioAdmin;
    private String telAdministrador;
/*
    @OneToMany(mappedBy = "idadministrador")
    private List<CuentaBancaria> cuentasBancarias;
  */
}
