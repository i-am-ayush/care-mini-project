package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spittr.Login;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
//    private Login login;

    @RequestMapping( method= RequestMethod.POST)
    public String showw(@Valid Login login, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "neww";
        } else {
            model.addAttribute("login", login);
            return "showdata";
       }
    }
}
