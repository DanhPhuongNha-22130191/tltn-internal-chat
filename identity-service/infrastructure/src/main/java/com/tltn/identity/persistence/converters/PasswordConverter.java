package com.tltn.identity.persistence.converters;

import com.tltn.identity.domain.valueobject.Password;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PasswordConverter implements AttributeConverter<Password, String> {
    @Override
    public String convertToDatabaseColumn(Password attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public Password convertToEntityAttribute(String dbData) {
        return dbData != null ? new Password(dbData) : null;
    }
}
