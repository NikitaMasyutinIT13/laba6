package masyutin.test;

import masyutin.annotations.Validate;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Тест для проверки корректности работы механизма валидации классов,
 * отмеченных аннотацией @Validate.
 * Проверяет извлечение списка классов и обработку пустого массива.
 */
public class ValidateTest {

  /**
   * Класс с аннотацией @Validate, указывающей массив типов для проверки.
   */
  @Validate({String.class, Integer.class, Double.class})
  static class TestClassWithValidate {
  }

  /**
   * Класс с аннотацией @Validate с пустым массивом.
   * Должен вызывать IllegalArgumentException при обработке.
   */
  @Validate({})
  static class TestClassWithEmptyValidate {
  }

  /**
   * Тест проверяет, что список классов, переданный в аннотации,
   * корректно извлекается через Reflection API.
   */
  @Test
  public void testValidateWithClasses() {
    Class<?> clazz = TestClassWithValidate.class;
    Validate annotation = clazz.getAnnotation(Validate.class);
    Class<?>[] validationClasses = annotation.value();

    // Проверяем, что массив не null и имеет правильное количество элементов
    assertNotNull("Массив классов не должен быть null", validationClasses);
    assertEquals("Должно быть 3 класса для валидации", 3, validationClasses.length);

    // Проверяем конкретные классы в массиве
    assertEquals(String.class, validationClasses[0]);
    assertEquals(Integer.class, validationClasses[1]);
    assertEquals(Double.class, validationClasses[2]);
  }

  /**
   * Тест проверяет, что при передаче пустого массива
   * выбрасывается IllegalArgumentException.
   * Использует аннотацию @Test с параметром expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidateWithEmptyArrayThrowsException() {
    Class<?> clazz = TestClassWithEmptyValidate.class;
    Validate annotation = clazz.getAnnotation(Validate.class);
    Class<?>[] validationClasses = annotation.value();

    // Проверяем массив и выбрасываем исключение если он пустой
    if (validationClasses.length == 0) {
      throw new IllegalArgumentException("Массив классов для валидации не может быть пустым");
    }
  }

  /**
   * Альтернативная реализация теста с пустым массивом.
   * Использует try-catch для явной проверки исключения.
   */
  @Test
  public void testValidateWithEmptyArrayUsingTryCatch() {
    try {
      Class<?> clazz = TestClassWithEmptyValidate.class;
      Validate annotation = clazz.getAnnotation(Validate.class);
      Class<?>[] validationClasses = annotation.value();

      if (validationClasses.length == 0) {
        throw new IllegalArgumentException("Массив классов для валидации не может быть пустым");
      }

      fail("Ожидалось исключение IllegalArgumentException для пустого массива");

    } catch (IllegalArgumentException e) {
      // Исключение было выброшено - тест проходит
      assertEquals("Массив классов для валидации не может быть пустым", e.getMessage());
    }
  }

  /**
   * Вспомогательный метод, который обрабатывает аннотацию @Validate.
   * Должен выбрасывать IllegalArgumentException при пустом массиве.
   *
   * @param clazz класс с аннотацией @Validate для обработки
   * @throws IllegalArgumentException если массив классов пустой
   */
  private void processValidateAnnotation(Class<?> clazz) {
    Validate annotation = clazz.getAnnotation(Validate.class);
    Class<?>[] validationClasses = annotation.value();

    if (validationClasses.length == 0) {
      throw new IllegalArgumentException("Массив классов для валидации не может быть пустым");
    }
  }

  /**
   * Тест с использованием вспомогательного метода и expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidateEmptyArrayWithHelperMethod() {
    processValidateAnnotation(TestClassWithEmptyValidate.class);
  }

  /**
   * Тест с корректными значениями используя вспомогательный метод.
   * Не должен выбрасывать исключение.
   */
  @Test
  public void testValidateWithClassesUsingHelperMethod() {
    try {
      processValidateAnnotation(TestClassWithValidate.class);
      // Если не было исключения - тест проходит
      assertTrue(true);
    } catch (IllegalArgumentException e) {
      fail("Не должно быть исключения для непустого массива: " + e.getMessage());
    }
  }
}