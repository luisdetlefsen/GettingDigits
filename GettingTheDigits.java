import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProblemA {

    private PrintWriter writer;

    private String collect(String input, String pattern) {
        if (input == null) {
            return null;
        }
        boolean numberCollected = false;
        String copy = input + "";
        for (int i = 0; i < pattern.length(); i++) {
            if (copy.indexOf(pattern.charAt(i)) >= 0) {
                copy = copy.replaceFirst("" + pattern.charAt(i), "_");
                numberCollected = true;
            } else {
                numberCollected = false;
                break;
            }
        }
        if (numberCollected) {
            return copy;
        } else {
            return null;
        }
    }

    private static String r = "";

    private int collectAll(String input, String pattern) {
        r = input;
        String last = r + "";
        int occurrences = 0;
        do {
            occurrences++;
            r = collect(r, pattern);
            if (r != null) {
                last = r;
            }
        } while (r != null);
        r = last;

        return occurrences - 1;
    }

    List<Integer> numbers = new ArrayList<>();
    private void printNumber(int c, String n) {
        for (int i = 0; i < c; i++) {
        //    writer.print(n);
            numbers.add(Integer.parseInt(n));
        }
    }


    private void decypher(String s, int testCase) throws Exception {
        String c = s + "_";
        writer.print("Case #" + testCase + ": ");
        numbers = new ArrayList();
        r = s + "";
        printNumber(collectAll(r, "SIX"), "6");
        printNumber(collectAll(r, "EIGHT"), "8");
        printNumber(collectAll(r, "ZERO"), "0");
        printNumber(collectAll(r, "FOUR"), "4");
        printNumber(collectAll(r, "TWO"), "2");
        printNumber(collectAll(r, "FIVE"), "5");
        printNumber(collectAll(r, "THREE"), "3");
        printNumber(collectAll(r, "SEVEN"), "7");
        printNumber(collectAll(r, "NINE"), "9");
        printNumber(collectAll(r, "ONE"), "1");

        if (r.replaceAll("_", "").compareTo("") != 0) {
            throw new Exception("String not empty:" + s + " test Case #" + testCase);
        };

        Collections.sort(numbers);
        numbers.forEach(x->writer.print(x));
        
        writer.println(" ");

    }

    private void processFile(String filePath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            int i = 0;
            String T = br.readLine();
            String line = "";
            while (i < Integer.parseInt(T)) {
                line = br.readLine();
                try {
                    decypher(line, i + 1);
                } catch (Exception e) {
                    System.err.println(e);
                };
                i++;
            }
            writer.print(" ");
        } finally {
            br.close();
        }
    }

    public static void main(String[] args) {
        try {
            ProblemA pb = new ProblemA();
            pb.writer = new PrintWriter("C:\\CodeJam\\1B\\A\\output_large.txt", "UTF-8");
            pb.processFile("C:\\CodeJam\\1B\\A\\A-large-practice.in");

            pb.writer.flush();
            pb.writer.close();
        } catch (IOException ex) {
            Logger.getLogger("").log(Level.SEVERE, null, ex);
        }

    }
}