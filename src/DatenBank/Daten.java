package DatenBank;

public interface Daten {
    // Datenbank-Verbindungsdaten
    // Treiberklasse
    public static final String dbTreiber = "org.mariadb.jdbc.Driver";
    // Datenbankserver (DNS-Name oder IP Adresse angeben)
    public static final String host = "localhost";
    // DBMS Server Port (Standardport MySQL: 3306)
    public static final String port = "3306";
    public static final String db = "big_boy_bagel"; // Datenbankname
    public static final String user = "root"; // Datenbank-User
    public static final String passwd = ""; // Passwort

}
