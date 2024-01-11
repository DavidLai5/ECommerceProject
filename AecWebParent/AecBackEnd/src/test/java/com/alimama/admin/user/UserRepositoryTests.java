package com.alimama.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.alimama.common.entity.Role;
import com.alimama.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User user1 = new User("zhaokuanglong@alimama.net", "123456", "Kuanglong", "Zhao");
		user1.addRole(roleAdmin);
		
		User savedUser = repo.save(user1);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		User user2 = new User("maniubi@alimama.net", "654321", "Niubi", "Ma");
		Role roleEditor = new Role(4);
		Role roleAssistant = new Role(6);
		
		user2.addRole(roleEditor);
		user2.addRole(roleAssistant);
		
		User savedUser = repo.save(user2);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	
	@Test
	public void testGetUserById() {
		User user1 = repo.findById(1).get();
		System.out.println(user1);
		assertThat(user1).isNotNull();
		
	}
	
	@Test
	public void testUpdateUserDetails() {
		User user1 = repo.findById(1).get();
		user1.setEnabled(true);
		user1.setEmail("zhaokuanglong@alimama.com.us");
		
		repo.save(user1);
		
	}
	
	@Test
	public void testUpdateUserRoles() {
		User user2 = repo.findById(2).get();
		Role roleEditor = new Role(4);
		Role roleSalesperson = new Role(3);
		
		user2.getRoles().remove(roleEditor);
		user2.addRole(roleSalesperson);
		
		repo.save(user2);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		repo.deleteById(userId);
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "zhaokuanglong@alimama.com.us";
		User user = repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
	}

}


