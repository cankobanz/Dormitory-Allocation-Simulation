import java.io.*;
import java.util.*;

public class project1main {
    public static void main(String[] args) throws IOException {

        //Assigning input file path from the argument
        File inputFile=new File(args[0]);

        Scanner scan =new Scanner(inputFile);

        /*Creating all ArrayLists that are used throughout the program
        studentArrayList: List of all students that have not houses and not graduated. (Graduated students firstly entered the list then they are removed before first semester starts.)
        houseArrayList: List of all houses whether empty or full.
        outputStudentList: List of students that could not find house, and students that are graduated.
         */
        ArrayList<Student> studentArrayList =new ArrayList<>();
        ArrayList<House> houseArrayList =new ArrayList<>();
        ArrayList<Student> outputStudentList=new ArrayList<>();

        /*In this while loop, input file is read. Then, input lines are separated according to
        first letter of the lines (h or s). Then variables in the lines are separated
        by using split function. As a regex " " is chosen. Then, different house and
        student objects are created according to id, duration, rating and name variables.
         */
        while(scan.hasNext()){
            String lineOfInput=scan.nextLine();

            /*Following is statement is the where house objects are created.
            Attention: stringHouseArray[0] holds string "h" but it is not
            necessary for the rest of the program, so it is discarded.*/
            if(lineOfInput.startsWith("h")){
                String[] stringHouseArray=lineOfInput.split(" ");

                int id=Integer.parseInt(stringHouseArray[1]);
                int duration=Integer.parseInt(stringHouseArray[2]);
                double rating=Double.parseDouble(stringHouseArray[3]);

                House house=new House(id,duration,rating);
                houseArrayList.add(house);
            }
            //Where student objects are created.
            else if(lineOfInput.startsWith("s")){
                String[] stringStudentArray=lineOfInput.split(" ");

                int id=Integer.parseInt(stringStudentArray[1]);
                String name=stringStudentArray[2];
                int duration=Integer.parseInt(stringStudentArray[3]);
                double rating=Double.parseDouble(stringStudentArray[4]);

                Student student=new Student(id,name,duration,rating);
                studentArrayList.add(student);
            }
            else{
                System.out.println("Line does not imply whether it is house or student");
            }
        }
        scan.close();

        /*Before the first semester starts, graduated students are removed from the
        studentArrayList. They are directly removed to outputStudentList
        because they could not find house and graduated.
         */
        for (int i = 0;  i < studentArrayList.size(); i++) {
            Student student = studentArrayList.get(i);
            if (student.getDuration() == 0) {
                Student unhousedStudent = studentArrayList.remove(i);
                outputStudentList.add(unhousedStudent);
                i--;    //After remove operation one item is removed but iteration number stayed same. Therefore, decrease it one to prevent index size error.
            }
        }

        //studentArrayList and houseArrayList are sorted according to ascending ID numbers.
        Collections.sort(studentArrayList, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return Integer.valueOf(student1.getId()).compareTo(student2.getId());
            }
        });
        Collections.sort(houseArrayList, new Comparator<House>() {
            @Override
            public int compare(House house1, House house2) {
                return Integer.valueOf(house1.getId()).compareTo(house2.getId());
            }
        });

        //The most outer for loop is increasing the semester.
       for (int semester = 1; semester <=8; semester++) {

           /*Below enhanced for loop matched the students and houses according to rating and
           availableness of the houses. Since Lists are sorted according to ID numbers, found
           first matched house and student have the least ID numbers in their lists.
           If the match occurs, student duration is assigned to the house duration since the house going
           to be full for matched student's duration of semesters. Then, student is removed from the
           studentArrayList since student has housed, and it cannot be a member of outputStudentList anymore.
           */
           for (int s = 0; s < studentArrayList.size(); s++) {
               Student student = studentArrayList.get(s);
               for (int h = 0; h < houseArrayList.size(); h++) {
                   House house = houseArrayList.get(h);
                   if (student.getRating() <= house.getRating() && house.getDuration() == 0) {
                       int matchedStudentDuration = student.getDuration();
                       house.setDuration(matchedStudentDuration);
                       studentArrayList.remove(s);
                       s--;
                       break;
                   }
               }
           }

           /*Every semester, student and house durations are updated by decreasing one.
           If a student is graduated (if student duration equals to 0), it is removed to the outputStudentList.
           */
           for (int i = 0;  i < studentArrayList.size(); i++) {
               Student student = studentArrayList.get(i);
               student.updateDuration();
               if (student.getDuration() == 0) {
                   Student unhousedStudent = studentArrayList.remove(i);
                   outputStudentList.add(unhousedStudent);
                   i--;
               }
           }

           for(int i=0; i<houseArrayList.size();i++){
               House house=houseArrayList.get(i);
               if(house.getDuration()>0){
                   house.updateDuration();
               }
           }
       }

       //outputStudentList is sorted according to ID numbers before it is written on output file.
        Collections.sort(outputStudentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.valueOf(s1.getId()).compareTo(s2.getId());
            }
        });


        //Assigning output file path from the argument
        File outputFile=new File(args[1]);
        //Writing output to the target file
        try {
            FileWriter writer=new FileWriter(outputFile);
            Writer output=new BufferedWriter(writer);
            for(int i = 0; i<outputStudentList.size(); i++){
                output.write(outputStudentList.get(i).getName()+"\n");
            }
            output.close();
        } catch (IOException e){
            System.out.println("Catch - An error occurred.");
            e.printStackTrace();
        }    }
}






















