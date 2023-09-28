package co.edu.usbcali.HollowBank.service.impl;

import co.edu.usbcali.HollowBank.domain.Administrador;
import co.edu.usbcali.HollowBank.dto.AdministradorDTO;
import co.edu.usbcali.HollowBank.mapper.AdministradorMapper;
import co.edu.usbcali.HollowBank.repository.AdministradorRepository;
import co.edu.usbcali.HollowBank.service.AdministradorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    private final AdministradorRepository administradorRepository;

    public AdministradorServiceImpl(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    @Override
    public AdministradorDTO guardarNuevoAdministrador(AdministradorDTO administradorDTO) throws Exception {
        //1. validar datos del administrador
        if (administradorDTO == null) {
            throw new Exception("el administrador es nulo");
        }
        if (administradorDTO.getNombre() == null || administradorDTO.getNombre().trim().isEmpty()) {
            throw new Exception("nombre vacio");
        }
        if (administradorDTO.getApellido() == null || administradorDTO.getApellido().trim().isEmpty()) {
            throw new Exception("apellido vacio");
        }
        if (administradorDTO.getSalario() == null || administradorDTO.getSalario().compareTo(BigDecimal.ZERO) == 0) {
            throw new Exception("Salario vacío o igual a cero");
        }

        if (administradorDTO.getTelefono() == null || administradorDTO.getTelefono().trim().isEmpty()) {
            throw new Exception("Telefono vacio");
        }


        //registrar administrador en BD mapeando desde DTO hacia DOMIN

        Administrador administrador = AdministradorMapper.dtoToDomain(administradorDTO);

        administrador = administradorRepository.save(administrador);

        //retornar la categoria mappeada a DTO

        administradorDTO = AdministradorMapper.domainToDto(administrador);

        return administradorDTO;
    }
}

