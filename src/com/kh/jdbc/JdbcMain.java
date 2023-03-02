package com.kh.jdbc;

import com.kh.jdbc.dao.*;
import com.kh.jdbc.vo.CusVO;
import com.kh.jdbc.vo.MenuVO;
import com.kh.jdbc.vo.OptVO;
import com.kh.jdbc.vo.SizeVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JdbcMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderDao orderDao = new OrderDao();
        CusDao cusDao = new CusDao();
        MenuDao menuDao = new MenuDao();
        while (true) { // 로그인/회원정보
            System.out.println("========= 인스타쌜드위취 =========");
            System.out.print("[1]로그인 [2]회원가입 [3]종료 [0]관리자모드\n -> ");
            int selNum = sc.nextInt();
            switch (selNum) {
                case 1:
                    boolean isWrongCus = true;
                    while (isWrongCus) {
                        int cusNo = cusDao.login();
                        if (cusNo != -1) {
                            isWrongCus = false;
                            System.out.println("반갑습니당~~ 하이");
                            continue;
                        } else {
                            System.out.println("--로그인 실패--");
                            System.out.print("[1] 로그인 [2] 회원가입 \n -> ");
                        }

                        int tempNum = sc.nextInt();
                        if (tempNum == 1) continue;
                        else if (tempNum == 2) break;
                    }
                    if (!isWrongCus) {
                        break;
                    }

                case 2:
                    cusDao.CusInsert();
                    System.out.println("회원가입을 완료했습니다.");
                    continue;
                case 3:
                    System.out.println("시스템을 종료합니다");
                    return;
                case 0:
                    System.out.print("비밀번호를 입력하세요 : ");
                    String pwd = sc.next();
                    if (pwd.equalsIgnoreCase("0000")) {
                        System.out.println("관리자 모드로 접속합니다");
                        return;
                    } else System.out.println("비밀번호 오류입니다");
                    continue;
            }


            while (true) {
                System.out.print("[1] 메뉴보기 [2] 주문하기 [3] 결제 \n-> ");
                int selNum2 = sc.nextInt();
                switch (selNum2) {
                    case 1:
                        List<MenuVO> list = menuDao.menuSelect();
                        menuDao.menuSelectPrint(list);
                        break;
                    case 2:

                        orderDao.orderInsert();

                        break;
                    case 3:
                        System.out.println("여기는 결제");
                        break;

                }
            }


        }
    }
}

