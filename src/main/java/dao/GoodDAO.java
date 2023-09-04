package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Good;

public class GoodDAO {
		//データベースに接続する情報
		private final String JDBC_URL ="jdbc:h2:tcp://localhost/~/dokoTsubu";
		private final String DB_USER = "sa";
		private final String DB_PASS = "";
		
	public List<Good> find(int targetId) {
		List<Good> goodList = new ArrayList<>();
		
		// JBDCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JBDCドライバを読み込めませんでした");
		}
		//データベースに接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//SELECT文を準備
			String sql = "SELECT G.MUTTERS_ID as MUTTERS_ID,G.USER_ID as USER_ID,A.NAME as NAME FROM GOOD G JOIN ACCOUNTS A ON G.USER_ID = A.USER_ID WHERE G.MUTTERS_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SELECT文中の[?}に使用する値を設定してSQL文を完成
			pStmt.setInt(1, targetId);
							
			//SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
					
			//結果表に格納されたレコードの内容をArrayListに格納
			while (rs.next()) {
				int mutterId = rs.getInt("MUTTERS_ID");
				String userId =rs.getString("USER_ID");
				String name =rs.getString("NAME");
				Good good = new Good(mutterId,userId,name);
				goodList.add(good);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return goodList;
	}


	public void create(Good good) {
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
					String sql = "INSERT INTO GOOD(MUTTERS_ID,USER_ID) VALUES(?,?)";
					PreparedStatement pStmt = conn.prepareStatement(sql);
					
					//INSERT文中の[?}に使用する値を設定してSQL文を完成
					pStmt.setInt(1, good.getMutterId());
					pStmt.setString(2, good.getUserId());
					
					//INSERT文を実行（resultには追加された行数が代入される）
					int result = pStmt.executeUpdate();

    }catch (SQLException e) {
		e.printStackTrace(); }
}
	public Map<Integer,Integer> countGood() {
		Map<Integer,Integer> good = new HashMap<>();
		int cnt = 0;
		
		// JBDCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JBDCドライバを読み込めませんでした");
		}
		//データベースに接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//SELECT文を準備
			String sql = "SELECT MUTTERS_ID,COUNT(*) as CNT FROM GOOD GROUP BY MUTTERS_ID";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			
			//SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			//結果表に格納されたレコードの内容をHashMapに格納
			while (rs.next()) {
				int mutterId = rs.getInt("MUTTERS_ID");
				cnt = rs.getInt("CNT");
				good.put(mutterId,cnt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return good;
	}	
	public Map<Integer, Integer> countPush(String userId) {
		Map<Integer,Integer> push = new HashMap<>();
		int cnt = 0;
// JBDCドライバを読み込む
			try {
				Class.forName("org.h2.Driver");
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException(
						"JBDCドライバを読み込めませんでした");
			}
			//データベースに接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
				
				//SELECT文を準備
				String sql = "SELECT MUTTERS_ID, COUNT(*) as CNT FROM GOOD  WHERE USER_ID = ? GROUP BY MUTTERS_ID";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				//SELECT文中の[?}に使用する値を設定してSQL文を完成
				pStmt.setString(1, userId);
				
				//SELECT文を実行
				ResultSet rs = pStmt.executeQuery();
				
				//ユーザーが存在したらtrueが返る
				while(rs.next()) {
					int mutterId = rs.getInt("MUTTERS_ID");
					cnt = rs.getInt("CNT");
					push.put(mutterId,cnt);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return push;
		}

}