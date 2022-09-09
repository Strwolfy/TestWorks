package org.example;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class TestWorkTest {
    ArrayList<String> indexes;
    @Test
    public void callFirstFunction() {

        indexes = new ArrayList<>();
        indexes.add("1,3-5");
        indexes.add("2-6");
        indexes.add("3-4");

        ArrayList<Integer[]> FirstBa = TestWork.firstFunction(indexes);

        ArrayList<Integer[]> check = new ArrayList<>();
        check.add(0, new Integer[]{1,3,4,5} );
        check.add(1, new Integer[]{2,3,4,5,6});
        check.add(2, new Integer[]{3,4});

        assertEquals(check.toArray(), FirstBa.toArray());
    }

    @Test
    public void callSecondsFunction() {

        ArrayList<String> indexes = new ArrayList<>();
        indexes.add("1,3-5");
        indexes.add("2-6");
        indexes.add("3-4");

        ArrayList<Integer[]> FirstBa = TestWork.firstFunction(indexes);
        TestWork.secondsFunction(FirstBa);
    }
}