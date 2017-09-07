package students;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamSchool {

  public static void main(String[] args) {
    Student fred = Student.ofNameGpaCourses("Fred", 3.2F, "Math", "Art", "History");
    List<Student> school = new ArrayList<>();
    school.add(fred);
    school.add(Student.ofNameGpaCourses("Jim", 2.2F, "Art", "Geography"));
    school.add(Student.ofNameGpaCourses("Sheila", 3.8F, "Math", "Physics", "Chemistry", "Astrophysics"));
    school.add(Student.ofNameGpaCourses("Sheila", 3.3F, "Chemistry", "Astrophysics"));
    school.add(Student.ofNameGpaCourses("Albert", 3.4F, "Math", "Astrophysics"));
    school.add(Student.ofNameGpaCourses("Tony", 2.8F, "Math"));

    school.stream()
        .filter(s -> s.getGpa() > 3.3F)
        //        .map(s->s.getGpa())
        .flatMap(s -> s.getCourses().stream())
        .distinct()
        .sorted()
        .forEach(s -> System.out.println("> " + s));
    System.out.println("------------------------------");

    school.stream()
        .map(s -> s.getName() + ": " + s.getGpa())
        .forEach(s -> System.out.println(s));

    System.out.println("------------------------------");
    school.stream()
        .filter(s->s.getGpa() > 3.2)
        .map(s -> s.getName() + ": " + s.getGpa())
        .forEach(s -> System.out.println(s));

    System.out.println("------------------------------");
    school.stream()
        .flatMap(s -> s.getCourses().stream().map(x->s.getName() + ": " + x))
        .forEach(s -> System.out.println(s));

    System.out.println("------------------------------");
    Stream.iterate(1, x -> x + 2)
        .peek(s->System.out.println("> " + s))
        .skip(10_000)
        .limit(100)
        .forEach(s->System.out.println(s))
        ;
  }
}
