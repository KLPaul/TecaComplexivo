package com.example.demo.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.dao.IMateriaDao;
import com.example.demo.models.entity.Materia;

@Service
public class MateriaServiceImpl implements IMateriaService{
	
	@Autowired
	private IMateriaDao matDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Materia> findAll() {
		
		return matDao.findAll();
	}
	
	@Transactional
	@Override
	public void save(Materia materia) {
		
		matDao.save(materia);
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public Materia findOne(Long id) {
		
		return matDao.findOne(id);
	}

	@Override
	public void delete(Long id) {
		
		matDao.delete(id);
		
	}

}
