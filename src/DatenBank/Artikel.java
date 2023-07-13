package DatenBank;

public class Artikel {
    private int artNr;
    private String artName;
    private double artPreis;
    private String bildSrc;

    public Artikel(int artNr, String artName, double artPreis, String bildSrc) {
        this.artNr = artNr;
        this.artName = artName;
        this.artPreis = artPreis;
        this.bildSrc = bildSrc;
    }

    public int getArtNr() {
        return artNr;
    }

    public String getArtName() {
        return artName;
    }

    public double getArtPreis() {
        return artPreis;
    }

    public String getBildSrc() {
        return bildSrc;
    }
}
