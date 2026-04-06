package com.duoc.backend;
import com.duoc.backend.User;
import com.duoc.backend.JWTAuthenticationConfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Controlador REST para manejar las solicitudes de inicio de sesión. Este controlador recibe las 
// credenciales del usuario, valida la contraseña y, si es correcta, genera un token JWT utilizando 
// la clase JWTAuthenticationConfig. El token se devuelve al cliente para que pueda ser utilizado en 
// futuras solicitudes autenticadas.
@RestController
public class LoginController {

    // Inyección de dependencias para la configuración de autenticación JWT y el servicio de detalles
    @Autowired
    JWTAuthenticationConfig jwtAuthtenticationConfig;

    // Inyección de dependencias para el servicio de detalles del usuario, que se utiliza para cargar
    // la información del usuario a partir del nombre de usuario proporcionado.
    @Autowired
    private MyUserDetailsService userDetailsService;

    // Método para manejar las solicitudes de inicio de sesión. Recibe el nombre de usuario y la contraseña
    // cifrada como parámetros de la solicitud. Valida la contraseña y, si es correcta, genera un 
    // token JWT y lo devuelve al cliente.
    @PostMapping("login")
    public String login(
            @RequestParam("user") String username,
            @RequestParam("encryptedPass") String encryptedPass) {


        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (!userDetails.getPassword().equals(encryptedPass)) {
            throw new RuntimeException("Invalid login");
        }

        String token = jwtAuthtenticationConfig.getJWTToken(username);
        return token;
    }

}