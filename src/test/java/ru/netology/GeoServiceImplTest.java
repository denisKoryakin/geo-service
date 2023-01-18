package ru.netology;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {

    @BeforeAll
    public static void BeforeAllTests() {
        System.out.println("All GeoServiceTests started");
    }

    @AfterAll
    public static void AfterAllTests() {
        System.out.println("All GeoServiceTests completed");
    }

    @ParameterizedTest
    @CsvSource({
            "172.0.32.11, RUSSIA",
            "96.00.00.00, USA"
    })
    public void GeoServiceTest(String ipAdress, String country) {
//        arrange
        GeoService geoServiceImpl = new GeoServiceImpl();
        Country expectedCountry = Country.valueOf(country);
        Location location = geoServiceImpl.byIp(ipAdress);
//        act
        Country result = location.getCountry();
//        assert
        Assertions.assertEquals(expectedCountry, result);
    }
}
