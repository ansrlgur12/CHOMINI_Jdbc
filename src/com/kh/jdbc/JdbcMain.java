package com.kh.jdbc;

import com.kh.jdbc.dao.*;
import java.util.Scanner;

public class JdbcMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FinDao dao = new FinDao();

        System.out.println("========= 어서오SAN! 맛있SAN! 먹어보SAN! =========");
        while (true) {
            System.out.print("[1] 로그인 [2] 회원가입 [3] 종료  [0] 관리자모드\n ☞ ");
            int selNum = sc.nextInt();
            switch (selNum)
            {
                case 1:
                    boolean isWrongCus = true;
                    while (isWrongCus) {
                        int cusNo = dao.login();
                        if (cusNo != -1) {
                            isWrongCus = false;
                            System.out.println("'어서오SAN! 맛있SAN! 먹어보SAN!' 방문을 환영합니다 ♥^____^♥\"");
                            System.out.println();
                            continue;
                        } else {
                            System.out.println();
                            System.out.println("※ 이름과 핸드폰번호를 확인하세요");
                            System.out.print("[1] 로그인 [2] 회원가입 \n ☞ ");
                        }

                        int tempNum = sc.nextInt();
                        if (tempNum == 1) continue;
                        else if (tempNum == 2) break;
                    }
                    if (!isWrongCus) break;

                case 2:
                    dao.CusInsert();
                    System.out.println("회원가입을 완료했습니다.");
                    System.out.println();
                    continue;
                case 3:
                    System.out.println("시스템을 종료합니다");
                    return;
                case 0:
                    dao.admin();
                    continue;

                default:
                    System.out.println("선택하신 번호를 다시 확인하세요.");
                    selNum = 0;
                    continue;
            }

            System.out.println();
            System.out.print("[1] 주문하기 [2] 장바구니 [3] 나가기(주문취소) \n-☞ ");
            while (true) {
                int selNum2 = sc.nextInt();
                switch (selNum2) {
                    case 1:
                        dao.tempBasketInsert();
                        break;
                    case 2:

                        dao.printBasket();
                        break;
                    case 3:
                        System.out.println("어서오SAN! 맛있SAN! 먹어보SAN! 방문을 감사드립니다 ♥^____^♥");
                        return;
                }
                while (true) {
                    System.out.println();
                    System.out.print("[1]추가주문 [2]장바구니보기 [3]주문변경 [4]주문취소 [5]결제하기 \n-☞ ");
                    int selNum3 = sc.nextInt();
                    switch (selNum3) {
                        case 1:
                            dao.tempBasketInsert();
                            break;
                        case 2:
                            dao.printBasket();
                            break;
                        case 3 :
                            dao.tempBasketUpdate();
                            break;
                        case 4 :
                            dao.tempBasketDelete();
                            break;
                        case 5:
                            System.out.println("결제가 완료되었습니다^__^");
                            System.out.println("어서오SAN! 맛있SAN! 먹어보SAN! 방문을 감사드립니다 ♥^____^♥");
                            //System.out.println("여기서 TEMP_BASKET에서 ORDER_LIST로 인서트! ");
                            dao.finalInsertIntoOrderList();
                            return;
                    } // 추가주문 switch 끝
                } // 추가주문 while 끝
            } // 주문 while 끝
        } // 로그인/회원정보 끝
    } // main 끝
}