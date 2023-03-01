package com.kh.jdbc;

import com.kh.jdbc.dao.CusDao;
import com.kh.jdbc.dao.MenuDao;
import com.kh.jdbc.dao.OptDao;
import com.kh.jdbc.dao.SizeDao;
import com.kh.jdbc.vo.CusVO;
import com.kh.jdbc.vo.MenuVO;
import com.kh.jdbc.vo.OptVO;
import com.kh.jdbc.vo.SizeVO;

import java.util.List;

public class JdbcMain {
    public static void main(String[] args) {

       // CusDao dao = new CusDao();
       // List<CusVO> list = dao.cusSelect();
       // dao.cusSelectPrint(list);

       // MenuDao dao = new MenuDao();
       // List<MenuVO> list = dao.menuSelect();
       // dao.menuSelectPrint(list);

//        SizeDao dao = new SizeDao();
//        List<SizeVO> list = dao.sizeSelect();
//        dao.sizeSelectPrint(list);

        OptDao dao = new OptDao();
        List<OptVO> list = dao.optSelect();
        dao.optSelectPrint(list);


    }
}
