package com.ganzi.travelmate.common.convertor;

import com.ganzi.travelmate.common.exception.InvalidEnumValueException;
import io.micrometer.common.util.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.EnumSet;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonTypeValueConvertUtils {

    public static <T extends Enum<T> & CommonType> T toEntityAttribute(Class<T> enumClass, String databaseColumn) {

        if (StringUtils.isBlank(databaseColumn)) {
            return null;
        }

        return EnumSet.allOf(enumClass).stream()
                .filter(v -> v.getCode().equals(databaseColumn))
                .findAny()
                .orElseThrow(() -> new InvalidEnumValueException(String.format("enum=[%s], code=[%s]가 존재하지 않습니다.", enumClass.getName(), databaseColumn)));
    }

    public static <T extends Enum<T> & CommonType> String toDatabaseColumn(T enumValue) {
        if (enumValue == null) {
            return "";
        }
        return enumValue.getCode();
    }

}
