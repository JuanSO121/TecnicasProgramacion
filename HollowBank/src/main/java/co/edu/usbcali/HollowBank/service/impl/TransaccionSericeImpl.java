package co.edu.usbcali.HollowBank.service.impl;

import co.edu.usbcali.HollowBank.domain.CuentaBancaria;
import co.edu.usbcali.HollowBank.domain.Transaccion;
import co.edu.usbcali.HollowBank.domain.Usuario;
import co.edu.usbcali.HollowBank.dto.TransaccionDTO;
import co.edu.usbcali.HollowBank.mapper.TransaccionMapper;
import co.edu.usbcali.HollowBank.repository.CuentaBancariaRepository;
import co.edu.usbcali.HollowBank.repository.TransaccionRepository;
import co.edu.usbcali.HollowBank.repository.UsuarioRepository;
import co.edu.usbcali.HollowBank.service.TransaccionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


        //validar si existe la cuentabancaria
        Optional<CuentaBancaria> cuentaBancariaOptional = cuentaBancariaRepository.findById(transaccionDTO.getCuentaBancariaId());
        if (cuentaBancariaOptional.isEmpty()) {
            throw new Exception(String.format("No se puede realizar la transacción pues no existe la cuenta bancaria: %s", transaccionDTO.getCuentaBancariaId()));
        }

        //validar si existe el usuario
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(transaccionDTO.getUsuarioId());
        if (usuarioOptional.isEmpty()) {
            throw new Exception(String.format("No se puede realizar el pago pues no existe el usuario con id: %s", transaccionDTO.getUsuarioId()));
        }

        //validar si existe el destinatario
        Optional<Usuario> destinatarioOptional = usuarioRepository.findById(transaccionDTO.getDestinatarioId());
        if (destinatarioOptional.isEmpty()) {
            throw new Exception(String.format("No se puede realizar el pago pues no existe el destinatario con id: %s", transaccionDTO.getDestinatarioId()));
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
        transaccion.setCuentaBancaria(cuentaBancariaOptional.get());
        transaccion.setDestinatario(destinatarioOptional.get());

        transaccion = transaccionRepository.save(transaccion);

        //3. retornar transaccion mapeado en DTO

        return TransaccionMapper.domainToDto(transaccion);
    }

    public List<TransaccionDTO> buscarTodas(){
        return TransaccionMapper.domainToDtoList(transaccionRepository.findAll());
    }

    @Override
    public void realizarTransferenciaPrueba(Integer cuentaBancariaId, Integer destinatarioId, BigDecimal monto) throws Exception {
        Optional<CuentaBancaria> cuentaOrigenOptional = cuentaBancariaRepository.findById(cuentaBancariaId);
        Optional<CuentaBancaria> cuentaDestinoOptional = cuentaBancariaRepository.findById(destinatarioId);

        if (cuentaOrigenOptional.isEmpty() || cuentaDestinoOptional.isEmpty()) {
            throw new Exception("Cuentas de origen o destino no encontradas.");
        }

        CuentaBancaria cuentaOrigen = cuentaOrigenOptional.get();
        CuentaBancaria cuentaDestino = getCuentaDestino(destinatarioId);

        // Validar que el saldo sea suficiente para la transferencia
        if (cuentaOrigen.getSaldo().compareTo(monto) < 0) {
            throw new Exception("Saldo insuficiente para realizar la transferencia.");
        }

        // Validar que la cuenta de origen y destino son diferentes
        if (cuentaOrigen.getId().equals(cuentaDestino.getId())) {
            throw new Exception("La cuenta de origen y destino deben ser diferentes.");
        }

        // Realizar la transferencia
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(monto));
        cuentaBancariaRepository.save(cuentaOrigen);

        cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(monto));
        cuentaBancariaRepository.save(cuentaDestino);
    }

    private CuentaBancaria getCuentaDestino(Integer destinatarioId) throws Exception {
        return cuentaBancariaRepository.findById(destinatarioId)
                .orElseThrow(() -> new Exception(String.format("No se puede realizar la transacción pues no existe la cuenta bancaria de destino: %s", destinatarioId)));
    }

    @Override
    @Transactional
    public TransaccionDTO transferencia(Integer cuentaBancariaId, BigDecimal monto, Integer destinatarioId) throws Exception {
        // Obtener la cuenta bancaria del usuario que realiza la transferencia
        CuentaBancaria cuentaBancariaOrigen = cuentaBancariaRepository.findById(cuentaBancariaId)
                .orElseThrow(() -> new Exception("No se puede realizar la transferencia, la cuenta bancaria del usuario de origen no existe."));

        // Obtener la cuenta bancaria del destinatario
        CuentaBancaria cuentaBancariaDestino = cuentaBancariaRepository.findById(destinatarioId)
                .orElseThrow(() -> new Exception(String.format("No se puede realizar la transferencia, la cuenta bancaria del destinatario con ID %s no existe.", destinatarioId)));

        // Verificar que haya saldo suficiente en la cuenta de origen
        if (cuentaBancariaOrigen.getSaldo().compareTo(monto) < 0) {
            throw new Exception("No hay saldo suficiente para realizar la transferencia.");
        }

        // Actualizar saldos de cuentas
        cuentaBancariaOrigen.setSaldo(cuentaBancariaOrigen.getSaldo().subtract(monto));
        cuentaBancariaDestino.setSaldo(cuentaBancariaDestino.getSaldo().add(monto));

        // Guardar transacción de transferencia
        Transaccion transaccion = new Transaccion();
        transaccion.setMonto(monto);
        transaccion.setTipo("TRANSFERENCIA");
        transaccion.setEstado("COMPLETADA");
        transaccion.setReferencia("Transferencia de " + cuentaBancariaOrigen.getId() + " a " + cuentaBancariaDestino.getId());
        transaccion.setCuentaBancaria(cuentaBancariaOrigen);
        transaccion.setDestinatario(cuentaBancariaDestino.getUsuario());
        transaccionRepository.save(transaccion);

        // Actualizar las cuentas bancarias en la base de datos
        cuentaBancariaRepository.saveAll(List.of(cuentaBancariaOrigen, cuentaBancariaDestino));

        // Retornar la transacción de transferencia
        return TransaccionMapper.domainToDto(transaccion);
    }




}
