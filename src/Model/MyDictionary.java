package Model;
import Exception.MyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {
    HashMap<K, V> dict;

    public MyDictionary() {
        dict = new HashMap<K, V>();
    }

    public MyDictionary(HashMap<K, V> dict) {
        this.dict = dict;
    }

    @Override
    public V get(K key) throws MyException {
        if(this.dict.get(key) == null) {
            throw new MyException("Inexistent key!");
        }
        return this.dict.get(key);
   }

    @Override
    public V put(K key, V val) {
        return dict.put(key, val);
    }

    @Override
    public Collection<V> getValues() {
        return this.dict.values();
    }

    @Override
    public HashMap<K, V> getAll() {
        HashMap<K, V> copy = new HashMap<>();
        for (Map.Entry<K, V> entry : dict.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            copy.put(key, value);
        }
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("SymTable:\n");
        for (K key : this.dict.keySet()) {
            result.append(key.toString() + " --> " + this.dict.get(key).toString() + "\n");
        }
        return result.toString();
    }
}
