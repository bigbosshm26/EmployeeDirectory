package ducthang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ducthang.entity.Login;
import ducthang.util.DBConnectionUtil;

public class LoginDAOImpl implements LoginDAO{

	@Override
	public String authenticate(Login login) {
		String sql="SELECT * FROM tbl_login WHERE email= ? and password = ?";
		try {
			Connection connection = DBConnectionUtil.openConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, login.getEmail());
			prepareStatement.setString(2, login.getPassword());
			ResultSet result = prepareStatement.executeQuery();
			if(result.next()) {
				return "true";
			}else {
				return "false";
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return "error";
	}

}
