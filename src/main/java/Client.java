import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    public static void main(String args[]) throws Exception {
        ArrayList<Exam> exams = new ArrayList<Exam>();
        exams.add(new Exam("SDP", 21, "2022/01/31"));
        exams.add(new Exam("MOBILE COMPUTING", 21, "2022/01/27"));
        Student student = new Student("Mattia", "Fasoli", 1998, new Residence("Milano", "Via Solferino, 44", 20121), exams);

        DataOutputStream outToServer;

        Socket clientSocket = new Socket("localhost", 6789);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());

        Gson gson = new Gson();
        String studentString = gson.toJson(student);

        outToServer.writeBytes(studentString + "\n");
    }
}

class Student {
    String name;
    String surname;
    Integer yearOfBirth;
    Residence residence;
    ArrayList<Exam> exams;

    public Student(String name, String surname, Integer yearOfBirth, Residence residence, ArrayList<Exam> exams) {
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.residence = residence;
        this.exams = exams;
    }

    public String print() {
        String student = "";
        student += "Name: " + name + ";\n";
        student += "Surname: " + surname + ";\n";
        student += "Year Of Birth: " + yearOfBirth + ";\n";
        student += "Residence: " + residence.print();
        student += "Exams: " + "\n";
        for (Exam exam: exams) {
            student += exam.print();
        };

        return student;
    }
}

class Residence {
    private String city;
    private String address;
    private Integer cap;

    public Residence(String city, String address, Integer cap) {
        this.city = city;
        this.address = address;
        this.cap = cap;
    }

    public String print() {
        String residence = "";
        residence += "City: " + city + ";\n" + "Address: " + address + ";\n" + "Cap: " + cap + ";\n";
        return residence;
    }
}

class Exam {
    private String name;
    private Integer mark;
    private String date;

    public Exam(String name, Integer mark, String date) {
        this.name = name;
        this.mark = mark;
        this.date = date;
    }

    public String print() {
        String exam = "";
        exam += "Name: " + name + ";\n" + "Mark: " + mark + ";\n" + "Date: " + date + ";\n";
        return exam;
    }
}
