package validation;

public class Validate {
    private String errorMessage = "";

    public boolean check(int x, double y, int r) {
        errorMessage = "";

        boolean isValidX = validateX(x);
        boolean isValidY = validateY(y);
        boolean isValidR = validateR(r);
        return isValidX && isValidY && isValidR;
    }

    private boolean validateX(int x) {
        if (x >= -4 && x <= 4) {
            return true;
        } else {
            errorMessage = "Значение X должно быть в диапазоне от -4 до 4.";
            return false;
        }
    }

    private boolean validateY(double y) {
        if (y >= -5 && y <= 5) {
            return true;
        } else {
            errorMessage = "Значение Y должно быть в диапазоне от -5 до 5.";
            return false;
        }
    }

    private boolean validateR(int r) {
        if (r >= 1 && r <= 5) {
            return true;
        } else {
            errorMessage = "Значение R должно быть в диапазоне от 1 до 5.";
            return false;
        }
    }

    public String getErr() {
        return errorMessage.isEmpty() ? "Все значения корректны" : errorMessage;
    }
}
