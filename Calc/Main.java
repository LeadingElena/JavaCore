package JavaCore.Calc;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1); // b = 0, делить на 0 нельзя
        int c = calc.devide.apply(a, b); // через проверку условия: если b = 0, возвращаем 0
        calc.println.accept(c);
    }
}
