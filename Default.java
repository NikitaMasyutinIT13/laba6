package masyutin.annotations;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания типа по умолчанию.
 * Целью может быть тип или поле.
 * Доступна во время исполнения программы.
 * Имеет обязательное свойство value типа Class.
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Default {

  /**
   * Обязательное свойство - класс по умолчанию.
   * @return класс по умолчанию
   */

  Class<?> value();
}