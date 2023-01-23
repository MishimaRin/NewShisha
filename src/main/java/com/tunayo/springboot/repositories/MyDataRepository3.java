package com.tunayo.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tunayo.springboot.Flavor;
import com.tunayo.springboot.User;
import java.util.Optional;
import java.util.List;

@Repository
public interface MyDataRepository3 extends JpaRepository<User, Long>{
	public List<User> findByIdAndPass(Integer id,String pass);
	public User findById(Integer id);
	public User findByName(String name);

}
