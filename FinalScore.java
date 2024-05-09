import java.util.ArrayList;

class FinalScore<E>{
    private String name;
    private ArrayList<QuestionsAndAnswers> questionsandanswers; //init the arraylist with QuestionsAndAnswers class

    //constructor that takes the name as an argument.
    FinalScore(String name){
        this.name = name;
        this.questionsandanswers = new ArrayList<>();
    }
    //method to add question and answer to the arraylist
    void addQuestionAndAnswer(String question, int answer){
        QuestionsAndAnswers qa = new QuestionsAndAnswers(this.name, question, answer);
        questionsandanswers.add(qa);
    }
    //method to get all the questions and answers
    ArrayList<QuestionsAndAnswers> getQuestionsAndAnswers(){
        return questionsandanswers;
    }

}














































// import java.util.ArrayList;
// import java.util.List;

// public class FinalScore {
//     private List<int[][]> allResults;
//     private int totalRespondents;

//     public FinalScore(int questionCount, int optionCount) {
//         allResults = new ArrayList<>();
//         totalRespondents = 0;
//     }

//     public void recordAnswers(int[][] answers) {
//         allResults.add(answers);
//         totalRespondents++;
//     }

//     public double getPercentage(int questionIndex, int answerIndex) {
//         int total = 0;
//         for (int[][] answers : allResults) {
//             total += answers[questionIndex][answerIndex];
//         }
//         if (totalRespondents == 0) return 0;
//         return ((double) total / totalRespondents) * 100;
//     }

//     public int getTotalRespondents() {
//         return totalRespondents;
//     }
// }
