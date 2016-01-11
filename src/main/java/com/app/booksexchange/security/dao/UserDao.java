package com.app.booksexchange.security.dao;

import com.app.booksexchange.entity.Users;

public interface UserDao {

	Users findByUserName(String username);

	Users getUserAttempts(String username);

	void updateFailAttempts(String username);

	void resetFailAttempts(String username);
}