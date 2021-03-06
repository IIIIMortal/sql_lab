# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'BookAdmin.ui'
#
# Created by: PyQt5 UI code generator 5.15.1
#
# WARNING: Any manual changes made to this file will be lost when pyuic5 is
# run again.  Do not edit this file unless you know what you are doing.


from PyQt5 import QtCore, QtGui, QtWidgets
from connect import *
from PyQt5.QtWidgets import QTableWidgetItem,QMessageBox
import datetime
import time

cursor,conn=connect()

class Ui_bookadmin(object):
    def setupUi(self, bookadmin):
        bookadmin.setObjectName("bookadmin")
        bookadmin.resize(846, 796)
        self.tabWidget = QtWidgets.QTabWidget(bookadmin)
        self.tabWidget.setGeometry(QtCore.QRect(20, 20, 811, 761))
        self.tabWidget.setObjectName("tabWidget")
        self.tab = QtWidgets.QWidget()
        self.tab.setObjectName("tab")
        self.verticalLayout_2 = QtWidgets.QVBoxLayout(self.tab)
        self.verticalLayout_2.setObjectName("verticalLayout_2")
        self.verticalLayout = QtWidgets.QVBoxLayout()
        self.verticalLayout.setObjectName("verticalLayout")
        self.horizontalLayout = QtWidgets.QHBoxLayout()
        self.horizontalLayout.setObjectName("horizontalLayout")
        self.label_2 = QtWidgets.QLabel(self.tab)
        self.label_2.setObjectName("label_2")
        self.horizontalLayout.addWidget(self.label_2)
        self.readerid = QtWidgets.QLineEdit(self.tab)
        self.readerid.setObjectName("readerid")
        self.horizontalLayout.addWidget(self.readerid)
        self.label = QtWidgets.QLabel(self.tab)
        self.label.setObjectName("label")
        self.horizontalLayout.addWidget(self.label)
        self.readername = QtWidgets.QLineEdit(self.tab)
        self.readername.setObjectName("readername")
        self.horizontalLayout.addWidget(self.readername)
        self.label_3 = QtWidgets.QLabel(self.tab)
        self.label_3.setObjectName("label_3")
        self.horizontalLayout.addWidget(self.label_3)
        self.unit = QtWidgets.QLineEdit(self.tab)
        self.unit.setObjectName("unit")
        self.horizontalLayout.addWidget(self.unit)
        self.verticalLayout.addLayout(self.horizontalLayout)
        self.horizontalLayout_2 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_2.setObjectName("horizontalLayout_2")
        self.select = QtWidgets.QPushButton(self.tab)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Fixed, QtWidgets.QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.select.sizePolicy().hasHeightForWidth())
        self.select.setSizePolicy(sizePolicy)
        self.select.setObjectName("select")
        self.horizontalLayout_2.addWidget(self.select)
        self.selectall = QtWidgets.QPushButton(self.tab)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Fixed, QtWidgets.QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.selectall.sizePolicy().hasHeightForWidth())
        self.selectall.setSizePolicy(sizePolicy)
        self.selectall.setObjectName("selectall")
        self.horizontalLayout_2.addWidget(self.selectall)
        self.verticalLayout.addLayout(self.horizontalLayout_2)
        self.readerinfors = QtWidgets.QTableWidget(self.tab)
        self.readerinfors.setObjectName("readerinfors")
        self.readerinfors.setColumnCount(8)
        self.readerinfors.setRowCount(0)
        item = QtWidgets.QTableWidgetItem()
        self.readerinfors.setHorizontalHeaderItem(0, item)
        item = QtWidgets.QTableWidgetItem()
        self.readerinfors.setHorizontalHeaderItem(1, item)
        item = QtWidgets.QTableWidgetItem()
        self.readerinfors.setHorizontalHeaderItem(2, item)
        item = QtWidgets.QTableWidgetItem()
        self.readerinfors.setHorizontalHeaderItem(3, item)
        item = QtWidgets.QTableWidgetItem()
        self.readerinfors.setHorizontalHeaderItem(4, item)
        item = QtWidgets.QTableWidgetItem()
        self.readerinfors.setHorizontalHeaderItem(5, item)
        item = QtWidgets.QTableWidgetItem()
        self.readerinfors.setHorizontalHeaderItem(6, item)
        item = QtWidgets.QTableWidgetItem()
        self.readerinfors.setHorizontalHeaderItem(7, item)
        self.verticalLayout.addWidget(self.readerinfors)
        self.verticalLayout_2.addLayout(self.verticalLayout)
        self.tabWidget.addTab(self.tab, "")
        self.tab_2 = QtWidgets.QWidget()
        self.tab_2.setObjectName("tab_2")
        self.verticalLayout_5 = QtWidgets.QVBoxLayout(self.tab_2)
        self.verticalLayout_5.setObjectName("verticalLayout_5")
        self.verticalLayout_3 = QtWidgets.QVBoxLayout()
        self.verticalLayout_3.setObjectName("verticalLayout_3")
        self.horizontalLayout_13 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_13.setObjectName("horizontalLayout_13")
        self.horizontalLayout_3 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_3.setObjectName("horizontalLayout_3")
        self.label_4 = QtWidgets.QLabel(self.tab_2)
        self.label_4.setObjectName("label_4")
        self.horizontalLayout_3.addWidget(self.label_4)
        self.bookid = QtWidgets.QLineEdit(self.tab_2)
        self.bookid.setObjectName("bookid")
        self.horizontalLayout_3.addWidget(self.bookid)
        self.horizontalLayout_13.addLayout(self.horizontalLayout_3)
        self.horizontalLayout_5 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_5.setObjectName("horizontalLayout_5")
        self.label_5 = QtWidgets.QLabel(self.tab_2)
        self.label_5.setObjectName("label_5")
        self.horizontalLayout_5.addWidget(self.label_5)
        self.bookname = QtWidgets.QLineEdit(self.tab_2)
        self.bookname.setObjectName("bookname")
        self.horizontalLayout_5.addWidget(self.bookname)
        self.horizontalLayout_13.addLayout(self.horizontalLayout_5)
        self.horizontalLayout_12 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_12.setObjectName("horizontalLayout_12")
        self.label_6 = QtWidgets.QLabel(self.tab_2)
        self.label_6.setObjectName("label_6")
        self.horizontalLayout_12.addWidget(self.label_6)
        self.author = QtWidgets.QLineEdit(self.tab_2)
        self.author.setObjectName("author")
        self.horizontalLayout_12.addWidget(self.author)
        self.horizontalLayout_13.addLayout(self.horizontalLayout_12)
        self.horizontalLayout_8 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_8.setObjectName("horizontalLayout_8")
        self.label_7 = QtWidgets.QLabel(self.tab_2)
        self.label_7.setObjectName("label_7")
        self.horizontalLayout_8.addWidget(self.label_7)
        self.type = QtWidgets.QLineEdit(self.tab_2)
        self.type.setObjectName("type")
        self.horizontalLayout_8.addWidget(self.type)
        self.horizontalLayout_13.addLayout(self.horizontalLayout_8)
        self.verticalLayout_3.addLayout(self.horizontalLayout_13)
        self.horizontalLayout_14 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_14.setObjectName("horizontalLayout_14")
        self.horizontalLayout_4 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_4.setObjectName("horizontalLayout_4")
        self.label_8 = QtWidgets.QLabel(self.tab_2)
        self.label_8.setObjectName("label_8")
        self.horizontalLayout_4.addWidget(self.label_8)
        self.prize = QtWidgets.QLineEdit(self.tab_2)
        self.prize.setObjectName("prize")
        self.horizontalLayout_4.addWidget(self.prize)
        self.horizontalLayout_14.addLayout(self.horizontalLayout_4)
        self.horizontalLayout_6 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_6.setObjectName("horizontalLayout_6")
        self.label_9 = QtWidgets.QLabel(self.tab_2)
        self.label_9.setObjectName("label_9")
        self.horizontalLayout_6.addWidget(self.label_9)
        self.press = QtWidgets.QLineEdit(self.tab_2)
        self.press.setObjectName("press")
        self.horizontalLayout_6.addWidget(self.press)
        self.horizontalLayout_14.addLayout(self.horizontalLayout_6)
        self.horizontalLayout_10 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_10.setObjectName("horizontalLayout_10")
        self.label_12 = QtWidgets.QLabel(self.tab_2)
        self.label_12.setObjectName("label_12")
        self.horizontalLayout_10.addWidget(self.label_12)
        self.totalnum = QtWidgets.QLineEdit(self.tab_2)
        self.totalnum.setObjectName("totalnum")
        self.horizontalLayout_10.addWidget(self.totalnum)
        self.horizontalLayout_14.addLayout(self.horizontalLayout_10)
        self.horizontalLayout_9 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_9.setObjectName("horizontalLayout_9")
        self.label_11 = QtWidgets.QLabel(self.tab_2)
        self.label_11.setObjectName("label_11")
        self.horizontalLayout_9.addWidget(self.label_11)
        self.num = QtWidgets.QLineEdit(self.tab_2)
        self.num.setObjectName("num")
        self.horizontalLayout_9.addWidget(self.num)
        self.horizontalLayout_14.addLayout(self.horizontalLayout_9)
        self.verticalLayout_3.addLayout(self.horizontalLayout_14)
        self.horizontalLayout_17 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_17.setObjectName("horizontalLayout_17")
        self.horizontalLayout_15 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_15.setObjectName("horizontalLayout_15")
        self.horizontalLayout_7 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_7.setObjectName("horizontalLayout_7")
        self.label_10 = QtWidgets.QLabel(self.tab_2)
        self.label_10.setObjectName("label_10")
        self.horizontalLayout_7.addWidget(self.label_10)
        self.abstract_2 = QtWidgets.QLineEdit(self.tab_2)
        self.abstract_2.setObjectName("abstract_2")
        self.horizontalLayout_7.addWidget(self.abstract_2)
        self.horizontalLayout_15.addLayout(self.horizontalLayout_7)
        self.horizontalLayout_11 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_11.setObjectName("horizontalLayout_11")
        self.label_13 = QtWidgets.QLabel(self.tab_2)
        self.label_13.setObjectName("label_13")
        self.horizontalLayout_11.addWidget(self.label_13)
        self.bookshelf = QtWidgets.QLineEdit(self.tab_2)
        self.bookshelf.setObjectName("bookshelf")
        self.horizontalLayout_11.addWidget(self.bookshelf)
        self.horizontalLayout_15.addLayout(self.horizontalLayout_11)
        self.horizontalLayout_17.addLayout(self.horizontalLayout_15)
        self.horizontalLayout_16 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_16.setObjectName("horizontalLayout_16")
        self.addbook = QtWidgets.QPushButton(self.tab_2)
        self.addbook.setObjectName("addbook")
        self.horizontalLayout_16.addWidget(self.addbook)
        self.deletebook = QtWidgets.QPushButton(self.tab_2)
        self.deletebook.setObjectName("deletebook")
        self.horizontalLayout_16.addWidget(self.deletebook)
        self.alterbook = QtWidgets.QPushButton(self.tab_2)
        self.alterbook.setObjectName("alterbook")
        self.horizontalLayout_16.addWidget(self.alterbook)
        self.horizontalLayout_17.addLayout(self.horizontalLayout_16)
        self.verticalLayout_3.addLayout(self.horizontalLayout_17)
        self.bookinfos = QtWidgets.QTableWidget(self.tab_2)
        self.bookinfos.setObjectName("bookinfos")
        self.bookinfos.setColumnCount(11)
        self.bookinfos.setRowCount(0)
        item = QtWidgets.QTableWidgetItem()
        self.bookinfos.setHorizontalHeaderItem(0, item)
        item = QtWidgets.QTableWidgetItem()
        self.bookinfos.setHorizontalHeaderItem(1, item)
        item = QtWidgets.QTableWidgetItem()
        self.bookinfos.setHorizontalHeaderItem(2, item)
        item = QtWidgets.QTableWidgetItem()
        self.bookinfos.setHorizontalHeaderItem(3, item)
        item = QtWidgets.QTableWidgetItem()
        self.bookinfos.setHorizontalHeaderItem(4, item)
        item = QtWidgets.QTableWidgetItem()
        self.bookinfos.setHorizontalHeaderItem(5, item)
        item = QtWidgets.QTableWidgetItem()
        self.bookinfos.setHorizontalHeaderItem(6, item)
        item = QtWidgets.QTableWidgetItem()
        self.bookinfos.setHorizontalHeaderItem(7, item)
        item = QtWidgets.QTableWidgetItem()
        self.bookinfos.setHorizontalHeaderItem(8, item)
        item = QtWidgets.QTableWidgetItem()
        self.bookinfos.setHorizontalHeaderItem(9, item)
        item = QtWidgets.QTableWidgetItem()
        self.bookinfos.setHorizontalHeaderItem(10, item)
        self.verticalLayout_3.addWidget(self.bookinfos)
        self.verticalLayout_5.addLayout(self.verticalLayout_3)
        self.tabWidget.addTab(self.tab_2, "")
        self.tab_3 = QtWidgets.QWidget()
        self.tab_3.setObjectName("tab_3")
        self.iteminfo = QtWidgets.QTableWidget(self.tab_3)
        self.iteminfo.setGeometry(QtCore.QRect(80, 40, 421, 631))
        self.iteminfo.setObjectName("iteminfo")
        self.iteminfo.setColumnCount(4)
        self.iteminfo.setRowCount(0)
        item = QtWidgets.QTableWidgetItem()
        self.iteminfo.setHorizontalHeaderItem(0, item)
        item = QtWidgets.QTableWidgetItem()
        self.iteminfo.setHorizontalHeaderItem(1, item)
        item = QtWidgets.QTableWidgetItem()
        self.iteminfo.setHorizontalHeaderItem(2, item)
        item = QtWidgets.QTableWidgetItem()
        self.iteminfo.setHorizontalHeaderItem(3, item)
        self.layoutWidget = QtWidgets.QWidget(self.tab_3)
        self.layoutWidget.setGeometry(QtCore.QRect(610, 160, 95, 301))
        self.layoutWidget.setObjectName("layoutWidget")
        self.verticalLayout_4 = QtWidgets.QVBoxLayout(self.layoutWidget)
        self.verticalLayout_4.setContentsMargins(0, 0, 0, 0)
        self.verticalLayout_4.setObjectName("verticalLayout_4")
        self.yes = QtWidgets.QPushButton(self.layoutWidget)
        self.yes.setObjectName("yes")
        self.verticalLayout_4.addWidget(self.yes)
        self.no = QtWidgets.QPushButton(self.layoutWidget)
        self.no.setObjectName("no")
        self.verticalLayout_4.addWidget(self.no)
        self.refresh = QtWidgets.QPushButton(self.layoutWidget)
        self.refresh.setObjectName("refresh")
        self.verticalLayout_4.addWidget(self.refresh)
        self.tabWidget.addTab(self.tab_3, "")

        self.retranslateUi(bookadmin)
        self.tabWidget.setCurrentIndex(2)
        QtCore.QMetaObject.connectSlotsByName(bookadmin)

        #***********************************************************************************
        self.show_all_books()
        
        self.selectall.clicked.connect(self.show_all_readers)
        self.select.clicked.connect(self.readerselect)


        self.addbook.clicked.connect(self.add_book)
        self.deletebook.clicked.connect(self.drop_book)
        self.alterbook.clicked.connect(self.update_book)

        
        self.bookinfos.setSelectionBehavior(QtWidgets.QAbstractItemView.SelectRows)#??????bookinfos??????????????????????????????????????????????????????
        self.bookinfos.setEditTriggers(QtWidgets.QAbstractItemView.EditKeyPressed)#??????bookinfos?????????????????????????????????????????????
        self.bookinfos.activated.connect(self.displayinfo)

        self.show_all_items()
        self.iteminfo.setSelectionBehavior(QtWidgets.QAbstractItemView.SelectRows)#??????iteminfo??????????????????????????????????????????????????????
        self.iteminfo.setEditTriggers(QtWidgets.QAbstractItemView.EditKeyPressed)#??????iteminfo?????????????????????????????????????????????
        self.yes.clicked.connect(self.pizhun)
        self.no.clicked.connect(self.bohui)
        self.refresh.clicked.connect(self.show_all_items)
        
        #**************************************************************************************

    def retranslateUi(self, bookadmin):
        _translate = QtCore.QCoreApplication.translate
        bookadmin.setWindowTitle(_translate("bookadmin", "?????????????????????"))
        self.label_2.setText(_translate("bookadmin", "??????ID"))
        self.label.setText(_translate("bookadmin", "??????"))
        self.label_3.setText(_translate("bookadmin", "??????"))
        self.select.setText(_translate("bookadmin", "??????"))
        self.selectall.setText(_translate("bookadmin", "????????????"))
        item = self.readerinfors.horizontalHeaderItem(0)
        item.setText(_translate("bookadmin", "??????ID"))
        item = self.readerinfors.horizontalHeaderItem(1)
        item.setText(_translate("bookadmin", "??????"))
        item = self.readerinfors.horizontalHeaderItem(2)
        item.setText(_translate("bookadmin", "??????"))
        item = self.readerinfors.horizontalHeaderItem(3)
        item.setText(_translate("bookadmin", "??????"))
        item = self.readerinfors.horizontalHeaderItem(4)
        item.setText(_translate("bookadmin", "????????????"))
        item = self.readerinfors.horizontalHeaderItem(5)
        item.setText(_translate("bookadmin", "????????????"))
        item = self.readerinfors.horizontalHeaderItem(6)
        item.setText(_translate("bookadmin", "????????????"))
        item = self.readerinfors.horizontalHeaderItem(7)
        item.setText(_translate("bookadmin", "??????"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab), _translate("bookadmin", "??????????????????"))
        self.label_4.setText(_translate("bookadmin", "??????"))
        self.label_5.setText(_translate("bookadmin", "??????"))
        self.label_6.setText(_translate("bookadmin", "??????"))
        self.label_7.setText(_translate("bookadmin", "??????"))
        self.label_8.setText(_translate("bookadmin", "??????"))
        self.label_9.setText(_translate("bookadmin", "?????????"))
        self.label_12.setText(_translate("bookadmin", "????????????"))
        self.label_11.setText(_translate("bookadmin", "????????????"))
        self.label_10.setText(_translate("bookadmin", "??????"))
        self.label_13.setText(_translate("bookadmin", "?????????"))
        self.addbook.setText(_translate("bookadmin", "??????"))
        self.deletebook.setText(_translate("bookadmin", "??????"))
        self.alterbook.setText(_translate("bookadmin", "??????"))
        item = self.bookinfos.horizontalHeaderItem(0)
        item.setText(_translate("bookadmin", "??????"))
        item = self.bookinfos.horizontalHeaderItem(1)
        item.setText(_translate("bookadmin", "??????"))
        item = self.bookinfos.horizontalHeaderItem(2)
        item.setText(_translate("bookadmin", "??????"))
        item = self.bookinfos.horizontalHeaderItem(3)
        item.setText(_translate("bookadmin", "??????"))
        item = self.bookinfos.horizontalHeaderItem(4)
        item.setText(_translate("bookadmin", "??????"))
        item = self.bookinfos.horizontalHeaderItem(5)
        item.setText(_translate("bookadmin", "?????????"))
        item = self.bookinfos.horizontalHeaderItem(6)
        item.setText(_translate("bookadmin", "??????"))
        item = self.bookinfos.horizontalHeaderItem(7)
        item.setText(_translate("bookadmin", "????????????"))
        item = self.bookinfos.horizontalHeaderItem(8)
        item.setText(_translate("bookadmin", "????????????"))
        item = self.bookinfos.horizontalHeaderItem(9)
        item.setText(_translate("bookadmin", "?????????"))
        item = self.bookinfos.horizontalHeaderItem(10)
        item.setText(_translate("bookadmin", "????????????"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab_2), _translate("bookadmin", "??????????????????"))
        item = self.iteminfo.horizontalHeaderItem(0)
        item.setText(_translate("bookadmin", "??????ID"))
        item = self.iteminfo.horizontalHeaderItem(1)
        item.setText(_translate("bookadmin", "??????"))
        item = self.iteminfo.horizontalHeaderItem(2)
        item.setText(_translate("bookadmin", "???/???"))
        item = self.iteminfo.horizontalHeaderItem(3)
        item.setText(_translate("bookadmin", "??????"))
        self.yes.setText(_translate("bookadmin", "??????"))
        self.no.setText(_translate("bookadmin", "??????"))
        self.refresh.setText(_translate("bookadmin", "??????"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab_3), _translate("bookadmin", "??????/????????????"))


