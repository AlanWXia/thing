import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//can only solve 3 different questions each with 2-3 variables
public class Solver{
    private Double[] Q1res = null; //array storing the answers to one of the questions
    private Double[] Q2res = null; //array storing the answers to one of the questions
    private Double[] Q3res = null; //array storing the answers to one of the questions
    public Solver(){
        
    }
    /**
     * 
     * @param QNum the number of the answer you want
     * @return the Double array of the answer number requested
     */
    public Double[] getQRes(int QNum){
        if(QNum == 1){
            return Q1res;
        } else if(QNum == 2){
            return Q2res;
        } else if(QNum == 3){
            return Q3res;
        }
        else return null;
    }
    /**
     * determine whether the equation has 2 or 3 variables and then calls the method to solve it
     */
    public void solveEquations(int[][] Q1, int[][] Q2, int[][] Q3){
        if(Q1 != null){
            boolean is2d = (Q1[2][0] == 0) && (Q1[2][1] == 0) && (Q1[2][2] == 0) && (Q1[2][3] == 0);
            if(is2d){
                Q1res = new Double[2];
                Q1res = solveEquations2(Q1);
                System.out.println("x = " + Q1res[0] + " y = " + Q1res[1]);
            } else {
                Q1res = new Double[3];
                Q1res = solveEquations3(Q1);
                System.out.println("x = " + Q1res[0] + " y = " + Q1res[1]+ " z = " + Q1res[2]);
            }
        }
        if(Q2 != null){
            boolean is2d = (Q2[2][0] == 0) && (Q2[2][1] == 0) && (Q2[2][2] == 0) && (Q2[2][3] == 0);
            if(is2d){
                Q2res = new Double[2];
                Q2res = solveEquations2(Q2);
                System.out.println("x = " + Q2res[0] + " y = " + Q2res[1]);
            } else {
                Q2res = new Double[3];
                Q2res = solveEquations3(Q2);
                System.out.println("x = " + Q2res[0] + " y = " + Q2res[1]+ " z = " + Q2res[2]);
            }
        }
        if(Q3 != null){
            boolean is2d = (Q3[2][0] == 0) && (Q3[2][1] == 0) && (Q3[2][2] == 0) && (Q3[2][3] == 0);
            if(is2d){
                Q3res = new Double[2];
                Q3res = solveEquations2(Q3);
                System.out.println("x = " + Q3res[0] + " y = " + Q3res[1]);
            } else {
                Q3res = new Double[3];
                Q3res = solveEquations3(Q3);
                System.out.println("x = " + Q3res[0] + " y = " + Q3res[1]+ " z = " + Q3res[2]);
            }
        }
    }
    /**
     * resolves a1x+b1y+c1=0
     *          a2x+b2y+c2=0
     * the mathematical solutions are x = (c2*b1 - c1*b2)/(a1*b2 - a2*b1)
     *                                y = (c2*a1 - c1*a2)/(b1*a2 - b2*a1)
     * @param Q the question to be solved
     * @return res; an array holding the solutions of the equation
     */
    private Double[] solveEquations2(int[][] Q){
        int a1 = Q[0][0];
        int a2 = Q[1][0];
        int b1 = Q[0][1];
        int b2 = Q[1][1];
        int c1 = Q[0][3];
        int c2 = Q[1][3];
        if((a1*b2 - a2*b1) == 0 || (b1*a2 - b2*a1) == 0){
            Double[] res = {null, null};
            return res;
        }
        Double x = (double) (c2*b1 - c1*b2)/ (double) (a1*b2 - a2*b1);
        Double y = (double) (c2*a1 - c1*a2)/ (double) (b1*a2 - b2*a1);
        Double[] res = {x, y};
        return res;
    }
    /**
     * solves a1x+b1y+c1z+d1=0
     *        a2x+b2y+c2z+d2=0
     *        a3x+b3y+c3z+d3=0
     * converts into a1x+b1y+c1=0
     *               a2x+b2y+c2=0 format first
     * solved for x and y and then uses x and y to solve for z
     * @param Q the question to be solved
     * @return res; an array holding the solutions of the equation
     */
    private Double[] solveEquations3(int[][] Q){
        int a1 = Q[0][0];
        int a2 = Q[1][0];
        int a3 = Q[2][0];
        int b1 = Q[0][1];
        int b2 = Q[1][1];
        int b3 = Q[2][1];
        int c1 = Q[0][2];
        int c2 = Q[1][2];
        int c3 = Q[2][2];
        int d1 = Q[0][3];
        int d2 = Q[1][3];
        int d3 = Q[2][3];
        int[][] newQ = new int[3][4];
        newQ[0][0] = a1*c2-a2*c1;
        newQ[0][1] = b1*c2-b2*c1;
        newQ[0][2] = 0;
        newQ[0][3] = d1*c2-d2*c1;
        newQ[1][0] = a2*c3-a3*c2;
        newQ[1][1] = b2*c3-b3*c2;
        newQ[1][2] = 0;
        newQ[1][3] = d2*c3-d3*c2;
        for(int j=0; j<4; j++){
            newQ[2][j] = 0;
        }
        Double[] res = new Double[3];
        Double[] temp = new Double[2];
        temp = solveEquations2(newQ);
        res[0] = temp[0];
        res[1] = temp[1];
        res[2] = (double) (a1*res[0]+b1*res[1]+d1)/(double)(-c1);
        return res;
    }
}