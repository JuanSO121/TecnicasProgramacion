package co.edu.usbcali.HollowBank.repository;

import co.edu.usbcali.HollowBank.domain.Administrador;
import co.edu.usbcali.HollowBank.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    Optional<Administrador> findAdministradorByNombre(String nombre);
}
