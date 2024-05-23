package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.EventsCollector;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class AllureReportsTests {


  @Test
  @DisplayName("Проверка наличия ошибки в списке")
  void checkIssueWithCleanSelenideTest() {
    open("https://github.com/");

    $(".header-search-button").click();
    $("#query-builder-test").click();
    $("#query-builder-test").setValue("Java").pressEnter();


    $("[data-testid='results-list']").$(".search-title").click();

    $("#issues-tab").click();

    $(withText("#5164")).should(Condition.exist);
  }

  @Test
  void checkIssueLambdaTest() {
    SelenideLogger.addListener("allure", new EventsCollector());

    step("Открываем страницу github", () -> {
      open("https://github.com/");
    });

    step("Вводим в поисковик слово 'Java'", () -> {
      $(".header-search-button").click();
      $("#query-builder-test").click();
      $("#query-builder-test").setValue("Java").pressEnter();
    });
    step("Ищем первый проект в списке", () -> {
      $("[data-testid='results-list']").$(".search-title").click();
    });

    step("Преходим в раздел issue", () -> {
      $("#issues-tab").click();
    });

    step("Проверяем наличе issue #5164", () -> {
      $(withText("#5164")).should(Condition.exist);
    });
  }

  @Test
  void checkIssueWithStepsTest() {
    SelenideLogger.addListener("allure", new AllureSelenide());

    IssueSteps steps = new IssueSteps();

    steps.openPage();
    steps.inputWord();
    steps.searchFirstProject();
    steps.clickIssueTab();
    steps.checkIssue();
  }


}
