package io.github.sergeivisotsky.metadata.preconfig.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.sergeivisotsky.metadata.preconfig.app.dto.ExtendedFormSection;
import io.github.sergeivisotsky.metadata.selector.dto.form.FormSection;
import io.github.sergeivisotsky.metadata.selector.dto.form.FormSectionCardinality;
import io.github.sergeivisotsky.metadata.selector.mapper.MetadataMapper;

public class FormSectionMapper implements MetadataMapper<FormSection> {

    @Override
    public String getSql() {
        return "SELECT fs.name,\n" +
                "       fs.parent_section_name,\n" +
                "       fs.cardinality,\n" +
                "       tr.ui_name,\n" +
                "       tr.ui_description\n" +
                "FROM form_section fs\n" +
                "         LEFT JOIN amd_translation tr on tr.form_name = fs.name\n" +
                "WHERE fs.name = :formName\n" +
                "  AND tr.lang = :lang";
    }

    @Override
    public FormSection map(ResultSet rs) {
        try {
            ExtendedFormSection formSection = new ExtendedFormSection();
            formSection.setName(rs.getString("name"));
            formSection.setParentSectionName(rs.getString("parent_section_name"));
            formSection.setCardinality(FormSectionCardinality.valueOf(rs.getString("cardinality")));
            formSection.setUiName(rs.getString("ui_name"));
            formSection.setUiDescription(rs.getString("ui_description"));
            return formSection;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get value from ResultSet for Mapper: {}" +
                    FormSectionMapper.class.getSimpleName(), e);
        }
    }
}
