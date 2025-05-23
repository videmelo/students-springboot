package br.com.studentmanagement.controller;

import br.com.studentmanagement.repository.UserRepository;
import br.com.studentmanagement.exception.ServiceException;
import br.com.studentmanagement.model.Student;
import br.com.studentmanagement.model.User;
import br.com.studentmanagement.service.UserService;
import br.com.studentmanagement.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService serviceUser;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Login/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home/index");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("Login/register");
        return modelAndView;
    }

    @PostMapping("/saveUser")
    public ModelAndView register(User user) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        serviceUser.saveUser(user);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid User user, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        if(br.hasErrors()) {
            modelAndView.setViewName("Login/login");
        }

        User userLogin = serviceUser.loginUser(user.getUser(), Util.md5(user.getPassword()));
        if(userLogin == null) {
            modelAndView.addObject("msg","User not encontrado. Tente novamente");
        } else {
            session.setAttribute("userLogado", userLogin);
            return index();
        }

        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return login();
    }


}
