/*
 * Lera Kos
 *
 * 05.10.2022
 *
 * This software joins two collections into one using 3 different ways(InnerJoin, LeftJoin, RightJoin).
 */
package operations;

import java.util.Collection;

/**
 * JoinOperation is the interface that provides two collections to be joined into one.
 *
 * @author Lera Kos
 * @version 1.0 05 Oct 2022
 */
public interface JoinOperation<D1, D2, R> {

    Collection<R> join(Collection<D1> leftCollection, Collection<D2> rightCollection);

}
