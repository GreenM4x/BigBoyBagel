package Logic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.time.LocalDate;


public class RechnungSchreiben {
    static String path = "src/Rechnung/";

    public static void NeueRechnungSchreiben(int rechnungsNr, double totalPreis, int[] wahrencorp, double discound) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        BufferedWriter writer = null;
        DecimalFormat df = new DecimalFormat("#.00");

        LocalDate date = LocalDate.now();


        try {
            writer = Files.newBufferedWriter(Path.of(path + rechnungsNr + ".txt"), charset, StandardOpenOption.CREATE_NEW);
            writer.write("--------------------[RECHNUNG]--------------------");
            writer.newLine();
            writer.write("["+ date + "] " + "| RECHNUNGS-NUMMER: "+rechnungsNr);
            writer.newLine();
            writer.write("The Big Boy - " + wahrencorp[0] + " Total: " + wahrencorp[0]* GetDBVallues.getDBArtPreisDouble(1));
            writer.newLine();
            writer.write("Vegi Boy - " + wahrencorp[1] + " Total: " + wahrencorp[1]* GetDBVallues.getDBArtPreisDouble(2));
            writer.newLine();
            writer.write("Sugar Boy - " + wahrencorp[2] + " Total: " + wahrencorp[2]* GetDBVallues.getDBArtPreisDouble(3));
            writer.newLine();
            writer.write("Discound: " + discound * 100 + "%");

            writer.newLine();
            writer.write("TOTAL: " + totalPreis);



        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }

        System.out.println("ORDER LOG");
    }
}
