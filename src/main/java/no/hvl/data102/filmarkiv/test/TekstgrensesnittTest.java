package no.hvl.data102.filmarkiv.test;

import no.hvl.data102.filmarkiv.impl.Film;
import no.hvl.data102.filmarkiv.impl.Sjanger;
import no.hvl.data102.filmarkiv.klient.Tekstgrensesnitt;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TekstgrensesnittTest {

    Tekstgrensesnitt grensesnitt = new Tekstgrensesnitt();
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    @Test
    public void testLesFilm() {
        String[] params = {"film number", "director", "movie title", "release year", "genre (COMEDY, TRAGEDY, DRAMA, ROMANCE, THRILLER)", "movie company"};
        AtomicInteger index = new AtomicInteger(0);

        JTextField field = new JTextField("Please enter " + params[index.get()]);
        JButton button = new JButton("Submit");
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        field.setHorizontalAlignment(JTextField.CENTER);

        frame.add(panel);
        frame.setVisible(true);

        panel.add(field);
        panel.add(button);

        panel.revalidate();
        panel.repaint();

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
                    JTextField filmText = new JTextField(film.toString());
                    filmText.setEditable(false);
                    panel.add(filmText);
                    panel.revalidate();
                    panel.repaint();
                } else {
                    params[index.getAndIncrement()] = field.getText().trim();
                    field.setText("Please enter " + params[index.get()]);
                    field.selectAll();
                }
            }
        });

        field.setText("1");
        button.doClick();

        field.setText("chrissy");
        button.doClick();

        field.setText("snow white");
        button.doClick();

        field.setText("1957");
        button.doClick();

        field.setText("drama");
        button.doClick();

        field.setText("company");
        button.doClick();

        assertEquals("1: snow white, by chrissy of company. DRAMA movie made in 1957", film.toString());
    }

}
