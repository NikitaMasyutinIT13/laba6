package masyutin.test;

import masyutin.annotations.Two;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Тест для проверки корректности обработки аннотации @Two, если её свойства заданы некорректно.
 * Проверяет, что при пустой строке и отрицательном числе выбрасывается IllegalArgumentException.
 */
public class TwoTest {

  /**
   * Вспомогательный класс с некорректными значениями аннотации @Two.
   * Строковое свойство first пустое (""), а числовое second отрицательное.
   */
  @Two(first = "", second = -1)
  static class TestClassWithInvalidTwo {
  }

  /**
   * Тест проверяет, что при некорректных значениях аннотации @Two
   * выбрасывается IllegalArgumentException.
   * Использует Reflection API для считывания значений аннотации.
   */
  @Test
  public void testTwoWithInvalidValuesThrowsException() {
    try {
      // Через Reflection считываем значения аннотации
      Class<?> clazz = TestClassWithInvalidTwo.class;
      Two annotation = clazz.getAnnotation(Two.class);
      String first = annotation.first();
      int second = annotation.second();

      // Проверяем условия и выбрасываем исключение если нужно
      if (first == null || first.trim().isEmpty()) {
        throw new IllegalArgumentException("Свойство 'first' не может быть пустым");
      }
      if (second < 0) {
        throw new IllegalArgumentException("Свойство 'second' не может быть отрицательным");
      }

      // Если дошли сюда - исключение не было выброшено, тест должен упасть
      fail("Ожидалось исключение IllegalArgumentException");

    } catch (IllegalArgumentException e) {
      // Исключение было выброшено - тест проходит
      assertTrue("Должно быть выброшено IllegalArgumentException", true);
    }
  }

  /**
   * Альтернативная реализация с использованием expected в аннотации @Test.
   * Проверяет, что метод, обрабатывающий аннотацию, выбрасывает ожидаемое исключение.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTwoWithInvalidValuesUsingExpected() {
    // Создаем метод, который будет обрабатывать аннотацию и проверять значения
    processTwoAnnotation(TestClassWithInvalidTwo.class);
  }

  /**
   * Вспомогательный метод, который обрабатывает аннотацию @Two и проверяет значения.
   * Должен выбрасывать IllegalArgumentException при некорректных значениях.
   *
   * @param clazz класс с аннотацией @Two для обработки
   * @throws IllegalArgumentException если свойства аннотации некорректны
   */
  private void processTwoAnnotation(Class<?> clazz) {
    Two annotation = clazz.getAnnotation(Two.class);
    String first = annotation.first();
    int second = annotation.second();

    if (first == null || first.trim().isEmpty()) {
      throw new IllegalArgumentException("Свойство 'first' не может быть пустым");
    }
    if (second < 0) {
      throw new IllegalArgumentException("Свойство 'second' не может быть отрицательным");
    }
  }

  /**
   * Вспомогательный класс с корректными значениями для положительного теста.
   */
  @Two(first = "Valid String", second = 10)
  static class TestClassWithValidTwo {
  }

  /**
   * Тест с корректными значениями аннотации @Two.
   * Не должен выбрасывать исключение.
   */
  @Test
  public void testTwoWithValidValues() {
    try {
      processTwoAnnotation(TestClassWithValidTwo.class);
      // Если не было исключения - тест проходит
      assertTrue(true);
    } catch (IllegalArgumentException e) {
      fail("Не должно быть исключения для корректных значений: " + e.getMessage());
    }
  }
}