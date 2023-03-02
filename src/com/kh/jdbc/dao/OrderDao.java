package com.kh.jdbc.dao;

import com.kh.jdbc.util.Common;
import com.kh.jdbc.vo.FinOrderVO;
import com.kh.jdbc.vo.OrdercheckVO;
import com.kh.jdbc.vo.SizeVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);


    public List<FinOrderVO> orderSelect(){
        List<FinOrderVO> list = new ArrayList<>();
        SizeVO sizeVO = new SizeVO();


        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM ORDER_BASKET";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Date date = rs.getDate("주문날짜");
                int orderNo = rs.getInt("주문번호");
                int customNo = rs.getInt("회원번호");
                String menuName = rs.getString("메뉴이름");
                String size = rs.getString("사이즈");
                String bread = rs.getString("빵");
                String cheese = rs.getString("치즈");
                String vegetable = rs.getString("야채");
                String sauce = rs.getString("소스");
                int howMany = rs.getInt("수량");
                FinOrderVO vo = new FinOrderVO(date, orderNo, customNo, menuName, size, bread, cheese, vegetable, sauce, howMany);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    public void orderSelectPrint(List<OrdercheckVO> list) {
        SizeVO sizeVO = new SizeVO();

        for (OrdercheckVO o : list) {
            System.out.println("--------- 주문 내역을 확인하세요 ---------");
            System.out.println("메뉴 이름 : " + o.getmName());
            System.out.println("사이즈  : " + o.getSize());
            System.out.println("빵 : " + o.getBread());
            System.out.println("치즈 : " + o.getCheese());
            System.out.println("야채 : " + o.getVege());
            System.out.println("소스  : " + o.getSource());
            System.out.println("금액  : " + o.getPrice());
            System.out.println("수량  : " + o.getHowMany());
            System.out.println("합계 : " + o.getsPrice());
            System.out.println(o.getPrice());
            System.out.println(o.getHowMany());
            System.out.println(o.getsPrice());


            System.out.println("합계 : " + lastPrice(o.getPrice(), o.getHowMany(), o.getsPrice() ));
            System.out.println("--------------------------------------");
        }
        System.out.println("결제하실 금액 : " + "원");
    }

    public int lastPrice(int price, int howMany, int sPrice) {

        return (price + sPrice) * howMany;
    }

    // 결제 전 주문확인
    public List<OrdercheckVO> orderCheck() {
        List<OrdercheckVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT O.메뉴이름, O.사이즈, 빵, 치즈, 야채, 소스, M.메뉴가격, 수량, (S.추가금액 + M.메뉴가격) "
                    + "FROM ORDER_BASKET O, SIZE_TABLE S, MENU M "
                    + "WHERE O.메뉴이름 = M.메뉴이름 "
                    + "AND O.사이즈 = S.사이즈";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String mName = rs.getString("메뉴이름");
                String size = rs.getString("사이즈");
                String bread = rs.getString("빵");
                String cheese = rs.getString("치즈");
                String vege = rs.getString("야채");
                String source = rs.getString("소스");
                int price = rs.getInt("메뉴가격");
                int howMany = rs.getInt("수량");
                int sPrice = rs.getInt("(추가금액 + 메뉴가격)");
                OrdercheckVO vo = new OrdercheckVO(mName, size, bread, cheese, vege, source, price, howMany,sPrice);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void orderUpdate() {
        System.out.print("변경을 원하는 메뉴명을 입력하세요 : ");
        String menuName = sc.next();
        System.out.print("다시 주문 할 메뉴 : ");
        String reMenuName = sc.next();
        System.out.print("수 량 : ");
        int howMany = sc.nextInt();

        String sql = "UPDATE ORDER_TABLE SET 메뉴이름 = ?, 수량 = ? WHERE 메뉴이름 = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, reMenuName);
            pStmt.setInt(2, howMany);
            pStmt.setString(3, menuName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void orderDelete() {
        System.out.print("삭제할 메뉴를 입력하세요 : ");
        String menuName = sc.next();
        String sql = "DELETE FROM ORDER_TABLE WHERE 메뉴이름 = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, menuName);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    // 총 주문량을 확인하기위해 완됴된 주문을 저장하는 테이블과 연동하는 코딩
    public void setOrder() {
        String sql_fin = "INSERT INTO FINAL_ORDER "
                + "SELECT ORDER_TABLE.회원번호, ORDER_TABLE.메뉴이름, ORDER_TABLE.사이즈, 빵, 치즈, 야채, 소스, 수량, ((메뉴가격+추가금액)*수량) AS 합계"
                + "FROM ORDER_TABLE, MENU, SIZE_TABLE, OPTION_TABLE "
                + "WHERE ORDER_TABLE.메뉴이름 = MENU.메뉴이름"
                + "AND ORDER_TABLE.사이즈 = SIZE_TABLE.사이즈"
                + "AND ORDER_TABLE.주문번호 = OPTION_TABLE.주문번호";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            int rst = stmt.executeUpdate(sql_fin);
            ;
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void orderInsert() {

        System.out.print("회원번호를 입력하세요 : ");
        int customNo = sc.nextInt();
        System.out.println();

        String menuName;
        while (true) {
            System.out.println("메뉴 번호를 입력하세요");
            System.out.print("[1]클럽샌드위치 [2]BLT샌드위치 [3]에그샌드위치 [4]참치샌드위치 [5]연어샌드위치 [6]콥샐러드 [7]두부샐러드 [8]에그샐러드 [9]참치샐러드 [10]연어샐러드\n-> ");
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

                case 6:
                    menuName = "콥샐러드";
                    break;

                case 7:
                    menuName = "두부샐러드";
                    break;

                case 8:
                    menuName = "에그샐러드";
                    break;

                case 9:
                    menuName = "참치샐러드";
                    break;

                case 10:
                    menuName = "연어샐러드";
                    break;

                default:
                    System.out.println("주문을 다시 확인하세요 ^___________^");
                    selMenu = 0;
                    continue;
            }
            break;
        }

        String size = null;
        while (true) {
            System.out.println("사이즈를 선택하세요 ");
            System.out.print("[1] S  [2] M  [3] L  \n-> ");
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
                    System.out.println("사이즈를 다시 선택하세요.");
                    selSize = 0;
                    continue;
            }
            break;
        }
        System.out.print("수량을 입력 하세요 -> ");
        int howMany = sc.nextInt();
        System.out.println("옵션을 선택하세요");

        String bread;
        while (true) {
            System.out.print("[1]화이트 [2]파마산오레가노 [3]위트 [4]허니오트 [5]하티 [6]플랫브래드 [7]빵X (샐러드)" +
                    "\n 빵 종류 선택 : ");
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
                    System.out.println("보기에 있는걸 시켜야죠");
                    selBreadType = 0;
                    continue;
            }
            if (selBreadType == 7) System.out.println("X");
            break;
        }


        String cheese;
        while (true) {
            System.out.print("[1]아메리칸치즈 [2]슈레드치즈 [3]모차렐라치즈 [4]선택안함" +
                    "\n원하는 치즈 종류 : ");
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
                default:
                    System.out.println("보기에있는걸 시켜야죵!");
                    selCheeseType = 0;
                    continue;
            }
            if (selCheeseType == 4) System.out.println("선택안함");
            break;
        }
        System.out.println("뺄 야채를 선택하세요");

        String vegetable;
        while (true) {
            System.out.print("[1]양상추 [2]토마토 [3]오이 [4]피망 [5]양파 [6]피클 [7]할라피뇨 [8]선택안함 \n-> ");
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
                default:
                    System.out.println("다시 입력해주세요!");
                    selVeg = 0;
                    continue;
            }
            if (selVeg == 8) System.out.println("선택안함");
            break;
        }
        System.out.println("소스를 선택하세요");

        String sauce;
        while (true) {
            System.out.println("[1]머스타드 [2]레드와인식초 [3]스위트어니언 [4]허니머스타드 [5]스위트칠리 [6]스모크바비큐 [7]랜치");
            System.out.print("[8]마요네즈 [9]핫칠리 [10]홀스래디쉬 [11]올리브오일 [12]소금 [13]후추 [14]선택안함 : ");
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
                default:
                    System.out.println("다시 선택해주세요");
                    selSau = 0;
                    continue;
            }
            break;


        }

        String sql ="INSERT INTO ORDER_BASKET(주문번호, 주문날짜, 회원번호, 메뉴이름, 사이즈, 빵, 치즈, 야채, 소스, 수량) VALUES(주문번호.NEXTVAL, SYSDATE, ?, ?, ?, ?, ?, ?, ?, ?)";
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
//            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        System.out.println();
        System.out.println("장바구니에 등록이 완료되었습니다!!!!");
        System.out.println();
    }
}