package org.open_structures.memento;

public interface Restorable<M extends Memento> {

    /**
     * Returns current state of the object
     */
    M getState();

    /**
     * Restores object to its previous state
     */
    void restore(M state);
}
