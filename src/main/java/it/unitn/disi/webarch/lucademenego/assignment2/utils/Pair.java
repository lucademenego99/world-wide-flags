package it.unitn.disi.webarch.lucademenego.assignment2.utils;

public class Pair<U, V>
{
    public final U fst;       // the first field of a pair
    public final V snd;      // the snd field of a pair

    // Constructs a new pair with specified values
    public Pair(U first, V second)
    {
        this.fst = first;
        this.snd = second;
    }

    @Override
    // Checks specified object is "equal to" the current object or not
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) o;

        // call `equals()` method of the underlying objects
        if (!fst.equals(pair.fst)) {
            return false;
        }
        return snd.equals(pair.snd);
    }

    @Override
    // Computes hash code for an object to support hash tables
    public int hashCode()
    {
        // use hash codes of the underlying objects
        return 31 * fst.hashCode() + snd.hashCode();
    }

    @Override
    public String toString() {
        return "(" + fst + ", " + snd + ")";
    }

    // Factory method for creating a typed Pair immutable instance
    public static <U, V> Pair <U, V> of(U a, V b)
    {
        // calls private constructor
        return new Pair<>(a, b);
    }
}
