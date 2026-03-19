package com.tltn.identity.persistence.converters;

import com.tltn.identity.domain.valueobject.Name;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class NameConverter implements AttributeConverter<Name, String> {
    @Override
    public String convertToDatabaseColumn(Name attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public Name convertToEntityAttribute(String dbData) {
        return dbData != null ? new Name(dbData) : null;
    }
}
