package masyutin.handler;

import masyutin.annotations.ToString;
import java.lang.reflect.Field;

/**
 * Обработчик для аннотации @ToString.
 * Формирует строковое представление объекта, учитывая только те поля, где @ToString имеет значение YES.
 */
public class ToStringHandler {

  /**
   * Формирует строковое представление объекта
   * @param obj объект для преобразования
   * @return строковое представление
   */
  public static String toString(Object obj) {
    if (obj == null) {
      return "null";
    }

    Class<?> clazz = obj.getClass();
    StringBuilder sb = new StringBuilder();
    sb.append(clazz.getSimpleName()).append("{");

    Field[] fields = clazz.getDeclaredFields();
    boolean firstField = true;

    for (Field field : fields) {
      ToString classAnnotation = clazz.getAnnotation(ToString.class);
      ToString fieldAnnotation = field.getAnnotation(ToString.class);

      boolean includeField = (classAnnotation != null &&
              (fieldAnnotation == null || fieldAnnotation.value() == ToString.Mode.YES)) ||
              (fieldAnnotation != null && fieldAnnotation.value() == ToString.Mode.YES);

      if (includeField) {
        try {
          field.setAccessible(true);
          Object value = field.get(obj);

          if (!firstField) {
            sb.append(", ");
          }

          sb.append(field.getName()).append("=").append(value);
          firstField = false;

        } catch (IllegalAccessException e) {
          sb.append(field.getName()).append("=N/A");
        }
      }
    }

    sb.append("}");
    return sb.toString();
  }
}