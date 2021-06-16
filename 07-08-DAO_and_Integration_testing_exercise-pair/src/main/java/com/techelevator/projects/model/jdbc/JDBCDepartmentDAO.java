package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCDepartmentDAO implements DepartmentDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments() {

			String sql = "SELECT name " +
					"FROM department";

			SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

			List<Department> departments = new ArrayList<Department>();
			while(rows.next()) {
				Department department = mapRowToDepartment( rows );
				departments.add( department );
			}
		return departments;
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) {

		String sql = "SELECT name " +
		"FROM department " +
		"WHERE name LIKE '*'?'*'"; // couldn't figure out how to use %s

		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, nameSearch);

		List<Department> departments = new ArrayList<Department>();
		while(rows.next()) {
			Department department = mapRowToDepartment( rows );
			departments.add( department );
		}
		return departments;
	}

	@Override
	public void saveDepartment(Department updatedDepartment) {

		String sql = "UPDATE department SET " +
				"name = ? " +
				"WHERE department_id = ?";

		jdbcTemplate.update(sql, updatedDepartment.getName(), updatedDepartment.getId());
	} // no error but doesn't update

	//public void update(Address address) {
		//String sql = "UPDATE address SET " +
		//		"line_one = ?, line_two = ?, postal_code = ?, city = ?, district = ?, " +
		//		"country = ?, type = ?, type_description = ? WHERE address_id = ?";
		//jdbcTemplate.update(sql, address.getLineOne(), address.getLineTwo(), address.getPostalCode(),
		//		address.getCity(), address.getDistrict(), address.getCountryCode(),
		//		address.getType(), address.getTypeDescription(), address.getAddressId());
	//}

	@Override
	public Department createDepartment(Department newDepartment) {
		String sql = "INSERT INTO department (department_id, name) " +
				"VALUES (DEFAULT, ?) RETURNING department_id ";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, newDepartment.getId(), newDepartment.getName());
		rows.next();
		newDepartment.setId( rows.getLong("department_id"));

		return newDepartment;
	}


	@Override
	public Department getDepartmentById(Long id) {
		String sql = "SELECT name" +
				"FROM department" +
				"WHERE department_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, id);

		Department department = null;
		if (rows.next()) {
			department = mapRowToDepartment(rows);
		}
		return department;
	}

	private Department mapRowToDepartment(SqlRowSet row) {
		Department department = new Department();

		department.setName(row.getString("name"));

		return department;
	}



}
