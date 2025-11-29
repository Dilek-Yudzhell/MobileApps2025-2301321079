package com.example.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.calculator.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Espresso UI тест за основния сценарий
 * Тества основната функционалност на калкулатора
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * Тест: Основен сценарий - събиране на две числа
     * Стъпки:
     * 1. Натиска бутон 5
     * 2. Натиска бутон +
     * 3. Натиска бутон 3
     * 4. Натиска бутон =
     * 5. Проверява че резултатът е 8
     */
    @Test
    fun additionTest_fivePlusThree_equalsEight() {
        // Натискаме 5
        onView(withId(R.id.btn5)).perform(click())

        // Проверяваме че дисплея показва 5
        onView(withId(R.id.tvResult)).check(matches(withText("5")))

        // Натискаме +
        onView(withId(R.id.btnAdd)).perform(click())

        // Натискаме 3
        onView(withId(R.id.btn3)).perform(click())

        // Проверяваме че дисплея показва 3
        onView(withId(R.id.tvResult)).check(matches(withText("3")))

        // Натискаме =
        onView(withId(R.id.btnEquals)).perform(click())

        // Проверяваме че резултатът е 8
        onView(withId(R.id.tvResult)).check(matches(withText("8")))
    }

    /**
     * Тест: Бутон Clear изчиства дисплея
     */
    @Test
    fun clearButton_resetsDisplay() {
        // Въвеждаме число
        onView(withId(R.id.btn7)).perform(click())
        onView(withId(R.id.btn8)).perform(click())
        onView(withId(R.id.btn9)).perform(click())

        // Проверяваме че дисплея показва 789
        onView(withId(R.id.tvResult)).check(matches(withText("789")))

        // Натискаме C (Clear)
        onView(withId(R.id.btnClear)).perform(click())

        // Проверяваме че дисплея е изчистен (показва 0)
        onView(withId(R.id.tvResult)).check(matches(withText("0")))
    }

    /**
     * Тест: Деление на нула показва грешка
     */
    @Test
    fun divisionByZero_showsError() {
        // 10 / 0 = Грешка
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.btn0)).perform(click())
        onView(withId(R.id.btnDivide)).perform(click())
        onView(withId(R.id.btn0)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())

        // Проверяваме че се показва грешка
        onView(withId(R.id.tvResult)).check(matches(withText("Грешка")))
    }

    /**
     * Тест: Всички бутони за цифри са видими
     */
    @Test
    fun allNumberButtons_areDisplayed() {
        onView(withId(R.id.btn0)).check(matches(isDisplayed()))
        onView(withId(R.id.btn1)).check(matches(isDisplayed()))
        onView(withId(R.id.btn2)).check(matches(isDisplayed()))
        onView(withId(R.id.btn3)).check(matches(isDisplayed()))
        onView(withId(R.id.btn4)).check(matches(isDisplayed()))
        onView(withId(R.id.btn5)).check(matches(isDisplayed()))
        onView(withId(R.id.btn6)).check(matches(isDisplayed()))
        onView(withId(R.id.btn7)).check(matches(isDisplayed()))
        onView(withId(R.id.btn8)).check(matches(isDisplayed()))
        onView(withId(R.id.btn9)).check(matches(isDisplayed()))
    }

    /**
     * Тест: Всички бутони за операции са видими
     */
    @Test
    fun allOperationButtons_areDisplayed() {
        onView(withId(R.id.btnAdd)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSubtract)).check(matches(isDisplayed()))
        onView(withId(R.id.btnMultiply)).check(matches(isDisplayed()))
        onView(withId(R.id.btnDivide)).check(matches(isDisplayed()))
        onView(withId(R.id.btnEquals)).check(matches(isDisplayed()))
    }
}
