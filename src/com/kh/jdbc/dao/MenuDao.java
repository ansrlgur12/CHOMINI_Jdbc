package com.kh.jdbc.dao;

import com.kh.jdbc.util.Common;
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
        for (MenuVO e : list) {
            System.out.println("메뉴이름 : " + e.getMenuName());
            System.out.println("메뉴가격 : " + e.getPrice());
            System.out.println("------------------------------------------");
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

        String sql = "UPDATE CUSTOMER SET 메뉴가격 = ? WHERE 메뉴이름 = ?";

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

}
