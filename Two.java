package masyutin.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация с двумя обязательными свойствами
 * Может применяться только к типам
 * Доступна во время выполнения программы
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Two {
  /**
   * Первое обязательное свойство - строка
   * @return строковое значение
   */
  String first();

  /**
   * Второе обязательное свойство - целое число
   * @return числовое значение
   */
  int second();
}