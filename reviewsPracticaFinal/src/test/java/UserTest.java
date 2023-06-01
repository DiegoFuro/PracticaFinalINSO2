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
    private registerController registerController1;

    @BeforeAll
    public void setUp() throws Exception {
        registerController = new registerController();
        registerController1 = new registerController();
    }
    
    @Test
    public void testNombreValidoSinNumeros() {
        registerController.setName("Juan"); 
        registerController.setLastName("Rodriguez");
        registerController.setUser("juanRod");
        registerController.setPassword("password");
        registerController.setIdRol(3);
        String resultado = registerController.registerUser();
        assertTrue(resultado.equals("index.xhtml?faces-redirect=true"));
    }

    @Test
    public void testNombreNoValidoConNumeros() {
        registerController1.setName("Juan123");
        registerController1.setLastName("Rodriguez");
        registerController1.setUser("juanRod");
        registerController1.setPassword("password");
        registerController1.setIdRol(3);
        String resultado = registerController1.registerUser();
        assertTrue(resultado.equals(""));
    }
}
