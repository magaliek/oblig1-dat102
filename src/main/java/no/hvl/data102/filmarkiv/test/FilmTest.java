package no.hvl.data102.filmarkiv.test;

import no.hvl.data102.filmarkiv.impl.Film;
import org.junit.jupiter.api.Test;
import no.hvl.data102.filmarkiv.impl.Sjanger;

import static org.junit.jupiter.api.Assertions.*;


public class FilmTest {
    Film film = new Film(1, "bobby brown",
            "the man under the sun", 1998, Sjanger.THRILLER, "company");
    Film filmSame = new Film(1, "bobby brown",
            "the man under the sun", 1998, Sjanger.THRILLER, "company");
    Film filmDiff = new Film(2, "lola christy",
            "vampyr", 2020, Sjanger.TRAGEDY, "company");

    @Test
    public void testEqualAndHash() {
        assertTrue(film.equals(filmSame) && film.hashCode()==filmSame.hashCode());
        assertNotEquals(film, filmDiff);
    }

    @Test
    public void testGetters() {
        assertEquals(1, film.getFilmnr());
        assertEquals("bobby brown", film.getFilmskaper());
        assertEquals("the man under the sun", film.getTittel());
        assertEquals(1998, film.getAar());
        assertEquals(Sjanger.THRILLER, film.getSjanger());
    }

    @Test
    public void testSetters() {
        filmDiff.setAar(2022);
        assertEquals(2022, filmDiff.getAar());
        filmDiff.setFilmselskap("Kanye & Co");
        assertEquals("Kanye & Co", filmDiff.getFilmselskap());
        filmDiff.setTittel("the boondocks");
        assertEquals("the boondocks", filmDiff.getTittel());
        filmDiff.setSjanger(Sjanger.COMEDY);
        assertEquals(Sjanger.COMEDY, filmDiff.getSjanger());
    }
}