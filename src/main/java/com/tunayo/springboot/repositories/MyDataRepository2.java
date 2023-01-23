package com.tunayo.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tunayo.springboot.Flavor;
import com.tunayo.springboot.MixFlavor;
import java.util.Optional;
import java.util.List;

@Repository
public interface MyDataRepository2 extends JpaRepository<MixFlavor, Long>{
	public Optional<MixFlavor> findById(Long name);
	public List<MixFlavor> findByNameLike(String name);
	public List<MixFlavor> findByIdIsNotNullOrderByIdDesc();
	public List<MixFlavor> findByValueGreaterThan(Integer value);
	//public List<Flavor> findByAgeBetween(Integer value1, Integer value2);
	 @Query(value = "SELECT * FROM mix_flavor limit 10", nativeQuery = true) // SQL
	 
		public List<MixFlavor> mix();
	
    public MixFlavor findById(Integer id);
    public List<MixFlavor> findByGenreOrderByValueAsc(String genre);
	public List<MixFlavor> findByGenreOrderByValueDesc(String genre); 
	
}
