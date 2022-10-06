/*
 * Lera Kos
 *
 * 05.10.2022
 *
 * This software joins two collections into one using 3 different ways(InnerJoin, LeftJoin, RightJoin).
 */
package operations;

import data.DataRow;
import data.JoinedDataRow;

import java.util.ArrayList;
import java.util.Collection;

/**
 * InnerJoinOperation is the implementation of JoinOperation. It provides join collections by the same key.
 *
 * @author Lera Kos
 * @version 1.0 05 Oct 2022
 */
public class InnerJoinOperation<K extends Comparable<K>, V1, V2> implements JoinOperation<DataRow<K, V1>, DataRow<K, V2>, JoinedDataRow<K, V1, V2>> {

    /**
     * The method joins two collections by keys that match each other.
     *
     * @return ArrayList
     */
    @Override
    public Collection<JoinedDataRow<K, V1, V2>> join(Collection<DataRow<K, V1>> leftCollection, Collection<DataRow<K, V2>> rightCollection) {
        if (leftCollection == null || rightCollection == null) {
            return null;
        }

        Collection<JoinedDataRow<K, V1, V2>> joinedDataRows = new ArrayList<>();

        for (DataRow<K, V1> left : leftCollection) {
            rightCollection.stream()
                    .filter(right -> left.getKey().compareTo(right.getKey()) == 0)
                    .findAny()
                    .ifPresent(right -> joinedDataRows.add(new JoinedDataRow<>(left.getKey(), left.getValue(),
                                                                                right.getValue())));
        }

        return joinedDataRows;
    }

}
