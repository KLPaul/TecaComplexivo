package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.entity.Materia;
import com.example.demo.models.service.IMateriaService;

@Controller
@SessionAttributes("materia")
public class MateriaController {
	
	@Autowired
	private IMateriaService matService;
	
	@GetMapping("/apiMaterias")
	@ResponseBody
    public List<Materia> listarMaterias() {
        return matService.findAll();
    }
	
	@RequestMapping(value="/listarMateria", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de Materias");
		model.addAttribute("materias", matService.findAll());
		
		return "listarMateria";
	}
	
	@RequestMapping(value="/formMateria")
	public String crear(Map<String, Object> model) {
		
		Materia materia = new Materia();
		model.put("materia", materia);
		model.put("titulo", "Abrir nueva materia");
		return "formMateria";
		
	}
	
	@RequestMapping(value="/formMateria", method=RequestMethod.POST)
	public String guardar(Materia materia,RedirectAttributes flash ) {
		
		String mensajeFls =(materia.getIdMateria() != null)? "El registro de materia se ha editado con exito" : "El registro de materia se ha creado con exito";
		matService.save(materia);
		flash.addFlashAttribute("success", mensajeFls);
		return "redirect:listarMateria"; 
		
	}
	
	@RequestMapping(value="/formMateria/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Materia materia = null;
		if(id>0) {
			
			materia = matService.findOne(id);
			
			if(materia == null) {
				
				flash.addFlashAttribute("info", "La materia no existe en la base de datos");
				
				return "redirect:/listarMateria";
			}
			
		}else {
			flash.addFlashAttribute("error", "El ID de la materia no puede ser cero");
			return "redirect:/listarMateria";
		}
		
		model.put("materia", materia);
		model.put("titulo", "Editar la Materia");
		
		return "formMateria";
	}
	
	@RequestMapping(value="/eliminarMateria/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id>0) {
			matService.delete(id);
			flash.addFlashAttribute("success", "Materia eliminado con exito");
		}
		
		return "redirect:/listarMateria";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
