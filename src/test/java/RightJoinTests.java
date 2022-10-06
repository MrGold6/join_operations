import data.DataRow;
import data.JoinedDataRow;
import operations.JoinOperation;
import operations.RightJoinOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * RightJoinTests tests the RightJoinOperation class. It checks how class work with normal data (same size and
 * different) and if collection is empty.
 *
 * @author Lera Kos
 * @version 1.0 05 Oct 2022
 */
public class RightJoinTests {

    Collection<DataRow<Integer, String>> leftCollection;
    Collection<DataRow<Integer, String>> rightCollection;
    List<JoinedDataRow<Integer, String, String>> joinedDataRows;

    List<JoinedDataRow<Integer, String, String>> expectRightJoinResult;

    JoinOperation<DataRow<Integer, String>, DataRow<Integer, String>, JoinedDataRow<Integer, String, String>> joinOperation;

    @BeforeEach
    void setUp() {
        leftCollection = new ArrayList<>(Arrays.asList(new DataRow<>(0, "Ukraine"), new DataRow<>(1, "Germany"),
                                                        new DataRow<>(2, "France")));
        rightCollection = new ArrayList<>(Arrays.asList(new DataRow<>(0, "Kyiv"), new DataRow<>(1, "Berlin"),
                                           new DataRow<>(3, "Budapest")));

        expectRightJoinResult =  new ArrayList<>(Arrays.asList(new JoinedDataRow<>(0, "Ukraine", "Kyiv"),
                                                   new JoinedDataRow<>(1, "Germany", "Berlin"),
                                                   new JoinedDataRow<>(3, null, "Budapest")));
    }

    @Test
    void rightJoin() {
        joinOperation = new RightJoinOperation<>();

        joinedDataRows = (List<JoinedDataRow<Integer, String, String>>) joinOperation
                                                                        .join(leftCollection, rightCollection);

        joinedDataRows.forEach(element -> System.out.println(element.getKey() + " " + element.getValue1() + " "
                                                              + element.getValue2()));

        Assertions.assertIterableEquals(expectRightJoinResult, joinedDataRows);
    }

    @Test
    void rightJoinWithDifferentSizeOfLeftCollection() {
        joinOperation = new RightJoinOperation<>();
        leftCollection.add(new DataRow<>(4, "Belgium"));

        joinedDataRows = (List<JoinedDataRow<Integer, String, String>>) joinOperation
                                                                        .join(leftCollection, rightCollection);

        Assertions.assertIterableEquals(expectRightJoinResult, joinedDataRows);
    }

    @Test
    void rightJoinWithDifferentSizeOfRightCollection() {
        joinOperation = new RightJoinOperation<>();
        rightCollection.add(new DataRow<>(4, "Brussel"));
        expectRightJoinResult.add(new JoinedDataRow<>(4, null,"Brussel"));

        joinedDataRows = (List<JoinedDataRow<Integer, String, String>>) joinOperation
                                                                        .join(leftCollection, rightCollection);

        Assertions.assertIterableEquals(expectRightJoinResult, joinedDataRows);
    }

    @Test
    void rightJoinWithEmptyList() {
        joinOperation = new RightJoinOperation<>();

        joinedDataRows = (List<JoinedDataRow<Integer, String, String>>) joinOperation.join(leftCollection, null);
        Assertions.assertIterableEquals(null, joinedDataRows);

        joinedDataRows = (List<JoinedDataRow<Integer, String, String>>) joinOperation.join(null, rightCollection);
        Assertions.assertIterableEquals(null, joinedDataRows);

        joinedDataRows = (List<JoinedDataRow<Integer, String, String>>) joinOperation.join(null, null);
        Assertions.assertIterableEquals(null, joinedDataRows);
    }

}
