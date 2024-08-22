package com.example.demo.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.dao.IEstudianteDao;
import com.example.demo.models.entity.Estudiante;

@Service
public class EstudianteServiceImpl implements IEstudianteService {
	
	@Autowired
	private IEstudianteDao estDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Estudiante> findAll() {
		
		return estDao.findAll();
	}
	
	@Transactional
	@Override
	public void save(Estudiante estudiante) {
		
		estDao.save(estudiante);
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public Estudiante findOne(Long id) {
		
		return estDao.findOne(id);
	}

	@Override
	public void delete(Long id) {
		
		estDao.delete(id);
		
	}

	@Override
	public List<Estudiante> findByNombreEstudiante(String nombreEstudiante) {
		
		return estDao.findByNombreEstudiante(nombreEstudiante);
	}
	
	
}
