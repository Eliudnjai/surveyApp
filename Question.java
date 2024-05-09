public class Question {
    private String question;
    private String answer;
    private String lastname;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question(String question, String answer, String lastname){
        this.question = question;
        this.answer = answer;
        this.lastname = lastname;
    }

    public void setLastname(String name){
        this.lastname = name;
    }

    public String getLastname(){
        return lastname;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
