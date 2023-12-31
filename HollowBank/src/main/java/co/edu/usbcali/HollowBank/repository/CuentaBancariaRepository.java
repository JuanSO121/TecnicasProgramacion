package co.edu.usbcali.HollowBank.repository;

import co.edu.usbcali.HollowBank.domain.CuentaBancaria;
import co.edu.usbcali.HollowBank.domain.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Integer> {

    void deleteByUsuarioId(Integer usuarioId);

    List<CuentaBancaria> findByUsuarioId(Integer userId);

    Optional<CuentaBancaria> findCuentaById(Integer cuentaBancariaId);
}
