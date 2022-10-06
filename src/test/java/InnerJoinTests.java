import data.DataRow;
import data.JoinedDataRow;
import operations.InnerJoinOperation;
import operations.JoinOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * InnerJoinTests tests the InnerJoinOperation class. It checks how class work with normal data (same size and
 * different) and if collection is empty.
 *
 * @author Lera Kos
 * @version 1.0 05 Oct 2022
 */
class InnerJoinTests {

    Collection<DataRow<Integer, String>> leftCollection;
    Collection<DataRow<Integer, String>> rightCollection;
    List<JoinedDataRow<Integer, String, String>> joinedDataRows;

    List<JoinedDataRow<Integer, String, String>> expectInnerJoinResult;

    JoinOperation<DataRow<Integer, String>, DataRow<Integer, String>, JoinedDataRow<Integer, String, String>> joinOperation;

    @BeforeEach
    void setUp() {
        leftCollection = new ArrayList<>(Arrays.asList(new DataRow<>(0, "Ukraine"), new DataRow<>(1, "Germany"),
                                                        new DataRow<>(2, "France")));
        rightCollection = new ArrayList<>(Arrays.asList(new DataRow<>(0, "Kyiv"), new DataRow<>(1, "Berlin"),
                                                        new DataRow<>(3, "Budapest")));

        expectInnerJoinResult = Arrays.asList(new JoinedDataRow<>(0, "Ukraine", "Kyiv"),
                                               new JoinedDataRow<>(1, "Germany", "Berlin"));
    }

    @Test
    void innerJoin() {
        joinOperation = new InnerJoinOperation<>();

        joinedDataRows = (List<JoinedDataRow<Integer, String, String>>) joinOperation
                                                                        .join(leftCollection, rightCollection);

        joinedDataRows.forEach(element -> System.out.println(element.getKey() + " " + element.getValue1()
                                                              + " " + element.getValue2()));

        Assertions.assertIterableEquals(expectInnerJoinResult, joinedDataRows);
    }

    @Test
    void innerJoinWithDifferentSizeOfLeftCollection() {
        joinOperation = new InnerJoinOperation<>();
        leftCollection.add(new DataRow<>(4, "Belgium"));

        joinedDataRows = (List<JoinedDataRow<Integer, String, String>>) joinOperation
                                                                        .join(leftCollection, rightCollection);

        Assertions.assertIterableEquals(expectInnerJoinResult, joinedDataRows);
    }

    @Test
    void innerJoinWithDifferentSizeOfRightCollection() {
        joinOperation = new InnerJoinOperation<>();
        rightCollection.add(new DataRow<>(4, "Brussel"));

        joinedDataRows = (List<JoinedDataRow<Integer, String, String>>) joinOperation
                                                                        .join(leftCollection, rightCollection);

        Assertions.assertIterableEquals(expectInnerJoinResult, joinedDataRows);
    }

    @Test
    void innerJoinWithEmptyList() {
        joinOperation = new InnerJoinOperation<>();

        joinedDataRows = (List<JoinedDataRow<Integer, String, String>>) joinOperation.join(leftCollection, null);
        Assertions.assertIterableEquals(null, joinedDataRows);

        joinedDataRows = (List<JoinedDataRow<Integer, String, String>>) joinOperation.join(null, rightCollection);
        Assertions.assertIterableEquals(null, joinedDataRows);

        joinedDataRows = (List<JoinedDataRow<Integer, String, String>>) joinOperation.join(null, null);
        Assertions.assertIterableEquals(null, joinedDataRows);
    }

}
