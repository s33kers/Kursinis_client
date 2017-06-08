package kursinis.client.controllers;

import kursinis.client.model.ServiceInput;
import kursinis.client.model.ServiceOutput;
import kursinis.client.services.SolverService;
import kursinis.client.utils.LoadTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @Autowired
    private SolverService solverService;

    @RequestMapping(value = "/{type}/{id}", method = RequestMethod.GET)
    public String index(@PathVariable("type") String type, @PathVariable("id") Long id, Model model) {
        model.addAttribute("serviceInput", LoadTest.loadTest(id, type));
        return "index";
    }

    @RequestMapping("/" )
    public String index(Model model) {
        model.addAttribute("serviceInput", new ServiceInput());
        return "index";
    }

    @RequestMapping(value = {"/", "/{type}/{id}"}, method = RequestMethod.POST)
    public String solve(ServiceInput serviceInput, Model model) {
        ServiceOutput output = solverService.solve(serviceInput);
        model.addAttribute("serviceOutput", output);
        return "index";
    }
}
