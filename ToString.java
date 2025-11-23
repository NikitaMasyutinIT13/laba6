package masyutin.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для управления строковым представлением.
 * Целью может быть ТИП или ПОЛЕ.
 * Доступна во время исполнения программы.
 * Имеет необязательное свойство value с вариантами YES или NO.
 * Значение по умолчанию: YES.
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ToString {

  /**
   * Перечисление режимов отображения
   */
  enum Mode {
    YES, NO
  }

  /**
   * Свойство для управления отображением
   * По умолчанию YES
   * @return режим отображения
   */
  Mode value() default Mode.YES;
}