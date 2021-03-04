package sample;

public class StudentRecord {
    private int student_id;
    private int midterm;
    private int assig;
    int final_exam;
    int final_mark;
    char letter_grade;

    public StudentRecord(int student_id, int midterm, int assig, int final_exam, int final_mark, char letter_grade){
        setID(student_id);
        setMid(midterm);
        setAssg(assig);
        setFinalEx(final_exam);
        setFinalMark(final_mark);
        setLetter(letter_grade);
    }

    //STUDENT ID
    public int getId() {
        return student_id;
    }
    public void setID(int s_id){
        this.student_id = (s_id);
    }

    //MIDTERM
    public int getMid() {
        return midterm;
    }
    public void setMid(int mid){
        this.midterm = mid;
    }

    //ASSIGNMENT
    public int getAssg() {
        return assig;
    }
    public void setAssg(int assignment){
        this.assig = assignment;
    }

    //FINAL EXAM
    public int getFinalEx() {
        return final_exam;
    }
    public void setFinalEx(int final_ex){
        this.final_exam = final_ex;
    }

    //FINAL MARK
    public int getFinalMark() {
        return final_mark;
    }
    public void setFinalMark(int final_m){
        this.final_mark = final_m;
    }

    //LETTER GRADE
    public char getLetter() {
        return letter_grade;
    }
    public void setLetter(char letter){
        this.letter_grade = letter;
    }

}
