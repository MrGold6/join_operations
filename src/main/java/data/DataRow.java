/*
 * Lera Kos
 *
 * 05.10.2022
 *
 * This software joins two collections into one using 3 different ways(InnerJoin, LeftJoin, RightJoin).
 */
package data;

import org.jetbrains.annotations.NotNull;

/**
 * DataRow is input data(D1, D2) for the implementation classes of JoinOperation<D1, D2, R>
 *
 * @author Lera Kos
 * @version 1.0 05 Oct 2022
 */
public class DataRow<K extends Comparable<K>, V> {

    private final K key;
    private final V value;

    public DataRow(@NotNull K key, @NotNull V value) {
        this.key = key;
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

}
