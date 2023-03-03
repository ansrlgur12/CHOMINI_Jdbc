package com.kh.jdbc.dao;

import com.kh.jdbc.util.Common;
import com.kh.jdbc.vo.CusVO;
import com.kh.jdbc.vo.MenuVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public List<MenuVO> menuSelect() {
        List<MenuVO> list = new ArrayList<>();
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM MENU";
            rs = stmt.executeQuery(sql);

            System.out.println("===============메뉴 조회==================");
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
        System.out.println("     메뉴     |      가격 ");
        System.out.println("------------------------");

        for (MenuVO e : list) {
            System.out.print(e.getMenuName());
            System.out.println("     |    " + e.getPrice() + "원");
            System.out.println("-------------------------");
        }

    }

    public void menuInsert() {
        System.out.println("메뉴 정보를 입력하세요");
        System.out.print("메뉴이름 : ");
        String menuName = sc.next();
        System.out.print("메뉴가격 : ");
        int price = sc.nextInt();

        String sql = "INSERT INTO MENU(메뉴이름, 메뉴가격) VALUES(?,?)";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, menuName);
            pStmt.setInt(2, price);

            int ret = pStmt.executeUpdate();
            System.out.println("Return : " + ret);
            System.out.println("메뉴 추가 성공!!");

        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }

    public  void menuUpdate(){
        System.out.print("메뉴이름을 입력하세요 : ");
        String menuName = sc.next();
        System.out.print("메뉴 가격 : ");
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
        System.out.print("삭제할 메뉴를 입력하세요 : ");
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
        Common.close(pStmt);
        Common.close(conn);
    }

    public void menuInfo(){
        System.out.println("메뉴관리 입니다 ");
        while (true) {
            System.out.print("[1]  메뉴조회  [2] 메뉴추가  [3] 메뉴수정  [4]  메뉴삭제  [5]  이전메뉴 \n -> ");
            int selNum = sc.nextInt();
            switch (selNum) {
                case 1 :
                    System.out.println("메뉴조회입니다.");
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
                    System.out.println("잘못입력하셨습니다.");
                    selNum = 0;
                    continue;
            }
        }
    }

}
