package com.example.demo.models.dao;

import java.util.List;

import com.example.demo.models.entity.Materia;



public interface IMateriaDao {
	
public List<Materia> findAll();
	
	public void save(Materia materia);
	
	public Materia findOne(Long id);
	
	public void delete(Long id);
	

}
