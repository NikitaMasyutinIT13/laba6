package masyutin.models;

import masyutin.annotations.Validate;

/**
 * Демонстрационный класс для аннотации @Validate
 * Указывает типы для валидации
 */
@Validate({String.class, Integer.class, Double.class})
public class ValidateDemo {

}