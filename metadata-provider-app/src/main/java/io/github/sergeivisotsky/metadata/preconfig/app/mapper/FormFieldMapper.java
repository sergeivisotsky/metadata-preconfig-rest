package io.github.sergeivisotsky.metadata.preconfig.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.sergeivisotsky.metadata.preconfig.app.dto.ExtendedFormField;
import io.github.sergeivisotsky.metadata.selector.dto.form.FieldType;
import io.github.sergeivisotsky.metadata.selector.dto.form.FormField;
import io.github.sergeivisotsky.metadata.selector.dto.form.UIControlType;
import io.github.sergeivisotsky.metadata.selector.mapper.MetadataMapper;

public class FormFieldMapper implements MetadataMapper<FormField> {

    @Override
    public String getSql() {
        return "SELECT ff.name,\n" +
                "       ff.type,\n" +
                "       ff.length,\n" +
                "       ff.decimal_scale,\n" +
                "       ff.editable,\n" +
                "       ff.form_section_name,\n" +
                "       ff.ui_control_type,\n" +
                "       ff.has_language,\n" +
                "       ff.\"order\",\n" +
                "       ff.controls_dynamic_change,\n" +
                "       tr.ui_name,\n" +
                "       tr.ui_description\n" +
                "FROM form_field ff\n" +
                "         LEFT JOIN amd_translation tr on tr.form_name = ff.name\n" +
                "WHERE ff.name = :formName\n" +
                "  AND tr.lang = :lang";
    }

    @Override
    public FormField map(ResultSet rs) {
        try {
            ExtendedFormField formField = new ExtendedFormField();
            formField.setName(rs.getString("name"));
            formField.setType(FieldType.valueOf(rs.getString("type")));
            formField.setDecimalScale(rs.getInt("decimal_scale"));
            formField.setEditable(rs.getBoolean("editable"));
            formField.setFormSectionName(rs.getString("form_section_name"));
            formField.setUiControlType(UIControlType.valueOf(rs.getString("ui_control_type")));
            formField.setHasLanguage(rs.getBoolean("has_language"));
            formField.setOrder(rs.getInt("order"));
            formField.setControlsDynamicChange(rs.getBoolean("controls_dynamic_change"));
            formField.setUiName(rs.getString("ui_name"));
            formField.setUiDescription(rs.getString("ui_description"));
            return formField;
        } catch (
                SQLException e) {
            throw new RuntimeException("Unable to get value from ResultSet for Mapper: {}" +
                    FormFieldMapper.class.getSimpleName(), e);
        }
    }
}