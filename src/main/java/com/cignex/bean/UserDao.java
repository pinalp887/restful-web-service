package com.cignex.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDao {

	private static List<User> list=new ArrayList<User>();
	private static int userCount=5;
	static {
		list.add(new User(1, "pinal", new Date()));
		list.add(new User(2, "ram", new Date()));
		list.add(new User(3, "rajesh", new Date()));
		list.add(new User(4, "rahul", new Date()));
		list.add(new User(5, "narayan", new Date()));
	}
	
	public List<User> findAll(){
		return list;
	}
	
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		list.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for (User user : list) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public void delete(int id) {
		list.removeIf(i->i.getId()==id);
	}
}
