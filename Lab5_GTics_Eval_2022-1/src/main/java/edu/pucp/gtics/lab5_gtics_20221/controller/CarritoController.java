
package edu.pucp.gtics.lab5_gtics_20221.controller;

import edu.pucp.gtics.lab5_gtics_20221.entity.*;
import edu.pucp.gtics.lab5_gtics_20221.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @Autowired
    JuegosRepository juegosRepository;

    @Autowired
    PlataformasRepository plataformasRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JuegosxUsuarioRepository juegosxUsuarioRepository;


    @GetMapping(value = {"/carrito/lista"})
    public String listaCarrito (Model model, Authentication auth) {

        String rol = "";
        for (GrantedAuthority role : auth.getAuthorities()) {
            rol = role.getAuthority();
            break;
        }

        if (rol.equals("ADMIN")) {
            model.addAttribute("listaCarrito", juegosxUsuarioRepository.findAll(Sort.by("precio")));
            return "carrito/lista";
        } else {
            model.addAttribute("listaCarrito", juegosxUsuarioRepository.obtenerJuegosPorUser(1));
            return "carrito/lista";
        }
    }
    }

    /*public void nuevoCarrito(@RequestParam("id") int id, ...){

       return "redirect:/vista";
    }
    public void editarCarrito(  ){

        //return "redirect:/juegos/lista";
    }

    public void borrarCarrito(){

        //return "redirect:/carrito/lista";
    }

    public void anadircarrito(){

        //return "redirect:/carrito/lista";
    }

}


 */