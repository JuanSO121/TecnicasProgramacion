package co.edu.usbcali.HollowBank.service.impl;

import co.edu.usbcali.HollowBank.domain.Prestamo;
import co.edu.usbcali.HollowBank.domain.Prestamo;
import co.edu.usbcali.HollowBank.domain.Usuario;
import co.edu.usbcali.HollowBank.dto.PrestamoDTO;
import co.edu.usbcali.HollowBank.dto.PrestamoDTO;
import co.edu.usbcali.HollowBank.mapper.PrestamoMapper;
import co.edu.usbcali.HollowBank.mapper.PrestamoMapper;
import co.edu.usbcali.HollowBank.mapper.PrestamoMapper;
import co.edu.usbcali.HollowBank.repository.PrestamoRepository;
import co.edu.usbcali.HollowBank.repository.UsuarioRepository;
import co.edu.usbcali.HollowBank.service.PrestamoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoSericeImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;

    public PrestamoSericeImpl(PrestamoRepository prestamoRepository, UsuarioRepository usuarioRepository) {
        this.prestamoRepository = prestamoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public PrestamoDTO guardarNuevoPrestamo(PrestamoDTO prestamoDTO) throws Exception {
        //1. validar datos prestamo
        if (prestamoDTO == null){
            throw new Exception("El prestamo es Nulo");
        }

        if (prestamoDTO.getMonto() == null || prestamoDTO.getMonto().compareTo(BigDecimal.ZERO) == 0) {
            throw new Exception("Debe ingresar el monto a desembolzar");
        }

        Timestamp fechaTimestamp = prestamoDTO.getFecha();

        if (fechaTimestamp == null) {
            throw new Exception("Fecha nula");
        }

// Comprobar si es un Timestamp válido (por ejemplo, no es negativo)
        if (fechaTimestamp.getTime() < 0) {
            throw new Exception("Fecha no válida");
        }

        if(prestamoDTO.getEstado() == null || prestamoDTO.getEstado().trim().isEmpty()){
            throw new Exception("Debe ingresar el estado del prestamo");
        }

        if (prestamoDTO.getReferencia() == null || prestamoDTO.getReferencia().trim().isEmpty()) {
            throw new Exception("debe ingresar la Referencia del prestamo");
        }

        //validar si existe el usuario
        Optional<Usuario> usuarioOptional= usuarioRepository.findById(prestamoDTO.getUsuarioId());
        if(usuarioOptional.isEmpty()){
            throw new Exception(String.format("No se puede realizar el pago pues no existe el usuario con id: %s",prestamoDTO.getUsuarioId()));
        }

        //Validar no exista un prestamo con la misma referencia
        Optional<Prestamo> prestamoOptional = prestamoRepository.findByReferencia(prestamoDTO.getReferencia());
        if(prestamoOptional.isPresent()){
            throw new Exception(String.format("No pueden haber pagos con la misma referencia: %s",prestamoDTO.getReferencia()));
        }

        //2. registrar el prestamo en DB Mapeando desde DTO hacia DOMAIN
        // convertir el prestamo dto a un objeto del dominio
        Prestamo prestamo = PrestamoMapper.dtoToDomain(prestamoDTO);
        prestamo.setUsuario(usuarioOptional.get());

        prestamo = prestamoRepository.save(prestamo);

        //3. retornar prestamo mapeado en DTO

        return PrestamoMapper.domainToDto(prestamo);
    }

    public List<PrestamoDTO> buscarTodos(){
        return PrestamoMapper.domainToDtoList(prestamoRepository.findAll());
    }
}
