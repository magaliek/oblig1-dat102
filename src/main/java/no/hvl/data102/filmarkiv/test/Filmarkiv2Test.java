package no.hvl.data102.filmarkiv.test;

import no.hvl.data102.filmarkiv.adt.FilmarkivADT;
import no.hvl.data102.filmarkiv.impl.Film;
import no.hvl.data102.filmarkiv.impl.Filmarkiv2;
import no.hvl.data102.filmarkiv.impl.Sjanger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Filmarkiv2Test {
    FilmarkivADT arkiv = new Filmarkiv2();
    Film film = new Film(1, "lola christy",
            "vampyr", 2020, Sjanger.TRAGEDY, "Warner Bros");
    Film film2 = new Film(2, "lola christy",
            "cry", 2023, Sjanger.COMEDY, "Warner Bros");
    Film film3 = new Film(3, "James Cameron",
            "Avatar", 2009, Sjanger.THRILLER, "Warner Bros");
    Film film4 = new Film(4, "Quentin Tarantino",
            "Pulp Fiction", 1994, Sjanger.COMEDY, "Warner Bros");
    Film film5 = new Film(5, "Greta Gerwig",
            "Little Women", 2019, Sjanger.DRAMA, "Warner Bros");

    @Test
    public void testLeggTilFilm() {
        arkiv.leggTilFilm(film);
        assertEquals(film, arkiv.getFilms()[0]);
        arkiv.leggTilFilm(film2);
        assertEquals(film2, arkiv.getFilms()[1]);

        arkiv.leggTilFilm(film3);
        assertEquals(film3, arkiv.getFilms()[2]);

        arkiv.leggTilFilm(film4);
        assertEquals(film4, arkiv.getFilms()[3]);

        arkiv.leggTilFilm(film5);
        assertEquals(film5, arkiv.getFilms()[4]);
    }

    @Test
    public void testFinnFilm() {
        arkiv.leggTilFilm(film);
        arkiv.leggTilFilm(film2);
        arkiv.leggTilFilm(film3);
        assertEquals(film, arkiv.finnFilm(1));

        assertNull(arkiv.finnFilm(10));
    }

    @Test
    public void testSlettFilm() {
        arkiv.leggTilFilm(film);
        arkiv.leggTilFilm(film2);
        arkiv.leggTilFilm(film3);
        assertEquals(film, arkiv.getFilms()[0]);
        arkiv.slettFilm(1);
        assertNull(arkiv.finnFilm(1));
    }

    @Test
    public void testProdusent() {
        arkiv.leggTilFilm(film);
        arkiv.leggTilFilm(film2);
        arkiv.leggTilFilm(film3);

        assertArrayEquals(new Film[] {film, film2}, arkiv.soekProdusent("lola"));
    }

    @Test
    public void testTittel() {
        arkiv.leggTilFilm(film);
        arkiv.leggTilFilm(film2);
        arkiv.leggTilFilm(film3);

        assertArrayEquals(new Film[] {film, film2}, arkiv.soekTittel("y"));
    }

    @Test
    public void testAntall() {
        arkiv.leggTilFilm(film);
        arkiv.leggTilFilm(film2);
        arkiv.leggTilFilm(film3);
        arkiv.leggTilFilm(film4);

        assertEquals(2, arkiv.antall(Sjanger.COMEDY));
        assertEquals(4, arkiv.antall());
    }

}
