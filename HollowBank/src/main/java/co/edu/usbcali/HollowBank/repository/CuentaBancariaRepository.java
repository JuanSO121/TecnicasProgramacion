package co.edu.usbcali.HollowBank.repository;

import co.edu.usbcali.HollowBank.domain.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Integer> {
}
