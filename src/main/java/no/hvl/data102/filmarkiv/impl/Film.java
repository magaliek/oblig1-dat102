package no.hvl.data102.filmarkiv.impl;

public class Film {
    private int filmnr;
    private String filmskaper;
    private String tittel;
    private int aar;
    private Sjanger sjanger;
    private String filmselskap;

    public Film() {
    }
    public Film(int filmnr, String filmskaper, String tittel, int aar, Sjanger sjanger, String filmselskap) {
        this.filmnr = filmnr;
        this.filmskaper = filmskaper;
        this.tittel = tittel;
        this.aar = aar;
        this.sjanger = sjanger;
        this.filmselskap = filmselskap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return filmnr == film.getFilmnr() && filmskaper.equals(film.getFilmskaper())
                && tittel.equals(film.getTittel()) && aar == film.getAar()
                && sjanger.equals(film.getSjanger()) && filmselskap.equals(film.getFilmselskap());
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + filmnr;
        result = 31 * result + (filmskaper == null ? 0 : filmskaper.hashCode());
        result = 31 * result + (tittel==null ? 0 : tittel.hashCode());
        result = 31 * result + aar;
        result = 31 * result + (sjanger==null ? 0 : sjanger.hashCode());
        result = 31 * result + (filmselskap==null ? 0 : filmselskap.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return Integer.toString(filmnr) + ": " + tittel + ", by " + filmskaper
                + " of " + filmselskap + ". " + sjanger + " movie made in " + Integer.toString(aar);
    }

    public int getFilmnr() {
        return filmnr;
    }

    public void setFilmnr(int i) {
        this.filmnr = i;
    }

    public String getFilmselskap() {
        return filmselskap;
    }
    public void setFilmselskap(String filmselskap) {
        this.filmselskap = filmselskap;
    }

    public String getTittel() {
        return tittel;
    }
    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public int getAar() {
        return aar;
    }
    public void setAar(int aar) {
        this.aar = aar;
    }

    public Sjanger getSjanger() {
        return sjanger;
    }
    public void setSjanger(Sjanger sjanger) {
        this.sjanger = sjanger;
    }

    public String getFilmskaper() {
        return filmskaper;
    }
    public void setFilmskaper(String filmskaper) {
        this.filmskaper = filmskaper;
    }

}
