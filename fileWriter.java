import java.io.IOException;
import java.io.FileWriter;

class fileWriter{
    static Double[] Q1res = null; //array storing the answers to one of the questions
    static Double[] Q2res = null; //array storing the answers to one of the questions
    static Double[] Q3res = null; //array storing the answers to one of the questions
    static int[][] grades = null; //2d array holding the grades of each person and each question answered
    static String[][] studentInfo = null; //2d array containing the student data from the file the user inputted
    public fileWriter(){
      grades = new int[5][2];
      studentInfo = new String[5][4];
      Q1res = new Double[2];
      Q2res = new Double[2];
      Q3res = new Double[3];

    }
     private static void writeAQ(FileWriter myWriter, Double[] Q){
        if(Q == null) return;
        try{
            for(int i=0; i<Q.length; i++){
                if(i==0){
                    myWriter.write("x=" + Q[0]);
                } else if(i==1){
                    myWriter.write(", y=" + Q[1]);
                } else{
                    myWriter.write(", z=" + Q[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

  
    public void writeToFile(){
        try {
            FileWriter myWriter = new FileWriter("answers.csv");
            if(Q1res != null){
                myWriter.write("Q1 results:\n");
                writeAQ(myWriter, Q1res);
            }
            if(Q2res != null){
                myWriter.write("\n\nQ2 results:\n");
                writeAQ(myWriter, Q2res);

            }
            if(Q3res != null){
                myWriter.write("\n\nQ3 results:\n");
                writeAQ(myWriter, Q3res);

            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
  
    public void writeGrades(){
      try {
        FileWriter myWriter = new FileWriter("student_grades.csv");
        //header line
        myWriter.write("Student Number,First Name,Last Name,Email,G1,G2");
        //checking if there are 2 or 3 questions
        if(grades[0].length == 3){
          myWriter.write(",G3");
        }
          myWriter.write("\n");
          //writing the student information to the next line
          for(int i=0; i<studentInfo.length; i++){
            myWriter.write(studentInfo[i][0] + "," + studentInfo[i][1] + "," + studentInfo[i][2] + "," + studentInfo[i][3]);
            //writing the grades on the same line
            for(int j=0; j<grades[i].length; j++){
                myWriter.write("," + grades[i][j]);
            }
            myWriter.write("\n");
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
}