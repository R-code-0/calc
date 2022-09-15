import java.util.HashMap;

public class Calculator {
    private String ra, rb;
    private int a, b;
    private boolean obs;
    private char op;

    public void in(String input) {
        input = input.replaceAll(" ", "").toUpperCase();
        char[] ch = {'+', '-', '*', '/'};
        int x = -1;
        for (char c : ch) {
            x = input.indexOf(c);
            if (x != -1) break;
        }
        if (x == -1) {
            throw new ArithmeticException("Error: unknown operand. not +, -, *, /");
        }
        if (input.replaceAll("\\D", "").length() == 0) {
            obs = true;
            ra = input.substring(0, x);
            rb = input.substring(x + 1);
        }
        op = input.charAt(x);
        a = obs ? romanToInt(ra) : Integer.parseInt(input.substring(0, x));
        b = obs ? romanToInt(rb) : Integer.parseInt(input.substring(x + 1));
        calculate();
    }

    private int romanToInt(String s) {
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
                default -> throw new RuntimeException("Error: invalid roman number");
            }
        }
        return r;
    }

    private String intToRoman(int num) {
        HashMap<Integer, String> db = new HashMap<>();
        db.put(1, "I");
        db.put(4, "IV");
        db.put(5, "V");
        db.put(6, "VI");
        db.put(9, "IX");
        db.put(10, "X");
        db.put(40, "XL");
        db.put(50, "L");
        db.put(60, "LX");
        db.put(90, "XC");
        db.put(100, "C");
        StringBuilder str = new StringBuilder();
        if (num < 0) {
            str.append('-');
            num = -num;
        }
        while (num != 0) {
            if (num >= 100) {
                str.append(db.get(100));
                num -= 100;
            } else if (num >= 90) {
                str.append(db.get(90));
                num -= 90;
            } else if (num >= 60) {
                str.append(db.get(60));
                num -= 60;
            } else if (num >= 50) {
                str.append(db.get(50));
                num -= 50;
            } else if (num >= 10) {
                str.append(db.get(10));
                num -= 10;
            } else if (num == 9) {
                str.append(db.get(9));
                num -= 9;
            } else if (num >= 6) {
                str.append(db.get(6));
                num -= 6;
            } else if (num == 5) {
                str.append(db.get(5));
                num -= 5;
            } else if (num == 4) {
                str.append(db.get(4));
                num -= 4;
            } else if (num >= 1) {
                str.append(db.get(1));
                num -= 1;
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
        } else throw new ArithmeticException("Error: incorrect value. 0<value<=10");
    }

    private void out(int out) {
        if (obs) System.out.printf("%s%s%s=%s\n", ra, op, rb, intToRoman(out));
        else System.out.printf("%d%s%d=%d\n", a, op, b, out);
    }
}
