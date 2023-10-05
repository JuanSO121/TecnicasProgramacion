package co.edu.usbcali.HollowBank.service.impl;

import co.edu.usbcali.HollowBank.domain.Administrador;
import co.edu.usbcali.HollowBank.domain.Administrador;
import co.edu.usbcali.HollowBank.dto.AdministradorDTO;
import co.edu.usbcali.HollowBank.mapper.AdministradorMapper;
import co.edu.usbcali.HollowBank.repository.AdministradorRepository;
import co.edu.usbcali.HollowBank.service.AdministradorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

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

        if (administradorDTO.getTelefono() == null || administradorDTO.getTelefono().trim().isEmpty()) {
            throw new Exception("Telefono vacio");
        }

        Optional<Administrador> administradorPorNombre = administradorRepository.findAdministradorByNombre(administradorDTO.getNombre());
        if(administradorPorNombre.isPresent()){
            throw new Exception(String.format("El administrador con el nombre %s ya se encuentra registrado",
                    administradorDTO.getNombre()));
        }


        //registrar administrador en BD mapeando desde DTO hacia DOMIN

        Administrador administrador = AdministradorMapper.dtoToDomain(administradorDTO);

        administrador = administradorRepository.save(administrador);

        //retornar la categoria mappeada a DTO

        administradorDTO = AdministradorMapper.domainToDto(administrador);

        return administradorDTO;
    }
}

