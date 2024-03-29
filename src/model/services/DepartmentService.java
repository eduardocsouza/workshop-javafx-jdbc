package model.services;

import java.util.List;

import model.dao.DepDaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	private DepartmentDao dao = DepDaoFactory.creatorDepartmentDao();
	
	public List<Department> findAll(){
		return dao.findAll();
	}

}
