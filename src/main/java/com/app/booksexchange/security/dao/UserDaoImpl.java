package com.app.booksexchange.security.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.app.booksexchange.entity.Users;
import com.app.booksexchange.services.UsersService;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Resource(name = "usersService")
    private UsersService userService;
    

    @SuppressWarnings("unchecked")
    @Override
    public Users findByUserName(String username) {

        List<Users> users;

        users = sessionFactory.getCurrentSession().getNamedQuery("AppUser.findByUsername").setParameter("username", username).list();

        if (users.size() < 1 || users.size() > 1) {
            return null;
        } else {
            return users.get(0);
        }
    }
    
	@Override
	public void updateFailAttempts(String username) {
		Session session = sessionFactory.getCurrentSession();
		Users users = userService.getUserByUsername(username);
		boolean locked = false;
		if (!StringUtils.isEmpty(users)) {
			session.save(users);
			if (locked)
				throw new LockedException("User Account is locked!");
		}

	}
    
    @Override
    public void resetFailAttempts(String username){
        Session session = sessionFactory.getCurrentSession();
        Users users = userService.getUserByUsername(username);
        if (!StringUtils.isEmpty(users)) {
            session.save(users);
        }    
    }
    
    @Override
    public Users getUserAttempts(String username){
    	Users users = userService.getUserByUsername(username);
        if (StringUtils.isEmpty(users)) {
            return null;
        } else {
            return users;
        }
    }

}
