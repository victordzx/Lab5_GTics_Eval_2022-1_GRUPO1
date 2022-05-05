
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // Lista de juegos en el carrito
    @GetMapping(value = {"/lista"})
    public String listaCarrito(Model model, Authentication auth) {
        return "carrito/lista";
    }

    // Compramos los juegos del carrito
    @GetMapping(value = {"/comprar"})
    public String comprarCarrito(HttpSession session, RedirectAttributes attr) {
        List<Juegos> carrito = (List<Juegos>) session.getAttribute("carrito");
        User user = (User) session.getAttribute("usuario");
        List<JuegosxUsuario> listaCompras = new ArrayList<>();

        if (carrito.size() == 0) {
            attr.addFlashAttribute("msg", "Carrito vacío");
            return "carrito/lista";
        }

        for (Juegos index : carrito) {
            JuegosxUsuario juegxuser = new JuegosxUsuario();
            juegxuser.setIdjuego(index);
            juegxuser.setIdusuario(user);
            listaCompras.add(juegxuser);
        }
        juegosxUsuarioRepository.saveAll(listaCompras);
        session.setAttribute("carrito", new ArrayList<Juegos>());
        session.setAttribute("ncarrito", 0);
        attr.addFlashAttribute("msg", "Juegos comprados con éxito");

        return "carrito/lista";
    }

    // Eliminamos un juego del carrito
    @GetMapping(value = {"/borrar"})
    public String borrarCarrito(@RequestParam("id") String id, HttpSession session) {

        List<Juegos> listaCarrito =(List<Juegos>) session.getAttribute("carrito");

        int i=0;
        for (Juegos juego : listaCarrito){
            if (juego.getIdjuego() == listaCarrito.get(i).getIdjuego()){
                listaCarrito.remove(i);
                break;
            }
            i++;
        }

        session.setAttribute("carrito",listaCarrito);
        session.setAttribute("ncarrito",listaCarrito.size()); // nuevo tamaño

        return "redirect:/carrito/lista";
    }

    // Agregamos un juego al carrito
    @GetMapping(value = {"/anadir"})
    public String anadircarrito(@RequestParam("id") String id, HttpSession session, RedirectAttributes attr) {

        if (session.getAttribute("carrito") == null) {
            // carrito vacío
            List<Juegos> listaCarrito = new ArrayList<>();
            listaCarrito.add(juegosRepository.findById(Integer.parseInt(id)).get());
            session.setAttribute("carrito",listaCarrito);
            session.setAttribute("ncarrito",1); // valor inicial
            return "redirect:/juegos/vista";
        }else{
            // carrito ya lleno
            List<Juegos> listaCarrito = (List<Juegos>) session.getAttribute("carrito");
            listaCarrito.add(juegosRepository.findById(Integer.parseInt(id)).get());
            session.setAttribute("carrito",listaCarrito);
            session.setAttribute("ncarrito",listaCarrito.size()); // tamaño del carrito
        }

        return "redirect:/juegos/vista";
    }

}