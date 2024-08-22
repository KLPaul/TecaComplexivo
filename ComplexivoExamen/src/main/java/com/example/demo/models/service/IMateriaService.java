package com.example.demo.models.service;

import java.util.List;

import com.example.demo.models.entity.Materia;

public interface IMateriaService {

	public List<Materia> findAll();
	
	public void save(Materia materia);
	
	public Materia findOne(Long id);
	
	public void delete(Long id);
	
}
