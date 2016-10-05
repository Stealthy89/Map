import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

class MyHashMap<K,V> implements Map<K,V> {

    private static final int INIT_CAPACITY = 16;
    private int size;
    private Entry<K,V>[] table;

    MyHashMap() {
        table = new Entry[INIT_CAPACITY];
    }

    private class Entry<K,V> {

        private K key;
        private V value;
        private Entry<K,V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int hash(Object key) {
        int result = 0;
        if (key == null) {
            return result;
        }
        return key.hashCode();
    }

    private int index(Object key) {
        if (key == null) {
            return 0;
        } else {
            return hash(key) % INIT_CAPACITY;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {

        Entry entry = table[index(key)];

        if (entry != null) {
            if (key == null) {
                return entry.key == null;
            }
            if (entry.key.equals(key)) {
                return true;

            } else if (entry.next != null) {

                while (entry.next != null) {
                    entry = entry.next;
                    if (entry.key.equals(key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {

        for (Entry element : table) {
            for (Entry entry = element; entry != null; entry = entry.next) {
                if (entry.value == value || (value != null && value.equals(entry.value))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V put(K key, V value) {

        Entry<K,V> newEntry = new Entry<>(key, value);

        int index = index(key);

        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry entry = table[index];

            while (entry.next != null) {
                entry = entry.next;
            }
            if (entry.key==key & entry.value!=value) {
                entry.value = value;
                return value;
            }
            else if(entry.key==key & entry.value==value){
                return value;
            }else {
                entry.next = newEntry;
            }
        }
        size++;
        return value;
    }

    @Override
    public V get(Object key) {

        Entry<K,V> entry = table[index(key)];

        if (entry != null) {
            if (key == null) {
                if (entry.key == null) {

                    return entry.value;
                }
            }
            if (entry.key.equals(key)) {
                return entry.value;

            } else if (entry.next != null) {

                while (entry.next != null) {
                    entry = entry.next;
                    if (entry.key.equals(key)) {
                        return entry.value;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public V remove(Object key) {

        V result;

        Entry<K,V> entry = table[index(key)];

        if (entry != null) {
            if (key == null) {

                if (entry.key == null) {

                    result = entry.value;
                    entry.value = null;

                    if (entry.next != null) {
                        Entry<K,V> nextEntry = entry.next;
                        entry.next = nextEntry.next;
                    }
                    size--;
                    return result;
                } else if (entry.next != null) {

                    while (entry.next != null) {
                        Entry prev = entry;
                        entry = entry.next;

                        if (entry.key == null) {
                            result = entry.value;

                            entry.value = null;

                            if (entry.next != null) {
                                prev.next = entry.next;
                            }
                            size--;
                            return result;
                        }
                    }
                }
            } else {
                if (entry.key.equals(key)) {
                    result = entry.value;

                    entry.key = null;
                    entry.value = null;

                    if (entry.next != null) {
                        table[index(key)] = entry.next;
                    }
                    size--;
                    return result;

                } else if (entry.next != null) {

                    while (entry.next != null) {
                        Entry prev = entry;
                        entry = entry.next;

                        if (entry.key.equals(key)) {
                            result = entry.value;

                            entry.key = null;
                            entry.value = null;

                            if (entry.next != null) {
                                prev.next = entry.next;
                            }
                            size--;
                            return result;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return null;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {

    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {

    }

    @Override
    public V putIfAbsent(K key, V value) {
        return null;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return false;
    }

    @Override
    public V replace(K key, V value) {
        return null;
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return null;
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "size=" + size +
                ", table=" + Arrays.toString(table) +
                '}';
    }
}
