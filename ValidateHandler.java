package masyutin.handler;

import masyutin.annotations.Validate;
import java.util.Arrays;
/**
 * Обработчик для аннотации @Validate.
 * Выводит, какие классы указаны в аннотации.
 */
public class ValidateHandler {

  /**
   * Обрабатывает класс, извлекая информацию из аннотации @Validate.
   * @param clazz класс для обработки
   * @throws IllegalArgumentException если класс равен null или массив классов пустой
   */
  public static void process(Class<?> clazz) {
    if (clazz == null) {
      throw new IllegalArgumentException("Класс не может быть null");
    }

    if (clazz.isAnnotationPresent(Validate.class)) {
      Validate annotation = clazz.getAnnotation(Validate.class);
      Class<?>[] validationClasses = annotation.value();

      if (validationClasses.length == 0) {
        throw new IllegalArgumentException("Массив классов для валидации не может быть пустым");
      }

      System.out.println("Классы для валидации: " + Arrays.toString(validationClasses));
      System.out.println("Количество классов для валидации: " + validationClasses.length);

    } else {
      System.out.println("Аннотация @Validate не найдена на классе " + clazz.getName());
    }
  }
}