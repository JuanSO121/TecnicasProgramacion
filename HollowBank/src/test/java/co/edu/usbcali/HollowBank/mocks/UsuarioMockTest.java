package co.edu.usbcali.HollowBank.mocks;

import co.edu.usbcali.HollowBank.domain.Usuario;

import java.util.Arrays;
import java.util.List;

public class UsuarioMockTest {

    public static Integer ID_UNO = 1;
    public static Integer ID_DOS = 2;
    public static String NOMBRE_UNO = "C1";
    public static String NOMBRE_DOS = "C2";
    public static String APELLIDO_UNO = "A1";
    public static String APELLIDO_DOS = "A2";
    public static String DIRECCION_UNO = "D1";
    public static String DIRECCION_DOS = "D2";
    public static String TELEFONO_UNO = "T1";
    public static String TELEFONO_DOS = "T2";
    public static String PASSWORD_UNO = "P1";
    public static String PASSWORD_DOS = "P2";

    public static Usuario USUARIO_UNO =
            Usuario.builder()
                    .id(ID_UNO)
                    .nombre(NOMBRE_UNO)
                    .apellido(APELLIDO_UNO)
                    .direccion(DIRECCION_UNO)
                    .telefono(TELEFONO_UNO)
                    .password(PASSWORD_UNO)
                    .build();

    public static Usuario USUARIO_DOS =
            Usuario.builder()
                    .id(ID_DOS)
                    .nombre(NOMBRE_DOS)
                    .apellido(APELLIDO_DOS)
                    .direccion(DIRECCION_DOS)
                    .telefono(TELEFONO_DOS)
                    .password(PASSWORD_DOS)
                    .build();

    public static List<Usuario> USUARIOS_LIST =
            Arrays.asList(USUARIO_UNO, USUARIO_DOS);
}
