package ru.netology;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTest {

    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("All GeoServiceTests started");
    }

    @AfterAll
    public static void afterAllTests() {
        System.out.println("All GeoServiceTests completed");
    }

    GeoService sut = new GeoServiceImpl();

    @ParameterizedTest
    @MethodSource("parametersDefinition")
    public void geoServiceTestByIP(String ipAdress, Country expectedCountry) {
//        arrange
//        act
        Location result = sut.byIp(ipAdress);
//        assert
        Assertions.assertEquals(expectedCountry, result != null ? result.getCountry() : null);
    }

    private static Stream<Arguments> parametersDefinition() {
        return Stream.of(
                Arguments.of("172.25.100.100", Country.RUSSIA),
                Arguments.of("96.168.168.168", Country.USA),
                Arguments.of("100.0.0.0", null),
                Arguments.of("255.00.00.00", null)
        );
    }

    @Test
    public void geoServiceTestByCoordinates() {
//        arrange
        Class<RuntimeException> excepted = RuntimeException.class;
        double latitude = 0;
        double longitude = 0;
//        act
        Executable executable = () -> sut.byCoordinates(latitude, longitude);
//        assert
        Assertions.assertThrows(excepted, executable);
    }
}
