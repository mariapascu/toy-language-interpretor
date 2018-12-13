package Model;

import javafx.util.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public interface MyITable<K, V1, V2> {
    Set<K> getKeys();
    HashMap<K, Pair<V1, V2>> getTable();
    Collection<Pair<V1, V2>> getValues();
    Pair<V1, V2> getValue(K k);
    void put(K k, V1 v1, V2 v2);
    void delete(K k);
}
