package com.kh.jdbc;

import com.kh.jdbc.dao.*;
import com.kh.jdbc.vo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JdbcMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TempBasketDao tempBasketDao = new TempBasketDao();
        OrderListDao orderDao = new OrderListDao();
        CusDao cusDao = new CusDao();
        MenuDao menuDao = new MenuDao();
        while (true) { // 로그인/회원정보
            System.out.println("========= 인스타쌜드위취 =========");
            System.out.print("[1] 로그인 [2] 회원가입 [3] 종료          [0] 관리자모드\n ☞ ");
            int selNum = sc.nextInt();
            switch (selNum) {
                case 1:
                    boolean isWrongCus = true;
                    while (isWrongCus) {
                        int cusNo = cusDao.login();
                        if (cusNo != -1) {
                            isWrongCus = false;
                            System.out.println("님  '어서오SAN! 맛있SAN! 먹어보SAN!' 방문을 환영합니다 ♥^____^♥\"");
                            continue;
                        } else {
                            System.out.println("이름과 핸드폰번호를 확인하세요");
                            System.out.print("[1] 로그인 [2] 회원가입 \n ☞ ");
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
                    System.out.print("비밀번호를 입력하세요 \n☞ ");
                    String pwd = sc.next();
                    if (pwd.equalsIgnoreCase("0000")) {
                        System.out.println("====================");
                        System.out.println("관리자 모드로 접속합니다");
                        System.out.println("====================");
                        while (true) {
                            System.out.print("[1] 메뉴관리  [2] 회원관리  [3] 매출조회  [4] 메뉴별판매현황  [5] VIP   [6] 종료 \n☞ ");
                            int num = sc.nextInt();
                            switch (num) {
                                case 1 :
                                    menuDao.menuInfo();
                                    break;
                                case 2 :
                                    cusDao.CusInfo();
                                    break;
                                case 3 :
                                    //System.out.println("매출조회입니다.");
                                    orderDao.dailySales();
                                    break;
                                case 4 :
                                    orderDao.menuCount();
                                    break;

                                case 5 :
                                    orderDao.vip();
                                    break;
                                case 7 :
                                    orderDao.checkList();

                                case 6 :
                                    System.out.println("관리자 모드를 종료합니다.");
                                    return;
                                default:
                                    System.out.println("선택하신 번호를 다시 확인하세요.");
                                    num = 0;
                                    continue;
                            }
                        }

                    } else System.out.println("비밀번호 오류입니다");
                    continue;
            }


            List<MenuVO> list = menuDao.menuSelect();
            menuDao.menuSelectPrint(list);
            System.out.print("[1] 주문하기 [2] 장바구니 [3] 나가기(주문취소) \n-☞ ");
            while (true) {
                int selNum2 = sc.nextInt();
                switch (selNum2) {
                    case 1:
                        tempBasketDao.tempBasketInsert();
                        break;
                    case 2:

                        tempBasketDao.printBasket();
                        break;
                    case 3:
                        System.out.println("여기는 결제-> 주문취소로 변경");
                        System.out.println("안녕히가세용~~");
                        return;
                }
                while (true) {
                    System.out.println();
                    System.out.print("[1]추가주문 [2]장바구니보기 [3]주문변경 [4]주문취소 [5]결제하기 \n-☞ ");
                    int selNum3 = sc.nextInt();
                    switch (selNum3) {
                        case 1:
                            tempBasketDao.tempBasketInsert();
                            break;
                        case 2:
                            tempBasketDao.printBasket();
                            break;
                        case 3 :
                            tempBasketDao.tempBasketUpdate();
                            break;
                        case 4 :
                            tempBasketDao.tempBasketDelete();
                            break;
                        case 5:
                            System.out.println("결제가 완료되었습니다^__^");
                            //System.out.println("여기서 TEMP_BASKET에서 ORDER_LIST로 인서트! ");
                            tempBasketDao.finalInsertIntoOrderList();
                            return;
                    } // 추가주문 switch 끝
                } // 추가주문 while 끝
            } // 주문 while 끝
        } // 로그인/회원정보 끝
    } // main 끝
}