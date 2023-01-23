package com.tunayo.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tunayo.springboot.Composition;
import com.tunayo.springboot.Flavor;
import java.util.Optional;
import java.util.Iterator;
import java.util.List;

@Repository
public interface MyDataRepository5 extends JpaRepository<Composition, Long>{
	//public List<Composition> findByCrenameAndMixname(String crename,String mixname);
	//public Composition findById(Integer id);
	//public Composition findByCrenameAndMixname(String crename,String mixname);
	//_これの表記の方法不明
	//@Query(value = "INSERT INTO composition VALUES (?,?,?)", nativeQuery = true) // SQL
	// 
	//public void it(String mixname,String crename,String flavor);
	public List<Composition> findByMixid(int mixid);
}