#******************************************************????????????????????????*******************************************************

    def show_all_readers(self):
        sql = "select * from readers"
        res = cursor.execute(sql)#?????????????????????????????????res
        readerinfo=cursor.fetchall()#??????????????????????????????????????????
        #print(readerinfo)
        self.readerinfors.setRowCount(res)
        for i in range(res):
            reader=readerinfo[i]
            readerid = QTableWidgetItem(reader[0])
            readername = QTableWidgetItem(reader[1])
            readersex = QTableWidgetItem(reader[2])
            readerunit = QTableWidgetItem(reader[3])
            readertype = QTableWidgetItem(reader[4])
            books_can_borrow = QTableWidgetItem(str(reader[5]))
            books_borrowed = QTableWidgetItem(str(reader[6]))
            charge_to_pay = QTableWidgetItem(str(reader[8]))

            self.readerinfors.setItem(i,0, readerid)
            self.readerinfors.setItem(i,1, readername)
            self.readerinfors.setItem(i,2, readersex)
            self.readerinfors.setItem(i,3, readerunit)
            self.readerinfors.setItem(i,4, readertype)
            self.readerinfors.setItem(i,5, books_can_borrow)
            self.readerinfors.setItem(i,6, books_borrowed)
            self.readerinfors.setItem(i,7, charge_to_pay)


    def readerselect(self):
        readerid = self.readerid.text()
        readername = self.readername.text()
        readerunit = self.unit.text()
        abc = 0
        if readerid:
            readerid = 'ID="%s"'%(readerid)
            abc = 1
        if readername:
            if abc == 1:
                readername = 'and ??????="%s"'%(readername)
            else:
                readername = '??????="%s"'%(readername)
                abc = 1
        if readerunit:
            if abc ==1:
                readerunit = 'and ??????="%s"'%(readerunit)
            else:
                readerunit = '??????="%s"'%(readerunit)
        sql0 = 'select * from readers where '
        sql1 = readerid+readername+readerunit
        sql = sql0 + sql1
        if sql1:
            res=cursor.execute(sql)
            if res:
                readerinfo = cursor.fetchall()#?????????????????????????????????
                self.readerinfors.setRowCount(res)
                for i in range(res):
                    reader=readerinfo[i]
                    readerid = QTableWidgetItem(reader[0])
                    readername = QTableWidgetItem(reader[1])
                    readersex = QTableWidgetItem(reader[2])
                    readerunit = QTableWidgetItem(reader[3])
                    readertype = QTableWidgetItem(reader[4])
                    books_can_borrow = QTableWidgetItem(str(reader[5]))
                    books_borrowed = QTableWidgetItem(str(reader[6]))
                    charge_to_pay = QTableWidgetItem(str(reader[8]))
                    self.readerinfors.setItem(i,0, readerid)
                    self.readerinfors.setItem(i,1, readername)
                    self.readerinfors.setItem(i,2, readersex)
                    self.readerinfors.setItem(i,3, readerunit)
                    self.readerinfors.setItem(i,4, readertype)
                    self.readerinfors.setItem(i,5, books_can_borrow)
                    self.readerinfors.setItem(i,6, books_borrowed)
                    self.readerinfors.setItem(i,7, charge_to_pay)
            else:
                QMessageBox.warning(self, "??????", "??????????????????????????????", QMessageBox.Yes)
        else:
            QMessageBox.warning(self, "??????", "?????????????????????????????????", QMessageBox.Yes)


