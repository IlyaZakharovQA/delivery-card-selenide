package ru.netology.card;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;


class DeliveryCardTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldRegisterCardDeliveryOrder() {
        Configuration.holdBrowserOpen = true;
        String planningDate = generateDate(8);
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").sendKeys("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(planningDate);
        $("[data-test-id='name'] input").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input").sendKeys("+79099999999");
        $("[data-test-id='agreement']").click();
        $x("//span[text()='Забронировать']").click();
        $("[data-test-id='notification']")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(12))
                .shouldBe(Condition.visible);
    }
}