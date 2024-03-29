package kz.bitlab.academy.secondsprint.controller;

import kz.bitlab.academy.secondsprint.service.ApplicationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ApplicationRequestController {

    private final ApplicationRequestService service;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("requests", service.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addRequestPage() {
        return "addRequest";
    }

    @GetMapping("/handledRequests")
    public String handledRequestsPage() {
        return "handledRequests";
    }

    @GetMapping("/newRequests")
    public String newRequestsPage() {
        return "newRequests";
    }

    @PostMapping("/add")
    public String create(@RequestParam("userName") String userName,
                         @RequestParam("courseName") String courseName,
                         @RequestParam("phone") String phone,
                         @RequestParam("commentary") String commentary) {

        if (userName.isBlank() || courseName.isBlank() || phone.isBlank())
            return "redirect:/add?badRequest";

        service.create(userName, courseName, commentary, phone);

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detailsPage(@PathVariable Long id, Model model) {
        model.addAttribute("request", service.findById(id));

        return "details";
    }

    @PostMapping("/{id}")
    public String handle(@PathVariable Long id) {
        service.handle(id);

        return "redirect:/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);

        return "redirect:/";
    }

}
