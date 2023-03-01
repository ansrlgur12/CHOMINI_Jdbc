package com.kh.jdbc;

import com.kh.jdbc.dao.CusDao;
import com.kh.jdbc.dao.MenuDao;
import com.kh.jdbc.dao.SizeDao;
import com.kh.jdbc.vo.CusVO;
import com.kh.jdbc.vo.MenuVO;
import com.kh.jdbc.vo.SizeVO;

import java.util.List;

public class JdbcMain {
    public static void main(String[] args) {

        //CusDao dao = new CusDao();
        //List<CusVO> list = dao.cusSelect();
        //dao.cusSelectPrint(list);
        MenuDao dao1 = new MenuDao();
        List<MenuVO> list1 = dao1.menuSelect();
        dao1.menuSelectPrint(list1);

        SizeDao dao2 = new SizeDao();
        List<SizeVO> list2 = dao2.sizeSelect();
        dao2.sizeSelectPrint(list2);
}
}