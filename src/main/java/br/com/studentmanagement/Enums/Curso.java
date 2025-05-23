package br.com.studentmanagement.Enums;

public enum Course {

    ADMINISTRACAO("Administração"),
    INFORMATICA("Informática"),
    CONTABILIDADE("Contabilidade"),
    ENFERMAGEM("Enfermagem"),
    BIOMEDICINA("Biomedicina"),
    DIREITO("Direito");

    private String course;

    private Course(String course) {
        this.course = course;
    }

}
