class QuestionsAndAnswers{
    private String name; 
    private String questions;
    private int answers;

    //constructor that takes in name, questions and answers arguments.
    QuestionsAndAnswers(String name, String questions, int answers){
        this.name = name;
        this.questions = questions;
        this.answers = answers;
    }
    //setter method for name
    void setName(String name){
        this.name = name;
    }
    //setter method for questions
    void setQuestions(String questions){
        this.questions = questions;
    }
    //getter method for answers
    void setAnswers(int answers){
        this.answers = answers;
    }
    //getter method for name
    String getName(){
        return name;
    }
    //getter method for questions
    String getQuestions(){
        return questions;
    }
    //getter method for answers
    int getAnswers(){
        return answers;
    }

}