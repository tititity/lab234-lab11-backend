package camt.se234.lab11.service;

import camt.se234.lab11.dao.StudentDao;
import camt.se234.lab11.dao.StudentDaoImpl;
import camt.se234.lab11.entity.Student;
import org.hamcrest.number.IsCloseTo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class StudentServiceImplTest {
    StudentDao studentDao;
    StudentServiceImpl studentService;

    @Before
    public void setup(){
        studentDao = mock(StudentDao.class);
        studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
    }
    @Test
    public void testFindById(){
        //not a mock object
        StudentDaoImpl studentDao = new StudentDaoImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
        assertThat(studentService.findStudentById("123"),is(new Student("123","A","temp",2.33)));
        assertThat(studentService.findStudentById("456"),is(new Student("456","B","kim",3.45)));
        assertThat(studentService.findStudentById("789"),is(new Student("789","C","lee",2.76)));
        assertThat(studentService.findStudentById("012"),is(new Student("012","D","ong",3.36)));
    }

    @Test
    public void getAverageGpa(){
        //not mock object
//        StudentDaoImpl studentDao = new StudentDaoImpl();
//        StudentServiceImpl studentService = new StudentServiceImpl();
//        studentService.setStudentDao(studentDao);
//        assertThat(studentService.getAverageGpa(), is(closeTo(3.18, 0.003)));
        //assertThat(studentService.getAverageGpa(), is(4.00));

        //using mock objects
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("121","AAA","temp", 2.33));
        mockStudents.add(new Student("122","BBB","temp", 2.63));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.getAverageGpa(), is(closeTo(2.48, 0.003)));

        List<Student> mockStudents1 = new ArrayList<>();
        mockStudents1.add(new Student("123","CCC","temp", 4.00));
        mockStudents1.add(new Student("124","DDD","temp", 3.26));
        mockStudents1.add(new Student("125","EEE","temp", 2.58));
        when(studentDao.findAll()).thenReturn(mockStudents1);
        assertThat(studentService.getAverageGpa(), is(closeTo(3.28, 0.003)));

        List<Student> mockStudents2 = new ArrayList<>();
        mockStudents2.add(new Student("124","DDD","temp", 3.26));
        mockStudents2.add(new Student("125","EEE","temp", 2.58));
        when(studentDao.findAll()).thenReturn(mockStudents2);
        assertThat(studentService.getAverageGpa(), is(closeTo(2.92, 0.003)));
    }

    @Test
    public void testWithMock(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("123","A","temp", 2.33));
        mockStudents.add(new Student("124","B","temp", 2.33));
        mockStudents.add(new Student("223","C","temp", 2.33));
        mockStudents.add(new Student("224","D","temp", 2.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentById("123"),is(new Student("123","A","temp",2.33)));

        //create another test, using your own data
        List<Student> mockStudent1 = new ArrayList<>();
        mockStudent1.add(new Student("001","AA","temp",2.23));
        mockStudent1.add(new Student("002","BB","temp",2.23));
        mockStudent1.add(new Student("003","CC","temp",2.23));
        mockStudent1.add(new Student("004","DD","temp",2.23));
        when(studentDao.findAll()).thenReturn(mockStudent1);
        assertThat(studentService.findStudentById("003"),is(new Student("003","CC","temp",2.23)));
    }

    @Test
    public void testFindByPartOfId(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("123","A","temp", 2.33));
        mockStudents.add(new Student("124","B","temp", 2.33));
        mockStudents.add(new Student("223","C","temp", 2.33));
        mockStudents.add(new Student("224","D","temp", 2.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentByPartOfId("22"),hasItem(new Student("223","C","temp",2.33)));
        assertThat(studentService.findStudentByPartOfId("22"),hasItems(new Student("223","C","temp",2.33),
                new Student("224","D","temp",2.33)));

        //create another test case, using your own data
        List<Student> mockStudent1 = new ArrayList<>();
        mockStudent1.add(new Student("113","AA","temp",3.33));
        mockStudent1.add(new Student("112","BB","temp",3.33));
        mockStudent1.add(new Student("121","CC","temp",3.33));
        when(studentDao.findAll()).thenReturn(mockStudent1);
        assertThat(studentService.findStudentByPartOfId("11"),hasItem(new Student("113","AA","temp",3.33)));
        assertThat(studentService.findStudentByPartOfId("11"),hasItems(new Student("113","AA","temp",3.33),
                new Student("112","BB","temp",3.33)));


    }

    @Test(expected = NoDataException.class)
    public void testNoDataException(){
        List<Student> mockStudent = new ArrayList<>();
        mockStudent.add(new Student("123","A","temp",2.33));
        when(studentDao.findAll()).thenReturn(mockStudent);
        assertThat(studentService.findStudentById("55"),nullValue());
    }

    @Test(expected = SizeOfArrayException.class)
    public void testSizeOfArrayException(){
        List<Student> mockStudent = new ArrayList<>();
        mockStudent.add(new Student("123","A","temp",2.33));
        mockStudent.add(new Student("124","B","temp",2.33));
        mockStudent.add(new Student("223","C","temp", 2.33));
        mockStudent.add(new Student("224","D","temp", 2.33));
        when(studentDao.findAll()).thenReturn(mockStudent);
        assertThat(studentService.findStudentByPartOfId("55"),nullValue());

    }

    @Test(expected = ArithmeticException.class)
    public void testArithmeticException(){
        List<Student> mockStudent = new ArrayList<>();
        when(studentDao.findAll()).thenReturn(mockStudent);
        assertThat(studentService.getAverageGpa(),nullValue());
    }
}
