package edu.pucp.gtics.lab5_gtics_20221.controller;

import edu.pucp.gtics.lab5_gtics_20221.entity.Juegos;
import edu.pucp.gtics.lab5_gtics_20221.entity.User;
import edu.pucp.gtics.lab5_gtics_20221.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/signIn")
    public String signIn(){
        return "user/signIn";
    }

    @GetMapping("/user/signInRedirect")
    public String signInRedirect(Authentication auth, HttpSession session){

        String rol = "";
        for (GrantedAuthority role : auth.getAuthorities()){
            rol = role.getAuthority();
            break;
        }

        User user = userRepository.findByCorreo(auth.getName());
        session.setAttribute("usuario",user);

        if (rol.equals("ADMIN")){
            return "redirect:/juegos/lista";
        }else {
            return "redirect:/juegos";
        }
    }

}