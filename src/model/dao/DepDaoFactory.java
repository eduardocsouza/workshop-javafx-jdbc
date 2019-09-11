package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;

public class DepDaoFactory {

	public static DepartmentDao creatorDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}

	
}
