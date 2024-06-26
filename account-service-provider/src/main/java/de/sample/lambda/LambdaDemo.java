package de.sample.lambda;

import java.util.*;
import java.util.function.*;

public class LambdaDemo {

  static double sum(double d1, double d2) {
    return d1 + d2;
  }

  static double sum2() {
    double result = 0.0;
    while (result <= 10) {
      result = result + Math.random();
    }
    return result;
  }

  @FunctionalInterface
  interface NumberGenerator {
    double nextDouble();
  }

  static double sum3(NumberGenerator numberGenerator) {
    double result = 0.0;
    while (result <= 10) {
      result = result + numberGenerator.nextDouble();
    }
    return result;
  }

  public static void main(String[] args) {

    // Addieren zweier Zahlen
    System.out.println(sum(3.4, 2.5));
    // Addieren zweier Zufallszahlen
    System.out.println(sum(Math.random(), Math.random()));
    System.out.println(sum(Math.random(), Math.random()));
    // Addiere Zufallszahlen solange, bis die Summe>10
    System.out.println(sum2());
    System.out.println(sum2());
    // Die Entscheidung, dass es Zufallszahlen sind, MUSS in main()-Methode fallen
    // Der Algorithmus soll in der sum-Methode bleiben.
    System.out.println(sum3(new NumberGenerator() {
      @Override
      public double nextDouble() {
        return Math.random();
      }
    }));
    System.out.println(sum3(new NumberGenerator() {
      @Override
      public double nextDouble() {
        return 0.4;
      }
    }));
    // Kurzschreibweise
    // Sonderfall: Functional Interface
    // Entfernen der Redundanzen für den Compiler
    System.out.println(sum3(/*new NumberGenerator() {
      @Override
      public double nextDouble*/() -> {
        return Math.random();
      }
      /*}*/));
    System.out.println(sum3(() -> {
        return Math.random();
      }
    ));
    // Sonderfall: nur 1 Anweisung in nextDouble()
    System.out.println(sum3(() -> Math.random()));
    // Sonderfall: Parameter matchen: nextDouble() -> random()
    System.out.println(sum3(Math::random));
    // :: = Method Reference Operator

    // Gibt es bereits Functional Interfaces?
    Supplier<Double> s1 = Math::random;
    System.out.println(s1.get());
    DoubleSupplier ds = Math::random;
    Supplier<Date> dateSupplier = Date::new;
    Date d = dateSupplier.get();
    System.out.println(ds.getAsDouble());
    Consumer<String> c1 = System.out::println;
    c1.accept("Hallo Welt");
    Function<Integer, String> f1 = Integer::toHexString;
    System.out.println(f1.apply(1467));
    Predicate<String> p1 = s -> s.toLowerCase().startsWith("hallo");
    System.out.println(p1.test("gelbekatze"));

    // Anwendungsfälle: Streams, Optional
    Collection<String> namen = List.of("Simon", "Oraz", "Silvia", "Hannes", "Klaus");
    // Anforderung: gesucht sind alle kurzen Namen (<6) in Großbuchstaben
    {
      Collection<String> kurznamen = new LinkedList<>();
      for(String name: namen) {
        if(name.length()<6) {
          kurznamen.add(name.toUpperCase());
        }
      }
      System.out.println(kurznamen);
    }
    {
      System.out.println(
        namen.stream()
          .filter(s -> s.length()<6)
          .map(String::toUpperCase)
          .toList()
      );
    }
    Optional<String> ersterName = namen.stream()
      .filter(s -> s.length() < 6)
      .map(String::toUpperCase)
      .findFirst();
    Optional<Integer> i = ersterName.map(String::length);
    Integer result = i.orElseThrow(IllegalStateException::new);

    // https://www.baeldung.com/java-8-streams-introduction
    // https://www.baeldung.com/java-optional

  }

}
