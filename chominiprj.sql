SHOW USER;

/* ���̺� ��� */
DROP TABLE ORDER_LIST;
DROP TABLE TEMP_BASKET;
DROP TABLE SIZE_TABLE;
DROP TABLE MENU;
DROP TABLE CUSTOMER;

/* ������ ��� */
DROP SEQUENCE seq_CUSTOMER;
DROP SEQUENCE seq_TEMPBASKET;
DROP SEQUENCE seq_ORDERLIST;

/* ���̺� ����Ʈ */
SELECT * FROM CUSTOMER;
SELECT * FROM MENU;
SELECT * FROM SIZE_TABLE;
SELECT * FROM TEMP_BASKET;
SELECT * FROM ORDER_LIST;

/* ������, ���̺� ���� */
CREATE SEQUENCE seq_CUSTOMER -- ����ȣ ������
INCREMENT BY 1
START WITH 1000
NOCYCLE
NOCACHE;

CREATE TABLE CUSTOMER( 
    ȸ����ȣ NUMBER PRIMARY KEY,
    ���̸� VARCHAR2(20) NOT NULL,
    ��ȭ��ȣ VARCHAR2(20) NOT NULL
);
INSERT INTO CUSTOMER VALUES(seq_CUSTOMER.NEXTVAL, '���̾�', '010-0000-0000');
INSERT INTO CUSTOMER VALUES(seq_CUSTOMER.NEXTVAL, '�Ҹ���', '010-0000-0001');
INSERT INTO CUSTOMER VALUES(seq_CUSTOMER.NEXTVAL, '�����', '010-0000-0002');
INSERT INTO CUSTOMER VALUES(seq_CUSTOMER.NEXTVAL, '����ġ', '010-0000-0003');
INSERT INTO CUSTOMER VALUES(seq_CUSTOMER.NEXTVAL, '������', '010-0000-0004');
INSERT INTO CUSTOMER VALUES(seq_CUSTOMER.NEXTVAL, '���ε�', '010-0000-0005');


CREATE TABLE MENU( 
    �޴��̸� VARCHAR2(40) PRIMARY KEY,
    �޴����� NUMBER NOT NULL
);
INSERT INTO MENU VALUES ( 'Ŭ��������ġ', 8000);
INSERT INTO MENU VALUES ( 'BLT������ġ', 8000);
INSERT INTO MENU VALUES ( '�ν�ƮġŲ������ġ', 8000);
INSERT INTO MENU VALUES ( '������������ġ', 9000);
INSERT INTO MENU VALUES ( '���������ġ', 9000);
INSERT INTO MENU VALUES ( '���׻�����ġ', 7000);
INSERT INTO MENU VALUES ( '��ġ������ġ', 7000);


CREATE TABLE SIZE_TABLE(
    ������     VARCHAR2(1)  PRIMARY KEY,
    �߰��ݾ�   NUMBER NOT NULL
);    
INSERT INTO SIZE_TABLE VALUES ('S', 0);
INSERT INTO SIZE_TABLE VALUES ('M', 500);
INSERT INTO SIZE_TABLE VALUES ('L', 1000);


CREATE SEQUENCE seq_TEMPBASKET -- ��ٱ��� �ӽ��ֹ���ȣ ������ 
INCREMENT BY 1
START WITH 1
NOCYCLE;

CREATE TABLE TEMP_BASKET(
    ȸ����ȣ NUMBER REFERENCES CUSTOMER,
    �ӽ��ֹ���ȣ NUMBER,
    �޴��̸� VARCHAR2(40) REFERENCES MENU,
    ������   VARCHAR2(1) REFERENCES SIZE_TABLE,
    ��       VARCHAR2(25),
    ġ��     VARCHAR2(20),
    ��ä     VARCHAR2(20),
    �ҽ�     VARCHAR2(20),
    ����     NUMBER,
    �հ�     NUMBER
);


CREATE SEQUENCE seq_ORDERLIST -- ��������Ʈ �ֹ���ȣ ������
INCREMENT BY 1
START WITH 1
NOCYCLE
NOCACHE;

