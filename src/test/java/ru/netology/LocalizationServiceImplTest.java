package ru.netology;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.entity.Country;

import java.util.stream.Stream;

public class LocalizationServiceImplTest {

    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("All LocalizationServiceTests started");
    }

    @AfterAll
    public static void afterAllTests() {
        System.out.println("All LocalizationServiceTests completed");
    }

    @ParameterizedTest
    @MethodSource("parametersDefinition")
    public void localizationServiceTest(Country country, String expectedMessage) {
//    arrange
        LocalizationService localizationServiceImpl = new LocalizationServiceImpl();
//    act
        String message = localizationServiceImpl.locale(country);
//    assert
        Assertions.assertEquals(expectedMessage, message);
    }

    private static Stream<Arguments> parametersDefinition() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, "ДоброПожаловать"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome")
        );
    }
}
