package com.ganzi.soccerhub.common.convertor;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.AttributeConverter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbstractEnumAttributeConvertor<E extends Enum<E> & CommonType> implements AttributeConverter<E, String> {

    private Class<E> targetEnumClass;

    private boolean nullable;

    private String enumName;

    @Override
    public E convertToEntityAttribute(String dbData) {
        if (StringUtils.isBlank(dbData)) {
            throw new IllegalArgumentException(String.format("%s이 DB에 null로 저장되어 있습니다.", enumName));
        }
        return CommonTypeValueConvertUtils.toEntityAttribute(targetEnumClass, dbData);
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        if (!nullable && attribute == null) {
            throw new IllegalArgumentException(String.format("%s는 null로 저장할 수 없습니다.", enumName));
        }
        return CommonTypeValueConvertUtils.toDatabaseColumn(attribute);
    }
}



