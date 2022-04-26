package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao;

import java.util.List;

/**
 * Methods all Content DAOs should contain.
 * @param <T> A Content type
 * @author Vincent Severin
 */
public interface Dao<T, S> {
    /**
     * Gets all Things from the Database.
     * @return returns a List of Things
     * @author Vincent Severin
     */
    List<T> getAll();

    /**
     * Gets a Thing from the Database based on the id.
     * @param id The id of Thing it is trying to find
     * @return The Thing with the same id as the id param
     * @author Vincent Severin
     */
    T get(S id);

    /**
     * Creates a Thing in the Database.
     * @param t The Thing that will be created
     * @author Vincent Severin
     */
    T create(T t);

    /**
     * Updates a Thing in the Database.
     * @param t The Thing that will be updated
     * @author Vincent Severin
     */
    void update(T t);

    /**
     * Deletes a Thing from the Database.
     * @param t The Thing that will be deleted
     * @author Vincent Severin
     */
    void delete(T t);
}
