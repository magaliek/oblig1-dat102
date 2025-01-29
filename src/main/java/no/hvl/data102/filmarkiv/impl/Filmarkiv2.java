package no.hvl.data102.filmarkiv.impl;

import no.hvl.data102.filmarkiv.adt.FilmarkivADT;

public class Filmarkiv2 implements FilmarkivADT {
    private int antall;
    private LinearNode<Film> start;
    private LinearNode<Film> end;

    public Filmarkiv2() {
        this.start = null;
        this.antall = 0;
    }

    @Override
    public Film finnFilm(int nr) {
        LinearNode<Film> temp = start;
        while (temp != null) {
            if (temp.data.getFilmnr() == nr) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public void leggTilFilm(Film nyFilm) {
        LinearNode<Film> newNode = new LinearNode<>(nyFilm);
        if (start == null) {
            start = newNode;
            end = start;
            antall++;
        } else {
            end.next = newNode;
            newNode.previous = end;
            end = newNode;
            antall++;
        }
    }

    @Override
    public boolean slettFilm(int filmnr) {
        LinearNode<Film> temp = start;
        while (temp != null) {
            if (temp.data.getFilmnr() == filmnr) {
                if (temp == start) {
                    start = start.next;
                    if (start != null) {
                        start.previous = null;
                    }
                    antall--;
                    return true;
                }
                if (temp.next == null) {
                    temp.previous.next = null;
                    end = temp.previous;
                    antall--;
                    return true;
                }
                temp.previous.next = temp.next;
                temp.next.previous = temp.previous;
                antall--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public Film[] soekTittel(String delstreng) {
        Film[] films = new Film[antall];
        int index = 0;
        LinearNode<Film> temp = start;
        while (temp != null) {
            if (temp.data.getTittel().contains(delstreng)) {
                films[index] = temp.data;
                index++;
            }
            temp = temp.next;
        }

        return trimTab(films, index);
    }

    @Override
    public Film[] soekProdusent(String delstreng) {
        Film[] films = new Film[antall];
        int index = 0;
        LinearNode<Film> temp = start;
        while (temp != null) {
            if (temp.data.getFilmskaper().contains(delstreng)) {
                films[index++] = temp.data;
            }
            temp = temp.next;
        }

        return trimTab(films, index);
    }

    @Override
    public int antall(Sjanger sjanger) {
        int ant = 0;
        LinearNode<Film> temp = start;
        while (temp != null) {
            if (temp.data.getSjanger() == sjanger) {
                ant++;
            }
            temp = temp.next;
        }
        return ant;
    }

    @Override
    public int antall() {
        LinearNode<Film> temp = start;
        int ant = 0;
        while (temp != null) {
            ant++;
            temp = temp.next;
        }
        return ant;
    }

    @Override
    public Film[] getFilms() {
        LinearNode<Film> temp = start;
        Film[] films = new Film[antall];
        int index = 0;
        while (temp != null) {
            films[index++] = temp.data;
            temp = temp.next;
        }
        return films;
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
}
