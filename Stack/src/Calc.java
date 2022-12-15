//Калькулятор арифметических формул.
public class Calc extends Compf {
    private StackDouble s;
    private static String OUTPUT = "";

    @Override
    protected int symOther(char c) throws Exception {
        if ((c < '0' || c > '9') && c != '.' && c != 'p' && c != 'e') {
            System.out.println("Недопустимый символ: " + c);
            throw new Exception("Недопустимый символ: " + c);
        }

        return SYM_OTHER;
    }

    @Override
    protected void nextOper(char c) {
        double second = s.pop();
        double first = s.pop();

        switch (c) {
            case '+':
                s.push(first + second);
                break;
            case '-':
                s.push(first - second);
                break;
            case '*':
                s.push(first * second);
                break;
            case '/':
                s.push(first / second);
                break;
            case '^':
                s.push(Math.pow(first, second));
                break;
        }
    }

    @Override
    protected void nextOther(char c) {
        if (!isOper) { // если мы попали сюда из SYM_OTHER
            if (c == 'p')
                OUTPUT += Double.toString(Math.PI);
            else if (c == 'e')
                OUTPUT += Double.toString(Math.E);
            else
                OUTPUT += c; // значит у нас обрабатывается цифра, записываем её.
        } else { // иначе (если попали из case SYM_RIGHT || case SYM_OPER)
            if (OUTPUT.length() != 0) { // если есть отложеное число
                s.push(Double.parseDouble(OUTPUT)); // вносим в числовой стек
                OUTPUT = ""; // очищаем строку чисел
            } else {
                if (c != ')') {
                    if (isAfterBracket) {
                        isUnar = false;
                        isAfterBracket = false;
                    } else {
                        OUTPUT += c;
                        isUnar = true;
                    }
                }
            }
            isOper = false; // изменяем переменную, чтобы начать обрабатывать оператор/скобку и после
                            // добавлять новые числа
        }
    }

    public Calc() {
        s = new StackDouble();
    }

    public final double compile(char[] str) throws Exception {
        super.compile(str);
        isOper = false;
        isUnar = false;
        isAfterBracket = false;
        System.out.println("" + s.top());
        return s.top();
    }
}