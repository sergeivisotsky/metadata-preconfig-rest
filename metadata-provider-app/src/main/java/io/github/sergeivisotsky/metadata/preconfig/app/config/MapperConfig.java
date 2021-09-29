package io.github.sergeivisotsky.metadata.preconfig.app.config;

import java.util.List;

import io.github.sergeivisotsky.metadata.preconfig.app.mapper.ComboBoxMapper;
import io.github.sergeivisotsky.metadata.preconfig.app.mapper.FormFieldMapper;
import io.github.sergeivisotsky.metadata.preconfig.app.mapper.FormMetadataMapper;
import io.github.sergeivisotsky.metadata.preconfig.app.mapper.FormSectionMapper;
import io.github.sergeivisotsky.metadata.preconfig.app.mapper.LayoutMapper;
import io.github.sergeivisotsky.metadata.preconfig.app.mapper.LookupHolderMapper;
import io.github.sergeivisotsky.metadata.preconfig.app.mapper.LookupMetadataMapper;
import io.github.sergeivisotsky.metadata.preconfig.app.mapper.NavigationMapper;
import io.github.sergeivisotsky.metadata.preconfig.app.mapper.ViewMetadataMapper;
import io.github.sergeivisotsky.metadata.selector.dto.ComboBox;
import io.github.sergeivisotsky.metadata.selector.dto.Layout;
import io.github.sergeivisotsky.metadata.selector.dto.LookupHolder;
import io.github.sergeivisotsky.metadata.selector.dto.LookupMetadata;
import io.github.sergeivisotsky.metadata.selector.dto.Navigation;
import io.github.sergeivisotsky.metadata.selector.dto.ViewMetadata;
import io.github.sergeivisotsky.metadata.selector.dto.form.FormField;
import io.github.sergeivisotsky.metadata.selector.dto.form.FormMetadata;
import io.github.sergeivisotsky.metadata.selector.dto.form.FormSection;
import io.github.sergeivisotsky.metadata.selector.mapper.MetadataMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sergei Visotsky
 */
@Configuration
public class MapperConfig {

    @Bean
    public MetadataMapper<ViewMetadata> metadataMetadataMapper() {
        return new ViewMetadataMapper();
    }

    @Bean
    public MetadataMapper<ComboBox> comboBoxMetadataMapper() {
        return new ComboBoxMapper();
    }

    @Bean
    public MetadataMapper<Layout> layoutMetadataMapper() {
        return new LayoutMapper();
    }

    @Bean
    public MetadataMapper<LookupHolder> lookupHolderMetadataMapper() {
        return new LookupHolderMapper();
    }

    @Bean
    public MetadataMapper<LookupMetadata> lookupMetadataMetadataMapper() {
        return new LookupMetadataMapper();
    }

    @Bean
    public MetadataMapper<List<Navigation>> navigationMapper() {
        return new NavigationMapper();
    }

    @Bean
    public MetadataMapper<FormField> formFieldMetadataMapper() {
        return new FormFieldMapper();
    }

    @Bean
    public MetadataMapper<FormSection> formSectionMetadataMapper() {
        return new FormSectionMapper();
    }

    @Bean
    public MetadataMapper<FormMetadata> formMetadataMetadataMapper() {
        return new FormMetadataMapper();
    }

}
