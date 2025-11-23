package masyutin.handler;

import masyutin.annotations.Two;

/**
 * Обработчик для аннотации @Two.
 * Считывает и выводит значения свойств аннотации.
 */
public class TwoHandler {

  /**
   * Обрабатывает класс, извлекая информацию из аннотации @Two.
   * @param clazz класс для обработки
   * @throws IllegalArgumentException если класс равен null или свойства некорректны
   */
  public static void process(Class<?> clazz) {
    if (clazz == null) {
      throw new IllegalArgumentException("Класс не может быть null");
    }

    if (clazz.isAnnotationPresent(Two.class)) {
      Two annotation = clazz.getAnnotation(Two.class);
      String first = annotation.first();
      int second = annotation.second();

      if (first == null || first.trim().isEmpty()) {
        throw new IllegalArgumentException("Свойство 'first' не может быть пустым");
      }

      if (second < 0) {
        throw new IllegalArgumentException("Свойство 'second' не может быть отрицательным");
      }

      System.out.println("Свойство first: " + first);
      System.out.println("Свойство second: " + second);

    } else {
      System.out.println("Аннотация @Two не найдена на классе " + clazz.getName());
    }
  }

}