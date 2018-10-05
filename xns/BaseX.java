package world.hello.helloworld.xns;

public class BaseX {

    public static void main(String[] args) {
        System.out.print(convertToBase("40", 16));
    }

    private static int convertToBase(String number, int base) {
        if (base < 2 || (base > 10 && base != 16)) {
            return -1;
        }
        int value = 0;
        for (int i = 0; i < number.length(); i++) {
            int digit = digitToValue(number.charAt(i));
            if (digit < 0 || digit >= base) {
                return -1;
            }
            int exp = (number.length() - 1) - i;
            value += (int) (digit * Math.pow(base, exp));
        }
        return value;
    }

    private static int digitToValue(char c) {
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        } else if (c >= 'A' && c <= 'F') {
            return (c - 'A') + 10;
        } else if (c >= '0' && c <= '9') {
            return Integer.parseInt(String.valueOf(c));
        }
        return -1;
    }
}
