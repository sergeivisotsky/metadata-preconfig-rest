package io.github.sergeivisotsky.metadata.preconfig.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.github.sergeivisotsky.metadata.preconfig.app.dto.ExtendedNavigation;
import io.github.sergeivisotsky.metadata.preconfig.app.dto.ExtendedNavigationElement;
import io.github.sergeivisotsky.metadata.selector.dto.Navigation;
import io.github.sergeivisotsky.metadata.selector.dto.NavigationElement;
import io.github.sergeivisotsky.metadata.selector.dto.NavigationType;
import io.github.sergeivisotsky.metadata.selector.mapper.MetadataMapper;
import org.springframework.stereotype.Component;

@Component
public class NavigationMapper implements MetadataMapper<Navigation> {

    @Override
    public String getSql() {
        return "SELECT n.id,\n" +
                "       n.type,\n" +
                "       n.number_of_elements,\n" +
                "       n.fixed,\n" +
                "       n.resizable,\n" +
                "       ne.code,\n" +
                "       ne.value,\n" +
                "       ne.is_active\n" +
                "FROM navigation n\n" +
                "         LEFT JOIN navigation_element ne on n.id = ne.navigation_id\n" +
                "WHERE n.view_name = :viewName";
    }

    @Override
    public Navigation map(ResultSet rs) {
        try {
            List<ExtendedNavigation> navFromRsList = new ArrayList<>();
            while (rs.next()) {
                ExtendedNavigation navigation = new ExtendedNavigation();
                navigation.setNumberOfElements(rs.getInt("number_of_elements"));
                navigation.setType(NavigationType.valueOf(rs.getString("type")));
                navigation.setResizable(rs.getBoolean("resizable"));
                navigation.setFixed(rs.getBoolean("fixed"));

                List<NavigationElement> navElements = new ArrayList<>();

                ExtendedNavigationElement navElem = new ExtendedNavigationElement();
                navElem.setCode(rs.getString("code"));
                navElem.setValue(rs.getString("value"));
                navElem.setActive(rs.getBoolean("is_active"));
                navElements.add(navElem);
                navigation.setElements(navElements);

                navFromRsList.add(navigation);
            }
            return normalizeNavigationMetadata(navFromRsList);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get value from ResultSet for Mapper: {}" +
                    LookupMetadataMapper.class.getSimpleName(), e);
        }
    }

    private ExtendedNavigation normalizeNavigationMetadata(List<ExtendedNavigation> originalNav) {
        ExtendedNavigation navigation = originalNav.get(0);

        List<NavigationElement> navElements = originalNav
                .stream()
                .flatMap(nav -> nav.getElements().stream())
                .collect(Collectors.toList());
        navigation.getElements().clear();

        navigation.setElements(navElements);
        return navigation;
    }
}
