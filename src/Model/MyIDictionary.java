package Model;
import Exception.MyException;

import java.util.Collection;
import java.util.HashMap;

public interface MyIDictionary<K, V> {
    V get(K key) throws MyException;
    V put(K key, V val);
    Collection<V> getValues();
    HashMap<K, V> getAll();
}
