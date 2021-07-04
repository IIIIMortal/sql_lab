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

public class JDBClibrary {

	static Connection conn;

	public static void main(String args[]) {

		start("haoge", "13570958266_He");
	}

	public static void start(String userid, String passwd) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylibrary?characterEncoding=utf8&useSSL=true",
					userid, passwd);

			System.out.println("********************************************************");
			System.out.println("\t\t\tͼ�����ϵͳ");
			System.out.println("********************************************************");

			Scanner reader = new Scanner(System.in);

			while (true) {
				System.out.println("1.ͼ���ѯ 2.���� 3.���� 4.ͼ������� 5.����֤���� 0.�˳�ϵͳ");
				System.out.println("��������Ҫ�ķ�����:");
				int choice = reader.nextInt();
				switch (choice) {
				case 0:
					conn.close();
					return;
				case 1:
					check_Book();
					break;
				case 2:
					borrow_Book();
					break;
				case 3:
					return_Book();
					break;
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

	static void check_Book() throws SQLException {
		String query;// ��ѯ���
		int choice = 0;// ����û�ѡ��
		PreparedStatement ps = null;

		Scanner reader = new Scanner(System.in);

		while (true) {
			System.out.println("1.��ѯȫ�� 2.�����Բ�ѯ 0.�˳�");
			System.out.println("��������Ҫ�ķ�����");
			choice = reader.nextInt();
			switch (choice) {
			case 0:
				return;
			case 1:
				// ִ��SQL���
				query = "SELECT * FROM book";

				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery(query);

				System.out.println("��ѯ�������");
				System.out.println("********************************************************************************");
				System.out.println("���\t���\t����\t������\t���\t����\t�۸�\t�ܲ�����\t���");
				System.out.println("********************************************************************************");
				while (rset.next()) {
					System.out.println(rset.getString("bno") + "\t" + rset.getString("category") + "\t"
							+ rset.getString("title") + "\t" + rset.getString("press") + "\t" + rset.getInt("year")
							+ "\t" + rset.getString("author") + "\t" + rset.getDouble("price") + "\t"
							+ rset.getInt("total") + "\t" + rset.getInt("stock"));
				}
				stmt.close();
				break;

			case 2:
				int option;
				String temp = null, act = "";

				// 0 the number, 1 bno, 2 category, 3 name, 4 press, 5 year, 6 author
				String search = "SELECT * FROM book WHERE ";
				System.out.println("�Ƿ�ʹ�����������1.�� 0.��");
				if (reader.nextInt() == 1) {

					System.out.println("�������ѯ���");
					if (reader.hasNext())
						temp = reader.next();
					temp = temp.strip();
					search = search + act + "bno = " + temp;
					act = " and ";
				}

				System.out.println("�Ƿ�ʹ�����������1.�� 0.��");
				if (reader.nextInt() == 1) {
					System.out.println("�������ѯ���");
					if (reader.hasNext())
						temp = reader.next();
					temp = temp.strip();
					search = search + act + "category = '" + temp + "'";
					act = " and ";
				}

				System.out.println("�Ƿ�ʹ������������1.�� 0.��");
				if (reader.nextInt() == 1) {
					System.out.println("�������ѯ����");
					if (reader.hasNext())
						temp = reader.next();
					temp = temp.strip();
					search = search + act + "title = '" + temp + "'";
					act = " and ";
				}

				System.out.println("�Ƿ�ʹ�ó�����������1.�� 0.��");
				if (reader.nextInt() == 1) {
					System.out.println("�������ѯ������");
					if (reader.hasNext())
						temp = reader.next();
					temp = temp.strip();
					search = search + act + "press ='" + temp + "'";
					act = " and ";
				}

				System.out.println("�Ƿ�ʹ�����������1.�� 0.��");
				if (reader.nextInt() == 1) {
					System.out.println("��ѡ���ѯ��ѯ��ʽ��1.���ڵ��� 2.С�ڵ��� 3.����");
					switch (reader.nextInt()) {
					case 1:
						System.out.println("���������");
						if (reader.hasNext())
							temp = reader.next();
						temp = temp.strip();
						search = search + act + "year >= " + temp;
						act = " and ";
						break;
					case 2:
						System.out.println("���������");
						if (reader.hasNext())
							temp = reader.next();
						temp = temp.strip();
						search = search + act + "year <= " + temp;
						act = " and ";
						break;
					case 3:
						System.out.println("���������");
						if (reader.hasNext())
							temp = reader.next();
						temp = temp.strip();
						search = search + "year = " + temp;
						act = " and ";
						break;
					default:
						System.out.println("����ѡ�ȡ���������");
						ps.setString(5, null);
					}
				}

				System.out.println("�Ƿ�ʹ������������1.�� 0.��");
				if (reader.nextInt() == 1) {
					System.out.println("�������ѯ����");
					if (reader.hasNext())
						temp = reader.next();
					temp = temp.strip();
					search = search + act + "author = '" + temp + "'";
					act = " and ";
				}
				System.out.println(search);

				Statement stmt1 = conn.createStatement();
				ResultSet searchres = stmt1.executeQuery(search);

				System.out.println("��ѯ�������");
				System.out.println("********************************************************************************");
				System.out.println("���\t���\t����\t������\t���\t����\t�۸�\t�ܲ�����\t���");
				System.out.println("********************************************************************************");
				while (searchres.next()) {
					System.out.println(searchres.getString("bno") + "\t" + searchres.getString("category") + "\t"
							+ searchres.getString("title") + "\t" + searchres.getString("press") + "\t"
							+ searchres.getInt("year") + "\t" + searchres.getString("author") + "\t"
							+ searchres.getDouble("price") + "\t" + searchres.getInt("total") + "\t"
							+ searchres.getInt("stock"));
				}
				break;

			default:
				System.out.println("����ѡ����ڣ�");

			}
		}
	}

	static void borrow_Book() throws SQLException {
		String cno = null;
		String bno = null;
		String checkcno = "SELECT cno, type FROM card WHERE cno = ";
		String checkbno = "SELECT bno, stock FROM book WHERE bno = ";
		String query = "SELECT state, borrow_date FROM borrow where ";
		Scanner reader = new Scanner(System.in);
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHH");
		String time = ft.format(dNow);
		String Rtime = null;

		System.out.println("���������֤���(0�˳�ϵͳ)");
		cno = reader.next();
		cno = cno.strip();
		if (cno.equals("0")) {
			System.out.println("ȡ������");
			return;
		}
		checkcno = checkcno + "'" + cno + "'";
		Statement stmtcno;
		try {
			stmtcno = conn.createStatement();
			ResultSet rsetcno = stmtcno.executeQuery(checkcno);
			if (!rsetcno.next()) {
				System.out.println("�����ڸý���֤��");
				return;
			} else {
				long t;
				if (rsetcno.getString("type").equals("T"))
					t = dNow.getTime() + 90L * 24 * 60 * 60 * 1000;
				else
					t = dNow.getTime() + 60L * 24 * 60 * 60 * 1000;
				Rtime = ft.format((new Date(t)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL���ִ��ʧ�ܣ���������λ��");
			System.out.println(e.toString());
			return;
		}

		System.out.println("���������(0�˳�ϵͳ)");
		bno = reader.next();
		bno = bno.strip();
		if (bno.equals("0")) {
			System.out.println("ȡ������");
			return;
		}

		checkbno = checkbno + "'" + bno + "'";
		int stock;
		try {
			Statement stmtbno = conn.createStatement();
			ResultSet rsetbno = stmtbno.executeQuery(checkbno);
			if (!rsetbno.next()) {
				System.out.println("�����ڸ���Ŀ��");
				return;
			}
			stock = rsetbno.getInt("stock");
			if (stock == 0) {
				System.out.println("����Ŀ��ȫ�������");
				Statement stmtquery = conn.createStatement();
				ResultSet rsetquery = stmtquery
						.executeQuery("SELECT min(return_date) FROM borrow where bno='" + bno + "'");
				rsetquery.next();
				System.out.println("����黹����Ϊ��" + rsetquery.getInt("min(return_date)"));
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL���ִ��ʧ�ܣ���������λ��");
			System.out.println(e.toString());
			return;
		}
		query = query + "bno = " + bno + " and cno = " + cno;
		try {
			Statement stmtquery = conn.createStatement();
			ResultSet rsetquery = stmtquery.executeQuery(query);
			while (rsetquery.next()) {
				if (rsetquery.getString("state").equals("O")) {
					System.out.println("����ʧ�ܣ����ѽ��Ĵ���");
					return;
				}
				if (rsetquery.getInt("borrow_date") == Integer.parseInt(time)) {
					System.out.println("�����Ĵ�������Ƶ������һСʱ���ٴγ���");
					return;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL���ִ��ʧ�ܣ���������λ��");
			System.out.println(e.toString());
		}

		String insert = "INSERT borrow VALUE( '" + cno + "', '" + bno + "', " + time + ", " + Rtime + ", 'O')";
		try {

			Statement stmtinsert = conn.createStatement();
			conn.setAutoCommit(false);
			stmtinsert.executeUpdate(insert);
			String update = "UPDATE book SET stock = " + Integer.toString(stock - 1) + " WHERE bno = '" + bno + "'";
			stmtinsert.executeUpdate(update);
			conn.commit();
			conn.setAutoCommit(true);
			System.out.println("��Ŀ���ĳɹ���");

		} catch (SQLException e) {
			System.out.println("SQL���ִ��ʧ�ܣ���������λ��");
			System.out.println(e.toString());
			conn.rollback();
			return;
		}

	}

	static void return_Book() throws SQLException {
		System.out.println("���������֤���(0�˳�ϵͳ)");
		String checkcno = "SELECT cno FROM card WHERE cno =";
		String cno = null;
		Scanner reader = new Scanner(System.in);
		String checkbor = "SELECT * FROM borrow WHERE cno ='";
		String bno = null;
		int stock = 0;
		cno = reader.next();
		if (cno.equals('0'))
			return;
		checkcno = checkcno + cno;

		try {
			Statement stmtcno;
			stmtcno = conn.createStatement();
			ResultSet rsetcno = stmtcno.executeQuery(checkcno);
			if (!rsetcno.next()) {
				System.out.println("����֤�����ڣ�");
				return;
			}
			checkbor = checkbor + cno + "' AND state = 'O'";
			Statement stmtbor;
			stmtbor = conn.createStatement();
			ResultSet rsetbor = stmtbor.executeQuery(checkbor);
			if (!rsetbor.next()) {
				System.out.println("�˽���֤�޴����鼮��");
				return;
			} else {
				System.out.println("�ý���֤������Ŀ����");
				System.out.println("********************************************************************************");
				System.out.println("����֤��\t���\t����ʱ��\t\tӦ��ʱ��\t\t״̬");
				System.out.println("********************************************************************************");
				do {
					System.out.println(rsetbor.getString("cno") + "\t" + rsetbor.getString("bno") + "\t"
							+ +rsetbor.getInt("borrow_date") + "\t" + rsetbor.getInt("return_date") + "\t"
							+ rsetbor.getString("state"));
				} while (rsetbor.next());
			}
			System.out.println("���������(0�˳�ϵͳ)");
			bno = reader.next();
			if (bno.equals('0'))
				return;
			String checkbno = "SELECT * FROM borrow WHERE cno ='" + cno + "' AND bno ='" + bno + "' AND state ='O'";
			Statement stmtbno;
			stmtbno = conn.createStatement();
			ResultSet rsetbno = stmtcno.executeQuery(checkbno);
			if (!rsetbno.next()) {
				System.out.println("����Ŀδ���˽���֤�����");
				return;
			} else {
				String findstock = "SELECT stock FROM book WHERE bno ='" + bno + "'";
				Statement stmtstock = conn.createStatement();
				ResultSet rsetstock = stmtstock.executeQuery(findstock);
				if (rsetstock.next())
					stock = rsetstock.getInt("stock");
			}

		} catch (SQLException e) {
			System.out.println("SQL���ִ��ʧ�ܣ���������λ��");
			System.out.println(e.toString());
			return;
		}

		try {
			String changestate = "UPDATE borrow SET state = 'I' WHERE cno ='" + cno + "' AND bno ='" + bno + "'";
			String update = "UPDATE book SET stock = " + Integer.toString(stock + 1) + " WHERE bno = '" + bno + "'";
			Statement stmtupdate = conn.createStatement();
			Statement stmtchange = conn.createStatement();
			conn.setAutoCommit(false);
			stmtchange.executeUpdate(changestate);
			stmtupdate.executeUpdate(update);
			conn.commit();
			conn.setAutoCommit(true);
			System.out.println("��Ŀ�黹�ɹ���");
		} catch (SQLException e) {
			System.out.println("SQL���ִ��ʧ�ܣ���������λ��");
			System.out.println(e.toString());
			conn.rollback();
			return;
		}

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
		switch(reader.nextInt())
		{
		case 0:
			return;
		case 1:
			System.out.println("���������֤��");
			cno = reader.next();
			checkcno = "SELECT * FROM borrow WHERE cno ='"+cno+"' AND state = 'O'";			
			try {
				Statement stmtcheckcno = conn.createStatement();
				ResultSet rsetcheckcno = stmtcheckcno.executeQuery(checkcno);
				if(rsetcheckcno.next())
				{
					System.out.println("�˽���֤����δ�黹��Ŀ�����ȹ黹��Ŀ��ɾ������֤��");
					return;
				}				
				else
				{
					checkcno = "SELECT * FROM card WHERE cno ='"+cno+"'";		
					stmtcheckcno = conn.createStatement();
					rsetcheckcno = stmtcheckcno.executeQuery(checkcno);
					if(!rsetcheckcno.next())
					{
						System.out.println("�˽���֤�����ڣ�");
						return;
					}
					String delete = "DELETE FROM card where cno ='"+cno+"'";
					Statement stmtdelete = conn.createStatement();
					stmtdelete.executeUpdate(delete);
					System.out.println("����֤ "+cno+" ɾ���ɹ���");
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
			checkcno = "SELECT * FROM card WHERE cno ='"+cno+"'";					
			try {
				Statement stmtcheckcno = conn.createStatement();
				ResultSet rsetcheckcno = stmtcheckcno.executeQuery(checkcno);
				if(rsetcheckcno.next())
				{
					System.out.println("���ʧ�ܣ��˽���֤�Ѵ��ڣ�");
					return;
				}
				else
				{
					System.out.println("������ ����\tѧԺ\tְλ��ѧ��S����ʦT�� �Կո�����");		
					name = reader.next();
					department = reader.next();
					type = reader.next();
					while(!type.equals("T") && !type.equals("S"))
					{
						System.out.println("�������������������룡");	
						type = reader.next();
					}
					String addcard = "INSERT card VALUE('"+cno+"', '"+name+"', '"+department+"', '"+type+"')";					
					Statement stmtaddcard = conn.createStatement();
					stmtaddcard.executeUpdate(addcard);
					System.out.println("����֤ "+cno+"\t"+name+"\t"+department+"\t"+type+" ��ӳɹ���");	
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
			checkcno = "SELECT * FROM card WHERE cno ='"+cno+"'";	
			int flag = 0;
			try {
				Statement stmtcheckcno = conn.createStatement();
				ResultSet rsetcheckcno = stmtcheckcno.executeQuery(checkcno);
				if(!rsetcheckcno.next())
				{
					System.out.println("�˽���֤�����ڣ�");
					return;
				}
				else
				{
					System.out.println("����֤��Ϣ��ϸ���£�");
					System.out.println(rsetcheckcno.getString("cno")+"\t"+rsetcheckcno.getString("name")+"\t"+rsetcheckcno.getString("department")+"\t"+rsetcheckcno.getString("type"));
					String update = "UPDATE card SET ";
					System.out.println("�Ƿ��޸�������1.�� 0.��");
					if(reader.nextInt()==1)
					{
						System.out.println("����������");
						name = reader.next();
						update = update + "name = '"+name+"'";
						flag = 1;
					}			
					System.out.println("�Ƿ��޸Ĳ��ţ�1.�� 0.��");
					if(reader.nextInt()==1)
					{
						System.out.println("�����벿��");
						department = reader.next();
						if(flag==1)
							update = update + ", ";
						update = update + "department = '"+department+"'";
						flag = 1;
					}
					System.out.println("�Ƿ��޸�ְλ��1.�� 0.��");
					if(reader.nextInt()==1)
					{
						if(rsetcheckcno.getString("type").equals("T"))
							type = "S";
						else
							type = "T";
						if(flag==1)
							update = update + ", ";
						update = update + "type = '"+type+"'";		
						flag = 1;
					}	
					if(flag == 0)
					{
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
