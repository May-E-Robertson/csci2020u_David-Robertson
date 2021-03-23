package sample;

public class StudentRecord {
    public String studentID;
    public float midtermMark;
    public float assignmentMark;
    public float finalExamMark;
    public float finalMark;
    public char letterGrade;

    public StudentRecord(String sid, float midterm, float assignment, float exam){
        this.studentID = sid;
        this.midtermMark = midterm;
        this.assignmentMark = assignment;
        this.finalExamMark = exam;

        this.finalMark = (this.assignmentMark * 0.2f + this.midtermMark * 0.3f  + this.finalExamMark * 0.5f);

        if(finalMark <= 49){
            this.letterGrade = 'F';
        } else if(finalMark >= 50 && finalMark <= 59){
            this.letterGrade = 'D';
        } else if(finalMark >= 60 && finalMark <= 69) {
            this.letterGrade = 'C';
        } else if(finalMark >= 70 && finalMark <= 79) {
            this.letterGrade = 'B';
        } else if(finalMark >= 80) {
            this.letterGrade = 'A';
        }
    }

    public String getStudentID() {
        return this.studentID;
    }
    public float getMidtermMark() {
        return this.midtermMark;
    }
    public float getAssignmentMark() {
        return this.assignmentMark;
    }
    public float getFinalExamMark() {
        return this.finalExamMark;
    }
    public float getFinalMark() {
        return this.finalMark;
    }
    public char getLetterGrade() {
        return this.letterGrade;
    }
}
