package io.github.sergeivisotsky.metadata.preconfig.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.sergeivisotsky.metadata.preconfig.app.dto.ExtendedComboBox;
import io.github.sergeivisotsky.metadata.selector.MetadataMapper;
import io.github.sergeivisotsky.metadata.selector.dto.ComboBox;
import org.springframework.stereotype.Component;

/**
 * @author Sergei Visotsky
 */
@Component
public class ComboBoxMapper implements MetadataMapper<ResultSet, ComboBox> {

    @Override
    public String getSql() {
        return "SELECT cb.id,\n" +
                "       cb.codifier,\n" +
                "       cb.font,\n" +
                "       cb.font_size,\n" +
                "       cb.weight,\n" +
                "       cb.height,\n" +
                "       cb.displayable,\n" +
                "       cb.immutable,\n" +
                "       cbc.id            AS combo_content_id,\n" +
                "       cbc.key           AS content_key,\n" +
                "       cbc.default_value AS content_value\n" +
                "FROM combo_box cb\n" +
                "         JOIN combo_box_and_content_relation cbacr ON cb.id = cbacr.box_id\n" +
                "         JOIN combo_box_content cbc ON cbacr.box_content_id = cbc.id\n" +
                "WHERE cb.form_metadata_id = :formMetadataId";
    }

    @Override
    public ComboBox apply(ResultSet rs) {
        try {
            ExtendedComboBox comboBox = new ExtendedComboBox();
            comboBox.setId(rs.getLong("id"));
            comboBox.setCodifier(rs.getString("codifier"));
            comboBox.setWeight(rs.getInt("weight"));
            comboBox.setHeight(rs.getInt("height"));
            comboBox.setFont(rs.getString("font"));
            comboBox.setFontSize(rs.getInt("font_size"));
            comboBox.setImmutable(rs.getBoolean("immutable"));
            comboBox.setDisplayable(rs.getBoolean("displayable"));
            return comboBox;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get value from ResultSet for Mapper: {}" +
                    FormMetadataMapper.class.getSimpleName(), e);
        }
    }
}
