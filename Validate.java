package masyutin.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания типов для валидации.
 * Целью может быть тип или аннотация.
 * Доступна во время исполнения программы.
 * Имеет обязательное свойство value типа Class[].
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
  /**
   * Обязательное свойство - массив классов для валидации
   * @return массив классов для валидации
   */
  Class<?>[] value();
}