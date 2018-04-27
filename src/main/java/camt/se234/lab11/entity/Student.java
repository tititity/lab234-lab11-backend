package camt.se234.lab11.entity;

public class Student {
    String studentId;
    String name;
    String surName;
    Double gpa;

    public Student(String studentId, String name, String surName, Double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.surName = surName;
        this.gpa = gpa;
    }
  public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Double getGpa() {
        return gpa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (studentId != null ? !studentId.equals(student.studentId) : student.studentId != null) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (surName != null ? !surName.equals(student.surName) : student.surName != null) return false;
        return gpa != null ? gpa.equals(student.gpa) : student.gpa == null;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (gpa != null ? gpa.hashCode() : 0);
        return result;
    }
}
