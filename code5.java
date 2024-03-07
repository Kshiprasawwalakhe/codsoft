import java.util.ArrayList;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    ArrayList<Student> registeredStudents; // Added this line

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>(); // Added this line
    }
}

class Student {
    int id;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class CourseRegistrationSystem {
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        initializeCourses();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Course Listing");
            System.out.println("2. Student Registration");
            System.out.println("3. Course Removal");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    displayCourseListing();
                    break;
                case 2:
                    performStudentRegistration(scanner);
                    break;
                case 3:
                    performCourseRemoval(scanner);
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeCourses() {
        courses.add(new Course("CSE101", "Introduction to Computer Science", "Basic CS concepts", 30,
                "Mon, Wed 10:00 AM - 11:30 AM"));
        courses.add(new Course("MATH201", "Calculus I", "Limits and derivatives", 25, "Tue, Thu 2:00 PM - 3:30 PM"));
        // Add more courses as needed
    }

    private static void displayCourseListing() {
        System.out.println("Course Listing:");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.code);
            System.out.println("Title: " + course.title);
            System.out.println("Description: " + course.description);
            System.out.println("Capacity: " + course.capacity);
            System.out.println("Schedule: " + course.schedule);
            System.out.println("Available Slots: " + (course.capacity - course.registeredStudents.size()));
            System.out.println("-------------------------------");
        }
    }

    private static void performStudentRegistration(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();

        Student student = new Student(studentId, studentName);
        students.add(student);

        displayCourseListing();

        System.out.print("Enter the course code to register: ");
        String courseCode = scanner.nextLine();

        Course selectedCourse = findCourse(courseCode);
        if (selectedCourse != null && selectedCourse.capacity > selectedCourse.registeredStudents.size()) {
            student.registeredCourses.add(selectedCourse);
            selectedCourse.registeredStudents.add(student);
            System.out.println("Registration successful!");
        } else {
            System.out.println("Course is either full or does not exist.");
        }
    }

    private static void performCourseRemoval(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        Student student = findStudent(studentId);
        if (student != null) {
            System.out.println("Registered Courses for " + student.name + ":");
            for (Course course : student.registeredCourses) {
                System.out.println("Course Code: " + course.code + ", Title: " + course.title);
            }

            System.out.print("Enter the course code to remove: ");
            String courseCode = scanner.nextLine();

            Course selectedCourse = findCourse(courseCode);
            if (selectedCourse != null && student.registeredCourses.contains(selectedCourse)) {
                student.registeredCourses.remove(selectedCourse);
                selectedCourse.registeredStudents.remove(student);
                System.out.println("Course removal successful!");
            } else {
                System.out.println("Student is not registered for the specified course.");
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    private static Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.code.equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private static Student findStudent(int studentId) {
        for (Student student : students) {
            if (student.id == studentId) {
                return student;
            }
        }
        return null;
    }
}
