package com.ganzi.travelmate.common.docs;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class RestDocsCommonFields {

    public static FieldDescriptor[] commonResponseFields() {
        return new FieldDescriptor[] {
                fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답 코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                fieldWithPath("data").description("실제 응답 데이터")
        };
    }
}
