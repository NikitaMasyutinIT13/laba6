package masyutin.handler;

import masyutin.annotations.Default;

/**
 Обработчик для аннотации @Default
 Извлекает и выводит информацию о классе по умолчанию
 */
public class DefaultHandler {
  /**
   * Обрабатывает класс, извлекая информацию из аннотации @Default.
   * @param clazz класс для обработки
   * @throws IllegalArgumentException если класс равен null
   */
  public static void process(Class<?> clazz) {
    if (clazz == null) {
      throw new IllegalArgumentException("Класс не может быть null");
    }

    if (clazz.isAnnotationPresent(Default.class)) {
      Default annotation = clazz.getAnnotation(Default.class);
      Class<?> defaultClass = annotation.value();
      System.out.println("Класс по умолчанию: " + defaultClass.getName());
    } else {
      System.out.println("Аннотация @Default не найдена на классе " + clazz.getName());
    }
  }
}