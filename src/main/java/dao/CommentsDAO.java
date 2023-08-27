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

import model.Comment;

public class CommentsDAO {

	//データベースに接続する情報
		private final String JDBC_URL ="jdbc:h2:tcp://localhost/~/dokoTsubu";
		private final String DB_USER = "sa";
		private final String DB_PASS = "";

		public List<Comment> find(int targetId) {
			List<Comment> commentList = new ArrayList<>();
			
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
				String sql = "SELECT C.ID as ID,C.MUTTERS_ID as MUTTERS_ID,C.USER_ID as USER_ID,C.COMMENT as COMMENT,A.NAME as NAME FROM COMMENTS C JOIN ACCOUNTS A ON C.USER_ID = A.USER_ID WHERE C.MUTTERS_ID = ? ORDER BY C.ID DESC";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				//SELECT文中の[?}に使用する値を設定してSQL文を完成
				pStmt.setInt(1, targetId);
								
				//SELECTを実行し、結果表を取得
				ResultSet rs = pStmt.executeQuery();
				
						
				//結果表に格納されたレコードの内容をArrayListに格納
				while (rs.next()) {
					int id = rs.getInt("ID");
					int mutterId = rs.getInt("MUTTERS_ID");
					String userId =rs.getString("USER_ID");
					String name =rs.getString("NAME");
					String comment =rs.getString("COMMENT");
					Comment cmt = new Comment(id,mutterId,userId,name,comment);
					commentList.add(cmt);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return commentList;
		}


		public void create(Comment comment) {
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
						String sql = "INSERT INTO COMMENTS(MUTTERS_ID,USER_ID,COMMENT) VALUES(?,?,?)";
						PreparedStatement pStmt = conn.prepareStatement(sql);
						
						//INSERT文中の[?}に使用する値を設定してSQL文を完成
						pStmt.setInt(1, comment.getMutterId());
						pStmt.setString(2, comment.getUserId());
						pStmt.setString(3, comment.getComment());
						
						//INSERT文を実行（resultには追加された行数が代入される）
						int result = pStmt.executeUpdate();

	    }catch (SQLException e) {
			e.printStackTrace(); }
	}
		public Map<Integer,Integer> countCmt() {
			Map<Integer,Integer> comments = new HashMap<>();
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
				String sql = "SELECT MUTTERS_ID,COUNT(*) as CNT FROM COMMENTS GROUP BY MUTTERS_ID";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				
				//SELECTを実行し、結果表を取得
				ResultSet rs = pStmt.executeQuery();
				
				//結果表に格納されたレコードの内容をHashMapに格納
				while (rs.next()) {
					int mutterId = rs.getInt("MUTTERS_ID");
					cnt = rs.getInt("CNT");
					comments.put(mutterId,cnt);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return comments;
		}	
}
