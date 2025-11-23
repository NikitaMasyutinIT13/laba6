package masyutin.models;

import masyutin.annotations.Invoke;

/**
 * Демонстрационный класс для аннотации @Invoke.
 * Содержит несколько методов, один из которых проаннотирован аннотацией @Invoke.
 */
public class InvokeDemo {

  private String message = "Привет от InvokeDemo";

/**
 * Метод, отмеченный аннотацией @Invoke.
 * Будет автоматически вызван обработчиком.
 */
  @Invoke
  public void annotatedMethod() {
    System.out.println("Вызван метод с аннотацией @Invoke: " + message);
  }

  /**
   * Обычный метод без аннотации.
   * Не будет вызван автоматически.
   */
  public void regularMethod() {
    System.out.println("Вызван обычный метод");
  }

  /**
   * Еще один метод с аннотацией @Invoke.
   */
  @Invoke
  public void anotherAnnotatedMethod() {
    System.out.println("Вызван другой метод с аннотацией @Invoke");
  }
}