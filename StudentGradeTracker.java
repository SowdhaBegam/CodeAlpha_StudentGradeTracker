import java.util.*;

class Student {
    String name;
    int rollNo;
    int[] marks = new int[3];
    double average;

    Student(String name, int rollNo, int[] marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
        calculateAverage();
    }

    void calculateAverage() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        this.average = total / 3.0;
    }

    String getGrade() {
        if (average >= 90) return "A+";
        else if (average >= 80) return "A";
        else if (average >= 70) return "B";
        else if (average >= 60) return "C";
        else if (average >= 50) return "D";
        else return "F";
    }
}

public class StudentGradeTracker {
    static ArrayList<Student> studentList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n📘 STUDENT GRADE TRACKER 📘");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Show Highest & Lowest Marks");
            System.out.println("4. Summary Report (with Grades)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    showHighestLowest();
                    break;
                case 4:
                    showSummaryReport();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        } while (choice != 5);
    }

    static void addStudent() {
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Roll No: ");
        int rollNo = sc.nextInt();

        int[] marks = new int[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter Marks for Subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
        }

        studentList.add(new Student(name, rollNo, marks));
        System.out.println("✅ Student added successfully!");
    }

    static void viewAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("📭 No student data available.");
            return;
        }
        System.out.println("\n📋 Student Details:");
        for (Student s : studentList) {
            System.out.println("Name: " + s.name + ", Roll No: " + s.rollNo +
                    ", Marks: " + Arrays.toString(s.marks) +
                    ", Average: " + String.format("%.2f", s.average) +
                    ", Grade: " + s.getGrade());
        }
    }

    static void showHighestLowest() {
        if (studentList.isEmpty()) {
            System.out.println("📭 No data to analyze.");
            return;
        }
        Student highest = studentList.get(0);
        Student lowest = studentList.get(0);

        for (Student s : studentList) {
            if (s.average > highest.average) highest = s;
            if (s.average < lowest.average) lowest = s;
        }

        System.out.println("\n🏆 Highest Average: " + String.format("%.2f", highest.average) + " by " + highest.name);
        System.out.println("📉 Lowest Average: " + String.format("%.2f", lowest.average) + " by " + lowest.name);
    }

    static void showSummaryReport() {
        if (studentList.isEmpty()) {
            System.out.println("📭 No data to summarize.");
            return;
        }

        double totalAverage = 0;
        System.out.println("\n📊 SUMMARY REPORT:");
        for (Student s : studentList) {
            totalAverage += s.average;
            System.out.println("Name: " + s.name +
                    " | Avg Marks: " + String.format("%.2f", s.average) +
                    " | Grade: " + s.getGrade());
        }

        double classAvg = totalAverage / studentList.size();
        System.out.println("\nTotal Students: " + studentList.size());
        System.out.printf("Class Average Marks: %.2f\n", classAvg);
    }
}