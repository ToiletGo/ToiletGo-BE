package sql;

/**
 * csv 파일의 내용을 sql을 통해 데이터에 삽입합니다.
 */

import java.io.*;
import java.sql.*;
import java.util.*;

public class SQLMapper {

    public static void main(String[] args) {

        Properties props = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/insert.properties")) {
            props.load(input);
        } catch (IOException e) {
            System.err.println("insert.properties 읽기 실패: " + e.getMessage());
            return;
        }

        String jdbcUrl = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        String fileName = "src/main/resources/toilet.csv";
        String insertSQL = "INSERT INTO toilet () " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
                Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
                PreparedStatement pstmt = conn.prepareStatement(insertSQL)
        ) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = parseCSVLine(line);
                if (fields.length < 17) continue;

                pstmt.setString(1, fields[1]);
                pstmt.setString(2, fields[2]);
                pstmt.setDouble(3, parseDouble(fields[3]));
                pstmt.setDouble(4, parseDouble(fields[4]));
                pstmt.setString(5, fields[5]);
                pstmt.setString(6, fields[7]);
                pstmt.setString(7, fields[8]);
                pstmt.setString(8, fields[9]);
                pstmt.setString(9, fields[10]);
                pstmt.setString(10, fields[11]);
                pstmt.setBoolean(11, !fields[12].trim().isEmpty());
                pstmt.setString(12, fields[13]);
                pstmt.setString(13, fields[14]);
                pstmt.setString(14, fields[16]);

                pstmt.executeUpdate();
            }

            System.out.println("csv data inserted.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder field = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '\"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(field.toString().trim());
                field.setLength(0);
            } else {
                field.append(c);
            }
        }
        result.add(field.toString().trim());
        return result.toArray(new String[0]);
    }

    private static double parseDouble(String s) {
        try {
            return Double.parseDouble(s.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }
}