#*****************************************************????????????????????????*******************************************************
    def show_all_books(self):       
        sql='SELECT * FROM books'
        res = cursor.execute(sql)
        books=cursor.fetchall()
        self.bookinfos.setRowCount(res)
        for i in range(res):
            book=books[i]
            self.bookinfos.setItem(i,0,QTableWidgetItem(book[0]))
            self.bookinfos.setItem(i,1,QTableWidgetItem(book[1]))
            self.bookinfos.setItem(i,2,QTableWidgetItem(book[2]))
            self.bookinfos.setItem(i,3,QTableWidgetItem(book[3]))
            self.bookinfos.setItem(i,4,QTableWidgetItem(str(book[4])))
            self.bookinfos.setItem(i,5,QTableWidgetItem(book[5]))
            self.bookinfos.setItem(i,6,QTableWidgetItem(book[6]))
            self.bookinfos.setItem(i,7,QTableWidgetItem(str(book[7])))
            self.bookinfos.setItem(i,8,QTableWidgetItem(str(book[8])))
            self.bookinfos.setItem(i,9,QTableWidgetItem(book[9]))
            self.bookinfos.setItem(i,10,QTableWidgetItem(str(book[10])))

    def add_book(self):
        bookid = self.bookid.text()
        bookname = self.bookname.text()
        author = self.author.text()
        booktype = self.type.text()
        prize1 = self.prize.text()
        press = self.press.text()

        abstract_2 = self.abstract_2.text()
        bookshelf = self.bookshelf.text()
        try:
            totalnum = int(self.totalnum.text())
            num = int(self.num.text())
            if prize1 != '':
                prize = float(prize1)
                if bookid and bookname and author and booktype and prize and press and totalnum and num and abstract_2 and bookshelf:
                    sql = 'insert into books(??????,??????,??????,??????,??????,?????????,??????,????????????,????????????,?????????,????????????) values ("%s","%s","%s","%s","%.2f","%s","%s","%d","%d","%s","%d")' % (bookid,bookname,author,booktype,prize,press,abstract_2,num,totalnum,bookshelf,0)
                    cursor.execute(sql)
                    conn.commit()
                    self.show_all_books()
                else:
                    QMessageBox.warning(self, "??????", "????????????????????????", QMessageBox.Yes)
            else:
                QMessageBox.warning(self, "??????", "????????????????????????", QMessageBox.Yes)
        except (pymysql.err.IntegrityError,ValueError):
            QMessageBox.warning(self, "??????", "?????????????????????", QMessageBox.Yes)
        

    def drop_book(self):
        s=self.bookinfos.currentRow()#???????????????????????????0?????????
        bookid = self.bookinfos.item(s,0).text()
        sql = 'delete from books where ??????="%s"'%(bookid)
        cursor.execute(sql)
        conn.commit()
        self.show_all_books()

    def displayinfo(self):
        s = self.bookinfos.currentRow()
        self.bookid.setText(self.bookinfos.item(s, 0).text())
        self.bookname.setText(self.bookinfos.item(s, 1).text())
        self.author.setText(self.bookinfos.item(s, 2).text())
        self.type.setText(self.bookinfos.item(s, 3).text())
        self.prize.setText(self.bookinfos.item(s, 4).text())
        self.press.setText(self.bookinfos.item(s, 5).text())
        self.abstract_2.setText(self.bookinfos.item(s, 6).text())
        self.num.setText(self.bookinfos.item(s, 7).text())
        self.totalnum.setText(self.bookinfos.item(s, 8).text())
        self.bookshelf.setText(self.bookinfos.item(s, 9).text())
    
    def update_book(self):
        bookid = self.bookid.text()
        bookname = self.bookname.text()
        author = self.author.text()
        booktype = self.type.text()
        prize1 = self.prize.text()
        press = self.press.text()
        
        abstract_2 = self.abstract_2.text()
        bookshelf = self.bookshelf.text()
        try:
            totalnum = int(self.totalnum.text())
            num = int(self.num.text())
            if prize1 != '':
                prize = float(prize1)
                if bookid and bookname and author and booktype and prize and press and totalnum and num and abstract_2 and bookshelf:
                    sql = 'UPDATE books SET ??????="%s",??????="%s",??????="%s",??????="%s",??????="%.2f",?????????="%s",??????="%s",????????????="%d",????????????="%d",?????????="%s",????????????="%d" WHERE ??????="%s"' % (bookid,bookname,author,booktype,prize,press,abstract_2,num,totalnum,bookshelf,0,bookid)
                    cursor.execute(sql)
                    conn.commit()
                    self.show_all_books()
                else:
                    QMessageBox.warning(self, "??????", "????????????????????????", QMessageBox.Yes)
            else:
                QMessageBox.warning(self, "??????", "????????????????????????", QMessageBox.Yes)
        except (pymysql.err.IntegrityError,ValueError):
            QMessageBox.warning(self, "??????", "?????????????????????", QMessageBox.Yes)





