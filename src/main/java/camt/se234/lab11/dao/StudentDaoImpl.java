package camt.se234.lab11.dao;

import camt.se234.lab11.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    List<Student> students;
    public StudentDaoImpl(){
        students = new ArrayList<>();
        students.add(new Student("123","A","temp",2.33));
        students.add(new Student("456","B","kim",3.45));
        students.add(new Student("789","C","lee",2.76));
        students.add(new Student("012","D","ong",3.36));
        students.add(new Student("345","E","kang",4.00));

    }

    @Override
    public List<Student> findAll() {
        return this.students;
    }
}
