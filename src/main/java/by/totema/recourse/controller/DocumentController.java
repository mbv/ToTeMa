package by.totema.recourse.controller;

import by.totema.recourse.document.DocumentType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public interface DocumentController {

    @GetMapping("/users/country")
    void generateCountryOrderReport(
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response);

    @GetMapping("/users/employee")
    void generateEmployeeOrderReport(
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response);

}