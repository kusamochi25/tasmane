package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ToDo;


public class ToDoDAO{
	
	private final String JDBC_URL ="jdbc:mySQL://localhost/Tasmane";
	private final String USER ="root";
	private final String PASS ="pass";
	
	public List<ToDo> findAll(){
		List<ToDo> todoList = new ArrayList<>();
		
		//db接続
		try( Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS)){
			String sql = "SELECT task_id, task, exp, registered_date, mark FROM tasmane ORDER BY registered_date";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("task_id");
				String task = rs.getString("task");
				String exp = rs.getString("exp");
				Date rd = rs.getDate("registered_date");
				
				int mark = rs.getInt("mark");
				
				ToDo td = new ToDo(id, task, mark, exp, rd);
				todoList.add(td);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return todoList;
	}
	
	public ToDo find(int pkNum) {
		
		try ( Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS)) {
			String sql = "SELECT task_id, task, exp, registered_date, mark FROM tasmane WHERE task_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, pkNum);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			ToDo td = new ToDo();
			td.setTask_id(rs.getInt("task_id"));
			td.setTask(rs.getString("task"));
			td.setExp(rs.getString("exp"));
			td.setRegistered_date(rs.getDate("registered_date"));
			td.setMark(rs.getInt("mark"));
			
			return td;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean create(ToDo td) {
		
		try ( Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS)) {
			String sql = "INSERT INTO tasmane(task, exp) VALUES(?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, td.getTask());
			ps.setString(2, td.getExp());
			
			int result = ps.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void delete(int gotId) {
		try( Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS)){
			String sql = "DELETE FROM tasmane WHERE task_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, gotId);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(int id, String task, String exp, Date d) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS)) {
			StringBuilder sql = new StringBuilder( "UPDATE tasmane SET " );

			if (task != null) {
				sql.append("task = '");
				sql.append(task);
				
				sql.append("' ");
				if (exp != null || d != null)
				sql.append(",");
			}
			
			if (exp != null) {
				sql.append("exp = '");
				sql.append(exp);
				sql.append("' ");
				if (d != null)
				sql.append(",");
			}
			
			if (d != null) {
				sql.append("registered_date = '");
				sql.append(d);
				sql.append("' ");
			}
			
			sql.append("WHERE task_id = ?");
			
			String stsql = sql.toString();
			PreparedStatement ps = con.prepareStatement(stsql);
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
