package com.kh.jdbc;

import com.kh.jdbc.dao.CusDao;
import com.kh.jdbc.vo.CusVO;

import java.util.List;

public class JdbcMain {
    public static void main(String[] args) {
        CusDao dao = new CusDao();
        List<CusVO> list = dao.cusSelect();
        dao.cusSelectPrint(list);
    }
}
