package Logic;

import DatenBank.DBTabelleLesen;

public class GetDBVallues {
    public static String getDBArtName(int artNr){
        return DBTabelleLesen.DB_getArtikelByNr(artNr).getArtName();
    }
    public static String getDBArtPreisString(int artNr) {
        String artPreisString = "0.00";
        double artPreis = DBTabelleLesen.DB_getArtikelByNr(artNr).getArtPreis();
        artPreis = Math.round(artPreis * 100.0) / 100.0;
        artPreisString = String.valueOf(artPreis);

        return artPreisString;
    }
    public static double getDBArtPreisDouble(int artNr){
        double artPreis = DBTabelleLesen.DB_getArtikelByNr(artNr).getArtPreis();
        artPreis = Math.round(artPreis * 100.0) / 100.0;
        return artPreis;
    }

    public static String GetDBArtBild(int artNr) {

        return DBTabelleLesen.DB_getArtikelByNr(artNr).getBildSrc();
    }
}
