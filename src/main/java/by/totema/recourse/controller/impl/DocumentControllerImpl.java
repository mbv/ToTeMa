package by.totema.recourse.controller.impl;

import by.totema.recourse.controller.DocumentController;
import by.totema.recourse.controller.exception.NotFoundException;
import by.totema.recourse.document.DocumentType;
import by.totema.recourse.document.generator.DocumentGenerator;
import by.totema.recourse.document.model.provider.*;
import by.totema.recourse.entity.dto.OrderReportDto;
import by.totema.recourse.entity.support.DocumentTypeEnumConverter;
import by.totema.recourse.service.*;
import by.totema.recourse.util.DocumentGeneratorCallWrapper;
import org.slf4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static by.totema.recourse.util.Util.sanitizeFilename;
import static org.slf4j.LoggerFactory.getLogger;


public class DocumentControllerImpl implements DocumentController {

    private static final Logger logger = getLogger(DocumentControllerImpl.class);
    private EmployeeService employeeService;
    private CountryService countryService;
    private OfficeService officeService;
    private DateService dateService;
    private ProductService productService;

    public DocumentControllerImpl(EmployeeService employeeService, CountryService countryService, OfficeService officeService,  DateService dateService, ProductService productService) {
        this.employeeService = employeeService;
        this.countryService = countryService;
        this.officeService = officeService;
        this.dateService = dateService;
        this.productService = productService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(DocumentType.class, new DocumentTypeEnumConverter());
    }


    @Override
    public void generateCountryOrderReport(@RequestParam("type") DocumentType documentType, HttpServletResponse response) {
        Optional<List<OrderReportDto>> orderReportDtoList = countryService.getOrderReport();
        if (orderReportDtoList.isPresent()) {
            List<OrderReportDto> existingOrderReportDtoList = orderReportDtoList.get();
            dispatchDocumentRequest(response, documentType, null, existingOrderReportDtoList, new CountryReportContentProvider());
        }else {
            throw new NotFoundException();
        }

    }

    @Override
    public void generateYearOrderReport(@RequestParam("type") DocumentType documentType, HttpServletResponse response) {
        Optional<List<OrderReportDto>> orderReportDtoList = dateService.getOrderReport();
        if (orderReportDtoList.isPresent()) {
            List<OrderReportDto> existingOrderReportDtoList = orderReportDtoList.get();
            dispatchDocumentRequest(response, documentType, null, existingOrderReportDtoList, new YearReportContentProvider());
        }else {
            throw new NotFoundException();
        }

    }

    @Override
    public void generateOfficeOrderReport(@RequestParam("type") DocumentType documentType, HttpServletResponse response) {
        Optional<List<OrderReportDto>> orderReportDtoList = officeService.getOrderReport();
        if (orderReportDtoList.isPresent()) {
            List<OrderReportDto> existingOrderReportDtoList = orderReportDtoList.get();
            dispatchDocumentRequest(response, documentType, null, existingOrderReportDtoList, new OfficeReportContentProvider());
        }else {
            throw new NotFoundException();
        }

    }

    @Override
    public void generateEmployeeOrderReport(@RequestParam("type") DocumentType documentType, HttpServletResponse response) {
        Optional<List<OrderReportDto>> orderReportDtoList = employeeService.getOrderReport();
        if (orderReportDtoList.isPresent()) {
            List<OrderReportDto> existingOrderReportDtoList = orderReportDtoList.get();
            dispatchDocumentRequest(response, documentType, null, existingOrderReportDtoList, new EmployeeReportContentProvider());
        }else {
            throw new NotFoundException();
        }

    }

    @Override
    public void generateProductOrderReport(@RequestParam("type") DocumentType documentType, HttpServletResponse response) {
        Optional<List<OrderReportDto>> orderReportDtoList = productService.getOrderReport();
        if (orderReportDtoList.isPresent()) {
            List<OrderReportDto> existingOrderReportDtoList = orderReportDtoList.get();
            dispatchDocumentRequest(response, documentType, null, existingOrderReportDtoList, new ProductReportContentProvider());
        }else {
            throw new NotFoundException();
        }

    }

    @Override
    public void generateProductDetailedOrderReport(@RequestParam("type") DocumentType documentType, HttpServletResponse response) {
        Optional<List<OrderReportDto>> orderReportDtoList = productService.getDetailedOrderReport();
        if (orderReportDtoList.isPresent()) {
            List<OrderReportDto> existingOrderReportDtoList = orderReportDtoList.get();
            dispatchDocumentRequest(response, documentType, null, existingOrderReportDtoList, new ProductDetailedReportContentProvider());
        }else {
            throw new NotFoundException();
        }

    }

    private <TMainEntity, TTableEntity> void dispatchDocumentRequest(HttpServletResponse response,
                                                                     DocumentType documentType,
                                                                     TMainEntity mainEntity,
                                                                     Collection<TTableEntity> tableEntities,
                                                                     ContentProvider<TMainEntity, TTableEntity> contentProvider) {
        DocumentGenerator<TMainEntity, TTableEntity> documentGenerator = documentType.createGenerator(contentProvider);
        response.setContentType(documentGenerator.getContentType());
        if (documentGenerator.isForceAttachment()) {
            response.setHeader("Content-disposition", "attachment;filename=" + createFilename(mainEntity, contentProvider, documentGenerator));
        }
        DocumentGeneratorCallWrapper.wrapDocumentGeneratorCall(() -> documentGenerator.writeDocument(
                response,
                mainEntity,
                tableEntities));
    }

    private <TMainEntity, TTableEntity> String createFilename(TMainEntity mainEntity, ContentProvider<TMainEntity, TTableEntity> contentProvider, DocumentGenerator<TMainEntity, TTableEntity> documentGenerator) {
        String filename = String.format("%s.%s",
                contentProvider.createFilename(mainEntity),
                documentGenerator.getFileExtension());
        filename = sanitizeFilename(filename);
        return filename;
    }
}