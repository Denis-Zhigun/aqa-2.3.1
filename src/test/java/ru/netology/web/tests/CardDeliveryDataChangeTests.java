package ru.netology.web.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeliveryDataChangeTests {

    @Test
    void shouldSendFormAgain() {
        open("http://localhost:9999/");

        $("[data-test-id=city] input").setValue(DataGenerator.getNewCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.DELETE);
        $("[data-test-id=date] input").setValue(DataGenerator.setDate(5));
        $("[data-test-id=name] input").setValue(DataGenerator.setName());
        $("[data-test-id=phone] input").setValue(DataGenerator.setPhone());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $("[data-test-id=success-notification]")
                .shouldBe((visible)).shouldHave(text("Успешно!"));
        String date = $("[data-test-id=date] input").getValue();
        String text = $("[data-test-id='success-notification'] .notification__content").text();
        assertEquals("Встреча успешно запланирована на " + date, text);

        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.DELETE);
        $("[data-test-id=date] input").setValue(DataGenerator.setDate(7));
        $(withText("Запланировать")).click();
        $("[data-test-id=replan-notification]")
                .shouldBe((visible)).shouldHave(text("Необходимо подтверждение"));
        $(withText("Перепланировать")).click();
        $("[data-test-id=success-notification]")
                .shouldBe((visible)).shouldHave(text("Успешно!"));
        String expected = $("[data-test-id=date] input").getValue();
        String actual = $("[data-test-id='success-notification'] .notification__content").text();
        assertEquals("Встреча успешно запланирована на " + expected, actual);
    }
}
