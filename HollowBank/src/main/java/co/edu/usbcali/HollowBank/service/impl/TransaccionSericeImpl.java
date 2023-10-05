package co.edu.usbcali.HollowBank.service.impl;

import co.edu.usbcali.HollowBank.domain.CuentaBancaria;
import co.edu.usbcali.HollowBank.domain.Transaccion;
import co.edu.usbcali.HollowBank.domain.Usuario;
import co.edu.usbcali.HollowBank.dto.TransaccionDTO;
import co.edu.usbcali.HollowBank.dto.TransaccionDTO;
import co.edu.usbcali.HollowBank.mapper.TransaccionMapper;
import co.edu.usbcali.HollowBank.mapper.TransaccionMapper;
import co.edu.usbcali.HollowBank.repository.CuentaBancariaRepository;
import co.edu.usbcali.HollowBank.repository.TransaccionRepository;
import co.edu.usbcali.HollowBank.repository.UsuarioRepository;
import co.edu.usbcali.HollowBank.service.TransaccionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionSericeImpl implements TransaccionService {
    private final TransaccionRepository transaccionRepository;
    private final UsuarioRepository usuarioRepository;
    private final CuentaBancariaRepository cuentaBancariaRepository;

    public TransaccionSericeImpl(TransaccionRepository transaccionRepository, UsuarioRepository usuarioRepository, CuentaBancariaRepository cuentaBancariaRepository) {
        this.transaccionRepository = transaccionRepository;
        this.usuarioRepository = usuarioRepository;
        this.cuentaBancariaRepository = cuentaBancariaRepository;
    }

    @Override
    public TransaccionDTO guardarNuevaTransaccion(TransaccionDTO transaccionDTO) throws Exception {
        //1. validar DTOs transaccion
        if (transaccionDTO == null) {
            throw new Exception("La cuenta Bancaria es Nula");
        }

        if (transaccionDTO.getMonto() == null || transaccionDTO.getMonto().compareTo(BigDecimal.ZERO) == 0) {
            throw new Exception("Monto Vacio");
        }


        if (transaccionDTO.getTipo() == null || transaccionDTO.getTipo().trim().isEmpty()) {
            throw new Exception("Tipo Vacio");
        }

        if (transaccionDTO.getEstado() == null || transaccionDTO.getEstado().trim().isEmpty()) {
            throw new Exception("Estado vacío");
        }

        if (transaccionDTO.getReferencia() == null || transaccionDTO.getReferencia().trim().isEmpty()) {
            throw new Exception("Referencia vacía");
        }


        //validar si existe la ceuntabancaria
        Optional<CuentaBancaria> cuentaBancariaOptional = cuentaBancariaRepository.findById(transaccionDTO.getCuentaBancariaId());
        if (cuentaBancariaOptional.isEmpty()) {
            throw new Exception(String.format("No se puede realizar la transaccion pues no existe la cuenta bancaria: %s", transaccionDTO.getUsuarioId()));
        }

        //validar si existe el usuario
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(transaccionDTO.getUsuarioId());
        if (usuarioOptional.isEmpty()) {
            throw new Exception(String.format("No se puede realizar el pago pues no existe el usuario con id: %s", transaccionDTO.getUsuarioId()));
        }

        //validar si existe el destinatario
        Optional<Usuario> destinatarioOptional = usuarioRepository.findById(transaccionDTO.getDestinatarioId());
        if (destinatarioOptional.isEmpty()) {
            throw new Exception(String.format("No se puede realizar el pago pues no existe el destinatario con id: %s", transaccionDTO.getUsuarioId()));
        }

        //Validar no exista un transaccion con la misma referencia
        Optional<Transaccion> transaccionOptional = transaccionRepository.findByReferencia(transaccionDTO.getReferencia());
        if (transaccionOptional.isPresent()) {
            throw new Exception(String.format("No pueden haber pagos con la misma referencia: %s", transaccionDTO.getReferencia()));
        }

        //2. registrar el transaccion en DB Mapeando desde DTO hacia DOMAIN
        // convertir el transaccion dto a un objeto del dominio
        Transaccion transaccion = TransaccionMapper.dtoToDomain(transaccionDTO);
        transaccion.setUsuario(usuarioOptional.get());

        transaccion = transaccionRepository.save(transaccion);

        //3. retornar transaccion mapeado en DTO

        return TransaccionMapper.domainToDto(transaccion);
    }

    public List<TransaccionDTO> buscarTodos(){
        return TransaccionMapper.domainToDtoList(transaccionRepository.findAll());
    }
}
