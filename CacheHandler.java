package masyutin.handler;

import masyutin.annotations.Cache;
import java.util.Arrays;
/**
 * Обработчик для аннотации @Cache.
 * Выводит список всех кешируемых областей или сообщение, что список пуст.
 */
public class CacheHandler {

  /**
   * Обрабатывает класс, извлекая информацию из аннотации @Cache.
   * @param clazz класс для обработки
   * @throws IllegalArgumentException если класс равен null
   */
  public static void process(Class<?> clazz) {
    if (clazz == null) {
      throw new IllegalArgumentException("Класс не может быть null");
    }

    if (clazz.isAnnotationPresent(Cache.class)) {
      Cache annotation = clazz.getAnnotation(Cache.class);
      String[] cacheAreas = annotation.value();

      if (cacheAreas.length == 0) {
        System.out.println("Список кешируемых областей пуст");
      } else {
        System.out.println("Кешируемые области: " + Arrays.toString(cacheAreas));
        System.out.println("Количество областей: " + cacheAreas.length);
      }

    } else {
      System.out.println("Аннотация @Cache не найдена на классе " + clazz.getName());
    }
  }
}