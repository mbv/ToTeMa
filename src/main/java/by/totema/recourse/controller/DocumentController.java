package by.totema.recourse.controller;

import by.totema.recourse.document.DocumentType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public interface DocumentController {

    @GetMapping("/reports/country")
    void generateCountryOrderReport(
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response);

    @GetMapping("/reports/office")
    void generateOfficeOrderReport(
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response);

    @GetMapping("/reports/years")
    void generateYearOrderReport(
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response);

    @GetMapping("/reports/employees")
    void generateEmployeeOrderReport(
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response);

    @GetMapping("/reports/products")
    void generateProductOrderReport(
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response);

    @GetMapping("/reports/products-detailed")
    void generateProductDetailedOrderReport(
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response);

}