package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MuttersDAO {

	//データベースに接続する情報
		private final String JDBC_URL ="jdbc:h2:tcp://localhost/~/dokoTsubu";
		private final String DB_USER = "sa";
		private final String DB_PASS = "";

		public List<Mutter> findAll() {
			List<Mutter> mutterList = new ArrayList<>();
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
				String sql = "SELECT ID,NAME,TEXT FROM MUTTERS ORDER BY ID DESC";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				//SELECTを実行し、結果表を取得
				ResultSet rs = pStmt.executeQuery();
				
				//結果表に格納されたレコードの内容をArrayListに格納
				while (rs.next()) {
					int id = rs.getInt("ID");
					String userName = rs.getString("NAME");
					String text =rs.getString("TEXT");
					Mutter mutter = new Mutter(id,userName,text);
					mutterList.add(mutter);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return mutterList;
		}
		public void create(Mutter mutter) {
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
						String sql = "INSERT INTO MUTTERS(NAME,TEXT) VALUES(?,?)";
						PreparedStatement pStmt = conn.prepareStatement(sql);
						
						//INSERT文中の[?}に使用する値を設定してSQL文を完成
						pStmt.setString(1, mutter.getUserName());
						pStmt.setString(2, mutter.getText());

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

}
