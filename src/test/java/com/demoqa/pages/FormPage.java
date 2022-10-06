package com.demoqa.pages;

import com.codeborne.selenide.selector.ByText;
import com.demoqa.pages.components.CalendarComponent;
import com.demoqa.pages.components.ResultForm;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormPage {

    CalendarComponent calendarComponent = new CalendarComponent();
    ResultForm resultForm = new ResultForm();

    @Step("Открываем сайт")
    public FormPage openPage() {
        open("automation-practice-form");

        return this;
    }

    @Step("Вводим имя")
    public FormPage fillName(String firstName, String lastName) {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);

        return this;
    }

    @Step("Вводим email")
    public FormPage fillEmail(String email) {
        $("#userEmail").setValue(email);

        return this;
    }

    @Step("Вводим номер")
    public FormPage fillNumber(String number) {
        $("#userNumber").setValue(number);

        return this;
    }

    @Step("Вводим пол")
    public FormPage fillGender(String gender) {
        $("#genterWrapper").find(new ByText(gender)).click();

        return this;
    }

    @Step("Вводим день рождения")
    public FormPage fillDayOfBirth(TestData testData) {
        calendarComponent.setDate(testData.date);

        return this;
    }

    @Step("Вводим адрес")
    public FormPage fillAddress(String address) {
        $("#currentAddress").setValue(address);

        return this;
    }

    @Step("Подтверждаем")
    public void submit () {
        $("#submit").click();

    }

    public FormPage fillForm(TestData testData) {
        this.fillName(testData.FIRST_NAME, testData.LAST_NAME)
                .fillEmail(testData.EMAIL)
                .fillNumber(testData.NUMBER)
                .fillGender(testData.GENDER)
                .fillDayOfBirth(testData)
                .fillAddress(testData.ADDRESS)
                .submit();

        return this;
    }

    @Step("Проверяем корректность заполнения")
    public void checkForm(TestData testData) {
        resultForm.assertFormParam("Student Name", testData.FIRST_NAME + " " + testData.LAST_NAME);
        resultForm.assertFormParam("Student Email", testData.EMAIL);
        resultForm.assertFormParam("Mobile", testData.NUMBER);
        resultForm.assertFormParam("Gender", testData.GENDER);
        resultForm.assertFormParam("Date of Birth", testData.updateDateOfBirth);
        resultForm.assertFormParam("Address", testData.ADDRESS);
    }
}