#******************************************************????????????????????????*******************************************************

    def show_all_items(self):
        sql='SELECT * FROM item'
        res = cursor.execute(sql)
        items=cursor.fetchall()
        self.iteminfo.setRowCount(res)
        for i in range(res):
            item=items[i]
            self.iteminfo.setItem(i,1,QTableWidgetItem(item[0]))
            self.iteminfo.setItem(i,0,QTableWidgetItem(item[1]))
            self.iteminfo.setItem(i,3,QTableWidgetItem(str(item[2])))
            self.iteminfo.setItem(i,2,QTableWidgetItem(item[3]))
            
    def pizhun(self):
        s=self.iteminfo.currentRow()#???????????????????????????0?????????
        readerid = self.iteminfo.item(s,0).text()
        bookid = self.iteminfo.item(s,1).text()
        selecttype = self.iteminfo.item(s,2).text()
        itemtime = str(self.iteminfo.item(s,3).text())
        
        
        if selecttype == 'borrow':
            #???item??????????????????borrow???
            sql1 = 'delete from item where bookid="%s"'%(bookid)
            cursor.execute(sql1)
            conn.commit()
            sql2 = 'insert into borrow(ID,??????,????????????) values ("%s","%s","%s")'%(readerid,bookid,itemtime)
            cursor.execute(sql2)
            conn.commit()
            #reader????????????????????????????????????????????????
            sql3 = 'update readers set ????????????=????????????-1,????????????=????????????+1 where ID="%s"'%(readerid)
            cursor.execute(sql3)
            conn.commit()
            #????????????????????????
            sql4 = 'update books set ????????????=????????????+1,????????????=????????????-1 where ??????="%s"'%(bookid)
            cursor.execute(sql4)
            conn.commit()
        else:
            sql = 'select * from borrow where ID="%s" and ??????="%s"'%(readerid,bookid)
            cursor.execute(sql)
            borrowtime=cursor.fetchall()
            borrowtime=borrowtime[0]
            borrowtime=str(borrowtime[-1])

            
            #????????????????????????????????????????????????
            borrowtime = time.strptime(borrowtime,"%Y-%m-%d")
            time_array1 = int(time.mktime(borrowtime))
            itemtime = time.strptime(itemtime,"%Y-%m-%d")
            time_array2 = int(time.mktime(itemtime))
            result = (time_array2 - time_array1) //60 //60 //24
            #?????????????????????????????????
            sql7 = 'select * from readers where ID="%s"'%(readerid)
            cursor.execute(sql7)
            item = cursor.fetchall()
            item = item[0]
            if item[4] == '???????????????':
                readertime = 60
            elif item[4] == '????????????':
                readertime = 30
            elif item[4] == '??????':
                readertime = 90
            time_to_cal = result - readertime
            #??????????????????
            if time_to_cal <= 0:
                QMessageBox.warning(self, "????????????", "???????????????", QMessageBox.Yes)
            else:
                sql8 = 'update readers set ??????=??????+"%d" where ID="%s"'%(time_to_cal,readerid)
                cursor.execute(sql8)
                conn.commit()
                QMessageBox.warning(self, "??????", "???????????????????????????????????????????????????", QMessageBox.Yes)
            sql5 = 'delete from borrow where ??????="%s"'%(bookid)
            cursor.execute(sql5)
            conn.commit()
            sql6 = 'delete from item where bookid="%s"'%(bookid)
            cursor.execute(sql6)
            conn.commit()
            sql9 = 'update readers set ????????????=????????????+1,????????????=????????????-1 where ID="%s"'%(readerid)
            cursor.execute(sql9)
            conn.commit()
            sql10 = 'update books set ????????????=????????????+1 where ??????="%s"'%(bookid)
            cursor.execute(sql10)
            conn.commit()
        self.show_all_items()

    def bohui(self):
        s=self.iteminfo.currentRow()#???????????????????????????0?????????
        bookid = self.iteminfo.item(s,1).text()
        sql = 'delete from item where bookid="%s"'%(bookid)
        cursor.execute(sql)
        conn.commit()
