package Logic;

import java.io.IOException;

public class OrderCalc {

    static int OrderNr = 0;

    static double discountVallue = 0;
    public static int[] counters = {0,0,0};

    public static void AddToCounter(int ArtNr){
        counters[ArtNr-1]++;
    }
    public static void RemoveFromCounter(int ArtNr){
        counters[ArtNr-1]--;
    }
    public static int GetCounterValue(int ArtNr){
        return counters[ArtNr-1];
    }

    public static double GetTotalValue(){

        double[] preise = {GetDBVallues.getDBArtPreisDouble(1), GetDBVallues.getDBArtPreisDouble(2), GetDBVallues.getDBArtPreisDouble(3)};
        double total = preise[0] * counters[0] + preise[1] * counters[1] + preise[2] * counters[2];
        total -= total * discountVallue;

        return  Math.round(total * 100.0) / 100.0;
    }

    public static void CheckIfCodeExist(String code){
        for (DiscountCode discountCode: DiscountCode.values()) {
            if (discountCode.toString().equals(code)){
                discountVallue = discountCode.getVallue();
                break;
            } else {
                discountVallue = 0;
            }
        }
    }


    public static void Order() {

        double total = GetTotalValue();
        try {
            RechnungsLog.writeLog(OrderNr, total);
            RechnungSchreiben.NeueRechnungSchreiben(OrderNr,total,counters,discountVallue);
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        OrderNr++;
        System.out.println("ORDER");
    }

}
