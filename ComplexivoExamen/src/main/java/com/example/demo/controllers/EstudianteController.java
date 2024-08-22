package com.example.demo.controllers;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.entity.Estudiante;
import com.example.demo.models.entity.Materia;
import com.example.demo.models.service.IEstudianteService;
import com.example.demo.models.service.IMateriaService;

@Controller
@SessionAttributes("estudiante")
public class EstudianteController {
	
	@Autowired
	private IEstudianteService estService;
	
	@Autowired
	private IMateriaService matService;
	
	@RequestMapping(value="/Inicio", method=RequestMethod.GET)
	public String Inicio() {
		
		return "/layout/layout";
		
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        // Para convertir el ID de materia
        binder.registerCustomEditor(Materia.class, "materia", new PropertyEditorSupport() {
            @Override
            public void setAsText(String id) {
                if (id != null && !id.isEmpty()) {
                    setValue(matService.findOne(Long.valueOf(id)));
                } else {
                    setValue(null);
                }
            }
        });

        // Para manejar la fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam(name="nombreEstudiante", required=false) String nombreEstudiante, Model model) {
	    if (nombreEstudiante != null && !nombreEstudiante.isEmpty()) {
	        model.addAttribute("estudiantes", estService.findByNombreEstudiante(nombreEstudiante));
	    } else {
	        model.addAttribute("estudiantes", estService.findAll());
	    }
	    return "listarEstudiante";  
	}

	@RequestMapping(value="/listarEstudiante", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de Estudiantes");
		model.addAttribute("estudiantes", estService.findAll());
		
		return "listarEstudiante";
	}
	
	@RequestMapping(value="/formEstudiante")
	public String crear(Map<String, Object> model) {
		
		Estudiante estudiante = new Estudiante();
		model.put("estudiante", estudiante);
		model.put("titulo", "Abrir nuevo estudiante");
		
		model.put("materias", matService.findAll());
		return "formEstudiante";
		
	}
	
	@RequestMapping(value="/formEstudiante", method=RequestMethod.POST)
	public String guardar(Estudiante estudiante,RedirectAttributes flash ) {
		
		String mensajeFls =(estudiante.getIdEstudiante() != null)? "El registro de estudiante se ha editado con exito" : "El registro de estudiante se ha creado con exito";
		estService.save(estudiante);
		flash.addFlashAttribute("success", mensajeFls);
		return "redirect:listarEstudiante"; 
		
	}
	
	@RequestMapping(value="/formEstudiante/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Estudiante estudiante = null;
		if(id>0) {
			
			estudiante = estService.findOne(id);
			
			if(estudiante == null) {
				
				flash.addFlashAttribute("info", "El estudiante no existe en la base de datos");
				
				return "redirect:/listarEstudiante";
			}
			
		}else {
			flash.addFlashAttribute("error", "El ID del estudiante no puede ser cero");
			return "redirect:/listarEstudiante";
		}
		
		model.put("estudiante", estudiante);
		model.put("titulo", "Editar del Estudiante");
		
		return "formEstudiante";
	}
	
	@GetMapping(value="/reporte/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash ) {
		
		Estudiante estudiante = estService.findOne(id);
		
		if(estudiante == null) {
			
			flash.addFlashAttribute("error", "El estudiante no se encuentra en la base de datos");
			return "redirect:/listarEstudiante";
		}
		
		model.put("estudiante", estudiante);
		
		model.put("titulo", "Informacion del estudiante de: " +estudiante.getNombreEstudiante());
		
		model.put("asunto","Numero de Serie: " +estudiante.getIdEstudiante());
		
		model.put("cedula", estudiante.getCedula());
		
		model.put("nombreEstudiante", estudiante.getNombreEstudiante());
		
		model.put("apellido", estudiante.getApellido());
		
		model.put("materia",estudiante.getMateria().getNombreMateria());

		return "reporte";
	}
	
	@RequestMapping(value="/eliminarEstudiante/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id>0) {
			estService.delete(id);
			flash.addFlashAttribute("success", "Estudiante eliminado con exito");
		}
		
		return "redirect:/listarEstudiante";
	}

	

}
