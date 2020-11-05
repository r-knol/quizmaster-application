package database.mysql;

import java.util.ArrayList;

/**
 * @author Huub van Thienen
 */

public interface GenericDAO<T> {
    public ArrayList<T> getAll();
    public T getOneById(int id);
    public void storeOne(T type);
    public void updateOne(T type); // toegevoegd door Wendy
    public void deleteOne(T type); // toegevoegd door Richard
}
