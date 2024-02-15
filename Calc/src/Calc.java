
import java.util.Scanner;
public class Calc {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение, состоящее из двух чисел:");
        String str = scanner.nextLine();
        System.out.println(calc(str));
    }

    public static String calc(String input) throws Exception {
        int number1;
        int number2;
        String operation;
        String result;
        boolean isRoman;

        String input1 = input.toUpperCase();
        String[] operator = {"+", "-", "*", "/"};
        int inputIndex = -1;
        for (int i = 0; i < operator.length; i++) {
            if (input1.contains(operator[i])) {
                inputIndex = i;
                break;
            }
        }if (inputIndex==-1){
            throw new Exception("Cтрока не является математической операцией");
        }

        String[] element = input.split("[+\\-*/]");
        if (element.length != 2) throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        operation = detectOperation(input);


        //если оба числа римские
        if (Roman.isRoman(element[0]) && Roman.isRoman(element[1])) {
            //конвертируем оба числа в арабские для вычесления действия
            number1 = Roman.convertToArabian(element[0]);
            number2 = Roman.convertToArabian(element[1]);
            isRoman = true;
        }
        //если оба числа арабские
        else if (!Roman.isRoman(element[0]) && !Roman.isRoman(element[1])) {
            number1 = Integer.parseInt(element[0]);
            number2 = Integer.parseInt(element[1]);
            isRoman = false;
        }
        //если одни число римское, а другое - арабское
        else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if (number1 > 10 || number1 <1 || number2>10 || number2<1) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calcul(number1, number2, operation);
        if (isRoman) {
            //если римское число получилось меньше либо равно нулю, генерируем ошибку
            if (arabian <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            //конвертируем результат операции из арабского в римское
            result = Roman.convertToRoman(arabian);
        } else {
            //Конвертируем арабское число в тип String
            result = String.valueOf(arabian);
        }
        //возвращаем результат
        return result;
    }

    static String detectOperation(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    static int calcul(int a, int b, String operation) {

        return switch (operation) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }
}

class Roman {
    static String[] array = new String[]{" ", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean isRoman(String string) {
        for (String s : array) {
            if (string.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String rom) {
        for (int i = 0; i < array.length; i++) {
            if (rom.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arab) {
        return array[arab];
    }
}
