package masyutin.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для автоматического вызова методов через Reflection API.
 * Целью может быть только МЕТОД.
 * Доступна во время исполнения программы.
 * Не имеет свойств.
 */
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface Invoke {
  }
