package Logic;

public enum DiscountCode {
    WELLI10(0.1),BBB50(0.5),FREEFORALL(1);

    private final double vallue;

    DiscountCode(double vallue) {
        this.vallue = vallue;
    }

    public double getVallue() {
        return vallue;
    }
}
