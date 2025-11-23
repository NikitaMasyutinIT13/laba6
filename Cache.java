package masyutin.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания кешируемых областей.
 * Целью может быть тип.
 * Доступна во время исполнения программы.
 * Имеет необязательное свойство value типа String[].
 * Значение по умолчанию: пустой массив.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
  /**
   * Необязательное свойство - массив кешируемых областей
   * По умолчанию пустой массив
   * @return массив кешируемых областей
   */
  String[] value() default {};
}