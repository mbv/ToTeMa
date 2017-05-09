package by.totema.recourse.controller.impl;

import by.totema.recourse.controller.DocumentController;
import by.totema.recourse.controller.exception.NotFoundException;
import by.totema.recourse.document.DocumentType;
import by.totema.recourse.document.generator.DocumentGenerator;
import by.totema.recourse.document.model.provider.ContentProvider;
import by.totema.recourse.document.model.provider.CountryReportContentProvider;
import by.totema.recourse.document.model.provider.EmployeeReportContentProvider;
import by.totema.recourse.entity.dto.CountryOrderReportDto;
import by.totema.recourse.entity.dto.EmployeeOrderReportDto;
import by.totema.recourse.entity.support.DocumentTypeEnumConverter;
import by.totema.recourse.service.CountryService;
import by.totema.recourse.service.EmployeeService;
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

    public DocumentControllerImpl(EmployeeService employeeService, CountryService countryService) {
        this.employeeService = employeeService;
        this.countryService = countryService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(DocumentType.class, new DocumentTypeEnumConverter());
    }


    @Override
    public void generateCountryOrderReport(@RequestParam("type") DocumentType documentType, HttpServletResponse response) {
        Optional<List<CountryOrderReportDto>> countryOrderReportDtoList = countryService.getOrderReport();
        if (countryOrderReportDtoList.isPresent()) {
            List<CountryOrderReportDto> existingCountryOrderReportDtoList = countryOrderReportDtoList.get();
            dispatchDocumentRequest(response, documentType, null, existingCountryOrderReportDtoList, new CountryReportContentProvider());
        }else {
            throw new NotFoundException();
        }

    }

    @Override
    public void generateEmployeeOrderReport(@RequestParam("type") DocumentType documentType, HttpServletResponse response) {
        Optional<List<EmployeeOrderReportDto>> employeeOrderReportDtoList = employeeService.getOrderReport();
        if (employeeOrderReportDtoList.isPresent()) {
            List<EmployeeOrderReportDto> existingEmployeeOrderReportDtoList = employeeOrderReportDtoList.get();
            dispatchDocumentRequest(response, documentType, null, existingEmployeeOrderReportDtoList, new EmployeeReportContentProvider());
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