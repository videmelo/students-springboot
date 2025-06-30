package br.com.studentmanagement.model;

import br.com.studentmanagement.enums.Course;
import br.com.studentmanagement.enums.Status;
import br.com.studentmanagement.enums.Shift;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(min = 5, max = 35, message = "O Name deve conter entre 5 a 35 caracteres")
    @NotBlank(message = "O name not pode ser vazio")
    @NotNull
    private String name;

    @Column(name = "registration")
    @NotNull
    @Size(min = 3, message = "É necessário Gerar o número de registration")
    private String registration;

    @Column(name = "course")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Course course;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @Column(name = "shift")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Shift shift;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}
