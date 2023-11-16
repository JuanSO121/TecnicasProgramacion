package co.edu.usbcali.HollowBank.repository;

import co.edu.usbcali.HollowBank.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findUsuarioByNombre(String nombre);

    Optional<Usuario> findByIdAndPassword(Integer id, String password);
}
