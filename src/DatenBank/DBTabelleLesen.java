package DatenBank;

import java.sql.*;
import java.util.List;

public class DBTabelleLesen implements Daten {


    public static Connection baueVerbindungAuf() {
        Connection verbindung = null;
        try {
            Class.forName(dbTreiber);
            String s = "jdbc:mariadb://" + host + ":" + port + "/" + db + "?"
                    + "user=" + user + "&" + "password=" + passwd;
            verbindung = DriverManager.getConnection(s);
        } catch (ClassNotFoundException e) {
            System.out.println("Treiber nicht gefunden");
        } catch (SQLException e) {
            System.out.println("Connect nicht moeglich:" + e.getMessage());
        }
        return verbindung;
    }

    public static Artikel DB_getArtikelByNr( int ArtNr) {

        int sArtikelNr = 0;
        String sArtikelName = "Error Bagel";
        double sartPreis = 0;
        String sArtBild = "";


        String sQuery = "select *"
                + " from artikel";

        try (Statement stmt = baueVerbindungAuf().createStatement())
        {
            ResultSet rs = stmt.executeQuery(sQuery);
            while (rs.next())
            {
                sArtikelNr = rs.getInt("artNr");
                if (sArtikelNr == ArtNr) {
                    sArtikelName = rs.getString("artName");
                    sartPreis = rs.getFloat("artPreis");
                    sArtBild = rs.getString("artBild");
                    break;
                }


            }


        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());;
        }

        return new Artikel(sArtikelNr,sArtikelName,sartPreis, sArtBild);

    }



}