/*
 * Lera Kos
 *
 * 05.10.2022
 *
 * This software joins two collections into one using 3 different ways(InnerJoin, LeftJoin, RightJoin).
 */
package data;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * JoinedDataRow is the result data(R) for the implementation classes of JoinOperation<D1, D2, R>
 *
 * @author Lera Kos
 * @version 1.0 05 Oct 2022
 */
public class JoinedDataRow<K extends Comparable<K>, V1, V2> {

    private final K key;
    private final V1 value1;
    private final V2 value2;

    public JoinedDataRow(@NotNull K key, V1 value1, V2 value2) {
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
    }

    public K getKey() {
        return key;
    }

    public V1 getValue1() {
        return value1;
    }

    public V2 getValue2() {
        return value2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final JoinedDataRow<K, V1, V2> other = (JoinedDataRow<K, V1, V2>) obj;

        if (!Objects.equals(this.key, other.key)) {
            return false;
        }

        if (!Objects.equals(this.value1, other.value1)) {
            return false;
        }

        if (!Objects.equals(this.value2, other.value2)) {
            return false;
        }

        return true;
    }

}
