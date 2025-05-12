package sql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVFileDump {

    public static void main(String[] args) {
        String fileName = "toilet.csv"; //

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line); // line
            }
        } catch (IOException e) {
            System.err.println("Error occured: " + e.getMessage());
        }
    }
}