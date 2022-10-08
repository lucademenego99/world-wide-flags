package it.unitn.disi.webarch.lucademenego.assignment2.utils;

/**
 * Simple class consisting of a pair of objects
 * @param <U> Type of the first object
 * @param <V> Type of the second object
 */
public class Pair<U, V> {
    /**
     * First object of the pair
     */
    public final U fst;

    /**
     * Second object of the pair
     */
    public final V snd;

    public Pair(U first, V second)  {
        this.fst = first;
        this.snd = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        if (!fst.equals(pair.fst))
            return false;
        return snd.equals(pair.snd);
    }

    @Override
    public int hashCode() {
        return 29 * fst.hashCode() + snd.hashCode();
    }

    @Override
    public String toString() {
        return "Pair{fst=" + fst + ",snd=" + snd + "}";
    }
}
