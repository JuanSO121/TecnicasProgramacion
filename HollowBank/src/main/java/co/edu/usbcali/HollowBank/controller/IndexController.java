package co.edu.usbcali.HollowBank.controller;

import co.edu.usbcali.HollowBank.dto.CuentaBancariaDTO;
import co.edu.usbcali.HollowBank.dto.TransaccionDTO;
import co.edu.usbcali.HollowBank.dto.UsuarioDTO;
import co.edu.usbcali.HollowBank.service.CuentaBancariaService;
import co.edu.usbcali.HollowBank.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class IndexController {
    private final UsuarioService usuarioService;
    private final CuentaBancariaService cuentaBancariaService;

    public IndexController(UsuarioService usuarioService, CuentaBancariaService cuentaBancariaService) {
        this.usuarioService = usuarioService;
        this.cuentaBancariaService = cuentaBancariaService;
    }

    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO()); // Cambiamos el nombre del objeto a "usuarioDTO"
        return "index";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("usuarioDTO") @Valid UsuarioDTO usuarioDTO,
                              BindingResult bindingResult,
                              HttpSession session,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        try {
            // Verificamos si hay errores de validación
            if (bindingResult.hasErrors()) {
                return "index";
            }

            // Verifica si el usuario y la contraseña son válidos
            if (usuarioService.existeUsuarioPorIdYContrasena(usuarioDTO.getId(), usuarioDTO.getPassword())) {
                // Almacena el ID del usuario en la sesión
                session.setAttribute("userId", usuarioDTO.getId());
                return "redirect:/home";
            } else {
                model.addAttribute("error", "Inicio de sesión fallido. Verifica tus credenciales.");
                return "index";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar el inicio de sesión.");
            return "error";
        }
    }


    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId != null) {
            // Realiza otras consultas o lógica según sea necesario usando el userId

            // Añade datos al modelo para ser utilizados en la página
            model.addAttribute("userId", userId);

            return "home";
        } else {
            // Manejar el caso en el que el atributo de sesión userId no esté presente
            model.addAttribute("error", "El atributo de sesión userId no está presente.");
            return "error";
        }
    }



    @GetMapping("/cliente")
    public String mostrarPaginaCliente(HttpSession session, Model model) {
        try {
            // Obtener el usuario de la sesión
            UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");

            // Verificar si el usuario está en la sesión
            if (usuarioDTO != null) {
                // Obtener la información de las cuentas bancarias asociadas al usuario
                List<CuentaBancariaDTO> cuentasBancarias = cuentaBancariaService.obtenerCuentasPorUsuario(usuarioDTO.getId());

                // Agregar las cuentas bancarias al modelo
                model.addAttribute("cuentasBancarias", cuentasBancarias);

                return "cliente";
            } else {
                // Manejar el caso en el que el usuario no esté en la sesión
                return "redirect:/login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar la página del cliente.");
            return "error";
        }
    }

    @GetMapping("/borrarCuenta/{cuentaId}")
    public String borrarCuenta(@PathVariable Long cuentaId, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            // Lógica para borrar la cuenta con el id especificado
            cuentaBancariaService.eliminarCuentasPorUsuario(Math.toIntExact(cuentaId));

            // Redireccionar a la página de cliente con mensaje de éxito
            redirectAttributes.addFlashAttribute("successMessage", "La cuenta se ha borrado exitosamente.");
            return "redirect:/cliente";
        } catch (Exception e) {
            // Manejar el error y redireccionar a la página de cliente con mensaje de error
            redirectAttributes.addFlashAttribute("errorMessage", "Error al borrar la cuenta.");
            return "redirect:/cliente";
        }
    }
}
