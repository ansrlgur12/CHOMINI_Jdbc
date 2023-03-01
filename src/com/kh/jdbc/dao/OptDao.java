package com.kh.jdbc.dao;

import com.kh.jdbc.util.Common;
import com.kh.jdbc.vo.OptVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OptDao {
        Connection conn = null; // 자바와 오라클에 대한 연결 설정
        Statement stmt = null;  //  SQL 문을 수행하기 위한 객체
        PreparedStatement pStmt = null;
        ResultSet rs = null;    // statement 동작에 대한 결과로 전달되는 DB 의 내용
        Scanner sc = new Scanner(System.in);

        // SELECT
        public List<OptVO> optSelect() {
            List<OptVO> list = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT * FROM OPTION_TABLE";
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int orderNum = rs.getInt("주문번호");
                    String breadType = rs.getString("빵");
                    String cheeseType = rs.getString("치즈");
                    String vegType = rs.getString("야채");
                    String sauceType = rs.getString("소스");
                    OptVO optVO = new OptVO(orderNum, breadType, cheeseType, vegType, sauceType);
                    list.add(optVO);
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

        public void optSelectPrint(List<OptVO> list) {
            if(list.isEmpty()) {
                System.out.println("No options found!");
                return;
            }

            for (OptVO e : list) {
                System.out.println("주문번호 : " + e.getOrderNum());
                System.out.println("빵      : " + e.getBreadType());
                System.out.println("치즈    : " + e.getCheeseType());
                System.out.println("야채    : " + e.getVegType());
                System.out.println("소스    : " + e.getSauceType());
                System.out.println("--------------------------------");

            }
        }

        public void optInsert() {
            System.out.println("옵션을 고르세요 ");
            System.out.print("주문번호 : "); // 정해진 주문 번호 입력해야하는 메소드?
            int orderNum = sc.nextInt();
            System.out.print("원하는 빵 종류 : ");
            String breadType = sc.next();
            System.out.print("원하는 치즈 종류 : ");
            String cheeseType = sc.next();
            System.out.print("원하는 야채 종류 : ");
            String vegType = sc.next();
            System.out.print("원하는 소스 종류 : ");
            String sauceType = sc.next();

            String sql = "INSERT INTO OPTION_TABLE VALUES(?,?,?,?,?)";
            try {
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setInt(1, orderNum);
                pStmt.setString(2, breadType);
                pStmt.setString(3, cheeseType);
                pStmt.setString(4, vegType);
                pStmt.setString(5, sauceType);
                pStmt.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
            Common.close(pStmt);
            Common.close(conn);

        }

        public void optUpdate() {
            System.out.print("옵션을 변경할 주문 번호를(?) 입력하세요 : ");
            int orderNum = sc.nextInt();
            System.out.print("원하는 빵 종류 : "); // 현재 빵 종류도 나오게 하면 좋겠다
            String breadType = sc.next();
            System.out.print("원하는 치즈 종류 : ");
            String cheeseType = sc.next();
            System.out.print("원하는 야채 종류 : ");
            String vegType = sc.next();
            System.out.print("원하는 소스 종류 : ");
            String sauceType = sc.next();

            String sql = "UPDATE OPTION_TABLE SET 빵 = ?, 치즈 = ?, 야채 = ?, 소스 = ? WHERE 주문번호 = ?";
            try {
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, breadType);
                pStmt.setString(2, cheeseType);
                pStmt.setString(3, vegType);
                pStmt.setString(4, sauceType);
                pStmt.setInt(5, orderNum);
                pStmt.executeUpdate();

            }catch (Exception e) {
                e.printStackTrace();
            }
            Common.close(pStmt);
            Common.close(conn);
        }

        public void optDelete() { //  초기화?삭제? 하시겠습니까 물어보면 좋을듯
            System.out.print("옵션 삭제를 원하는 주문번호를 입력 하세요 : ");
            int orderNum = sc.nextInt();
            String sql = "DELETE FROM OPTION_TABLE WHERE 주문번호 = ?";
            try {
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setInt(1,orderNum);
                pStmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Common.close(pStmt);
            Common.close(conn);
        }
    }
