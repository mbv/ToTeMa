package by.totema.recourse.entity.support;

import by.totema.recourse.document.DocumentType;

import java.beans.PropertyEditorSupport;

public class DocumentTypeEnumConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(DocumentType.valueOf(text.toUpperCase()));
    }
}