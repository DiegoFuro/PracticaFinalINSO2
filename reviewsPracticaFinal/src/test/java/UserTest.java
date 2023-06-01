/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controller.registerController;
import Modelo.Rol;
import Modelo.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Diego
 */
public class UserTest {

    private static Usuario user;
    private static Usuario user2;

    private static Rol rol;
    private registerController registerController;

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @BeforeAll
    public static void setUp() throws Exception {
        rol = new Rol();
        rol.setIdRol(3);
        rol.setDescription("usuario");
        rol.setUserType('U');

        user = new Usuario();
        user.setIdRol(rol);
        user.setIdUser(1);
        user.setName("Juan");
        user.setLastName("Rodriguez");
        user.setPassword("password");
        user.setUser("juanRod");

        user2 = new Usuario();
        user2.setIdRol(rol);
        user2.setIdUser(1);
        user2.setName("Juan19");
        user2.setLastName("Rodriguez");
        user2.setPassword("password");
        user2.setUser("juanRod");

    }

    @Test
    public void testNombreValidoSinNumeros() {
        registerController = new registerController();
        assertTrue(registerController.checkNameNumbers(user.getName()));
    }
    
       @Test
    public void testNombreNoValidoConNumeros() {
        registerController = new registerController();
        assertFalse(registerController.checkNameNumbers(user2.getName()));
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
