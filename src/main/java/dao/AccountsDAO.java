package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.User;

public class AccountsDAO {
	//データベースに接続する情報
	private final String JDBC_URL ="jdbc:h2:tcp://localhost/~/dokoTsubu";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public Account findByLogin(User user) {
		Account account = null;
		// JBDCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException
			("JBDCドライバを読み込めませんでした");
		}
		//データベースに接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//SELECT文を準備
			String sql = "SELECT USER_ID,PASS,MAIL,NAME,AGE FROM ACCOUNTS WHERE USER_ID = ? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getUserId());
			pStmt.setString(2, user.getPass());
			
			//SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			//ユーザーが存在したらデータを取得
			//そのユーザーを表すAccountインスタンスを生成
			while (rs.next()) {
				String userId = rs.getString("USER_ID");
				String pass =rs.getString("PASS");
				String mail =rs.getString("MAIL");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				account = new Account(userId,pass,mail,name,age);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}
	public void create(Account account) {
		// JBDCドライバを読み込む
					try {
						Class.forName("org.h2.Driver");
					} catch (ClassNotFoundException e) {
						throw new IllegalStateException(
								"JBDCドライバを読み込めませんでした");
					}
					//データベースに接続
					try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
						
						//INSERT文を準備
						String sql = "INSERT INTO ACCOUNTS(USER_ID,PASS,MAIL,NAME,AGE) VALUES(?,?,?,?,?)";
						PreparedStatement pStmt = conn.prepareStatement(sql);
						
						//INSERT文中の[?}に使用する値を設定してSQL文を完成
						pStmt.setString(1, account.getUserId());
						pStmt.setString(2, account.getPass());
						pStmt.setString(3, account.getMail());
						pStmt.setString(4, account.getName());
						pStmt.setInt(5, account.getAge());
						//INSERT文を実行（resultには追加された行数が代入される）
						int result = pStmt.executeUpdate();
//						if (result != 1) {
//							return false;
//						}
	    }catch (SQLException e) {
			e.printStackTrace();
//			return false;
		}
//		return true;
	}
	public Account findByUserId(Account account) {
//		Account account = null;
		// JBDCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException
			("JBDCドライバを読み込めませんでした");
		}
		//データベースに接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//SELECT文を準備
			String sql = "SELECT USER_ID,PASS,MAIL,NAME,AGE FROM ACCOUNTS WHERE USER_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getUserId());
			
			//SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			//ユーザーが存在したらデータを取得
			//そのユーザーを表すAccountインスタンスを生成
			if(rs.next()) {
				String userId = rs.getString("USER_ID");
				String pass =rs.getString("PASS");
				String mail =rs.getString("MAIL");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				account = new Account(userId,pass,mail,name,age);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}
}
