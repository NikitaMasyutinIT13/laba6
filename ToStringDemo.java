package masyutin.models;

import masyutin.annotations.ToString;

/**
 * Демонстрационный класс для аннотации @ToString.
 * Проаннотирован аннотацией @ToString, а одно из полей - с @ToString(Mode.NO).
 */

@ToString
public class ToStringDemo {

  @ToString
  private String name = "Test Object";

  @ToString
  private int id = 123;

  @ToString(ToString.Mode.NO)
  private String secret = "Confidential Data";

  @ToString
  private double value = 13.13;

  /**
   * Конструктор по умолчанию
   */
  public ToStringDemo() {}

  /**
   * Конструктор с параметрами
   * @param name имя
   * @param id идентификатор
   * @param secret секретные данные
   * @param value значение
   */
  public ToStringDemo(String name, int id, String secret, double value) {
    this.name = name;
    this.id = id;
    this.secret = secret;
    this.value = value;
  }
}