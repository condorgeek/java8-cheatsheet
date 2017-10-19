package my.helloworld;


import java.io.*;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Hello {

    String hello(String name) {
        return "hello " + name;
    }

    Long bignumber(String[] values) {

        String reduced = Arrays.stream(values)
                .filter(s -> s != null && s.length() > 0)
                .map(s -> new Integer(s.trim()))
                .sorted(desc)
                .map(i -> i + "").reduce("", (a, b) -> a + b);

        return new Long(reduced);
    }

    Long max(String[] values) {
        Optional<Long> max = Arrays.stream(values)
                .filter(s -> s != null && s.length() > 0)
                .map(s -> new Long(s.trim()))
                .max((a, b) -> a < b ? -1 : 0);

        return max.isPresent() ? max.get() : null;

    }

    Long splitnumber(String value) {
        return bignumber(value.split(" "));
    }

    String[] loadfile(String filename) throws FileNotFoundException, IOException {

        File file = new File(filename);
        InputStreamReader input = new InputStreamReader(new FileInputStream(file));
        BufferedReader reader = new BufferedReader(input);

        List<String> lines = new ArrayList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        return lines.toArray(new String[lines.size()]);
    }

    List<Integer> loadNumbers(String filename) throws FileNotFoundException, IOException {

        File file = new File(filename);
        InputStreamReader input = new InputStreamReader(new FileInputStream(file));
        BufferedReader reader = new BufferedReader(input);

        List<Integer> numbers = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            numbers.addAll(Stream.of(line.split("\\s"))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList()));
        }

        return numbers.stream()
                .sorted((a, b) -> a > b ? -1 : 0)
                .collect(Collectors.toList());
    }

    String[] loadfile2(String filename) throws FileNotFoundException, IOException {

        File file = new File(filename);
        List<String> lines = Files.readAllLines(file.toPath());

        return lines.toArray(new String[lines.size()]);
    }

    String sortString(String value) {
        StringBuffer buffer = new StringBuffer();
        value.chars().boxed()
                .sorted(desc)
                .forEach(i -> buffer.append((char) (i.byteValue())));

        return buffer.toString();
    }

    String reverse(String value) {

        StringBuffer buffer = new StringBuffer();
        for (int i = value.length(); i > 0; i--) {
            buffer.append(value.charAt(i - 1));
        }

        return buffer.toString();
    }

    String reverse2(String value) {
        StringBuffer buffer = new StringBuffer();
        value.chars().forEach( c -> buffer.insert(0, (char) c));
        return buffer.toString();
    }

    String reverse3(String value) {
        return new StringBuffer(value).reverse().toString();
    }

    Integer[] generatePrimes(int number) {

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < number; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes.toArray(new Integer[primes.size()]);
    }

    boolean isPrime(int number) {
        int cnt = 0;
        if (number % 2 == 0) return false;

        int floor = (int) Math.floor(Math.sqrt(number));
        for (int i = 3; i <= floor; i += 2, cnt++) {
            if (number % i == 0) return false;
        }
        System.out.println("Interation count: " + cnt + " Floor: " + floor);
        return true;
    }

    String[] split(String value) {
        String[] words = value.split("\\s+|,");
        return words;
    }

    String joinNumber(String value) {
        String[] values = value.split("(\\s+|,+|\\|+)+");
        String joined = Arrays.stream(values)
                .sorted(stringdesc)
                .reduce("", (a, b) -> a + b);

        return joined;
    }

    int factorial(int number) {
        if (number == 1) return 1;
        return number * factorial(number - 1);
    }

    int fact2(int number) {
        return IntStream.rangeClosed(2, number)
                .reduce(1, (a, b) -> a * b);
    }

    void list(String path) throws IOException {
        File file = new File(path);
        if (file.isDirectory()) {
            System.out.println("/-- " + file.getName());
            list(file, 1);
        }
    }

    void list(File file, int level) {
        File[] files = file.listFiles();

        if (files == null) return;
        for (File f : files) {
            if (f.isFile()) {
                System.out.println((span(level, " ")) + "|-- " + f.getName() + " (" + f.length() + ")");
            }
        }
        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println((span(level, " ")) + "/-- " + f.getName());
                list(f, ++level);
            }
        }
    }

    String span(int len, String token) {
        String span = "";
        for (int i = 0; i < len; i++) {
            span = span + token;
        }
        return span + " ";
    }


    Map<Integer, List<Double>> amortization(double principal, double apr, int period) {
        Map<Integer, List<Double>> totals = new HashMap<>();
        _amortization(principal, apr, period, principal / period, totals);

        System.out.println(String.join("   +",
                new String[]{" PRINCIPAL", "  TOT PAY ", " INTEREST ", " MTL RATE ", "  BALANCE"}));
        totals.keySet().stream().sorted().forEach(i -> {
            List<Double> a = totals.get(i);
            String l = a.stream()
                    .map(d -> String.format("%11.2f", d))
                    .reduce("", (s, d) -> s + d + "   |");
            System.out.println(l);
        });

        return totals;
    }

    void _amortization(double principal, double apr, int period, double rate, Map<Integer, List<Double>> totals) {
        if (principal == 0.0) return;
        double interest = principal * apr / period;
        List<Double> line = Arrays.asList(principal, rate + interest, principal * apr / period, rate, principal - rate);
        totals.put(totals.size() + 1, line);
        _amortization(principal - rate, apr, period, rate, totals);
    }

    Integer sumPrimes(int[] numbers) {
        Integer sum = Arrays.stream(numbers).boxed()
                .filter(i -> !(i % 2 == 0 || i == 1))
                .filter(i -> {
                    int ceil = (int) Math.floor(Math.sqrt(i));

                    for (int a = 3; a <= ceil; a += 2) {
                        if (i % a == 0) return false;
                    }
                    return true;
                })
                .reduce(0, (a, b) -> a + b);

        return sum;
    }

    long daysElapsed(String date) {
        LocalDate start = LocalDate.of(2017, 1, 1);
        LocalDate now = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        return ChronoUnit.DAYS.between(start, now);
    }

    Comparator<String> stringdesc = (a, b) -> b.compareTo(a);
    Comparator<Integer> desc = (a, b) -> a > b ? -1 : 0;

}
