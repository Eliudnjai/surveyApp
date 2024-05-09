import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SurveyApp extends JFrame {
    private List<int[]> responses = new ArrayList<>();
    private FinalScore<QuestionsAndAnswers> finalScore;
    private Set<String> takenSurveys = new HashSet<>(); // Set to store names of users who have taken the survey

    public SurveyApp(String title) {
        super(title);
        finalScore = new FinalScore<>("Survey");

        // Create GUI components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2, 5, 5)); // GridLayout with 2 columns and dynamic rows, with spacing of 5 pixels

        // Add question 1 and its corresponding slider
        JLabel question1Label = new JLabel("How likely are you to Vote for Trump!:");
        mainPanel.add(question1Label);

        JSlider answerSlider1 = new JSlider(0, 100);
        answerSlider1.setMajorTickSpacing(10);
        answerSlider1.setPaintTicks(true);
        answerSlider1.setPaintLabels(true);
        mainPanel.add(answerSlider1);

        // Add question 2 and its corresponding slider
        JLabel question2Label = new JLabel("How well do you know the MiddleEastern conflict?:");
        mainPanel.add(question2Label);

        JSlider answerSlider2 = new JSlider(0, 100);
        answerSlider2.setMajorTickSpacing(10);
        answerSlider2.setPaintTicks(true);
        answerSlider2.setPaintLabels(true);
        mainPanel.add(answerSlider2);

        // Add question 3 and its corresponding slider
        JLabel question3Label = new JLabel("How confident are you of Biden's win?:");
        mainPanel.add(question3Label);

        JSlider answerSlider3 = new JSlider(0, 100);
        answerSlider3.setMajorTickSpacing(10);
        answerSlider3.setPaintTicks(true);
        answerSlider3.setPaintLabels(true);
        mainPanel.add(answerSlider3);

        // Add question 4 and its corresponding slider
        JLabel question4Label = new JLabel("How well do you think the Ukraine war is going?:");
        mainPanel.add(question4Label);

        JSlider answerSlider4 = new JSlider(0, 100);
        answerSlider4.setMajorTickSpacing(10);
        answerSlider4.setPaintTicks(true);
        answerSlider4.setPaintLabels(true);
        mainPanel.add(answerSlider4);

        // Add question 5 and its corresponding slider
        JLabel question5Label = new JLabel("What is the likely hood of a global war with Russia and China?:");
        mainPanel.add(question5Label);

        JSlider answerSlider5 = new JSlider(0, 100);
        answerSlider5.setMajorTickSpacing(10);
        answerSlider5.setPaintTicks(true);
        answerSlider5.setPaintLabels(true);
        mainPanel.add(answerSlider5);

        // Add text field for name
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Name: "));
        JTextField nameField = new JTextField(20);
        namePanel.add(nameField);
        mainPanel.add(namePanel);

        // Add submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform actions on submit
                String name = nameField.getText();
                if (takenSurveys.contains(name)) { // Check if the user has already taken the survey
                    JOptionPane.showMessageDialog(SurveyApp.this, "You've already taken the survey.", "Survey Already Taken", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int[] userResponse = {
                        answerSlider1.getValue(),
                        answerSlider2.getValue(),
                        answerSlider3.getValue(),
                        answerSlider4.getValue(),
                        answerSlider5.getValue()
                };
                responses.add(userResponse);
                finalScore.addQuestionAndAnswer("How likely are you to Vote for Trump!:", userResponse[0]);
                finalScore.addQuestionAndAnswer("How well do you know the MiddleEastern conflict?:", userResponse[1]);
                finalScore.addQuestionAndAnswer("How confident are you of Biden's win?:", userResponse[2]);
                finalScore.addQuestionAndAnswer("How well do you think the Ukraine war is going?:", userResponse[3]);
                finalScore.addQuestionAndAnswer("What is the likely hood of a global war with Russia and China?:", userResponse[4]);

                takenSurveys.add(name); // Add the user's name to the set of taken surveys

                String result = "Name: " + name + "\n" +
                        "Question 1: " + userResponse[0] + "\n" +
                        "Question 2: " + userResponse[1] + "\n" +
                        "Question 3: " + userResponse[2] + "\n" +
                        "Question 4: " + userResponse[3] + "\n" +
                        "Question 5: " + userResponse[4];
                JOptionPane.showMessageDialog(SurveyApp.this, result, "Survey Results", JOptionPane.PLAIN_MESSAGE);

                int option = JOptionPane.showConfirmDialog(SurveyApp.this, "Would you like to start a new survey?", "New Survey", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.NO_OPTION) {
                    showOverallResults();
                } else {
                    // Clear the fields for a new survey
                    nameField.setText("");
                    answerSlider1.setValue(0);
                    answerSlider2.setValue(0);
                    answerSlider3.setValue(0);
                    answerSlider4.setValue(0);
                    answerSlider5.setValue(0);
                }
            }
        });
        mainPanel.add(submitButton);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add components to the frame
        add(scrollPane);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    //calculate the final results.
    private void showOverallResults() {
        StringBuilder result = new StringBuilder();
        result.append("Overall Survey Results:\n\n");
        ArrayList<QuestionsAndAnswers> questionsAndAnswers = finalScore.getQuestionsAndAnswers();
        int totalResponses = questionsAndAnswers.size();
        if (totalResponses > 0) {
            int[] totalScores = new int[5];
            for (int i = 0; i < 5; i++) {
                int questionTotal = 0;
                for (int j = 0; j < totalResponses; j++) {
                    questionTotal += questionsAndAnswers.get(j).getAnswers();
                }
                totalScores[i] = questionTotal / totalResponses;
            }

            // Display average percentages for each question
            for (int i = 0; i < 5; i++) {
                result.append("Question ").append(i + 1).append(": ").append(totalScores[i]).append("%\n");
            }
        } else {
            result.append("No responses recorded yet.");
        }
        JOptionPane.showMessageDialog(this, result.toString(), "Overall Survey Results", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SurveyApp("Survey Application"));
    }
}

















































// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

// public class SurveyApp extends JFrame {
//     private String userName;
//     String question1 = "From a scale of 1 to 5. How much do you know about politics ";
//     String question2 = "From a scale of 1 to 5. How much do you understand MiddleEast conflict";
//     String question3 = "From a scale of 1 to 5. How luckly are you to vote for Trump?";
//     String question4 = "From a scale of 1 to 5. How fit do you think President Biden is?";
//     String question5 = "From a scale of 1 to 5. How fit do you think President Trump is?";

//     private Question[] questions = {
//             new Question(question1, ""),
//             new Question(question2, ""),
//             new Question(question3, ""),
//             new Question(question4, ""),
//             new Question(question5, "")
//     };
//     private FinalScore finalScore = new FinalScore(questions.length, 5);

//     private int currentQuestionIndex = 0;

//     private JLabel questionLabel;
//     private ButtonGroup answerGroup;
//     private JButton nextButton;

//     public SurveyApp() {
//         // Ask for the user's name first
//         askUserName();

//         setTitle("Survey");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(800, 500);
//         setLayout(new BorderLayout());

//         questionLabel = new JLabel(questions[currentQuestionIndex].getQuestion());
//         add(questionLabel, BorderLayout.NORTH);

//         JPanel radioPanel = new JPanel();
//         radioPanel.setLayout(new GridLayout(5, 1));
//         answerGroup = new ButtonGroup();
//         for (int i = 1; i <= 5; i++) {
//             JRadioButton radioButton = new JRadioButton("" + i);
//             radioButton.setActionCommand("" + i);
//             answerGroup.add(radioButton);
//             radioPanel.add(radioButton);
//         }
//         add(radioPanel, BorderLayout.CENTER);

//         nextButton = new JButton("Next");
//         nextButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 recordAnswer();
//                 if (currentQuestionIndex < questions.length - 1) {
//                     currentQuestionIndex++;
//                     updateQuestion();
//                 } else {
//                     showResults();
//                 }
//             }
//         });
//         add(nextButton, BorderLayout.SOUTH);

//         setVisible(true);
//     }

//     private void askUserName() {
//         // Ask for the user's name
//         userName = JOptionPane.showInputDialog(this, "Please enter your name:");
//     }

//     private void recordAnswer() {
//         String selectedAnswer = answerGroup.getSelection().getActionCommand();
//         questions[currentQuestionIndex].setAnswer(selectedAnswer);
//         int[][] answers = new int[questions.length][5];
//         for (int i = 0; i < questions.length; i++) {
//             for (int j = 0; j < 5; j++) {
//                 answers[i][j] = 0;
//             }
//         }
//         answers[currentQuestionIndex][Integer.parseInt(selectedAnswer) - 1] = 1;
//         finalScore.recordAnswers(answers);
//     }

//     private void updateQuestion() {
//         questionLabel.setText(questions[currentQuestionIndex].getQuestion());
//         answerGroup.clearSelection();
//     }

//     private void showResults() {
//         StringBuilder result = new StringBuilder("Survey Results for " + userName + ":\n");
//         for (int i = 0; i < questions.length; i++) {
//             result.append(questions[i].getQuestion()).append(":\n");
//             for (int j = 0; j < 5; j++) {
//                 result.append("\tOption ").append(j + 1).append(": ").append(finalScore.getPercentage(i, j)).append("%\n");
//             }
//         }
//         JOptionPane.showMessageDialog(this, result.toString());

//         // Offer the option to start a new survey
//         int option = JOptionPane.showConfirmDialog(this, "Would you like to start a new survey?", "New Survey", JOptionPane.YES_NO_OPTION);
//         if (option == JOptionPane.YES_OPTION) {
//             // Restart the survey
//             resetSurvey();
//         } else {
//             // Return totals
//             showTotals();
//             System.exit(0);
//         }
//     }

//     private void resetSurvey() {
//         askUserName(); // Ask for the user's name again
//         for (Question question : questions) {
//             question.setAnswer(""); // Reset answers
//         }
//         currentQuestionIndex = 0;
//         updateQuestion(); // Start from the first question
//     }

//     private void showTotals() {
//         StringBuilder totalResult = new StringBuilder("Total Survey Results:\n");
//         for (int i = 0; i < questions.length; i++) {
//             totalResult.append(questions[i].getQuestion()).append(":\n");
//             for (int j = 0; j < 5; j++) {
//                 totalResult.append("\tOption ").append(j + 1).append(": ").append(finalScore.getPercentage(i, j)).append("%\n");
//             }
//         }
//         totalResult.append("Total respondents: ").append(finalScore.getTotalRespondents());
//         JOptionPane.showMessageDialog(this, totalResult.toString(), "Total Results", JOptionPane.INFORMATION_MESSAGE);
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(new Runnable() {
//             public void run() {
//                 new SurveyApp();
//             }
//         });
//     }
// }




