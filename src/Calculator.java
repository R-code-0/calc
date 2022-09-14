public class Calculator {
    private int a;
    private int b;
    private boolean obs;
    private char op;

    public void in(String input) {
        input = input.replaceAll(" ", "").toUpperCase();
        if (input.replaceAll("\\D", "").length() == 0) obs = true;
        System.out.println(obs);
        char[] ch = {'+', '-', '*', '/'};
        int x = -1;
        for (char c : ch) {
            x = input.indexOf(c);
            if (x != -1) break;
        }
        if (x == -1) {
            throw new ArithmeticException("Error. Unknown operand. not +, -, *, /");
        }
        op = input.charAt(x);
        a = obs ? romanToInt(input.substring(0, x)) : Integer.parseInt(input.substring(0, x));
        b = obs ? romanToInt(input.substring(x + 1)) : Integer.parseInt(input.substring(x + 1));
        calculate();
    }

    public int romanToInt(String s) {
        int r = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'I' -> {
                    if (i + 1 < s.length() && s.charAt(i + 1) == 'V') {
                        r += 4;
                        i++;
                    } else if (i + 1 < s.length() && s.charAt(i + 1) == 'X') {
                        r += 9;
                        i++;
                    } else r += 1;
                }
                case 'V' -> r += 5;
                case 'X' -> {
                    if (i + 1 < s.length() && s.charAt(i + 1) == 'L') {
                        r += 40;
                        i++;
                    } else if (i + 1 < s.length() && s.charAt(i + 1) == 'C') {
                        r += 90;
                        i++;
                    } else r += 10;
                }
                case 'L' -> r += 50;
                case 'C' -> {
                    if (((i + 1) < s.length()) && (s.charAt(i + 1) == 'D')) {
                        r += 400;
                        i++;
                    } else if (i + 1 < s.length() && s.charAt(i + 1) == 'M') {
                        r += 900;
                        i++;
                    } else r += 100;
                }
                case 'D' -> {
                    if (i + 1 < s.length() && s.charAt(i + 1) == 'C') {
                        r += 600;
                        i++;
                    } else r += 500;
                }
                case 'M' -> r += 1000;
            }
        }
        return r;
    }

    public String intToRoman(int s) {
        if (s == 100) return "C";
        StringBuilder str = new StringBuilder();
        if (s < 0) {
            str.append('-');
            s = -s;
        }
        while (s != 0) {
            if (s >= 50) {
                if (s >= 90) {
                    str.append("XC");
                    s -= 90;
                }
                else {
                    str.append('L');
                    s -= 50;
                }
            } else if (s >= 10) {
                if (s >= 40) {
                    str.append("XL");
                    s -= 40;
                } else {
                    str.append('X');
                    s -= 10;
                }
            } else if (s >= 5) {
                if (s == 9) {
                    str.append("IX");
                    s -= 9;
                } else {
                    str.append('V');
                    s -= 5;
                }
            } else if (s >= 1) {
                if (s == 4) {
                    str.append("IV");
                    s -= 4;
                } else {
                    str.append('I');
                    s -= 1;
                }
            }
        }
        return str.toString();
    }


    private void calculate() {
        if (a > 0 && a <= 10 && b > 0 && b <= 10) {
            switch (op) {
                case '+' -> out(a + b);
                case '-' -> out(a - b);
                case '*' -> out(a * b);
                case '/' -> out(a / b);
            }
        } else throw new ArithmeticException("Incorrect value. 0<value<=10");
    }

    private void out(int out) {
        if (obs) System.out.println(intToRoman(out));
        else System.out.println(out);
    }
}
