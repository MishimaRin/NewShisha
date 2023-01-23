package com.tunayo.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tunayo.springboot.Flavor;
import java.util.Optional;
import java.util.Iterator;
import java.util.List;

@Repository
public interface MyDataRepository extends JpaRepository<Flavor, Long>{
	public Optional<Flavor> findById(Long name);
	public List<Flavor> findByNameLike(String name);
	public List<Flavor> findByIdIsNotNullOrderByIdDesc();
	//public List<Flavor> findByIdAndName(Integer id,String name);
	public List<Flavor> findByIdAndName(Integer id,String name);
	//public List<Flavor> findByIdIsNotNullOrderByvalueDesc();
	//public List<Flavor> findByValueOrderByasc();
	public List<Flavor> findByValueGreaterThan(Integer value);
	//public List<Flavor> findByAgeBetween(Integer value1, Integer value2);
	public List<Flavor> findByGenreOrderByValueAsc(String genre);
	public List<Flavor> findByGenreOrderByValueDesc(String genre);
	
	public Flavor findById(Integer id);
	public List<Flavor> findByIdGreaterThan(Integer id);
	public Flavor findByName(String name);
	
	
}
