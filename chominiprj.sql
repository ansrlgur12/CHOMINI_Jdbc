SHOW USER;

/* 테이블 드랍 */
DROP TABLE ORDER_LIST;
DROP TABLE TEMP_BASKET;
DROP TABLE SIZE_TABLE;
DROP TABLE MENU;
DROP TABLE CUSTOMER;

/* 시퀀스 드랍 */
DROP SEQUENCE seq_CUSTOMER;
DROP SEQUENCE seq_TEMPBASKET;
DROP SEQUENCE seq_ORDERLIST;

/* 테이블 셀렉트 */
SELECT * FROM CUSTOMER;
SELECT * FROM MENU;
SELECT * FROM SIZE_TABLE;
SELECT * FROM TEMP_BASKET;
SELECT * FROM ORDER_LIST;

/* 시퀀스, 테이블 생성 */
CREATE SEQUENCE seq_CUSTOMER -- 고객번호 시퀀스
INCREMENT BY 1
START WITH 1000
NOCYCLE
NOCACHE;

CREATE TABLE CUSTOMER( 
    회원번호 NUMBER PRIMARY KEY,
    고객이름 VARCHAR2(20) NOT NULL,
    전화번호 VARCHAR2(20) NOT NULL
);
INSERT INTO CUSTOMER VALUES(seq_CUSTOMER.NEXTVAL, '라이언', '010-0000-0000');
INSERT INTO CUSTOMER VALUES(seq_CUSTOMER.NEXTVAL, '죠르디', '010-0000-0001');
INSERT INTO CUSTOMER VALUES(seq_CUSTOMER.NEXTVAL, '춘식이', '010-0000-0002');
INSERT INTO CUSTOMER VALUES(seq_CUSTOMER.NEXTVAL, '어피치', '010-0000-0003');
INSERT INTO CUSTOMER VALUES(seq_CUSTOMER.NEXTVAL, '제이지', '010-0000-0004');
INSERT INTO CUSTOMER VALUES(seq_CUSTOMER.NEXTVAL, '프로도', '010-0000-0005');


CREATE TABLE MENU( 
    메뉴이름 VARCHAR2(40) PRIMARY KEY,
    메뉴가격 NUMBER NOT NULL
);
INSERT INTO MENU VALUES ( '클럽샌드위치', 8000);
INSERT INTO MENU VALUES ( 'BLT샌드위치', 8000);
INSERT INTO MENU VALUES ( '로스트치킨샌드위치', 8000);
INSERT INTO MENU VALUES ( '쉬림프샌드위치', 9000);
INSERT INTO MENU VALUES ( '연어샌드위치', 9000);
INSERT INTO MENU VALUES ( '에그샌드위치', 7000);
INSERT INTO MENU VALUES ( '참치샌드위치', 7000);


CREATE TABLE SIZE_TABLE(
    사이즈     VARCHAR2(1)  PRIMARY KEY,
    추가금액   NUMBER NOT NULL
);    
INSERT INTO SIZE_TABLE VALUES ('S', 0);
INSERT INTO SIZE_TABLE VALUES ('M', 500);
INSERT INTO SIZE_TABLE VALUES ('L', 1000);


CREATE SEQUENCE seq_TEMPBASKET -- 장바구니 임시주문번호 시퀀스 
INCREMENT BY 1
START WITH 1
NOCYCLE;

CREATE TABLE TEMP_BASKET(
    회원번호 NUMBER REFERENCES CUSTOMER,
    임시주문번호 NUMBER,
    메뉴이름 VARCHAR2(40) REFERENCES MENU,
    사이즈   VARCHAR2(1) REFERENCES SIZE_TABLE,
    빵       VARCHAR2(25),
    치즈     VARCHAR2(20),
    야채     VARCHAR2(20),
    소스     VARCHAR2(20),
    수량     NUMBER,
    합계     NUMBER
);


