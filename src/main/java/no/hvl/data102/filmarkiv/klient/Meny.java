package no.hvl.data102.filmarkiv.klient;

import no.hvl.data102.filmarkiv.adt.FilmarkivADT;
import no.hvl.data102.filmarkiv.impl.Film;
import no.hvl.data102.filmarkiv.impl.Sjanger;

public class Meny {
    private Tekstgrensesnitt tekstgr;
    private FilmarkivADT filmarkiv;
    public Meny(FilmarkivADT filmarkiv){
        tekstgr = new Tekstgrensesnitt();
        this.filmarkiv = filmarkiv;
    }
    public void start(){
        filmarkiv.leggTilFilm(new Film(1, "lola christy", "vampyr", 2020, Sjanger.TRAGEDY, "company"));
        filmarkiv.leggTilFilm(new Film(2, "lola christy", "cry", 2023, Sjanger.COMEDY, "company"));
        filmarkiv.leggTilFilm(new Film(3, "James Cameron", "Avatar", 2009, Sjanger.THRILLER, "company"));
        filmarkiv.leggTilFilm(new Film(4, "Quentin Tarantino", "Pulp Fiction", 1994, Sjanger.COMEDY, "company"));
        filmarkiv.leggTilFilm(new Film(5, "Greta Gerwig", "Little Women", 2019, Sjanger.DRAMA, "company"));

        tekstgr.lesFilm();
        tekstgr.skrivUtFilmDelstrengITittel(filmarkiv, "y");
        tekstgr.findMovie(filmarkiv, 5);
        tekstgr.removeMovie(4, filmarkiv);
    }
}