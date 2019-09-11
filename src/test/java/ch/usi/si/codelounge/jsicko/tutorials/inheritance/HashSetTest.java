package ch.usi.si.codelounge.jsicko.tutorials.inheritance;

import org.junit.jupiter.api.Test;

public class HashSetTest {

    @Test
    public void addTest() throws Throwable {
        HashSet<Integer> intSet = new HashSet<>();
        intSet.add(3);
        intSet.add(3);
    }

    @Test
    public void removeTest() throws Throwable {
        HashSet<Integer> intSet = new HashSet<>();
        intSet.add(3);
        intSet.remove(3);
        intSet.remove(4);
        intSet.remove(3);
        intSet.remove(3);
    }

}
