package com.tunayo.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tunayo.springboot.Flavor;

import java.util.List;

@Repository
public interface MyDataRepository4 extends JpaRepository<Flavor, Long>{
	
	 @Query(value = "SELECT * FROM flavor limit 10", nativeQuery = true) // SQL
	 
	public List<Flavor> fin();
	 
	 @Query(value = "SELECT * FROM flavor limit 10,22", nativeQuery = true) // SQL
	 
		public List<Flavor> fon();
	 
	 @Query(value = "SELECT * FROM flavor limit ?,?", nativeQuery = true) 
	 //.setParameter(1, 変数)
		//.setParameter(2, 変数);
	public Flavor test(int i, int o);
	 
	 @Query(value = "SELECT * FROM flavor where id > ?", nativeQuery = true) // SQL
	 
		public List<Flavor> fono(int i);
	
}
