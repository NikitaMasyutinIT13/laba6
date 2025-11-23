package masyutin.handler;
import masyutin.annotations.Invoke;
import java.lang.reflect.Method;
/**
 * Обработчик для аннотации @Invoke через Reflection API.
 * Находит методы, отмеченные аннотацией @Invoke и вызывает их автоматически.
 */
public class InvokeHandler {

  /**
   * Обрабатывает объект, находя и вызывая методы с аннотацией @Invoke.
   *
   * @param obj объект для обработки
   * @throws IllegalArgumentException если объект равен null
   */

  public static void process(Object obj) {
    if (obj == null) {
      throw new IllegalArgumentException("Объект не может быть null");
    }

    Class<?> clazz = obj.getClass();
    Method[] methods = clazz.getDeclaredMethods();

    int invokedCount = 0;

    for (Method method : methods) {
      if (method.isAnnotationPresent(Invoke.class)) {
        try {
          method.setAccessible(true);
          method.invoke(obj);
          invokedCount++;
        } catch (Exception e) {
          System.err.println("Ошибка при вызове метода " + method.getName() + ": " + e.getMessage());
        }
      }
    }

    System.out.println("Вызвано методов с @Invoke: " + invokedCount);
  }
}