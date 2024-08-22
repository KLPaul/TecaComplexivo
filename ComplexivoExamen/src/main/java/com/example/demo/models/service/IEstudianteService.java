package com.example.demo.models.service;

import java.util.List;

import com.example.demo.models.entity.Estudiante;


public interface IEstudianteService {
	
	public List<Estudiante> findAll();
	
	public void save(Estudiante estudiante);
	
	public Estudiante findOne(Long id);
	
	public void delete(Long id);
	
	public List<Estudiante> findByNombreEstudiante(String nombreEstudiante);

}
