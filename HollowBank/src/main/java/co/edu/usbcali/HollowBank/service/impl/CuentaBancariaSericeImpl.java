package co.edu.usbcali.HollowBank.service.impl;

import co.edu.usbcali.HollowBank.domain.CuentaBancaria;
import co.edu.usbcali.HollowBank.domain.Usuario;
import co.edu.usbcali.HollowBank.dto.CuentaBancariaDTO;
import co.edu.usbcali.HollowBank.mapper.CuentaBancariaMapper;
import co.edu.usbcali.HollowBank.repository.CuentaBancariaRepository;
import co.edu.usbcali.HollowBank.repository.UsuarioRepository;
import co.edu.usbcali.HollowBank.service.CuentaBancariaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaBancariaSericeImpl implements CuentaBancariaService {

    private final CuentaBancariaRepository cuentaBancariaRepository;
    private final UsuarioRepository usuarioRepository;

    public CuentaBancariaSericeImpl(CuentaBancariaRepository cuentaBancariaRepository, UsuarioRepository usuarioRepository) {
        this.cuentaBancariaRepository = cuentaBancariaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public CuentaBancariaDTO guardarNuevaCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO) throws Exception {
        //1. validar datos cuentaBancaria
        if (cuentaBancariaDTO == null){
            throw new Exception("La cuentaBancaria es Nula");
        }

        if (cuentaBancariaDTO.getSaldo() == null || cuentaBancariaDTO.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
            throw new Exception("Debe ingresar el deposito inicial");
        }

        if(cuentaBancariaDTO.getTipo() == null || cuentaBancariaDTO.getTipo().trim().isEmpty()){
            throw new Exception("Debe ingresar el tipo de cuentaBancaria");
        }

        //validar si existe el usuario
        Optional<Usuario> usuarioOptional= usuarioRepository.findById(cuentaBancariaDTO.getUsuarioId());
        if(usuarioOptional.isEmpty()){
            throw new Exception(String.format("No se puede realizar el pago pues no existe el usuario con id: %s",cuentaBancariaDTO.getUsuarioId()));
        }

        //2. registrar el cuentaBancaria en DB Mapeando desde DTO hacia DOMAIN
        // convertir el cuentaBancaria dto a un objeto del dominio
        CuentaBancaria cuentaBancaria = CuentaBancariaMapper.dtoToDomain(cuentaBancariaDTO);
        cuentaBancaria.setUsuario(usuarioOptional.get());

        cuentaBancaria = cuentaBancariaRepository.save(cuentaBancaria);

        //3. retornar cuentaBancaria mapeado en DTO

        return CuentaBancariaMapper.domainToDto(cuentaBancaria);
    }

    public List<CuentaBancariaDTO> buscarTodas(){
        return CuentaBancariaMapper.domainToDtoList(cuentaBancariaRepository.findAll());
    }


}
