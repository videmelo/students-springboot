package br.com.studentmanagement.controller;

import br.com.studentmanagement.repository.StudentRepository;
import br.com.studentmanagement.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/addStudents")
    public ModelAndView InsertStudents(Student student) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Student/formStudent");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("InsertStudents")
    public ModelAndView addStudent(@Valid Student student, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("Student/formStudent");
            modelAndView.addObject("student");
        } else {
        modelAndView.setViewName("redirect:/students-added");
        studentRepository.save(student);
        }
        return modelAndView;
    }

    @GetMapping("students-added")
    public ModelAndView listagemStudents() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Student/listStudents");
        modelAndView.addObject("studentsList", studentRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Student/edit");
        Student student = studentRepository.getById(id);
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView edit(Student student) {
        ModelAndView modelAndView = new ModelAndView();
        studentRepository.save(student);
        modelAndView.setViewName("redirect:/students-added");
        return modelAndView;
    }

    @GetMapping("/remove/{id}")
    public String removeStudent(@PathVariable("id") Long id) {
        studentRepository.deleteById(id);
        return "redirect:/students-added";
    }

    @GetMapping("filter-students")
    public ModelAndView filterStudents() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Student/filterStudents");
        return modelAndView;
    }

    @GetMapping("students-active")
    public ModelAndView listaStudentsActives() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Student/students-active");
        modelAndView.addObject("studentsActives", studentRepository.findByStatusActive());
        return modelAndView;
    }

    @GetMapping("students-inactive")
    public ModelAndView listaStudentsInactives() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Student/students-inactive");
        modelAndView.addObject("studentsInactives", studentRepository.findByStatusInactive());
        return modelAndView;
    }

    @PostMapping("/searchr-student")
    public ModelAndView searchrStudent(@RequestParam(required = false) String name) {
        ModelAndView modelAndView = new ModelAndView();
        List<Student> listaStudents;
        if(name == null || name.trim().isEmpty()) {
            listaStudents = studentRepository.findAll();
        } else {
            listaStudents = studentRepository.findByNameContainingIgnoreCase(name);
        }
        modelAndView.addObject("ListaDeStudents", listaStudents);
        modelAndView.setViewName("Student/search-resultado");
        return modelAndView;
    }
}
