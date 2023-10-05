package co.edu.usbcali.HollowBank.service.impl;

import co.edu.usbcali.HollowBank.domain.Usuario;
import co.edu.usbcali.HollowBank.domain.PagoServicio;
import co.edu.usbcali.HollowBank.dto.PagoServicioDTO;
import co.edu.usbcali.HollowBank.dto.PrestamoDTO;
import co.edu.usbcali.HollowBank.mapper.PagoServicioMapper;
import co.edu.usbcali.HollowBank.repository.PagoServicioRepository;
import co.edu.usbcali.HollowBank.repository.UsuarioRepository;
import co.edu.usbcali.HollowBank.service.PagoServicioService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class PagoServiciosSericeImpl implements PagoServicioService {

    private final PagoServicioRepository pagoServicioRepository;
    private final UsuarioRepository usuarioRepository;

    public PagoServiciosSericeImpl(PagoServicioRepository pagoServicioRepository, UsuarioRepository usuarioRepository) {
        this.pagoServicioRepository = pagoServicioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public PagoServicioDTO guardarNuevoPagoServicio(PagoServicioDTO pagoServicioDTO) throws Exception {
        //1. validar datos pagoServicio
        if (pagoServicioDTO == null){
            throw new Exception("El pagoServicioDTO es Nulo");
        }

        if(pagoServicioDTO.getServicio() == null || pagoServicioDTO.getServicio().isBlank()){
            throw new Exception("Debe ingresar el Servicio al pago");
        }

        Timestamp fechaTimestamp = pagoServicioDTO.getFecha();

        if (fechaTimestamp == null) {
            throw new Exception("Fecha nula");
        }

// Comprobar si es un Timestamp válido (por ejemplo, no es negativo)
        if (fechaTimestamp.getTime() < 0) {
            throw new Exception("Fecha no válida");
        }

        if (pagoServicioDTO.getMonto() == null || pagoServicioDTO.getMonto().compareTo(BigDecimal.ZERO) == 0) {
            throw new Exception("Debe haber fondos para realizar el pago");
        }

        if (pagoServicioDTO.getReferencia() == null || pagoServicioDTO.getReferencia().isBlank()) {
            throw new Exception("referencia no valida");
        }

        //Validar no exista un pago con la misma referencia
        Optional<PagoServicio> pagoServicioOptional = pagoServicioRepository.findByReferencia(pagoServicioDTO.getReferencia());
        if(pagoServicioOptional.isPresent()){
            throw new Exception(String.format("No pueden haber pagos con la misma referencia: %s",pagoServicioDTO.getReferencia()));
        }

        //validar si existe el usuario
        Optional<Usuario> usuarioOptional= usuarioRepository.findById(pagoServicioDTO.getUsuarioId());
        if(usuarioOptional.isEmpty()){
            throw new Exception(String.format("No se puede realizar el pago pues no existe el usuario con id: %s",pagoServicioDTO.getUsuarioId()));
        }

        //2. registrar el pagoServicio en DB Mapeando desde DTO hacia DOMAIN
        // convertir el pagoServicio dto a un objeto del dominio
        PagoServicio pagoServicio = PagoServicioMapper.dtoToDomain(pagoServicioDTO);
        pagoServicio.setUsuario(usuarioOptional.get());

        pagoServicio = pagoServicioRepository.save(pagoServicio);

        //3. retornar pagoServicio mapeado en DTO

        return PagoServicioMapper.domainToDto(pagoServicio);
    }

    public List<PagoServicioDTO> buscarTodos(){
        return PagoServicioMapper.domainToDtoList(pagoServicioRepository.findAll());
    }
}
