package co.edu.usbcali.HollowBank.repository;

import co.edu.usbcali.HollowBank.domain.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion,Integer> {
}
