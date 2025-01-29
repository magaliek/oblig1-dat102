package no.hvl.data102.filmarkiv.impl;

import no.hvl.data102.filmarkiv.adt.FilmarkivADT;

public class Filmarkiv  implements FilmarkivADT {

    private Film[] films;

    public Filmarkiv(int antFilmer) {
        this.films = new Film[antFilmer];
    }

    @Override
    public Film finnFilm(int nr) {
        Film film=null;
        for (Film value : films) {
            if (value != null && value.getFilmnr() == nr) {
                film = value;
            }
        }
        return film;
    }

    @Override
    public void leggTilFilm(Film nyFilm) {
        if (isFull()) {
            Film[] biggerFilms = new Film[films.length*2];
            System.arraycopy(films, 0, biggerFilms, 0, films.length);
            this.films = biggerFilms;
        }
        for (int i=0; i<films.length; i++) {
            if (films[i] == null) {
                films[i] = nyFilm;
                return;
            }
        }
    }

    @Override
    public boolean slettFilm(int filmnr) {
        for (int i =0; i<films.length; i++) {
            if (films[i].getFilmnr()==filmnr) {
                films[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public Film[] soekTittel(String delstreng) {
        Film[] filmer = new Film[films.length];
        int index = 0;
        for (int i = 0; i < films.length; i++) {
            if (films[i] != null) {
                if (films[i].getTittel().contains(delstreng)) {
                    filmer[index++] = films[i];
                }
            }
        }
        return trimTab(filmer, index);
    }

    @Override
    public Film[] soekProdusent(String delstreng) {
        Film[] filmer = new Film[films.length];
        int index = 0;
        for (int i = 0; i < films.length; i++) {
            if (films[i] != null) {
                if (films[i].getFilmskaper().contains(delstreng)) {
                    filmer[index++] = films[i];
                }
            }
        }
        return trimTab(filmer, index);
    }

    @Override
    public int antall(Sjanger sjanger) {
        int ant = 0;
        for (int i=0; i<films.length; i++) {
            if (films[i] != null && films[i].getSjanger() == sjanger) {
                ant++;
            }
        }
        return ant;
    }

    @Override
    public int antall() {
        int ant = 0;
        for (int i=0; i<films.length; i++) {
            if (films[i] != null) {
                ant++;
            }
        }
        return ant;
    }

    private boolean isFull() {
        for (int i = 0; i<films.length; i++) {
            if (films[i] == null) {
                return false;
            }
        }
        return true;
    }

    private Film[] trimTab(Film[] tab, int n) {
        Film[] nytab = new Film[n];
        int i = 0;
        while (i < n) {
            nytab[i] = tab[i];
            i++;
        }
        return nytab;
    }

    @Override
    public Film[] getFilms() {
        return films;
    }
}
