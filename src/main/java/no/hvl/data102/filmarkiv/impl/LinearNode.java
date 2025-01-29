package no.hvl.data102.filmarkiv.impl;

public class LinearNode<Film> {
    public Film data;
    public LinearNode<Film> next;
    public LinearNode<Film> previous;

    public LinearNode(Film data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    @Override
    public String toString() {
        if (next == null) {
            return "[" + data + "] | null";
        }
        return "[" + data + "| •-]--> " + next;
    }
}
