package JavaCore.Calc;

import org.w3c.dom.ls.LSOutput;

public class Calculator {
    public interface Supplier <T> {
        T get();
    }

    public interface BinaryOperator <T> {
        T apply(T t1, T t2);
    }

    public interface UnaryOperator <T> {
        T apply(T t1);
    }

    public interface Predicate <T> {
        boolean test(T t);
    }

    public interface Consumer <T> {
        void accept(T t);
    }

    static Supplier<Calculator> instance = Calculator::new;

    BinaryOperator<Integer> plus = (x, y) -> x + y;
    BinaryOperator<Integer> minus = (x, y) -> x - y;
    BinaryOperator<Integer> multiply = (x, y) -> x * y;
    BinaryOperator<Integer> devide = (x, y) -> {
        if (y == 0) {
            throw new ArithmeticException("b = 0, деление на ноль запрещено!");
        } else {
            return x / y;
        }
    };

    UnaryOperator<Integer> pow = x -> x * x;
    UnaryOperator<Integer> abs = x -> x > 0 ? x : x * -1;
    Predicate<Integer> isPositive = x -> x > 0;
    Consumer<Integer> println = System.out::println;
}
