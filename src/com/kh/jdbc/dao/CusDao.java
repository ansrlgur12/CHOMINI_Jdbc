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
}
