package com.kh.jdbc.dao;

import com.kh.jdbc.util.Common;
import com.kh.jdbc.vo.CusVO;
import com.kh.jdbc.vo.MenuVO;
import com.kh.jdbc.vo.OrderListVO;
import com.kh.jdbc.vo.SizeVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class FinDao {
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null; // SQL문을 수행하기 위한 객체
    PreparedStatement pStmt = null;
    ResultSet rs = null; // statement 동작에 대한 결과로 전달되는 DB의 내용
    Scanner sc = new Scanner(System.in);

    public List<CusVO> cusSelect() {
        List<CusVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM CUSTOMER";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int cusNo = rs.getInt("회원번호");
                String cusName = rs.getString("고객이름");
                String phone = rs.getString("전화번호");
                CusVO vo = new CusVO(cusNo, cusName, phone);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public void cusSelectPrint(List<CusVO> list) {
        System.out.println(" 회원번호   |  고객이름  |  전화번호");
        System.out.println("-----------------------------------------");
        for (CusVO e : list) {
            System.out.println("   " + e.getCusNo() + "   |   " + e.getCusName() + "   |   " + e.getPhone());
            System.out.println("------------------------------------------");
        }
    }
    public void cusUpdate(){
        System.out.print("변경할 회원의 이름을 입력하세요 ☞ ");
        String cusName = sc.next();
        System.out.print("전화번호 ☞ ");
        String phone = sc.next();

        String sql = "UPDATE CUSTOMER SET 전화번호 = ? WHERE 고객이름 = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, phone);
            pStmt.setString(2, cusName);
            pStmt.executeUpdate();
            System.out.println("수정 완료!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }
    public void cusDelete() {
        System.out.print("삭제할 회원의 회원번호를 입력하세요 ☞ ");
        int cusNo = sc.nextInt();
        String sql = "DELETE FROM ORDER_LIST WHERE 회원번호 = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, cusNo);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql2 = "DELETE FROM CUSTOMER WHERE 회원번호 = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql2);
            pStmt.setInt(1, cusNo);
            pStmt.executeUpdate();
            System.out.println("삭제 완료!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(pStmt);
        Common.close(conn);
    }

    public int login() {

        int cusNo = 0;
        System.out.print("이름을 입력 ☞ ");
        String cusName = sc.next();
        System.out.print("핸드폰번호 마지막 4자리 입력 ☞ ");
        String phone = sc.next();

        try {
            String sql = "SELECT * FROM CUSTOMER WHERE 고객이름 = ? AND SUBSTR(전화번호, 10,4) = ?";
            conn = Common.getConnection(); // 연결 생성
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, cusName);
            pStmt.setString(2, phone);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                cusNo = rs.getInt("회원번호");
            } else cusNo = -1;
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        if (cusNo != -1) {
            System.out.println();
            System.out.print("회원번호 " + cusNo + "번 " + cusName + "님 ");
        }
        return cusNo;

    }

    public void CusInsert() {
        System.out.println("회원 가입을 진행 합니다");

        System.out.print("성함을 입력해주세요 ☞ ");
        String cusName = sc.next();
        System.out.print("전화번호(010-****-****)를 입력해주세요 ☞ ");
        String phone = sc.next();

        String sql = "INSERT INTO CUSTOMER (회원번호, 고객이름, 전화번호) VALUES (회원번호.NEXTVAL, ?, ?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, cusName);
            pStmt.setString(2, phone);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void CusInfo() {
        System.out.println("회원관리 메뉴입니다 ");
        while (true) {
            System.out.print("[1]  회원조회  [2]  회원수정  [3]  회원삭제  [4]  이전메뉴 \n ☞ ");
            int selNum = sc.nextInt();
            switch (selNum) {
                case 1 :
                    System.out.println("회원조회입니다.");
                    List<CusVO> list = cusSelect();
                    cusSelectPrint(list);
                    break;
                case 2 :
                    System.out.println("회원 수정입니다.");
                    cusUpdate();
                    break;
                case 3 :
                    System.out.println("회원 삭제 입니다.");
                    cusDelete();
                    break;
                case 4 :
                    System.out.println("회원관리메뉴를 종료합니다.");
                    return;
                default:
                    System.out.println("선택하신 번호를 다시 확인하세요.");
                    selNum = 0;
                    continue;
            }
        }
    }
    // CusDao 끝
    public List<MenuVO> menuSelect() {
        List<MenuVO> list = new ArrayList<>();
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM MENU";
            rs = stmt.executeQuery(sql);

            // System.out.println("===============메뉴 조회==================");
            while(rs.next()) {
                String menuName = rs.getString("메뉴이름");
                int price = rs.getInt("메뉴가격");
                MenuVO vo = new MenuVO(menuName, price);
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

    public void menuSelectPrint(List<MenuVO> list) {
        System.out.println("   [메뉴]          [가격] ");
        System.out.println("------------------------------------");

        for (MenuVO e : list) {
            System.out.printf("%-10s", e.getMenuName());
            System.out.println("         " + e.getPrice() + "원");
            // System.out.println("-------------------------");
        }

    }

    public void menuInsert() {
        System.out.println("메뉴 정보를 입력하세요");
        System.out.print("메뉴이름 ☞ ");
        String menuName = sc.next();
        System.out.print("메뉴가격 ☞ ");
        int price = sc.nextInt();

        String sql = "INSERT INTO MENU(메뉴이름, 메뉴가격) VALUES(?,?)";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, menuName);
            pStmt.setInt(2, price);

            int ret = pStmt.executeUpdate();
            //System.out.println("Return : " + ret);
            System.out.println("메뉴 추가 성공!!");

        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }

    public  void menuUpdate(){
        System.out.print("메뉴이름을 입력하세요 ☞ ");
        String menuName = sc.next();
        System.out.print("메뉴 가격 ☞ ");
        int price = sc.nextInt();

        String sql = "UPDATE MENU SET 메뉴가격 = ? WHERE 메뉴이름 = ?";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, price);
            pStmt.setString(2, menuName);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void menuDelete() {
        System.out.print("삭제할 메뉴를 입력하세요 ☞ ");
        String menuName = sc.next();
        String sql = "DELETE FROM MENU WHERE 메뉴이름 = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, menuName);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("메뉴를 삭제했습니다");
        System.out.println();
        Common.close(pStmt);
        Common.close(conn);
    }

    public void menuInfo(){
        System.out.println("메뉴관리 입니다 ");
        while (true) {
            System.out.print("[1]  메뉴조회  [2] 메뉴추가  [3] 메뉴수정  [4]  메뉴삭제  [5]  이전메뉴 \n ☞ ");
            int selNum = sc.nextInt();
            switch (selNum) {
                case 1 :
                    System.out.println("===============메뉴 조회==================");
                    List<MenuVO> list = menuSelect();
                    menuSelectPrint(list);
                    break;
                case 2 :
                    System.out.println("메뉴추가 입니다.");
                    menuInsert();
                    break;

                case 3 :
                    System.out.println("메뉴 수정입니다.");
                    menuUpdate();
                    break;

                case 4 :
                    System.out.println("메뉴 삭제 입니다.");
                    menuDelete();
                    break;

                case 5 :
                    System.out.println("회원관리메뉴를 종료합니다.");
                    return;
                default:
                    System.out.println("선택하신 번호를 다시 확인하세요.");
                    selNum = 0;
                    continue;
            }
        }
    }
    // MenuDal 끝
    public void dailySales() {
        System.out.print("조회할 날짜를 입력하세요(yy/mm/dd) : ");
        String day = sc.next();

        String sql = "SELECT * FROM ORDER_LIST WHERE TO_DATE(주문날짜, 'YY/MM/DD') = TO_DATE(?, 'YY/MM/DD') ";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, day);
            pStmt.executeUpdate();
            rs = pStmt.getResultSet();

            int total = 0;
            System.out.println();
            System.out.println("================== [" + day + " 주문내역] ==================");
            System.out.println("  ♡날  짜♡    ♡메 뉴♡   ♡사이즈♡  ♡수량♡   ♡합계♡");
            while (rs.next()) {
                System.out.print(rs.getDate("주문날짜") );
                System.out.printf("%10s ", rs.getString("메뉴이름"));
                System.out.printf("%4s", rs.getString("사이즈"));
                System.out.printf("%9d", rs.getInt("수량"));
                System.out.printf("%11d", rs.getInt("합계"));
                total += rs.getInt("합계");
                System.out.println();
            }
            System.out.println("해당 날짜의 총 매출 금액은 " + total + "원 입니다.");
            System.out.println("=====================================================");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);

    }


    public void menuCount() {

        String sql = "SELECT 메뉴이름, COUNT(메뉴이름) AS 판매수량\n" +
                "    FROM ORDER_LIST\n" +
                "    GROUP BY 메뉴이름" ;
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.executeUpdate();
            rs = pStmt.getResultSet();
            System.out.println();
            System.out.println("            [메뉴별 판매현황]           ");
            System.out.println("    ☆메뉴 이름☆        ☆판매 수량☆");
            System.out.println("-------------------------------------");
            while (rs.next()) {
                System.out.printf("%10s",rs.getString("메뉴이름") );
                System.out.printf("%15s ", rs.getInt("판매수량"));
                System.out.println();
                System.out.println("-------------------------------------");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
    }

    public void vip() {
        String sql = "SELECT 고객이름, COUNT(고객이름) AS 방문횟수\n" +
                "    FROM CUSTOMER C JOIN ORDER_LIST O\n" +
                "        ON C.회원번호 = O.회원번호\n" +
                "        GROUP BY 고객이름\n" +
                "            HAVING COUNT(고객이름) IN  (SELECT MAX(COUNT(고객이름))\n" +
                "                                    FROM CUSTOMER C JOIN ORDER_LIST O\n" +
                "                                    ON C.회원번호 = O.회원번호\n" +
                "                                    GROUP BY 고객이름) " +
                "ORDER BY COUNT(고객이름) DESC ";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.executeUpdate();
            rs = pStmt.getResultSet();
            int num = 1;
            System.out.println();
            System.out.println("=========== [ohoh 우리의 ☆VIP★ ohoh] ==========");
            System.out.println("    ☆찬란한 우리의 고객님☆     ☆방문횟수☆");
            while (rs.next()) {

                System.out.printf("%-10s" , num + "등");
                System.out.printf("%-3s", rs.getString("고객이름") );
                System.out.printf("%15s ", rs.getInt("방문횟수")+"회");
                System.out.println();
                System.out.println("=====================================================");
                System.out.println();
                num++;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
    }


    public void checkList() { // 월별 재료 소진 확인
        int month;
        String sql;
        System.out.println();
        System.out.print("※ 조회할 달을 입력하세요 \n ☞ ");
        month = sc.nextInt();
        System.out.println();
        while (true) {
            System.out.println("※ 조회할 재료명 번호를 입력하세요");
            System.out.print("[1] 빵  [2] 치즈  [3] 야채  [4] 소스  [5] 종료\n ☞ ");
            int selNum = sc.nextInt();
            switch (selNum) {

                case 1:

                    sql = "SELECT 빵, COUNT(빵)\n" +
                            "    FROM ORDER_LIST\n" +
                            "    WHERE EXTRACT(MONTH FROM 주문날짜) = ?\n" +
                            "    GROUP BY 빵\n" +
                            "    ORDER BY 2 DESC";
                    try {
                        conn = Common.getConnection();
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setInt(1, month);
                        pStmt.executeUpdate();
                        rs = pStmt.getResultSet();
                        System.out.println();
                        System.out.println("======= [" + month + "월 빵 소진 내역] =======");
                        System.out.println("  빵      수량");
                        System.out.println("----------------");
                        while (rs.next()) {
                            System.out.printf("%-10s", rs.getString("빵"));
                            System.out.print(rs.getInt("COUNT(빵)"));
                            System.out.println();
                        }
                        System.out.println("----------------");
                        System.out.println();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Common.close(rs);
                    Common.close(pStmt);
                    Common.close(conn);
                    break;

                case 2:
                    // System.out.print("조회할 달을 입력하세요 \n ☞ ");
                    // month = sc.nextInt();
                    sql = "SELECT 치즈, COUNT(치즈)\n" +
                            "    FROM ORDER_LIST\n" +
                            "    WHERE EXTRACT(MONTH FROM 주문날짜) = ?\n" +
                            "    GROUP BY 치즈\n" +
                            "    ORDER BY 2 DESC";
                    try {
                        conn = Common.getConnection();
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setInt(1, month);
                        pStmt.executeUpdate();
                        rs = pStmt.getResultSet();
                        System.out.println();
                        System.out.println("======= [" + month + "월 치즈 소진 내역] =======");
                        System.out.println("  치즈      수량");
                        System.out.println("----------------");
                        while (rs.next()) {
                            System.out.printf("%-10s", rs.getString("치즈"));
                            System.out.print(rs.getInt("COUNT(치즈)"));
                            System.out.println();
                        }
                        System.out.println("----------------");
                        System.out.println();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Common.close(rs);
                    Common.close(pStmt);
                    Common.close(conn);
                    break;

                case 3 :
                    //  System.out.print("조회할 달을 입력하세요 \n ☞ ");
                    //  month = sc.nextInt();
                    sql = "SELECT 야채, COUNT(야채)\n" +
                            "    FROM ORDER_LIST\n" +
                            "    WHERE EXTRACT(MONTH FROM 주문날짜) = ?\n" +
                            "    GROUP BY 야채\n" +
                            "    ORDER BY 2 DESC";
                    try {
                        conn = Common.getConnection();
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setInt(1, month);
                        pStmt.executeUpdate();
                        rs = pStmt.getResultSet();
                        System.out.println();
                        System.out.println("======= [" + month + "월 야채 소진 내역] =======");
                        System.out.println("  야채      수량");
                        System.out.println("----------------");
                        while (rs.next()) {
                            System.out.printf("%-10s", rs.getString("야채"));
                            System.out.print(rs.getInt("COUNT(야채)"));
                            System.out.println();
                        }
                        System.out.println("----------------");
                        System.out.println();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Common.close(rs);
                    Common.close(pStmt);
                    Common.close(conn);
                    break;
                case 4 :
                    //  System.out.print("조회할 달을 입력하세요 \n ☞ ");
                    //  month = sc.nextInt();
                    sql = "SELECT 소스, COUNT(소스)\n" +
                            "    FROM ORDER_LIST\n" +
                            "    WHERE EXTRACT(MONTH FROM 주문날짜) = ?\n" +
                            "    GROUP BY 소스\n" +
                            "    ORDER BY 2 DESC";
                    try {
                        conn = Common.getConnection();
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setInt(1, month);
                        pStmt.executeUpdate();
                        rs = pStmt.getResultSet();
                        System.out.println();
                        System.out.println("======= [" + month + "월 소스 소진 내역] =======");
                        System.out.println("  소스      수량");
                        System.out.println("----------------");
                        while (rs.next()) {
                            System.out.printf("%-10s", rs.getString("소스"));
                            System.out.print(rs.getInt("COUNT(소스)"));
                            System.out.println();
                        }
                        System.out.println("----------------");
                        System.out.println();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Common.close(rs);
                    Common.close(pStmt);
                    Common.close(conn);
                    break;

                case 5 :
                    System.out.println("재료소진 확인을 종료합니다");
                    System.out.println();
                    return;

                default:
                    System.out.println("선택하신 번호를 다시 확인하세요.");
                    System.out.println();
                    selNum = 0;
                    continue;

            }
        }
    }
    // OrderListDao 끝
    public void tempBasketInsert() {
        System.out.print("회원번호를 입력하세요 \n ☞ ");
        int customNo = sc.nextInt();

        String menuName;
        System.out.println();
        List<MenuVO> list = menuSelect();
        menuSelectPrint(list);
        System.out.println();
        System.out.print("※ 메뉴 이름 입력하세요 \n☞ ");
        String selMenu = sc.next();

        String size = null;
        while (true) {
            System.out.println();
            System.out.println("※ 사이즈 번호를 입력하세요 ");
            System.out.println("※ M사이즈 + 500원 L사이즈 + 1000원");
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
            pStmt.setString(2, selMenu);
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
        System.out.println();
        List<MenuVO> list = menuSelect();
        menuSelectPrint(list);
        System.out.println();
        System.out.print("※ 메뉴 이름 입력하세요 \n☞ ");
        String selMenu = sc.next();

        String size = null;
        while (true) {
            System.out.println();
            System.out.println("※ 사이즈 번호를 입력하세요 ");
            System.out.println("※ M사이즈 + 500원 L사이즈 + 1000원");
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
            pStmt.setString(1, selMenu);
            pStmt.setString(2, size);
            pStmt.setString(3, bread);
            pStmt.setString(4, cheese);
            pStmt.setString(5, vegetable);
            pStmt.setString(6, sauce);
            pStmt.setInt(7, howMany);
            pStmt.setInt(8, tempOrderNo);
            pStmt.executeUpdate();
            System.out.println(" 주문번호 " + tempOrderNo + "번의 변경이 완료되었습니다.");

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
                "    메뉴이름 VARCHAR2(40),\n" +
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
