package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spittr.PojoTest;

@Controller
@RequestMapping(value="/data")
public class PojoController {
private PojoTest pojoTest;
@RequestMapping(method= RequestMethod.GET)
public String pojoData(Model model){
    pojoTest=new PojoTest();
    model.addAttribute("data",pojoTest);
    return "showdata";
}
}
