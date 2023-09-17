package co.edu.usbcali.HollowBank.repository;

import co.edu.usbcali.HollowBank.domain.Prestamo;
import co.edu.usbcali.HollowBank.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
}
