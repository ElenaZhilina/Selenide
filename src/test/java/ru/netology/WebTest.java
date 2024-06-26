package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class WebTest {

    LocalDate futureDate = LocalDate.now().plusDays(3);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Test
    public void cardTest() {

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");

        $("[data-test-id='date'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(futureDate.format(formatter));

        $("[data-test-id='name'] input").setValue("Елена");
        $("[data-test-id='phone'] input").setValue("+77772100609");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        //$$("button").find(exactText("Забронировать")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        //$(".button__icon").shouldBe(Condition.visible, Duration.ofSeconds(15));
        //$("[data-test-id='notification']").shouldHave(exactText("Встреча успешно забронирована на " + futureDate.format(formatter)));
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + futureDate.format(formatter)));

    }
}