CREATE SEQUENCE seq_ORDERLIST -- 오더리스트 주문번호 시퀀스
INCREMENT BY 1
START WITH 1
NOCYCLE
NOCACHE;

CREATE TABLE ORDER_LIST (
	주문번호          NUMBER, 
    주문날짜          DATE,
    회원번호          NUMBER REFERENCES CUSTOMER,
    메뉴이름          VARCHAR2(40) REFERENCES MENU,
    사이즈            VARCHAR2(1) REFERENCES SIZE_TABLE,
    빵                VARCHAR2(25),
    치즈              VARCHAR2(20),
    야채              VARCHAR2(20),
    소스              VARCHAR2(20),
    수량              NUMBER DEFAULT 1 NOT NULL,
    합계              NUMBER NOT NULL
);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE, 1000, '클럽샌드위치', 'S', '허니오트', '슈레드치즈', '양상추', '머스타드', 1, 8000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE, 1000, 'BLT샌드위치', 'M', '화이트', '아메리칸치즈', '토마토', '후추', 1, 8500);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+1, 1001, '클럽샌드위치', 'S', '허니오트', '슈레드치즈', '양상추', '머스타드', 1, 8000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+5, 1005, '쉬림프샌드위치', 'M', '플랫브레드', '슈레드치즈', '피클', '스위트칠리', 2, 19000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+5, 1001, '쉬림프샌드위치', 'S', '오트', '모차렐라치즈', '양상추', '스위트어니언', 5, 45000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+12, 1002, '연어샌드위치', 'S', '허니오트', '아메리칸치즈', '피망', '후추', 2, 18000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+13, 1003, '클럽샌드위치', 'L', '하티', '슈레드치즈', '선택안함', '후추', 1, 9000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+24, 1004, 'BLT샌드위치', 'M', '하티', '슈레드치즈', '할라피뇨', '핫칠리', 1, 8500);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+25, 1003, '로스트치킨샌드위치', 'L', '하티', '슈레드치즈', '올리브', '올리브오일', 1, 9000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+30, 1001, '에그샌드위치', 'S', '파마산오레가노', '아메리칸치즈', '오이', '랜치', 10, 70000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+30, 1002, '에그샌드위치', 'S', '플랫브레드', '모차렐라치즈', '피망', '올리브유', 1, 7000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+30, 1004, '클럽샌드위치', 'S', '허니오트', '모차렐라치즈', '오이', '랜치', 1, 8000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+36, 1005, '로스트치킨샌드위치', 'L', '위트', '슈레드치즈', '선택안함', '스모크바비큐', 2, 9000);
--INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE, 1000, '클럽샌드위치', 'S', '허니오트', '슈레드치즈', '양상추', '머스타드', 1, 8000);





/* 쿼리문 */

-- 날짜별 주문 목록 조회
SELECT * 
    FROM ORDER_LIST 
        WHERE TO_DATE(주문날짜, 'YY/MM/DD') = '2023/03/05';
        
-- 메뉴별 판매 현황
SELECT 메뉴이름, COUNT(메뉴이름) AS 판매수량
    FROM ORDER_LIST 
    GROUP BY 메뉴이름;
        
-- VIP 조회 (IN -> =)
SELECT 고객이름, COUNT(고객이름) AS 방문횟수
    FROM CUSTOMER C JOIN ORDER_LIST O
    ON C.회원번호 = O.회원번호
    GROUP BY 고객이름
        HAVING COUNT(고객이름) IN  (SELECT MAX(COUNT(고객이름))
                                    FROM CUSTOMER C JOIN ORDER_LIST O
                                    ON C.회원번호 = O.회원번호
                                    GROUP BY 고객이름)
ORDER BY 고객이름;

-- 월별 재료 사용 내역 조회
SELECT 빵, COUNT(빵)
FROM ORDER_LIST
WHERE EXTRACT(MONTH FROM 주문날짜) = 3
GROUP BY 빵
ORDER BY 2 DESC;



        
        
COMMIT;