import masyutin.models.*;
import masyutin.handler.*;
import masyutin.test.ValidateTest;
import masyutin.test.TwoTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import java.util.Scanner;

/**
 * Главный класс приложения для демонстрации работы всех аннотаций и обработчиков.
 * Предоставляет пользовательское меню для навигации по функциональности.
 */
public class Main {

  private static Scanner scanner = new Scanner(System.in);

  /**
   * Главный метод приложения.
   * Запускает пользовательское меню и управляет выполнением программы.
   *
   * @param args аргументы командной строки (не используются)
   */
  public static void main(String[] args) {
    showMainMenu();
    scanner.close();
  }

  /**
   * Отображает главное меню приложения.
   * Предоставляет доступ ко всей функциональности программы.
   */
  private static void showMainMenu() {
    while (true) {
      System.out.println("\n=== ДЕМОНСТРАЦИЯ АННОТАЦИЙ ===");
      System.out.println("1. Демонстрация всех аннотаций");
      System.out.println("2. Демонстрация отдельной аннотации");
      System.out.println("3. Запуск тестов");
      System.out.println("4. Демонстрация ошибок валидации");
      System.out.println("0. Выход");
      System.out.print("Выберите пункт: ");

      int choice = readIntInput();

      switch (choice) {
        case 1:
          demonstrateAllAnnotations();
          break;
        case 2:
          showAnnotationMenu();
          break;
        case 3:
          runTestsMenu();
          break;
        case 4:
          demonstrateValidationErrors();
          break;
        case 0:
          System.out.println("Выход из программы.");
          return;
        default:
          System.out.println("Неверный выбор. Попробуйте снова.");
      }
    }
  }

  /**
   * Отображает меню выбора отдельной аннотации для демонстрации.
   * Позволяет пользователю выбрать конкретную аннотацию для просмотра.
   */
  private static void showAnnotationMenu() {
    while (true) {
      System.out.println("\n=== ВЫБОР АННОТАЦИИ ===");
      System.out.println("1. @Invoke - автовызов методов");
      System.out.println("2. @Default - тип по умолчанию");
      System.out.println("3. @ToString - строковое представление");
      System.out.println("4. @Validate - валидация типов");
      System.out.println("5. @Two - два свойства");
      System.out.println("6. @Cache - кешируемые области");
      System.out.println("0. Назад");
      System.out.print("Выберите аннотацию: ");

      int choice = readIntInput();

      if (choice == 0) break;

      switch (choice) {
        case 1:
          demonstrateInvoke();
          break;
        case 2:
          demonstrateDefault();
          break;
        case 3:
          demonstrateToString();
          break;
        case 4:
          demonstrateValidate();
          break;
        case 5:
          demonstrateTwo();
          break;
        case 6:
          demonstrateCache();
          break;
        default:
          System.out.println("Неверный выбор.");
      }

      System.out.print("\nНажмите Enter для продолжения...");
      scanner.nextLine();
    }
  }

  /**
   * Отображает меню запуска тестов.
   * Предоставляет возможность запуска отдельных тестов или всех тестов.
   */
  private static void runTestsMenu() {
    System.out.println("\n=== ЗАПУСК ТЕСТОВ ===");
    System.out.println("1. Запуск всех тестов");
    System.out.println("2. Тест @Validate");
    System.out.println("3. Тест @Two");
    System.out.println("0. Назад");
    System.out.print("Выберите тест: ");

    int choice = readIntInput();

    JUnitCore junit = new JUnitCore();

    switch (choice) {
      case 1:
        System.out.println("\nЗапуск всех тестов...");
        Result result1 = junit.run(ValidateTest.class, TwoTest.class);
        printSimpleTestResult(result1, "Все тесты");
        break;
      case 2:
        System.out.println("\nЗапуск тестов @Validate...");
        Result result2 = junit.run(ValidateTest.class);
        printSimpleTestResult(result2, "ValidateTest");
        break;
      case 3:
        System.out.println("\nЗапуск тестов @Two...");
        Result result3 = junit.run(TwoTest.class);
        printSimpleTestResult(result3, "TwoTest");
        break;
      case 0:
        return;
      default:
        System.out.println("Неверный выбор.");
    }

    System.out.print("\nНажмите Enter для продолжения...");
    scanner.nextLine();
  }

