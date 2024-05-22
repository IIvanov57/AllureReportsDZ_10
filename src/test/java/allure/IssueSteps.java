package allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class IssueSteps {

  @Step("Открываем страницу github")
  public void openPage() {
    open("https://github.com/");
  }

  @Step("Вводим в поисковик слово 'Java'")
  public void inputWord() {
    $(".header-search-button").click();
    $("#query-builder-test").click();
    $("#query-builder-test").setValue("Java").pressEnter();
  }

  @Step("Ищем первый проект в списке")
  public void searchFirstProject() {
    $("[data-testid='results-list']").$(".search-title").click();
  }

  @Step("Преходим в раздел issue")
  public void clickIssueTab() {
    $("#issues-tab").click();
  }

  @Step("Проверяем наличе issue #5164")
  public void checkIssue() {
    $(withText("#5164")).should(Condition.exist);
  }
}
