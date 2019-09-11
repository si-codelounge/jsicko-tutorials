package ch.usi.si.codelounge.jsicko.tutorials.inheritance;

import org.junit.jupiter.api.Test;

public class ArrayListTest {

    @Test
    public void addTest() throws Throwable {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(3);
    }

    @Test
    public void addNullTest() throws Throwable {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(null);
    }

    @Test
    public void removeTest() throws Throwable {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(3);
        list.add(3);
        list.remove(3);
        list.remove(4);
        list.remove(3);
        list.remove(3);
    }

}
