import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JDBClibraryGUI {

	static Connection conn;

	public static void main(String args[]) {

		start("root", "syz412");
	}

	public static void start(String userid, String passwd) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library?characterEncoding=utf8&useSSL=true",
					userid, passwd);

			JFrame.setDefaultLookAndFeelDecorated(true);
			JFrame frm;
			frm = new JFrame();
			frm.setTitle("ͼ�����ϵͳ");
			frm.setBounds(600, 300, 500, 400);
			frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JPanel mainpanel = new JPanel();
			frm.add(mainpanel);
			JLabel label = new JLabel("��ӭʹ��ͼ�����ϵͳ����ѡ�����");
			label.setBounds(200, 20, 200, 20);
			mainpanel.add(label);

			JButton button_searchbook = new JButton("ͼ���ѯ");
			button_searchbook.setBounds(200, 50, 40, 40);
			mainpanel.add(button_searchbook);
			button_searchbook.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						check_Book();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			JButton button_borrowbook = new JButton("ͼ�����");
			button_borrowbook.setBounds(200, 50, 40, 40);
			mainpanel.add(button_borrowbook);
			button_borrowbook.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						borrow_Book();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			JButton button_returnbook = new JButton("ͼ��黹");
			button_returnbook.setBounds(200, 50, 40, 40);
			mainpanel.add(button_returnbook);
			button_returnbook.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						return_Book();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			frm.setVisible(true);

			System.out.println("********************************************************");
			System.out.println("\t\t\tͼ�����ϵͳ");
			System.out.println("********************************************************");

			Scanner reader = new Scanner(System.in);

			while (true) {
				System.out.println("4.ͼ������� 5.����֤���� 0.�˳�ϵͳ");
				System.out.println("��������Ҫ�ķ�����:");
				int choice = reader.nextInt();
				switch (choice) {
				case 0:
					conn.close();
					return;
				case 4:
					add_Book();
					break;
				case 5:
					proof_Manag();
					break;
				default:
					System.out.println("�����Ŵ���");
				}
			}
		} catch (Exception sqle) {
			System.out.println("Exception : " + sqle);
		}
	}

	static void search_all() throws SQLException {
		JFrame frm;
		frm = new JFrame();
		frm.setTitle("��ѯ���");
		frm.setBounds(600, 300, 800, 800);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		frm.add(panel);

		String query = "SELECT * FROM book";
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);

		String output = "��ѯ�������\n���\t���\t����\t������\t���\t����\t�۸�\t�ܲ�����\t���\n";
		while (rset.next()) {
			output = output + rset.getString("bno") + "\t" + rset.getString("category") + "\t" + rset.getString("title")
					+ "\t" + rset.getString("press") + "\t" + rset.getInt("year") + "\t" + rset.getString("author")
					+ "\t" + rset.getDouble("price") + "\t" + rset.getInt("total") + "\t" + rset.getInt("stock") + "\n";
		}

		stmt.close();
		JTextArea jta1 = new JTextArea(output, 40, 70); // ���������ı���
		jta1.setLineWrap(true);
		panel.add(jta1);

		frm.setVisible(true);

	}

	static void test() {
		System.out.println("test!");
	}

	static void showresult(ResultSet rset) throws SQLException {
		JFrame frm;
		frm = new JFrame();
		frm.setTitle("��ѯ���");
		frm.setBounds(600, 300, 800, 800);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		frm.add(panel);

		String output = "��ѯ�������\n���\t���\t����\t������\t���\t����\t�۸�\t�ܲ�����\t���\n";
		while (rset.next()) {
			output = output + rset.getString("bno") + "\t" + rset.getString("category") + "\t" + rset.getString("title")
					+ "\t" + rset.getString("press") + "\t" + rset.getInt("year") + "\t" + rset.getString("author")
					+ "\t" + rset.getDouble("price") + "\t" + rset.getInt("total") + "\t" + rset.getInt("stock") + "\n";
		}

		JTextArea jta1 = new JTextArea(output, 40, 70); // ���������ı���
		jta1.setLineWrap(true);
		panel.add(jta1);

		frm.setVisible(true);
	}

	static void search_detail() {
		JFrame frm;
		frm = new JFrame();
		frm.setTitle("ͼ���ѯ");
		frm.setBounds(600, 300, 500, 400);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JButton button_search = new JButton("��ѯ");
		JPanel panel = new JPanel();
		panel.add(button_search);
		frm.add(panel);
		JLabel a1 = new JLabel("���");
		JTextField bno = new JTextField();
		JLabel a2 = new JLabel("���");
		JTextField cat = new JTextField();
		JLabel a3 = new JLabel("����");
		JTextField name = new JTextField();
		JLabel a4 = new JLabel("������");
		JTextField press = new JTextField();
		JLabel a5 = new JLabel("����");
		JTextField author = new JTextField();
		JButton okbtn = new JButton("��ѯ");

		/* ���ⲿ��--North */
		Container c = frm.getContentPane();
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel("�����Բ�ѯ����ʹ�õ����Բ�����д��"));
		c.add(titlePanel, "North");

		/* ���벿��--Center */
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		a1.setBounds(50, 20, 50, 20);
		a2.setBounds(50, 60, 50, 20);
		a3.setBounds(50, 100, 50, 20);
		a4.setBounds(50, 140, 50, 20);
		a5.setBounds(50, 180, 50, 20);
		fieldPanel.add(a1);
		fieldPanel.add(a2);
		fieldPanel.add(a3);
		fieldPanel.add(a4);
		fieldPanel.add(a5);
		bno.setBounds(110, 20, 120, 20);
		cat.setBounds(110, 60, 120, 20);
		name.setBounds(110, 100, 120, 20);
		press.setBounds(110, 140, 120, 20);
		author.setBounds(110, 180, 120, 20);

		fieldPanel.add(bno);
		fieldPanel.add(cat);
		fieldPanel.add(name);
		fieldPanel.add(press);
		fieldPanel.add(author);
		c.add(fieldPanel, "Center");

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(okbtn);
		c.add(buttonPanel, "South");
		okbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String search = "SELECT * FROM book WHERE ";
				String act = "";
				if (!bno.getText().equals("")) {
					search = search + act + "bno = " + bno.getText().strip();
					act = " and ";
				}
				if (!cat.getText().equals("")) {
					search = search + act + "category = '" + cat.getText().strip() + "'";
					act = " and ";
				}
				if (!name.getText().equals("")) {
					search = search + act + "title = '" + name.getText().strip() + "'";
					act = " and ";
				}
				if (!press.getText().equals("")) {
					search = search + act + "press = '" + press.getText().strip() + "'";
					act = " and ";
				}
				if (!author.getText().equals("")) {
					search = search + act + "author = '" + author.getText().strip() + "'";
					act = " and ";
				}

				Statement stmt;
				try {
					stmt = conn.createStatement();
					ResultSet rset = stmt.executeQuery(search);
					showresult(rset);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println(search);
					e1.printStackTrace();
				}

			}
		});

		frm.setVisible(true);
	}

	static void check_Book() throws SQLException {

		JFrame frm;
		frm = new JFrame();
		frm.setTitle("ͼ���ѯ");
		frm.setBounds(600, 300, 500, 400);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JButton button_searchall = new JButton("��ѯȫ��");
		JButton button_searchdetail = new JButton("�����Բ�ѯ");
		JPanel panel = new JPanel();
		panel.add(button_searchall);
		panel.add(button_searchdetail);
		frm.add(panel);
		button_searchall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					search_all();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		button_searchdetail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				search_detail();
			}
		});

		frm.setVisible(true);

	}

	static void borrow_Book() throws SQLException {

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHH");
		String time = ft.format(dNow);

		JFrame frm;
		frm = new JFrame();
		frm.setTitle("ͼ�����");
		frm.setBounds(600, 300, 500, 400);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JButton button_borrow = new JButton("����");
		JPanel panel = new JPanel();

		frm.add(panel);
		JLabel a1 = new JLabel("����֤��");
		JTextField getcno = new JTextField();
		JLabel a2 = new JLabel("���");
		JTextField getbno = new JTextField();

		Container c = frm.getContentPane();
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel("���������֤�������"));
		c.add(titlePanel, "North");

		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		a1.setBounds(50, 20, 50, 20);
		a2.setBounds(50, 60, 50, 20);
		getcno.setBounds(110, 20, 120, 20);
		getbno.setBounds(110, 60, 120, 20);

		fieldPanel.add(getbno);
		fieldPanel.add(getcno);
		fieldPanel.add(a1);
		fieldPanel.add(a2);
		c.add(fieldPanel, "Center");

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(button_borrow);
		c.add(buttonPanel, "South");

		button_borrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String cno = null;
					String bno = null;
					String checkcno = "SELECT cno, type FROM card WHERE cno = ";
					String checkbno = "SELECT bno, stock FROM book WHERE bno = ";
					String query = "SELECT state, borrow_date FROM borrow where ";
					String Rtime = null;
					bno = getbno.getText();
					cno = getcno.getText();
					bno = bno.strip();
					cno = cno.strip();
					if (bno.equals("") || cno.equals(""))
						JOptionPane.showMessageDialog(frm, "������������Ϣ��", "����ʧ��", JOptionPane.INFORMATION_MESSAGE);
					else {
						checkcno = checkcno + "'" + cno + "'";
						Statement stmtcno;
						stmtcno = conn.createStatement();
						ResultSet rsetcno = stmtcno.executeQuery(checkcno);
						if (!rsetcno.next()) {
							JOptionPane.showMessageDialog(frm, "�����ڴ˽���֤��", "����ʧ��", JOptionPane.INFORMATION_MESSAGE);
							return;
						} else {
							long t;
							if (rsetcno.getString("type").equals("T"))
								t = dNow.getTime() + 90L * 24 * 60 * 60 * 1000;
							else
								t = dNow.getTime() + 60L * 24 * 60 * 60 * 1000;
							Rtime = ft.format((new Date(t)));
						}
						checkbno = checkbno + "'" + bno + "'";
						int stock;
						Statement stmtbno = conn.createStatement();
						ResultSet rsetbno = stmtbno.executeQuery(checkbno);
						if (!rsetbno.next()) {
							JOptionPane.showMessageDialog(frm, "�����ڸ���Ŀ��", "����ʧ��", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						stock = rsetbno.getInt("stock");
						if (stock == 0) {
							Statement stmtquery = conn.createStatement();
							ResultSet rsetquery = stmtquery
									.executeQuery("SELECT min(return_date) FROM borrow where bno='" + bno + "'");
							rsetquery.next();
							JOptionPane.showMessageDialog(frm,
									"����Ŀ��ȫ�������\n����黹����Ϊ��" + rsetquery.getInt("min(return_date)"), "����ʧ��",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						query = query + "bno = " + bno + " and cno = " + cno;
						Statement stmtquery = conn.createStatement();
						ResultSet rsetquery = stmtquery.executeQuery(query);
						while (rsetquery.next()) {
							if (rsetquery.getString("state").equals("O")) {
								JOptionPane.showMessageDialog(frm, "���ѽ��Ĵ��飡", "����ʧ��", JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							if (rsetquery.getInt("borrow_date") == Integer.parseInt(time)) {
								JOptionPane.showMessageDialog(frm, "�����Ĵ�������Ƶ������һСʱ���ٴγ���", "����ʧ��",
										JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						}

						String insert = "INSERT borrow VALUE( '" + cno + "', '" + bno + "', " + time + ", " + Rtime
								+ ", 'O')";
						Statement stmtinsert = conn.createStatement();
						conn.setAutoCommit(false);
						stmtinsert.executeUpdate(insert);
						String update = "UPDATE book SET stock = " + Integer.toString(stock - 1) + " WHERE bno = '"
								+ bno + "'";
						stmtinsert.executeUpdate(update);
						conn.commit();
						conn.setAutoCommit(true);
						JOptionPane.showMessageDialog(frm, "��Ŀ" + bno + "���ĳɹ���", "���ĳɹ�",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e1) {
					System.out.println("SQL���ִ��ʧ�ܣ���������λ��");
					System.out.println(e.toString());
				}
			}
		});
		frm.setVisible(true);

	}

	static void return_Book() throws SQLException {

		JFrame frm;
		frm = new JFrame();
		frm.setTitle("ͼ��黹");
		frm.setBounds(600, 300, 500, 400);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();

		frm.add(panel);
		JLabel a1 = new JLabel("����֤��");
		JTextField getcno = new JTextField();
		JLabel a2 = new JLabel("���");
		JTextField getbno = new JTextField();

		Container c = frm.getContentPane();
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel("���������֤�������"));
		c.add(titlePanel, "North");

		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		a1.setBounds(50, 20, 50, 20);
		a2.setBounds(50, 60, 50, 20);
		getcno.setBounds(110, 20, 120, 20);
		getbno.setBounds(110, 60, 120, 20);

		fieldPanel.add(getbno);
		fieldPanel.add(getcno);
		fieldPanel.add(a1);
		fieldPanel.add(a2);
		c.add(fieldPanel, "Center");

		JButton button_checkreturn = new JButton("��ѯ���黹��Ŀ");
		JButton button_return = new JButton("�黹��Ŀ");
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(button_checkreturn);
		buttonPanel.add(button_return);
		c.add(buttonPanel, "South");

		button_checkreturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String checkcno = "SELECT cno FROM card WHERE cno =";
				String cno = null;
				String checkbor = "SELECT * FROM borrow WHERE cno ='";
				String bno = null;
				bno = getbno.getText();
				cno = getcno.getText();
				bno = bno.strip();
				cno = cno.strip();
				if (cno.equals("")) {
					JOptionPane.showMessageDialog(frm, "���������֤�ţ�", "�黹ʧ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				checkcno = checkcno + cno;
				Statement stmtcno;
				try {
					stmtcno = conn.createStatement();
					ResultSet rsetcno = stmtcno.executeQuery(checkcno);
					if (!rsetcno.next()) {
						JOptionPane.showMessageDialog(frm, "����֤�����ڣ�", "�黹ʧ��", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					checkbor = checkbor + cno + "' AND state = 'O'";
					Statement stmtbor;
					stmtbor = conn.createStatement();
					ResultSet rsetbor = stmtbor.executeQuery(checkbor);
					if (!rsetbor.next()) {
						JOptionPane.showMessageDialog(frm, "�˽���֤�޴����鼮��", "�黹ʧ��", JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						String output = "�ý���֤������Ŀ����\n����֤�� ���  ����ʱ��  Ӧ��ʱ��  ״̬\n";
						do {
							output = output + rsetbor.getString("cno") + "    " + rsetbor.getString("bno") + " "
									+ +rsetbor.getInt("borrow_date") + "  " + rsetbor.getInt("return_date") + "  "
									+ rsetbor.getString("state") + "\n";
						} while (rsetbor.next());
						JOptionPane.showMessageDialog(frm, output, "����֤����", JOptionPane.INFORMATION_MESSAGE);
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("SQL���ִ��ʧ�ܣ���������λ��");
					System.out.println(e.toString());
				}
			}
		});

		button_return.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String checkcno = "SELECT cno FROM card WHERE cno =";
				String cno = null;
				String checkbor = "SELECT * FROM borrow WHERE cno ='";
				String bno = null;
				int stock = 0;
				bno = getbno.getText();
				cno = getcno.getText();
				bno = bno.strip();
				cno = cno.strip();
				if (cno.equals("")) {
					JOptionPane.showMessageDialog(frm, "���������֤�ţ�", "�黹ʧ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				checkcno = checkcno + cno;
				Statement stmtcno;
				try {
					stmtcno = conn.createStatement();

					ResultSet rsetcno = stmtcno.executeQuery(checkcno);
					if (!rsetcno.next()) {
						JOptionPane.showMessageDialog(frm, "����֤�����ڣ�", "�黹ʧ��", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					checkbor = checkbor + cno + "' AND state = 'O'";
					Statement stmtbor;
					stmtbor = conn.createStatement();
					ResultSet rsetbor = stmtbor.executeQuery(checkbor);
					if (!rsetbor.next()) {
						JOptionPane.showMessageDialog(frm, "�˽���֤�޴����鼮��", "�黹ʧ��", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if (bno.equals("")) {
						JOptionPane.showMessageDialog(frm, "��������ţ�", "�黹ʧ��", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					String checkbno = "SELECT * FROM borrow WHERE cno ='" + cno + "' AND bno ='" + bno
							+ "' AND state ='O'";
					Statement stmtbno;
					stmtbno = conn.createStatement();
					ResultSet rsetbno = stmtcno.executeQuery(checkbno);
					if (!rsetbno.next()) {
						JOptionPane.showMessageDialog(frm, "����Ŀδ���˽���֤�����", "�黹ʧ��", JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						String findstock = "SELECT stock FROM book WHERE bno ='" + bno + "'";
						Statement stmtstock = conn.createStatement();
						ResultSet rsetstock = stmtstock.executeQuery(findstock);
						if (rsetstock.next())
							stock = rsetstock.getInt("stock");
					}
					String changestate = "UPDATE borrow SET state = 'I' WHERE cno ='" + cno + "' AND bno ='" + bno
							+ "'";
					String update = "UPDATE book SET stock = " + Integer.toString(stock + 1) + " WHERE bno = '" + bno
							+ "'";
					Statement stmtupdate = conn.createStatement();
					Statement stmtchange = conn.createStatement();
					conn.setAutoCommit(false);
					stmtchange.executeUpdate(changestate);
					stmtupdate.executeUpdate(update);
					conn.commit();
					conn.setAutoCommit(true);
					JOptionPane.showMessageDialog(frm, "��Ŀ�黹�ɹ���", "����ɹ�", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("SQL���ִ��ʧ�ܣ���������λ��");
					System.out.println(e.toString());
				}
			}
		});

		frm.setVisible(true);

	}

	static void add_Book() {
		int choice = 0;
		String info[] = { "", "", "", "", "", "", "", "" };
		Scanner reader = new Scanner(System.in);

		System.out.println("��ѡ�����뷽ʽ��1.�����ֶ����� 2.�౾�ļ�����");
		choice = reader.nextInt();
		if (choice == 1) {
			System.out.println("������ ��ţ���������������磬��ݣ����ߣ��۸����� ��8��Կո�����(0ȡ������)");
			if (reader.hasNext())
				for (int i = 0; i < 8; i++) {
					if (reader.hasNext())
						info[i] = reader.next();
					else {
						System.out.println("������Ϣ���㣡����");
						return;
					}

				}
			insert_book(info);
		} else if (choice == 2) {

			String filename = null;
			String[] temp = null;
			String rec = null;
			System.out.println("�������ļ���������·����");
			Scanner reader1 = new Scanner(System.in);
			if (reader1.hasNext())
				filename = reader1.nextLine();
			filename = filename.strip();
			System.out.println(filename);
			try {
				BufferedReader in = new BufferedReader(new FileReader(filename));
				while ((rec = in.readLine()) != null) {
					rec = rec.strip();
					temp = rec.split(", ");
					for (int i = 0; i < 8; i++)
						System.out.print(temp[i] + " ");
					insert_book(temp);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("�ļ���ʧ�ܣ������ļ�����·��");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("�ļ���дʧ�ܣ������ļ������Ƿ���");
				e.printStackTrace();
			}

		}
	}

	static void insert_book(String info[]) {
		String ins = "INSERT book VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int choice;
		PreparedStatement ps = null;
		Scanner reader = new Scanner(System.in);
		String query = "SELECT * FROM book 	WHERE bno = " + info[0];
		Statement stmt;
		ResultSet rset;

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				System.out.println("�����鼮�Ѵ��ڣ�");
				int stock = rset.getInt("stock");
				int total = rset.getInt("total");
				System.out.println(rset.getString("bno") + "\t" + rset.getString("category") + "\t"
						+ rset.getString("title") + "\t" + rset.getString("press") + "\t" + rset.getInt("year") + "\t"
						+ rset.getString("author") + "\t" + rset.getDouble("price") + "\t" + total + "\t" + stock);
				System.out.println("��ѡ���Ӧ������1.����ȫ����Ϣ����桢�����ۼ� 2.�����ӿ������ 3.�����¼۸� 4.ɾ����Ŀ 0.��������");
				choice = reader.nextInt();
				switch (choice) {
				case 0:
					System.out.println("����������");
					return;
				case 1:
					String upall = "UPDATE book SET category = ?, title = ?, press = ?, year = ?, author = ?, price = ?, total = ?, stock = ? WHERE bno = ?";
					ps = conn.prepareStatement(upall);
					ps.setString(1, info[1]);
					ps.setString(2, info[2]);
					ps.setString(3, info[3]);
					ps.setInt(4, Integer.parseInt(info[4]));
					ps.setString(5, info[5]);
					ps.setDouble(6, Double.parseDouble(info[6]));
					ps.setInt(7, total + Integer.parseInt(info[7]));
					ps.setInt(8, stock + Integer.parseInt(info[7]));
					ps.setString(9, info[0]);
					ps.executeUpdate();
					System.out.println("��Ŀ��" + info[2] + "����Ϣ���ǳɹ�");
					return;
				case 2:
					String upstock = "UPDATE book SET total = ?, stock = ? WHERE bno = ?";
					ps = conn.prepareStatement(upstock);
					ps.setInt(1, total + Integer.parseInt(info[7]));
					ps.setInt(2, stock + Integer.parseInt(info[7]));
					ps.setString(3, info[0]);
					ps.executeUpdate();
					System.out.println("��Ŀ " + info[0] + " ��������޸ĳɹ�");
					return;
				case 3:
					String upprice = "UPDATE book SET price = ? WHERE bno = ?";
					ps = conn.prepareStatement(upprice);
					ps.setDouble(1, Double.parseDouble(info[6]));
					ps.setString(2, info[0]);
					ps.executeUpdate();
					System.out.println("��Ŀ " + info[0] + " �۸��޸ĳɹ�");
					return;
				case 4:
					String delete = "DELETE FROM book WHERE bno = ?";
					ps = conn.prepareStatement(delete);
					ps.setString(1, info[0]);
					ps.executeUpdate();
					System.out.println("��Ŀ " + info[0] + " ��Ϣɾ���ɹ�");
					return;
				default:
					System.out.println("����ѡ����ڣ�");
					return;
				}

			} else {
				ps = conn.prepareStatement(ins);
				ps.setString(1, info[0]);
				ps.setString(2, info[1]);
				ps.setString(3, info[2]);
				ps.setString(4, info[3]);
				ps.setInt(5, Integer.parseInt(info[4]));
				ps.setString(6, info[5]);
				ps.setDouble(7, Double.parseDouble(info[6]));
				ps.setInt(8, Integer.parseInt(info[7]));
				ps.setInt(9, Integer.parseInt(info[7]));
				ps.executeUpdate(); // ִ��sql���
				System.out.println("����Ŀ��" + info[2] + "�����ɹ�");
			}
		} catch (SQLException e1) {
			System.out.println("SQL���ִ��ʧ�ܣ��������´���:");
			System.out.println(e1.toString());
		} catch (NumberFormatException e) {
			System.out.println("���ݸ�ʽ�����֡��ַ�����ƥ�������������´���:");
			System.out.println(e.toString());
		}
		return;
	}

	static void proof_Manag() {
		System.out.println("1.ɾ������֤ 2.���ӽ���֤ 3.����֤�޸� 0.�˳�ϵͳ");
		Scanner reader = new Scanner(System.in);
		String checkcno = null;
		String cno = null;
		String name = null;
		String department = null;
		String type = null;
		switch (reader.nextInt()) {
		case 0:
			return;
		case 1:
			System.out.println("���������֤��");
			cno = reader.next();
			checkcno = "SELECT * FROM borrow WHERE cno ='" + cno + "' AND state = 'O'";
			try {
				Statement stmtcheckcno = conn.createStatement();
				ResultSet rsetcheckcno = stmtcheckcno.executeQuery(checkcno);
				if (rsetcheckcno.next()) {
					System.out.println("�˽���֤����δ�黹��Ŀ�����ȹ黹��Ŀ��ɾ������֤��");
					return;
				} else {
					checkcno = "SELECT * FROM card WHERE cno ='" + cno + "'";
					stmtcheckcno = conn.createStatement();
					rsetcheckcno = stmtcheckcno.executeQuery(checkcno);
					if (!rsetcheckcno.next()) {
						System.out.println("�˽���֤�����ڣ�");
						return;
					}
					String delete = "DELETE FROM card WHERE cno ='" + cno + "'";
					Statement stmtdelete = conn.createStatement();
					stmtdelete.executeUpdate(delete);
					System.out.println("����֤ " + cno + " ɾ���ɹ���");
					return;
				}
			} catch (SQLException e) {
				System.out.println("SQL���ִ��ʧ�ܣ���������λ��");
				System.out.println(e.toString());
				return;
			}
		case 2:
			System.out.println("��������������֤�ţ����7λ�ַ���");
			cno = reader.next();
			checkcno = "SELECT * FROM card WHERE cno ='" + cno + "'";
			try {
				Statement stmtcheckcno = conn.createStatement();
				ResultSet rsetcheckcno = stmtcheckcno.executeQuery(checkcno);
				if (rsetcheckcno.next()) {
					System.out.println("���ʧ�ܣ��˽���֤�Ѵ��ڣ�");
					return;
				} else {
					System.out.println("������ ����\tѧԺ\tְλ��ѧ��S����ʦT�� �Կո�����");
					name = reader.next();
					department = reader.next();
					type = reader.next();
					while (!type.equals("T") && !type.equals("S")) {
						System.out.println("�������������������룡");
						type = reader.next();
					}
					String addcard = "INSERT card VALUE('" + cno + "', '" + name + "', '" + department + "', '" + type
							+ "')";
					Statement stmtaddcard = conn.createStatement();
					stmtaddcard.executeUpdate(addcard);
					System.out.println("����֤ " + cno + "\t" + name + "\t" + department + "\t" + type + " ��ӳɹ���");
					return;
				}
			} catch (SQLException e) {
				System.out.println("SQL���ִ��ʧ�ܣ���������λ��");
				System.out.println(e.toString());
				return;
			}
		case 3:
			System.out.println("�������޸Ľ���֤��");
			cno = reader.next();
			checkcno = "SELECT * FROM card WHERE cno ='" + cno + "'";
			int flag = 0;
			try {
				Statement stmtcheckcno = conn.createStatement();
				ResultSet rsetcheckcno = stmtcheckcno.executeQuery(checkcno);
				if (!rsetcheckcno.next()) {
					System.out.println("�˽���֤�����ڣ�");
					return;
				} else {
					System.out.println("����֤��Ϣ��ϸ���£�");
					System.out.println(rsetcheckcno.getString("cno") + "\t" + rsetcheckcno.getString("name") + "\t"
							+ rsetcheckcno.getString("department") + "\t" + rsetcheckcno.getString("type"));
					String update = "UPDATE card SET ";
					System.out.println("�Ƿ��޸�������1.�� 0.��");
					if (reader.nextInt() == 1) {
						System.out.println("����������");
						name = reader.next();
						update = update + "name = '" + name + "'";
						flag = 1;
					}
					System.out.println("�Ƿ��޸Ĳ��ţ�1.�� 0.��");
					if (reader.nextInt() == 1) {
						System.out.println("�����벿��");
						department = reader.next();
						if (flag == 1)
							update = update + ", ";
						update = update + "department = '" + department + "'";
						flag = 1;
					}
					System.out.println("�Ƿ��޸�ְλ��1.�� 0.��");
					if (reader.nextInt() == 1) {
						if (rsetcheckcno.getString("type").equals("T"))
							type = "S";
						else
							type = "T";
						if (flag == 1)
							update = update + ", ";
						update = update + "type = '" + type + "'";
						flag = 1;
					}
					if (flag == 0) {
						System.out.println("δ������Ϣ��");
						return;
					}
					Statement stmtupdate = conn.createStatement();
					stmtupdate.executeUpdate(update);
					System.out.println("����֤��Ϣ�޸ĳɹ���");
				}
			} catch (SQLException e) {
				System.out.println("SQL���ִ��ʧ�ܣ���������λ��");
				System.out.println(e.toString());
				return;
			}
		default:
			System.out.println("����ѡ����ڣ�");
			return;
		}
	}
}
