package com.kh.jdbc.dao;

import com.kh.jdbc.util.Common;

import java.sql.*;
import java.util.Scanner;

public class TempBasketDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);


    public void tempBasketInsert() {
        System.out.print("회원번호를 입력하세요 \n ☞ ");
        int customNo = sc.nextInt();

        String menuName;
        while (true) {
            System.out.println();
            System.out.println("※ 메뉴 번호를 입력하세요");
            System.out.print("[1]클럽샌드위치 [2]BLT샌드위치 [3]에그샌드위치 [4]참치샌드위치 [5]연어샌드위치\n ☞ ");
            int selMenu = sc.nextInt();
            switch (selMenu) {
                case 1:
                    menuName = "클럽샌드위치";
                    break;

                case 2:
                    menuName = "BLT샌드위치";
                    break;

                case 3:
                    menuName = "에그샌드위치";
                    break;

                case 4:
                    menuName = "참치샌드위치";
                    break;

                case 5:
                    menuName = "연어샌드위치";
                    break;


                default:
                    System.out.println("선택하신 번호를 다시 확인하세요");
                    selMenu = 0;
                    continue;
            }
            break;
        }

        String size = null;
        while (true) {
            System.out.println();
            System.out.println("※ 사이즈 번호를 입력하세요 ");
            System.out.print("[1] S  [2] M  [3] L  \n ☞ ");
            int selSize = sc.nextInt();

            switch (selSize) {
                case 1:
                    size = "S";
                    break;

                case 2:
                    size = "M";
                    break;

                case 3:
                    size = "L";
                    break;

                default:
                    System.out.println("선택하신 번호를 다시 확인하세요.");
                    selSize = 0;
                    continue;
            }
            break;
        }
        System.out.println();
        System.out.print("수량을 입력 하세요 \n ☞ ");
        int howMany = sc.nextInt();

        String bread;
        while (true) {
            System.out.println();
            System.out.println("※ 빵 번호를 입력하세요");
            System.out.print("[1] 화이트 [2] 파마산오레가노 [3] 위트 [4] 허니오트 [5] 하티 [6] 플랫브래드" +
                    "\n ☞ ");
            int selBreadType = sc.nextInt();
            switch (selBreadType) {
                case 1:
                    bread = "화이트";
                    break;
                case 2:
                    bread = "파마산오레가노";
                    break;
                case 3:
                    bread = "위트";
                    break;
                case 4:
                    bread = "허니오트";
                    break;
                case 5:
                    bread = "하티";
                    break;
                case 6:
                    bread = "플랫브레드";
                    break;

                default:
                    System.out.println("선택하신 번호를 다시 확인하세요");
                    selBreadType = 0;
                    continue;
            }
            break;
        }


        String cheese;
        while (true) {
            System.out.println();
            System.out.println("※ 치즈 번호를 입력하세요");
            System.out.print("[1] 아메리칸치즈 [2] 슈레드치즈 [3] 모차렐라치즈 [4] 선택안함\n ☞ ");
            int selCheeseType = sc.nextInt();
            switch (selCheeseType) {
                case 1:
                    cheese = "아메리칸치즈";
                    break;
                case 2:
                    cheese = "슈레드치즈";
                    break;
                case 3:
                    cheese = "모차렐라치즈";
                    break;
                case 4 :
                    cheese = "선택안함";
                    break;
                default:
                    System.out.println("선택하신 번호를 다시 확인하세요");
                    selCheeseType = 0;
                    continue;
            }
            break;
        }

        String vegetable;
        while (true) {
            System.out.println();
            System.out.println("※ 빼고 싶은 야채번호를 입력하세요");
            System.out.print("[1] 양상추 [2] 토마토 [3] 오이 [4] 피망 [5] 양파 [6] 피클 [7] 할라피뇨 [8] 선택안함 \n ☞ ");
            int selVeg = sc.nextInt();
            switch (selVeg) {
                case 1:
                    vegetable = "양상추";
                    break;
                case 2:
                    vegetable = "토마토";
                    break;
                case 3:
                    vegetable = "오이";
                    break;
                case 4:
                    vegetable = "피망";
                    break;
                case 5:
                    vegetable = "양파";
                    break;
                case 6:
                    vegetable = "피클";
                    break;
                case 7:
                    vegetable = "할라피뇨";
                    break;
                case 8 :
                    vegetable = "선택안함";
                    break;
                default:
                    System.out.println("선택하신 번호를 다시 확인하세요");
                    selVeg = 0;
                    continue;
            }
            break;
        }

        String sauce;
        while (true) {
            System.out.println();
            System.out.println("※ 소스 번호를 입력하세요");
            System.out.print("[1] 머스타드 [2] 레드와인식초 [3] 스위트어니언 [4] 허니머스타드 [5] 스위트칠리 [6] 스모크바비큐 \n[7] 랜치 [8] 마요네즈 [9] 핫칠리 [10] 홀스래디쉬 [11] 올리브오일 [12] 소금 [13] 후추 [14] 선택안함 \n ☞  ");
            int selSau = sc.nextInt();
            switch (selSau) {
                case 1:
                    sauce = "머스타드";
                    break;
                case 2:
                    sauce = "레드와인식초";
                    break;
                case 3:
                    sauce = "스위트어니언";
                    break;
                case 4:
                    sauce = "허니머스타드";
                    break;
                case 5:
                    sauce = "스위트칠리";
                    break;
                case 6:
                    sauce = "스모크바비큐";
                    break;
                case 7:
                    sauce = "랜치";
                    break;
                case 8:
                    sauce = "마요네즈";
                    break;
                case 9:
                    sauce = "핫칠리";
                    break;
                case 10:
                    sauce = "홀스래디쉬";
                    break;
                case 11:
                    sauce = "올리브오일";
                    break;
                case 12:
                    sauce = "소금";
                    break;
                case 13:
                    sauce = "후추";
                    break;
                case 14 :
                    sauce = "선택안함";
                    break;
                default:
                    System.out.println("선택하신 번호를 다시 확인하세요");
                    selSau = 0;
                    continue;
            }
            break;
        }

        String sql = "INSERT INTO TEMP_BASKET(회원번호, 임시주문번호, 메뉴이름, 사이즈, 빵, 치즈, 야채, 소스, 수량)" +
                " VALUES(?,seq_TEMPBASKET.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, customNo);
            pStmt.setString(2, menuName);
            pStmt.setString(3, size);
            pStmt.setString(4, bread);
            pStmt.setString(5, cheese);
            pStmt.setString(6, vegetable);
            pStmt.setString(7, sauce);
            pStmt.setInt(8, howMany);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }


    public void printBasket() {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql_check = "SELECT 임시주문번호, TEMP_BASKET.메뉴이름, TEMP_BASKET.사이즈, 빵, 치즈, 야채, 소스, 수량, ((MENU.메뉴가격+SIZE_TABLE.추가금액)*수량) 합계"
                    + " FROM TEMP_BASKET, SIZE_TABLE, MENU"
                    + " WHERE TEMP_BASKET.메뉴이름 = MENU.메뉴이름"
                    + " AND TEMP_BASKET.사이즈 = SIZE_TABLE.사이즈";
            rs = stmt.executeQuery(sql_check);

            System.out.println();
            int total = 0;
            System.out.println("---------- 주문을 확인 하세요 ----------");
            System.out.println();
            while (rs.next()) {
                System.out.println("주문번호 : " + rs.getInt("임시주문번호"));
                System.out.println("메뉴이름 : " + rs.getString("메뉴이름"));
                System.out.println("사이즈  : " + rs.getString("사이즈"));
                System.out.println("재료    : " + rs.getString("빵") + " / " + rs.getString("치즈") + " / " + rs.getString("야채") + " / " + rs.getString("소스"));
                System.out.println("수량    : " + rs.getInt("수량"));
                System.out.println("합계    : " + rs.getInt("합계"));
                total += rs.getInt("합계");
                System.out.println("-------------------------------------");
            }
            System.out.println("결제하실 총 금액은 " + total + "원 입니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
    }

    public void tempBasketUpdate() {
        System.out.print("변경을 원하는 주문번호를 입력하세요 ☞ ");
        int tempOrderNo = sc.nextInt();
        String menuName;
        while (true) {
            System.out.println();
            System.out.println("※ 메뉴 번호를 입력하세요");
            System.out.print("[1] 클럽샌드위치 [2] BLT샌드위치 [3] 에그샌드위치 [4] 참치샌드위치 [5] 연어샌드위치\n ☞ ");
            int selMenu = sc.nextInt();
            switch (selMenu) {
                case 1:
                    menuName = "클럽샌드위치";
                    break;

                case 2:
                    menuName = "BLT샌드위치";
                    break;

                case 3:
                    menuName = "에그샌드위치";
                    break;

                case 4:
                    menuName = "참치샌드위치";
                    break;

                case 5:
                    menuName = "연어샌드위치";
                    break;

                default:
                    System.out.println("선택하신 번호를 다시 확인하세요");
                    selMenu = 0;
                    continue;
            }
            break;
        }

        String size = null;
        while (true) {
            System.out.println();
            System.out.println("※ 사이즈 번호를 입력하세요 ");
            System.out.print("[1] S  [2] M  [3] L  \n ☞ ");
            int selSize = sc.nextInt();

            switch (selSize) {
                case 1:
                    size = "S";
                    break;

                case 2:
                    size = "M";
                    break;

                case 3:
                    size = "L";
                    break;

                default:
                    System.out.println("선택하신 번호를 다시 확인하세요.");
                    selSize = 0;
                    continue;
            }
            break;
        }
        System.out.println();
        System.out.print("※ 수량을 입력 하세요 \n ☞ ");
        int howMany = sc.nextInt();

        String bread;
        while (true) {
            System.out.println();
            System.out.println("※ 빵 번호를 입력하세요 ");
            System.out.print("[1] 화이트 [2] 파마산오레가노 [3] 위트 [4] 허니오트 [5] 하티 [6] 플랫브래드 \n  ☞ ");
            int selBreadType = sc.nextInt();
            switch (selBreadType) {
                case 1:
                    bread = "화이트";
                    break;
                case 2:
                    bread = "파마산오레가노";
                    break;
                case 3:
                    bread = "위트";
                    break;
                case 4:
                    bread = "허니오트";
                    break;
                case 5:
                    bread = "하티";
                    break;
                case 6:
                    bread = "플랫브레드";
                    break;

                default:
                    System.out.println("선택하신 번호를 다시 확인하세요");
                    selBreadType = 0;
                    continue;
            }
            break;
        }


        String cheese;
        while (true) {
            System.out.println();
            System.out.println("※ 치즈 번호를 입력하세요");
            System.out.print("[1] 아메리칸치즈 [2] 슈레드치즈 [3] 모차렐라치즈 [4] 선택안함 \n ☞ ");
            int selCheeseType = sc.nextInt();
            switch (selCheeseType) {
                case 1:
                    cheese = "아메리칸치즈";
                    break;
                case 2:
                    cheese = "슈레드치즈";
                    break;
                case 3:
                    cheese = "모차렐라치즈";
                    break;
                case 4 :
                    cheese = "선택안함";
                    break;
                default:
                    System.out.println("선택하신 번호를 다시 확인하세요");
                    selCheeseType = 0;
                    continue;
            }
            break;
        }

        String vegetable;
        while (true) {
            System.out.println();
            System.out.println("※ 뺄 야채 번호를 입력하세요");
            System.out.print("[1] 양상추 [2] 토마토 [3] 오이 [4] 피망 [5] 양파 [6] 피클 [7] 할라피뇨 [8] 선택안함 \n ☞ ");
            int selVeg = sc.nextInt();
            switch (selVeg) {
                case 1:
                    vegetable = "양상추";
                    break;
                case 2:
                    vegetable = "토마토";
                    break;
                case 3:
                    vegetable = "오이";
                    break;
                case 4:
                    vegetable = "피망";
                    break;
                case 5:
                    vegetable = "양파";
                    break;
                case 6:
                    vegetable = "피클";
                    break;
                case 7:
                    vegetable = "할라피뇨";
                    break;
                case 8 :
                    vegetable = "선택안함";
                    break;
                default:
                    System.out.println("선택하신 번호를 다시 확인하세요");
                    selVeg = 0;
                    continue;
            }
            break;
        }

        String sauce;
        while (true) {
            System.out.println();
            System.out.println("※ 소스 번호를 입력하세요");
            System.out.print("[1] 머스타드 [2] 레드와인식초 [3] 스위트어니언 [4] 허니머스타드 [5] 스위트칠리 [6] 스모크바비큐 \n[7] 랜치 [8] 마요네즈 [9] 핫칠리 [10] 홀스래디쉬 [11] 올리브오일 [12] 소금 [13] 후추 [14] 선택안함\n ☞ ");
            int selSau = sc.nextInt();
            switch (selSau) {
                case 1:
                    sauce = "머스타드";
                    break;
                case 2:
                    sauce = "레드와인식초";
                    break;
                case 3:
                    sauce = "스위트어니언";
                    break;
                case 4:
                    sauce = "허니머스타드";
                    break;
                case 5:
                    sauce = "스위트칠리";
                    break;
                case 6:
                    sauce = "스모크바비큐";
                    break;
                case 7:
                    sauce = "랜치";
                    break;
                case 8:
                    sauce = "마요네즈";
                    break;
                case 9:
                    sauce = "핫칠리";
                    break;
                case 10:
                    sauce = "홀스래디쉬";
                    break;
                case 11:
                    sauce = "올리브오일";
                    break;
                case 12:
                    sauce = "소금";
                    break;
                case 13:
                    sauce = "후추";
                    break;
                case 14 :
                    sauce = "선택안함";
                    break;
                default:
                    System.out.println("선택하신 번호를 다시 확인하세요");
                    continue;
            }
            break;
        }

        String sql = "UPDATE TEMP_BASKET SET 메뉴이름 = ?, 사이즈 = ?, 빵 = ?, 치즈 = ?, " +
                "                            야채 = ?, 소스 = ?, 수량 = ?" +
                "                            WHERE 임시주문번호 = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, menuName);
            pStmt.setString(2, size);
            pStmt.setString(3, bread);
            pStmt.setString(4, cheese);
            pStmt.setString(5, vegetable);
            pStmt.setString(6, sauce);
            pStmt.setInt(7, howMany);
            pStmt.setInt(8, tempOrderNo);
            pStmt.executeUpdate();
            System.out.println("주문번호 " + tempOrderNo + "번의 변경이 완료되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void tempBasketDelete() {
        System.out.print("취소할 주문 번호를 입력하세요 ☞ ");
        int tempOrderNo = sc.nextInt();
        String sql = "DELETE FROM TEMP_BASKET WHERE 임시주문번호 = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, tempOrderNo);
            pStmt.executeUpdate();
            System.out.println("주문번호 " + tempOrderNo + "번을 취소하였습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void finalInsertIntoOrderList() {
        String sqlFinalInsert = "INSERT INTO ORDER_LIST(주문번호, 주문날짜, 회원번호, 메뉴이름, 사이즈, 빵, 치즈, 야채, 소스, 수량, 합계) " +
                "SELECT seq_ORDERLIST.NEXTVAL, SYSDATE, 회원번호, TEMP_BASKET.메뉴이름, TEMP_BASKET.사이즈, 빵, 치즈, 야채, 소스, 수량, ((MENU.메뉴가격+SIZE_TABLE.추가금액)*수량) 합계 " +
                "FROM TEMP_BASKET, SIZE_TABLE, MENU " +
                "WHERE TEMP_BASKET.메뉴이름 = MENU.메뉴이름 " +
                "AND TEMP_BASKET.사이즈 = SIZE_TABLE.사이즈";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlFinalInsert);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql2 = "DROP TABLE TEMP_BASKET";
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            stmt.execute(sql2);
            Common.close(stmt);
            Common.close(conn);
            //System.out.println("확인용 : temp_basket 테이블 비우기 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql3 = "CREATE TABLE TEMP_BASKET(\n" +
                "    회원번호 NUMBER REFERENCES CUSTOMER,\n" +
                "    임시주문번호 NUMBER,\n" +
                "    메뉴이름 VARCHAR2(20),\n" +
                "    사이즈   VARCHAR2(1),\n" +
                "    빵       VARCHAR2(25),\n" +
                "    치즈     VARCHAR2(20),\n" +
                "    야채     VARCHAR2(20),\n" +
                "    소스     VARCHAR2(20),\n" +
                "    수량     NUMBER,\n" +
                "    합계     NUMBER\n" +
                ")";
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            stmt.execute(sql3);
            Common.close(stmt);
            Common.close(conn);
            //System.out.println("확인용 : temp_basket 테이블 만들기 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql4 = "DROP SEQUENCE SEQ_TEMPBASKET";
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            stmt.execute(sql4);
            Common.close(stmt);
            Common.close(conn);
            //System.out.println("확인용 : SEQ_TEMPBASKET 지우기 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql5 = "CREATE SEQUENCE seq_TEMPBASKET\n" +
                "INCREMENT BY 1\n" +
                "START WITH 1\n" +
                "NOCYCLE";
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            stmt.execute(sql5);
            Common.close(stmt);
            Common.close(conn);
            //System.out.println("확인용 : SEQ_TEMPBASKET 만들기 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}