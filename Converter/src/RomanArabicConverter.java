import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class RomanArabicConverter {
    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            RomanNumeral[] values = RomanNumeral.values();
            Collections.reverse(Arrays.asList(values));
            return Arrays.asList(values);
        }
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase(); // Переводим строку в верхний регистр
        if (!validateRoman(romanNumeral)) // Проверяем строку через регулярное выражение
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int result = 0;
        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) { // Проходимся по списку римских цифр
            RomanNumeral symbol = (RomanNumeral) romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) { // Если input начинается с названия symbol
                result += symbol.getValue(); // Прибавляем значение symbol к результату
                romanNumeral = romanNumeral.substring(symbol.name().length()); // Удаляем подстроку symbol из input
            } else
                i++; // Переходим к следующему символу
        }

        if (romanNumeral.length() > 0)
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000))
            throw new IllegalArgumentException(number + " is not in range (0,4000]");

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        StringBuilder sb = new StringBuilder();
        int i = 0;

        while ((number > 0) && (i < romanNumerals.size())) { // Проходимся по списку римских цифр
            RomanNumeral currentSymbol = (RomanNumeral) romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) { // Если римская <= number
                sb.append(currentSymbol.name()); // Записываем римскую цифру в строку
                number -= currentSymbol.getValue(); // Вычитаем значение римской цифры из числа
            } else
                i++; // Переходим к следующему символу
        }

        return sb.toString();
    }

    private static boolean validateRoman(String word) {
        return word.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    }

    public static void main(String[] args) {
        /*
         * 1) Возможность записи всех римских чисел (I, V, X, M, C, L, D)
         * 2) Проверка на правильность ввода чисел (не более трёх одинаковых чисел
         * подряд, ввод чисел по уменьшению)
         * 3) Перевод римских чисел в арабские
         * 4) Проверка на ввод реальных символов, соответствующих римским (ошибка при
         * вводе символа A (например))
         * 4*) Дополнительное задание: перевод арабских чисел в римские
         */
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("1 - Arabic->Roman, 2 - Roman->Arabic, 3 - Exit: ");
            int choice = in.nextInt();
            in.nextLine();

            if (choice == 1) {
                System.out.print("\nArabic value: ");
                int arabic = in.nextInt();
                try {
                    System.out.println("Roman value: " + RomanArabicConverter.arabicToRoman(arabic) + "\n");
                } catch (Exception e) {
                    System.out.println(e);
                }

            } else if (choice == 2) {
                System.out.print("\nRoman value: ");
                String roman = in.nextLine();
                try {
                    System.out.println("Arabic value: " + RomanArabicConverter.romanToArabic(roman) + "\n");
                } catch (Exception e) {
                    System.out.println(e.getClass());
                }
            } else {
                break;
            }
        }
        in.close();
    }
}
