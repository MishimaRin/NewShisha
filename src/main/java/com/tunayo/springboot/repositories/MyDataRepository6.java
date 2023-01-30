package com.tunayo.springboot.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tunayo.springboot.Flavor;
import com.tunayo.springboot.Review;

import java.util.Optional;
import java.util.Iterator;
import java.util.List;

@Repository
public interface MyDataRepository6 extends JpaRepository<Review, Long>{
	//public Optional<Review> findById(Long name);
	//public List<Review> findByNameLike(String name);
	//public List<Review> findByIdIsNotNullOrderByIdDesc();
	//public List<Flavor> findByIdAndName(Integer id,String name);
	//public List<Flavor> findByIdAndName(Integer id,String name);
	//public List<Flavor> findByIdIsNotNullOrderByvalueDesc();
	//public List<Flavor> findByValueOrderByasc();
	//public List<Flavor> findByValueGreaterThan(Integer value);
	//public List<Flavor> findByAgeBetween(Integer value1, Integer value2);
	//public List<Flavor> findByGenreOrderByValueAsc(String genre);
	//public List<Flavor> findByGenreOrderByValueDesc(String genre);
	
	//public Flavor findById(Integer id);
	//public List<Flavor> findByIdGreaterThan(Integer id);
	public Review findById(Integer id);
	public List<Review> findByName(String name);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM review WHERE id = ?", nativeQuery = true) 
	 
	public void fono(int i);
	
	@Transactional
	List<Review> deleteById(int id);
	
}
