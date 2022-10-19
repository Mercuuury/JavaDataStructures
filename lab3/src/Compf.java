//Стековый компилятор формул.
public class Compf extends Stack {
    // Типы символов (скобки, знаки операций, иное).
    protected final static int SYM_LEFT = 0,
            SYM_RIGHT = 1,
            SYM_OPER = 2,
            SYM_OTHER = 3;
    protected boolean isOper, isUnar, isAfterBracket = false;

    private int symType(char c) throws Exception {
        switch (c) {
            case '(':
                return SYM_LEFT;
            case ')':
                return SYM_RIGHT;
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
                return SYM_OPER;
            default:
                return symOther(c);
        }
    }

    private void processSymbol(char c) throws Exception {
        switch (symType(c)) {
            case SYM_LEFT:
                push(c);
                break;
            case SYM_RIGHT:
                isOper = true; // убедимся, что в стеке есть числа в случае необходимости их обработать
                nextOther(c); // переходим в nextOther() со значением isOper = true;
                processSuspendedSymbols(c);
                pop();
                isAfterBracket = true;
                break;
            case SYM_OPER:
                isOper = true; // убедимся, что в стеке есть числа в случае необходимости их обработать
                nextOther(c); // переходим в nextOther() со значением isOper = true;
                if (!isUnar) {
                    processSuspendedSymbols(c);
                    push(c);
                }
                isUnar = false;
                break;
            case SYM_OTHER:
                nextOther(c);
                break;
        }
    }

    private void processSuspendedSymbols(char c) throws Exception {
        while (precedes(top(), c))
            nextOper(pop());
    }

    private int priority(char c) {
        if (c == '+' || c == '-')
            return 1;
        else if (c == '*' || c == '/')
            return 2;
        else
            return 3; // Самым высоким приоритетом обладает оператор возведения в степень
    }

    private boolean precedes(char a, char b) throws Exception {
        if (symType(a) == SYM_LEFT)
            return false;
        if (symType(b) == SYM_RIGHT)
            return true;
        if (priority(a) == 3 && priority(b) == 3) // если встречаем два оператора возведения в степень
            return false; // откладываем операцию

        return priority(a) >= priority(b);
    }

    protected int symOther(char c) throws Exception {
        if ((c < '0' || c > '9') && (c != '.' || c != 'p' || c != 'e')) {
            System.out.println("Недопустимый символ: " + c);
            throw new Exception("Недопустимый символ: " + c);
        }

        return SYM_OTHER;
    }

    protected void nextOper(char c) {
        System.out.print("" + c + " ");
    }

    protected void nextOther(char c) {
        nextOper(c);
    }

    public double compile(char[] str) throws Exception {
        processSymbol('(');
        for (int i = 0; i < str.length; i++)
            processSymbol(str[i]);
        processSymbol(')');

        System.out.print("\n");
        return 0;
    }
}