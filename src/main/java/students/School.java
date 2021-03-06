package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

interface StudentCriterion extends Criterion<Student> {
  boolean test(Student s);
}

interface Criterion<E> {
  boolean test(E s);
  
  static <F> Criterion<F> not(Criterion<F> original) {
    return x -> ! original.test(x);
  }
  
  default Criterion<E> and(Criterion<E> other) {
    return x -> this.test(x) && other.test(x);
  }
}


//class SmartStudent implements StudentCriterion {
//  @Override
//  public boolean test(Student s) {
//    return s.getGpa() > 3;
//  }
//}
//
//class EnthusiasticStudent implements StudentCriterion {
//  @Override
//  public boolean test(Student s) {
//    return s.getCourses().size() > 3;
//  }
//}

public class School {
  
  public static Comparator<Student> reverse(Comparator<Student> cs) {
    return (s1, s2) -> cs.compare(s2, s1);
  }
  
  public static void showAllStudents(Iterable<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
  }
  
//  public static List<Student> getSmartStudents(Iterable<Student> in, float threshold) {
//    List<Student> rv = new ArrayList<>();
//    for (Student s : in) {
//      if (s.getGpa() > threshold) {
//        rv.add(s);
//      }
//    }
//    return rv;
//  }
//
//  public static List<Student> getEnthusiasticStudents(Iterable<Student> in, int threshold) {
//    List<Student> rv = new ArrayList<>();
//    for (Student s : in) {
//      if (s.getCourses().size() > threshold) {
//        rv.add(s);
//      }
//    }
//    return rv;
//  }
  
  public static List<Student> getStudentsByCriterion(
      Iterable<Student> in, StudentCriterion criterion) {
    List<Student> rv = new ArrayList<>();
    for (Student s : in) {
      if (criterion.test(s)) {
        rv.add(s);
      }
    }
    return rv;
  }
  
  public static <E> List<E> getByCriterion(
      Iterable<E> in, Criterion<E> criterion) {
    List<E> rv = new ArrayList<>();
    for (E s : in) {
      if (criterion.test(s)) {
        rv.add(s);
      }
    }
    return rv;
  }

  public static void main(String[] args) {
    Student fred = Student.ofNameGpaCourses("Fred", 3.2F, "Math", "Art", "History");
    List<Student> school = new ArrayList<>();
    school.add(fred);
    school.add(Student.ofNameGpaCourses("Jim", 2.2F, "Art", "Geography"));
    school.add(Student.ofNameGpaCourses("Sheila", 3.8F, "Math", "Physics", "Chemistry", "Astrophysics"));
    showAllStudents(school);
    System.out.println("-------------------------------");
//    List<Student> smart = getSmartStudents(school, 3.0F);
    List<Student> smart = getStudentsByCriterion(
        school, new Student.SmartStudent());
    showAllStudents(smart);
    System.out.println("-------------------------------");
//    List<Student> enthusiastic = getEnthusiasticStudents(school, 3);
    List<Student> enthusiastic = getStudentsByCriterion(
        school, Student.getEnthusiasticCriterion());
    showAllStudents(enthusiastic);
    System.out.println("-------------------------------");
//    StudentCriterion shortCrit = s->s.getName().length() < 5;
//    List<Student> shortNames = getStudentsByCriterion(
//        school, shortCrit);
    List<Student> shortNames = getByCriterion(
        school, (Student s) -> s.getName().length() < 5);
    showAllStudents(enthusiastic);
    
    
    // Context can also come from a cast. Note this cast differs
    // from traditional casts, it actually changes the 
    boolean b = ((StudentCriterion)(s->s.getName().length() < 5)).test(fred);
    
    System.out.println("By Name -------------------------------");
    school.sort((s1, s2)-> s1.getName().compareTo(s2.getName()));
    showAllStudents(school);
    
    System.out.println("By Grade -------------------------------");
    school.sort((s1, s2)-> Float.compare(s1.getGpa(), s2.getGpa()));
    showAllStudents(school);
    
    System.out.println("By Descending Enthusiasm -------------------------------");
    school.sort((s1, s2)-> s2.getCourses().size() - s1.getCourses().size());
    showAllStudents(school);
    
    System.out.println("By Length Criterion -------------------------------");
    List<Student> shortishNames = getByCriterion(
        school, Student.getNameLengthCriterion(4));
    showAllStudents(shortishNames);

    System.out.println("By name -------------------------------");
    school.sort(Student.getNameComparator());
    showAllStudents(school);
    System.out.println("Reverse name -------------------------------");
    school.sort(reverse(Student.getNameComparator()));
    showAllStudents(school);
    
    System.out.println("short Strings -------------------------------");
    List<String> text = Arrays.asList("Albert", "Orinoco", "WaffleSprocket", "int");

    Criterion<String> shortString = s->s.length() < 7;
    Criterion<String> startsLowerCase = s->Character.isLowerCase(s.charAt(0));
    List<String> shortText = getByCriterion(text, shortString.and(startsLowerCase));
//    for (String s : shortText) {
//      System.out.println("> " + s);
//    }
    shortText.forEach(s->System.out.println("> " + s));
    
    System.out.println("-------------------------------");
    shortText.replaceAll(s->s.toUpperCase());
    shortText.forEach(s->System.out.println("> " + s));
    
  } 
}
