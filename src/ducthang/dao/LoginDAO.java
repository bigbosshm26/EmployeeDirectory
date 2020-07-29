package ducthang.dao;

import ducthang.entity.Login;

public interface LoginDAO {
	String authenticate(Login login);
}
