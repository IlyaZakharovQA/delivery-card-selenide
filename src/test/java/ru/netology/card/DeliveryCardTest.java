package ru.netology.card;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;


class DeliveryCardTest {

    @Test
    void shouldRegisterCardDeliveryOrder() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys("18.02.2023");
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+79099999999");
        $("[data-test-id='agreement']").click();
        $x("//span[text()='Забронировать']").click();
        $x("//div[contains(text(), 'Встреча успешно забронирована на ')]").should(Condition.appear, Duration.ofSeconds(12));
    }
}