  /**
   * Демонстрирует работу всех аннотаций последовательно.
   * Показывает примеры использования каждой аннотации в проекте.
   */
  private static void demonstrateAllAnnotations() {
    System.out.println("\n=== ДЕМОНСТРАЦИЯ ВСЕХ АННОТАЦИЙ ===");

    demonstrateInvoke();
    System.out.println();
    demonstrateDefault();
    System.out.println();
    demonstrateToString();
    System.out.println();
    demonstrateValidate();
    System.out.println();
    demonstrateTwo();
    System.out.println();
    demonstrateCache();

    System.out.print("\nНажмите Enter для продолжения...");
    scanner.nextLine();
  }

  /**
   * Демонстрирует обработку ошибок валидации для аннотаций @Two и @Validate.
   * Показывает, как обработчики реагируют на некорректные значения.
   */
  private static void demonstrateValidationErrors() {
    System.out.println("\n=== ДЕМОНСТРАЦИЯ ОШИБОК ВАЛИДАЦИИ ===");

    System.out.println("1. @Two с пустой строкой и отрицательным числом:");
    @masyutin.annotations.Two(first = "", second = -1)
    class InvalidTwoDemo {}

    try {
      TwoHandler.process(InvalidTwoDemo.class);
    } catch (IllegalArgumentException e) {
      System.out.println("   Ошибка: " + e.getMessage());
    }

    System.out.println("2. @Validate с пустым массивом:");
    @masyutin.annotations.Validate({})
    class InvalidValidateDemo {}

    try {
      ValidateHandler.process(InvalidValidateDemo.class);
    } catch (IllegalArgumentException e) {
      System.out.println("   Ошибка: " + e.getMessage());
    }

    System.out.println("3. @Two с корректными значениями:");
    @masyutin.annotations.Two(first = "Корректная строка", second = 42)
    class ValidTwoDemo {}

    try {
      TwoHandler.process(ValidTwoDemo.class);
      System.out.println("   Успешно обработано");
    } catch (IllegalArgumentException e) {
      System.out.println("   Ошибка: " + e.getMessage());
    }

    System.out.print("\nНажмите Enter для продолжения...");
    scanner.nextLine();
  }

  /**
   * Демонстрирует работу аннотации @Invoke.
   * Показывает автоматический вызов методов, помеченных аннотацией @Invoke.
   */
  private static void demonstrateInvoke() {
    System.out.println("=== @Invoke ===");
    System.out.println("Автоматический вызов методов с аннотацией @Invoke:");
    InvokeHandler.process(new InvokeDemo());
  }

  /**
   * Демонстрирует работу аннотации @Default.
   * Показывает извлечение и отображение класса по умолчанию.
   */
  private static void demonstrateDefault() {
    System.out.println("=== @Default ===");
    DefaultHandler.process(DefaultDemo.class);
  }

  /**
   * Демонстрирует работу аннотации @ToString.
   * Показывает формирование строкового представления с учетом аннотаций.
   */
  private static void demonstrateToString() {
    System.out.println("=== @ToString ===");
    ToStringDemo toStringDemo = new ToStringDemo();
    System.out.println("Результат: " + ToStringHandler.toString(toStringDemo));
    System.out.println("Поле 'secret' исключено благодаря @ToString(Mode.NO)");
  }

  /**
   * Демонстрирует работу аннотации @Validate.
   * Показывает извлечение и отображение списка классов для валидации.
   */
  private static void demonstrateValidate() {
    System.out.println("=== @Validate ===");
    ValidateHandler.process(ValidateDemo.class);
  }

  /**
   * Демонстрирует работу аннотации @Two.
   * Показывает извлечение и отображение двух свойств аннотации.
   */
  private static void demonstrateTwo() {
    System.out.println("=== @Two ===");
    TwoHandler.process(TwoDemo.class);
  }

  /**
   * Демонстрирует работу аннотации @Cache.
   * Показывает извлечение и отображение списка кешируемых областей.
   */
  private static void demonstrateCache() {
    System.out.println("=== @Cache ===");
    CacheHandler.process(CacheDemo.class);
  }

  /**
   * Выводит упрощенные результаты тестирования.
   *
   * @param result результаты выполнения тестов
   * @param testName название теста или группы тестов
   */
  private static void printSimpleTestResult(Result result, String testName) {
    if (result.wasSuccessful()) {
      System.out.println(" " + testName + ": ПРОЙДЕНЫ");
    } else {
      System.out.println(" " + testName + ": ОШИБКИ (" + result.getFailureCount() + " из " + result.getRunCount() + ")");
    }
  }

  /**
   * Читает целочисленный ввод от пользователя с обработкой ошибок.
   * Повторяет запрос до получения корректного числового значения.
   *
   * @return введенное пользователем целое число
   */
  private static int readIntInput() {
    while (true) {
      try {
        return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.print("Введите число: ");
      }
    }
  }
}