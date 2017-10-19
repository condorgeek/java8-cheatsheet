package my.helloworld;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class HelloTest {
    Hello hello = new Hello();

    @Test
    public void testHelloWorld() {
        assertEquals("hello Peter", hello.hello("Peter"));
    }

    @Test
    public void testBigNumber() {
        Long number = hello.bignumber(new String[]{"77", "332", null, "10", "500"});
        assertEquals(new Long(5003327710L), number);

        Long n2 = hello.bignumber(new String[]{"332", "", "500", null});
        assertEquals(new Long(500332), n2);
    }

    @Test
    public void testMax() {
        Long number = hello.max(new String[]{"77", "332", null, "10", "500", ""});
        assertEquals(new Long(500), number);
    }

    @Test
    public void testSplitNumber() {
        Long number = hello.splitnumber("77 332   10 500");
        assertEquals(new Long(5003327710L), number);
    }

    @Test
    public void testLoadData() throws IOException {
        String[] lines = hello.loadfile("data.txt");
        assertEquals(3, lines.length);

        String[] ll = hello.loadfile2("data.txt");
        assertEquals(3, ll.length);

        Long number = hello.splitnumber(lines[0]);
        assertEquals(new Long(10011002811L), number);

        Long nn = hello.splitnumber(ll[0]);
        assertEquals(new Long(10011002811L), nn);
    }

    /** data.txt
     100 28 11 1001
     10 09 35 44 51 543 34 21 27 1
     45 28 96 75 41 66 73 89 65 48
    */
    @Test
    public void testLoadNumbers() throws IOException {
        List<Integer> numbers = hello.loadNumbers("data.txt");

        assertEquals(24, numbers.size());
        assertEquals(new Integer(1001), numbers.get(0));
        assertEquals(new Integer(1), numbers.get(23));
    }

    @Test
    public void testSortString() {
        String reversed = hello.sortString("zabcxdef12");
        assertEquals("zxfedcba21", reversed);
    }

    @Test
    public void testReverse() {
        String reversed = hello.reverse("zabcxdef12");
        assertEquals("21fedxcbaz", reversed);

        String reversed2 = hello.reverse2("zabcxdef12");
        assertEquals("21fedxcbaz", reversed2);

        String reversed3 = hello.reverse3("zabcxdef12");
        assertEquals("21fedxcbaz", reversed3);
    }

    @Test
    public void testPrime() {
        boolean isPrime = hello.isPrime(23);
        assertTrue(isPrime);

        boolean isPrimeToo = hello.isPrime(113);
        assertTrue(isPrimeToo);

        boolean isNot = hello.isPrime(100);
        assertFalse(isNot);
    }

    @Test
    public void testGeneratePrimes() {
        Integer[] primes = hello.generatePrimes(10);
        assertArrayEquals(new Integer[]{3, 5, 7}, primes);
    }

    @Test
    public void testSplit() {
        String[] words = hello.split("One Two,Three  Four,Five ");
        assertEquals(5, words.length);
    }

    @Test
    public void testJoinNumber() {
        String joined = hello.joinNumber("11 ,  34,,   45 ,,  88, 99||22");
        assertEquals("998845342211", joined);
    }

    @Test
    public void testFactorial() {
        int factorial = hello.factorial(5);
        assertEquals(120, factorial);

        int fact2 = hello.fact2(5);
        assertEquals(120, factorial);
    }

    @Test
    public void testLd() throws Exception {
        hello.list("../HelloWorld");
    }

    @Test
    public void testAmortization() {
        hello.amortization(20000, 0.6, 20);
    }

    @Test
    public void testSumPrimes() {
        Integer sum = hello.sumPrimes(new int[]{1, 4, 5, 5, 7, 9, 13, 21, 30, 7});
        assertEquals(new Integer(37), sum);
    }

    @Test public void testDaysToDate() {
        long days = hello.daysElapsed("2017.01.10");
        assertEquals(9, days);

        long d2 = hello.daysElapsed("2017.02.01");
        assertEquals(31, d2);

        long d3 = hello.daysElapsed("2017.03.01");
        assertEquals(59, d3);
    }
}