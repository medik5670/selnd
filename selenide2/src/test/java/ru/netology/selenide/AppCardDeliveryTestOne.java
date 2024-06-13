package ru.netology.selenide;

import java.time.Duration;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import java.time.format.DateTimeFormatter;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
public class AppCardDeliveryTestOne{
    private DateTimeFormatter DataTimeFormatter;

    private String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DataTimeFormatter.ofPattern(pattern));
    }
    @Test
    public void shouldBeSuccessfullyCompleted(){
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Казань");
        String planningDate = generateDate(4,"dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $("[data-test-id='date'] input").setValue("planningDate");
        $("[data-test-id='name'] input").setValue("Петров Петр Петрович");
        $("[data-test-id='phone'] input").setValue("+79177113298");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на" + planningDate));


    }
}
