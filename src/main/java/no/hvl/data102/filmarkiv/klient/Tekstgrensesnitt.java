package no.hvl.data102.filmarkiv.klient;

import no.hvl.data102.filmarkiv.impl.Film;
import no.hvl.data102.filmarkiv.adt.FilmarkivADT;
import no.hvl.data102.filmarkiv.impl.Filmarkiv;
import no.hvl.data102.filmarkiv.impl.Sjanger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class Tekstgrensesnitt {

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel panel1;
    private JPanel panel2;

    public Tekstgrensesnitt() {
        this.frame = new JFrame("Tekstgrensesnitt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel();
        this.cardPanel.setLayout(cardLayout);
        this.panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(this.panel1, BoxLayout.Y_AXIS));
        this.panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(this.panel2, BoxLayout.Y_AXIS));
        frame.add(cardPanel);
        cardPanel.add(panel1, "lesFilm");
        cardPanel.add(panel2, "skrivUtFilm");
        frame.setVisible(true);
    }

    public Film lesFilm(){
        panel1.removeAll();
        String[] params = {"film number", "director", "movie title", "release year", "genre (COMEDY, TRAGEDY, DRAMA, ROMANCE, THRILLER)", "movie company"};
        AtomicInteger index = new AtomicInteger(0);

        JTextField field = new JTextField("Please enter " + params[index.get()]);
        JButton button = new JButton("Submit");
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        field.setHorizontalAlignment(JTextField.CENTER);

        panel1.add(field);
        panel1.add(button);

        panel1.revalidate();
        panel1.repaint();

        cardLayout.show(cardPanel,"lesFilm");

        Film film = new Film();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index.get() >= 5) {
                    params[index.get()] = field.getText().trim();
                    film.setFilmnr(parseInt(params[0]));
                    film.setFilmskaper(params[1]);
                    film.setTittel(params[2]);
                    film.setAar(parseInt(params[3]));
                    film.setSjanger(Sjanger.valueOf(params[4].toUpperCase()));
                    film.setFilmselskap(params[5]);
                    skrivUtFilm(film);
                } else {
                    params[index.getAndIncrement()] = field.getText().trim();
                    field.setText("Please enter " + params[index.get()]);
                    field.selectAll();
                }
            }
        });

        field.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "submitEnter");
        field.getActionMap().put("submitEnter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.doClick();
            }
        });
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                field.selectAll();
            }
        });
        return film;
    }

// Skriver ut en film med alle opplysninger på skjerm (husk tekst for sjanger)
    public void skrivUtFilm(Film film) {
        cardLayout.show(cardPanel, "skrivUtFilm");
        JTextField filmText = new JTextField(film.toString());
        filmText.setEditable(false);
        panel2.add(filmText);
        panel2.revalidate();
        panel2.repaint();
    }

// Skriver ut alle filmer med en spesiell delstreng i tittelen
    public void skrivUtFilmDelstrengITittel(FilmarkivADT arkiv, String delstreng) {
        for (int i=0; i<arkiv.soekTittel(delstreng).length; i++) {
            System.out.println(arkiv.soekTittel(delstreng)[i]);
        }
    }

// Skriver ut alle Filmer av en produsent (produsent er delstreng)
    public void skrivUtFilmProdusent(FilmarkivADT arkiv, String delstreng) {
        for (int i=0; i<arkiv.soekProdusent(delstreng).length; i++) {
            System.out.println(arkiv.soekProdusent(delstreng)[i]);
        }
    }

// Skriver ut en enkel statistikk som inneholder antall filmer totalt
// og hvor mange det er i hver sjanger.
    public void skrivUtStatistikk(FilmarkivADT arkiv) {
        System.out.println("there are " + arkiv.antall() + " films total.");
        for (Sjanger genre : Sjanger.values()) {
            System.out.println(arkiv.antall(genre));
        }
    }

//prints the placement of movie in the array from film number
    public void findMovie(FilmarkivADT arkiv, int filmnr) {
        for (int i=0; i<arkiv.getFilms().length; i++) {
            if (arkiv.getFilms()[i] != null && arkiv.getFilms()[i].getFilmnr() == filmnr) {
                System.out.println("movie " + filmnr + " is in placement: " + i);
            }
        }
    }

    // removes a movie then print it
    public void removeMovie(int filmnr, FilmarkivADT arkiv) {
        Film film = arkiv.finnFilm(filmnr);
        arkiv.slettFilm(filmnr);
        System.out.println("removed " + film);
    }
}
