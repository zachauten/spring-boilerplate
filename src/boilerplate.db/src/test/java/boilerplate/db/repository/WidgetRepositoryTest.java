package boilerplate.db.repository;

import boilerplate.db.entities.Widget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for WidgetRepository.
 * Tests the repository layer with a real database using Testcontainers.
 */
@DataJpaTest
@ContextConfiguration(classes = boilerplate.db.config.TestDatabaseConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@ActiveProfiles("test")
class WidgetRepositoryTest {

    @Autowired
    private WidgetRepository widgetRepository;

    @BeforeEach
    void setUp() {
        // Any test-specific setup can go here
        // The database will be automatically reset between tests due to @Transactional
    }

    @Test
    void shouldSaveAndFindWidget() {
        // Given
        Widget widget = new Widget("Test Widget", "A test widget");

        // When
        Widget savedWidget = widgetRepository.save(widget);

        // Then
        assertNotNull(savedWidget.getId());
        assertEquals("Test Widget", savedWidget.getName());
        assertEquals("A test widget", savedWidget.getDescription());

        // Verify it can be found
        Optional<Widget> foundWidget = widgetRepository.findById(savedWidget.getId());
        assertTrue(foundWidget.isPresent());
        assertEquals("Test Widget", foundWidget.get().getName());
    }

    @Test
    void shouldFindAllWidgets() {
        // Given
        Widget widget1 = new Widget("Widget 1", "First widget");
        Widget widget2 = new Widget("Widget 2", "Second widget");

        widgetRepository.save(widget1);
        widgetRepository.save(widget2);

        // When
        List<Widget> allWidgets = widgetRepository.findAll();

        // Then
        assertEquals(2, allWidgets.size());
        assertTrue(allWidgets.stream().anyMatch(w -> "Widget 1".equals(w.getName())));
        assertTrue(allWidgets.stream().anyMatch(w -> "Widget 2".equals(w.getName())));
    }

    @Test
    void shouldDeleteWidget() {
        // Given
        Widget widget = new Widget("Test Widget", "A test widget");
        Widget savedWidget = widgetRepository.save(widget);

        // When
        widgetRepository.deleteById(savedWidget.getId());

        // Then
        Optional<Widget> foundWidget = widgetRepository.findById(savedWidget.getId());
        assertTrue(foundWidget.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenWidgetNotFound() {
        // When
        Optional<Widget> foundWidget = widgetRepository.findById(999);

        // Then
        assertTrue(foundWidget.isEmpty());
    }

    @Test
    @Sql("/sql/widgets-test-data.sql")
    void shouldFindWidgetsFromSqlFile() {
        // When
        List<Widget> widgets = widgetRepository.findAll();

        // Then
        assertFalse(widgets.isEmpty());
        // Add assertions based on your SQL test data
    }
}
