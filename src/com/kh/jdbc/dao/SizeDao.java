package com.kh.jdbc.dao;

import com.kh.jdbc.util.Common;
import com.kh.jdbc.vo.MenuVO;
import com.kh.jdbc.vo.SizeVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SizeDao {

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public List<SizeVO> sizeSelect() {
        List<SizeVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM SIZE_TABLE";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String size = rs.getString("사이즈");
                int sPrice = rs.getInt("추가금액");
                SizeVO vo = new SizeVO(size, sPrice);
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

    public void sizeSelectPrint(List<SizeVO> list) {
        for (SizeVO e : list) {
            System.out.println("사이즈 : " + e.getSize());
            System.out.println("추가금액 : " + e.getsPrice());
            System.out.println("------------------------------------------");
        }
    }


    public void sizeUpdate() {
        System.out.print("사이즈를 선택하세요 : ");
        String size = sc.next();
        System.out.print("추가금액 : ");
        int sPrice = sc.nextInt();

        String sql = "UPDATE CUSTOMER SET 추가금액 = ? WHERE 사이즈 = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, sPrice);
            pStmt.setString(2, size);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }
}

