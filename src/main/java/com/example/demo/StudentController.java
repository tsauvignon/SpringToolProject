package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
	
	@Autowired
	StudentService service;
	
	@GetMapping("/")
	public String home( Model m)
	
	{
		List<Student> stu = service.getAllStudents();
		
		m.addAttribute("stu",stu);
		
		return "index";
	}
	
	@GetMapping("/addStudent")
	public String addStuForm()
	{
		return "add_student";
	}
	
	@PostMapping("/register")
	public String stuRegister(@ModelAttribute Student s,HttpSession session)
	{
		System.out.println(s);
		service.addStudent(s);
		session.setAttribute("msg", "student added successfully");
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model m)
	{
		Student s = service.getStuById(id);
 
		m.addAttribute("stu", s);
		
		return "edit";
	}
	
	@PostMapping("/update")
	public String updateStu(@ModelAttribute Student s,HttpSession session)
	{
		service.addStudent(s);
		session.setAttribute("msg","student data updated successfully");
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteStu(@PathVariable int id)
	{
		service.deleteStu(id);
		return "redirect:/";
	}
}
