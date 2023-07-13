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
import java.util.Date;

public class RechnungsLog {
    static String path = "src/LogFiles/rechnungsLog.txt";

    public static void writeLog(int rechnungsNr, double totalPreis) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        BufferedWriter writer = null;
        DecimalFormat df = new DecimalFormat("#.00");

        LocalDate date = LocalDate.now();


        try {
            writer = Files.newBufferedWriter(Path.of(path), charset, StandardOpenOption.APPEND);
            writer.write("["+ date + "] " + "| RECHNUNGS-NUMMER: "+rechnungsNr + " | TOTAL: " + totalPreis);
            writer.newLine();



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
