package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
				st = conn.prepareStatement("INSERT INTO department "
						+ "(Name) "
						+ "VALUES "
						+ "(?)",
						+ Statement.RETURN_GENERATED_KEYS);
				
				st.setString(1, obj.getName());
				
				int rows = st.executeUpdate();
				if(rows > 0) {
					ResultSet rs = st.getGeneratedKeys();
					if(rs.next()) {
						int id = rs.getInt(1);
						obj.setId(id);
					
				}
				else {
						throw new DbException("Erro!");
				}
			}
		}		
		catch(SQLException e) {
			throw new DbException("erro!!");
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null; 
		try {
			st = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id= ?");
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			int rows = st.executeUpdate();
			if(rows > 0) {
				System.out.println("Update Complited!");
			}
			else {
				throw new DbException("Erro no Update");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteByld(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM department WHERE Id = ?");
			
			st.setInt(1, id);
			
			int rows = st.executeUpdate();
			if(rows > 0 ) {
				System.out.println("Delete Complited!");
			}
			else {
				throw new DbException("Id não existe");
			}
		
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Department findByld(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM department WHERE Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department dep = instatiationDepartment(rs);
				return dep;
			}
			return null;
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	private Department instatiationDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
		st = conn.prepareStatement(
				"SELECT * FROM department ORDER BY Name");
		
		rs = st.executeQuery();
		List<Department> list = new ArrayList<>();
			while(rs.next()) {
				Department obj = instatiationDepartment(rs);
				list.add(obj);				
			}
			
			return list;
		
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

}
