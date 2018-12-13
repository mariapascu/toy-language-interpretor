package Model;

import javafx.util.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class MyTable<K, V1, V2> implements MyITable<K, V1, V2> {
    private HashMap<K, Pair<V1, V2>> table = new HashMap<>();
    public HashMap<K, Pair<V1, V2>> getTable() {
        return table;
    }
    public Set<K> getKeys() {
        return table.keySet();
    }
    public Collection<Pair<V1, V2>> getValues() {
        return table.values();
    }
    public Pair<V1, V2> getValue(K k) {
        return table.get(k);
    }
    public void put(K k, V1 v1, V2 v2) {
        table.put(k, new Pair<>(v1, v2));
    }
    public void delete(K k) {
        table.remove(k);
    }

    @Override
    public String toString() {
        return "MyTable{" +
                "table=" + table +
                '}';
    }
}
