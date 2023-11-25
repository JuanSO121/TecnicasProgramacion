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
    public String showHomePage(HttpSession session, Model model) throws Exception {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId != null) {
            // Obtén el usuario y las cuentas bancarias asociadas
            UsuarioDTO usuarioDTO = usuarioService.obtenerUsuarioPorId(userId);
            List<CuentaBancariaDTO> cuentasBancarias = cuentaBancariaService.obtenerCuentasPorUsuario(userId);

            // Agrega los datos al modelo
            model.addAttribute("usuario", usuarioDTO);
            model.addAttribute("cuentasBancarias", cuentasBancarias);

            return "home";
        } else {
            // Manejar el caso en el que el atributo de sesión userId no esté presente
            model.addAttribute("error", "El atributo de sesión userId no está presente.");
            return "error";
        }
    }

    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "signup";
    }

    @PostMapping("/signup")
    public String handleSignup(@Valid @ModelAttribute UsuarioDTO usuarioDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        // ...
        if (bindingResult.hasErrors()) {
            // Si hay errores de validación, vuelve a mostrar el formulario con los errores
            return "signup";
        }

        try {
            // Guardar el usuario en la base de datos (usando el servicio de usuario)
            usuarioService.guardarNuevoUsuario(usuarioDTO);

            // Redireccionar a la página de inicio de sesión con un mensaje de éxito
            redirectAttributes.addFlashAttribute("successMessage", "¡Registro exitoso! Por favor, inicia sesión.");
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar el registro.");
            return "signup";
        }
    }



    @GetMapping("/crear-cuenta")
    public String showCrearCuentaPage(Model model) {
        model.addAttribute("cuentaBancariaDTO", new CuentaBancariaDTO());
        return "crearCuenta";
    }

    @PostMapping("/crear-cuenta")
    public String handleCrearCuenta(@Valid @ModelAttribute CuentaBancariaDTO cuentaBancariaDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            // Verificamos si hay errores de validación
            if (bindingResult.hasErrors()) {
                return "crearCuenta";
            }

            // Obtener el ID del usuario desde la sesión
            Integer userId = (Integer) session.getAttribute("userId");

            // Verificar si el usuario está en la sesión
            if (userId != null) {
                // Asignar el ID del usuario a la cuenta bancaria
                cuentaBancariaDTO.setUsuarioId(userId);

                // Guardar la nueva cuenta bancaria en el servicio
                CuentaBancariaDTO nuevaCuenta = cuentaBancariaService.guardarNuevaCuentaBancaria(cuentaBancariaDTO);

                // Redireccionar a la página del cliente con un mensaje de éxito
                redirectAttributes.addFlashAttribute("successMessage", "¡Cuenta bancaria creada con éxito!");
                return "redirect:/home";
            } else {
                // Manejar el caso en el que el usuario no esté en la sesión
                model.addAttribute("error", "No se puede crear la cuenta bancaria. Usuario no encontrado.");
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar la creación de la cuenta bancaria.");
            return "error";
        }
    }

    @GetMapping("/cambiar-cuenta")
    public String mostrarCambiarCuentaPage(HttpSession session, Model model) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");

            if (userId != null) {
                // Obtén el usuario y sus cuentas bancarias
                UsuarioDTO usuarioDTO = usuarioService.obtenerUsuarioPorId(userId);
                List<CuentaBancariaDTO> cuentasBancarias = cuentaBancariaService.obtenerCuentasPorUsuario(userId);

                // Agrega los datos al modelo
                model.addAttribute("usuario", usuarioDTO);
                model.addAttribute("cuentasBancarias", cuentasBancarias);

                // Devuelve la vista para cambiar de cuenta
                return "cambiar-cuenta";
            } else {
                // Manejar el caso en que el atributo de sesión userId no esté presente
                model.addAttribute("error", "El atributo de sesión userId no está presente.");
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar la página para cambiar de cuenta.");
            return "error";
        }
    }
    @PostMapping("/cambiar-cuenta")
    public String handleCambiarCuenta(@RequestParam("cuentaId") Integer nuevaCuentaId, HttpSession session, RedirectAttributes redirectAttributes,Model model) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");

            if (userId != null) {
                // Obtener la cuenta actual del usuario
                UsuarioDTO usuarioDTO = usuarioService.obtenerUsuarioPorId(userId);

                // Verificar si la nueva cuenta pertenece al usuario
                boolean cuentaPerteneceUsuario = usuarioDTO.getCuentasBancarias()
                        .stream()
                        .anyMatch(cuenta -> cuenta.getId().equals(nuevaCuentaId));

                if (cuentaPerteneceUsuario) {
                    // Obtener la información detallada de la nueva cuenta
                    CuentaBancariaDTO nuevaCuenta = cuentaBancariaService.obtenerCuentaBancariaPorId(nuevaCuentaId);

                    // Actualizar la cuenta actual del usuario en la sesión
                    usuarioDTO.setCuentaActualId(nuevaCuentaId);

                    // Actualizar el usuario en la sesión
                    session.setAttribute("usuario", usuarioDTO);

                    // Redireccionar a la página del cliente con un mensaje de éxito
                    redirectAttributes.addFlashAttribute("successMessage", "¡Cuenta cambiada con éxito!");
                    return "redirect:/home";
                } else {
                    // Manejar el caso en que la nueva cuenta no pertenece al usuario
                    model.addAttribute("error", "La nueva cuenta no pertenece al usuario.");
                    return "error";
                }
            } else {
                // Manejar el caso en que el usuario no esté en la sesión
                return "redirect:/login";
            }
        } catch (Exception e) {
            // Manejar errores, por ejemplo, redireccionar a una página de error
            return "redirect:/error";
        }
    }
}
