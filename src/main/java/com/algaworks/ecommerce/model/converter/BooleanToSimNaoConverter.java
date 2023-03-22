package com.algaworks.ecommerce.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class BooleanToSimNaoConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        return Boolean.TRUE.equals(aBoolean) ? "SIM" : "NAO";
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        return s.equals("SIM") ? Boolean.TRUE : Boolean.FALSE;
    }
}
