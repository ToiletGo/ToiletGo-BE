package sql;

import java.io.*;

public class CSVFileDump {

    public static void main(String[] args) {
        String fileName = "src/main/resources/toilet.csv"; //
        System.out.println("한글 인코딩 테스트 <UNK> <UNK>");

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), "MS949"))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("\uFEFF")) {
                    line = line.substring(1); // BOM 제거
                }
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Error occured: " + e.getMessage());
        }
    }
}