CREATE TABLE ORDER_LIST (
	�ֹ���ȣ          NUMBER, 
    �ֹ���¥          DATE,
    ȸ����ȣ          NUMBER REFERENCES CUSTOMER,
    �޴��̸�          VARCHAR2(40) REFERENCES MENU,
    ������            VARCHAR2(1) REFERENCES SIZE_TABLE,
    ��                VARCHAR2(25),
    ġ��              VARCHAR2(20),
    ��ä              VARCHAR2(20),
    �ҽ�              VARCHAR2(20),
    ����              NUMBER DEFAULT 1 NOT NULL,
    �հ�              NUMBER NOT NULL
);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE, 1000, 'Ŭ��������ġ', 'S', '��Ͽ�Ʈ', '������ġ��', '�����', '�ӽ�Ÿ��', 1, 8000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE, 1000, 'BLT������ġ', 'M', 'ȭ��Ʈ', '�Ƹ޸�ĭġ��', '�丶��', '����', 1, 8500);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+1, 1001, 'Ŭ��������ġ', 'S', '��Ͽ�Ʈ', '������ġ��', '�����', '�ӽ�Ÿ��', 1, 8000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+5, 1005, '������������ġ', 'M', '�÷��극��', '������ġ��', '��Ŭ', '����Ʈĥ��', 2, 19000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+5, 1001, '������������ġ', 'S', '��Ʈ', '��������ġ��', '�����', '����Ʈ��Ͼ�', 5, 45000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+12, 1002, '���������ġ', 'S', '��Ͽ�Ʈ', '�Ƹ޸�ĭġ��', '�Ǹ�', '����', 2, 18000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+13, 1003, 'Ŭ��������ġ', 'L', '��Ƽ', '������ġ��', '���þ���', '����', 1, 9000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+24, 1004, 'BLT������ġ', 'M', '��Ƽ', '������ġ��', '�Ҷ��Ǵ�', '��ĥ��', 1, 8500);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+25, 1003, '�ν�ƮġŲ������ġ', 'L', '��Ƽ', '������ġ��', '�ø���', '�ø������', 1, 9000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+30, 1001, '���׻�����ġ', 'S', '�ĸ����������', '�Ƹ޸�ĭġ��', '����', '��ġ', 10, 70000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+30, 1002, '���׻�����ġ', 'S', '�÷��극��', '��������ġ��', '�Ǹ�', '�ø�����', 1, 7000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+30, 1004, 'Ŭ��������ġ', 'S', '��Ͽ�Ʈ', '��������ġ��', '����', '��ġ', 1, 8000);
INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE+36, 1005, '�ν�ƮġŲ������ġ', 'L', '��Ʈ', '������ġ��', '���þ���', '����ũ�ٺ�ť', 2, 9000);
--INSERT INTO ORDER_LIST VALUES(SEQ_ORDERLIST.NEXTVAL, SYSDATE, 1000, 'Ŭ��������ġ', 'S', '��Ͽ�Ʈ', '������ġ��', '�����', '�ӽ�Ÿ��', 1, 8000);





/* ������ */

-- ��¥�� �ֹ� ��� ��ȸ
SELECT * 
    FROM ORDER_LIST 
        WHERE TO_DATE(�ֹ���¥, 'YY/MM/DD') = '2023/03/05';
        
-- �޴��� �Ǹ� ��Ȳ
SELECT �޴��̸�, COUNT(�޴��̸�) AS �Ǹż���
    FROM ORDER_LIST 
    GROUP BY �޴��̸�;
        
-- VIP ��ȸ (IN -> =)
SELECT ���̸�, COUNT(���̸�) AS �湮Ƚ��
    FROM CUSTOMER C JOIN ORDER_LIST O
    ON C.ȸ����ȣ = O.ȸ����ȣ
    GROUP BY ���̸�
        HAVING COUNT(���̸�) IN  (SELECT MAX(COUNT(���̸�))
                                    FROM CUSTOMER C JOIN ORDER_LIST O
                                    ON C.ȸ����ȣ = O.ȸ����ȣ
                                    GROUP BY ���̸�)
ORDER BY ���̸�;

-- ���� ��� ��� ���� ��ȸ
SELECT ��, COUNT(��)
FROM ORDER_LIST
WHERE EXTRACT(MONTH FROM �ֹ���¥) = 3
GROUP BY ��
ORDER BY 2 DESC;



        
        
COMMIT;