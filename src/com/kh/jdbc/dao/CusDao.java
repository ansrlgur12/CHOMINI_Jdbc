package com.kh.jdbc.dao;

import com.kh.jdbc.util.Common;
import com.kh.jdbc.vo.CusVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CusDao {
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
        for (CusVO e : list) {
            System.out.println("회원번호 : " + e.getCusNo());
            System.out.println("고객이름 : " + e.getCusName());
            System.out.println("전화번호 : " + e.getPhone());
            System.out.println("------------------------------------------");
        }
    }

    public void cusInsert() {
        System.out.println("회원정보를 입력하세요");
        System.out.print("회원번호(4자리) : ");
        int cusNo = sc.nextInt();
        System.out.print("이름 : ");
        String cusName = sc.next();
        System.out.print("전화번호 : ");
        String phone = sc.next();

        String sql = "INSERT INTO CUSTOMER(회원번호, 고객이름, 전화번호) VALUES(?,?,?)";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, cusNo);
            pStmt.setString(2, cusName);
            pStmt.setString(3, phone);

            int ret = pStmt.executeUpdate();
            System.out.println("Return : " + ret);

        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }

    public void cusUpdate(){
        System.out.print("변경할 회원의 이름을 입력하세요 : ");
        String cusName = sc.next();
        System.out.print("전화번호 : ");
        String phone = sc.next();

        String sql = "UPDATE CUSTOMER SET 전화번호 = ? WHERE 고객이름 = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, phone);
            pStmt.setString(2, cusName);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void cusDelete() {
        System.out.print("삭제할 이름을 입력 하세요 : ");
        String cusName = sc.next();
        String sql = "DELETE FROM CUSTOMER WHERE 고객이름 = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, cusName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public int login() {

        int cusNo = 0;
        System.out.print("이름을 입력 : ");
        String cusName = sc.next();
        System.out.print("핸드폰번호 마지막 4자리 입력 : ");
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
        if (cusNo != -1) System.out.print("회원번호 " + cusNo + " " + cusName + "님 ");
        return cusNo;

    }

    public void CusInsert() {
        System.out.println("회원 가입을 진행 합니다");

        System.out.print("고객이름 : ");
        String cusName = sc.next();
        System.out.print("전화번호 : ");
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
}
