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
 * LeftJoinOperation is the implementation of JoinOperation. Pick all data from the left collection, and
 * the matching values by key from the right collection.
 *
 * @author Lera Kos
 * @version 1.0 05 Oct 2022
 */
public class LeftJoinOperation<K extends Comparable<K>, V1, V2> implements JoinOperation<DataRow<K, V1>, DataRow<K, V2>, JoinedDataRow<K, V1, V2>> {

    /**
     * The method pick all data from left collection and join with the data of right collection
     * if it has the same key, if not the second value will be null.
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
            DataRow<K, V2> right = rightCollection.stream()
                    .filter(r -> left.getKey().compareTo(r.getKey()) == 0)
                    .findAny().orElse(null);

            joinedDataRows.add(new JoinedDataRow<>(left.getKey(), left.getValue(), (right == null)
                                                                                    ? null
                                                                                    : right.getValue()));
        }

        return joinedDataRows;
    }

}
