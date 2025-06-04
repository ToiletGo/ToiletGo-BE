package sql;

/**
 * csv 파일의 내용을 sql을 통해 데이터에 삽입합니다.
 */

import java.io.*;
import java.sql.*;
import java.util.*;

public class DataInsert {

    public static void main(String[] args) {

        Properties props = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            props.load(input);
            System.out.println("application.properties 읽기 성공 ");
        } catch (IOException e) {
            System.err.println("application.properties 읽기 실패: " + e.getMessage());
            return;
        }

        String jdbcUrl = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        String fileName = "src/main/resources/toilet.csv";
        String insertSQL = "INSERT INTO toilet (road_address, lot_address, latitude, longitude, building_name, gu_name, tel_no, toilet_type, open_time, toilet_usage, toilet_status, has_handicap_access, facilities, sign_info, location_detail, note) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
                Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
                PreparedStatement pstmt = conn.prepareStatement(insertSQL)
        ) {
            conn.setAutoCommit(false);
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                // System.out.println("읽은 줄: " + line); //
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = parseCSVLine(line);
                if (fields.length < 16) continue;

                // parseDouble(fields[3])

                // pstmt.setString(1, fields[0]); // 1 연번 toilet_id integer
                pstmt.setString(1, fields[1]); // 1 도로명주소 road_address varchar
                pstmt.setString(2, fields[2]); // 2 지번주소 lot_address varchar
                pstmt.setDouble(3, parseDouble(fields[3])); // 3 x 좌표 (경도) latitude decimal
                pstmt.setDouble(4, parseDouble(fields[4])); // 4 y 좌표 (위도) longitude decimal
                pstmt.setString(5, fields[5]); // 5 건물명 building_name varchar
                pstmt.setString(6, fields[6]); // 6 구 명칭 gu_name varchar
                pstmt.setString(7, fields[7]); // 7 전화번호 tel_no varchar
                pstmt.setString(8, fields[8]); // 8 유형 toilet_type varchar
                pstmt.setString(9, fields[9]); // 9 개방시간 open_time varchar
                pstmt.setString(10, fields[10]); // 10 소재지 용도 toilet_usage varchar
                pstmt.setString(11, fields[11]); // 11 화장실 현황 toilet_status varchar
                pstmt.setBoolean(12, !fields[12].trim().isEmpty()); // 12 장애인화장실 현황 has_handicap_access boolean
                pstmt.setString(13, fields[13]); // 13 편의시설 facilities text
                pstmt.setString(14, fields[14]); // 14 안내표지 sign_info
                pstmt.setString(15, fields[15]); // 15 소재지 location_detail
                pstmt.setString(16, fields[16]); // 16 비고 note


                pstmt.executeUpdate();
            }
            conn.commit();

            System.out.println("csv data inserted.");

        } catch (SQLException ex) {
            System.err.println("삽입 실패: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception e) {
            // conn.rollback();  // 추가
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
