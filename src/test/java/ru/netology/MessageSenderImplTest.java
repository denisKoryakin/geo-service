package ru.netology;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.entity.Country.*;

public class MessageSenderImplTest {

    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("All MessageSendTests started");
    }

    @AfterAll
    public static void afterAllTests() {
        System.out.println("All MessageSendTests completed");
    }

    @ParameterizedTest
    @CsvSource({
            "172.123.12.19, Добро пожаловать",
            "172.0.32.11, Добро пожаловать",
            "96.00.00.00, Welcome"
    })
    public void messageSendTest(String ipAdress, String expectedMessage) {
//        arrange
        Map<String, String> headers = new HashMap<>();
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.123.12.19")).thenReturn(new Location("Moscow", RUSSIA, null, 0));
        Mockito.when(geoService.byIp("172.0.32.11")).thenReturn(new Location("Moscow", RUSSIA, "Lenina", 15));
        Mockito.when(geoService.byIp("96.00.00.00")).thenReturn(new Location("New York", USA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(USA)).thenReturn("Welcome");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAdress);
//        act
        String message = messageSender.send(headers);
//        assert
        Assertions.assertEquals(expectedMessage, message);
    }
}
