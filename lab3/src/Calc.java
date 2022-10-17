//Калькулятор арифметических формул.
public class Calc extends Compf {
    private StackInt s;
    private static String OUTPUT = "";

    // private static int char2int(char c){
    // return (int)c - (int)'0';
    // }

    @Override
    protected int symOther(char c) throws Exception {
        if (c < '0' || c > '9') {
            System.out.println("Недопустимый символ: " + c);
            // System.exit(0);
            throw new Exception("Недопустимый символ: " + c);
        }

        return SYM_OTHER;
    }

    @Override
    protected void nextOper(char c) {
        int second = s.pop();
        int first = s.pop();

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
                int result = 1;
                for (int i = 1; i <= second; i++)
                    result *= first;
                s.push(result);
                break;
        }
    }

    @Override
    protected void nextOther(char c) {
        if (!isOper) { // если мы попали сюда из SYM_OTHER
            OUTPUT += c; // значит у нас обрабатывается цифра, записываем её.
        } else { // иначе (если попали из case SYM_RIGHT || case SYM_OPER)
            if (OUTPUT.length() != 0) { // если есть отложеное число
                s.push(Integer.parseInt(OUTPUT)); // вносим в числовой стек
                OUTPUT = ""; // очищаем строку чисел
            }
            isOper = false; // изменяем переменную, чтобы начать обрабатывать оператор/скобку и после
                            // добавлять новые числа
        }
        // s.push(char2int(c));
        // s.push(Character.getNumericValue(c));
    }

    public Calc() {
        s = new StackInt();
    }

    public final int compile(char[] str) throws Exception {
        super.compile(str);

        System.out.println("" + s.top());
        return s.top();
    }
}