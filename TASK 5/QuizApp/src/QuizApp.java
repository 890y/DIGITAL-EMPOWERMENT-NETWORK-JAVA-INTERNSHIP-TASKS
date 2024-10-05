import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizApp extends JFrame implements ActionListener {
    JLabel heading;
    JButton startQuizButton;

    // Quiz questions and answers
    String[][] questions = {
        {"What is the capital of France?", "Paris", "London", "Berlin", "Madrid", "Paris"},
        {"Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Venus", "Mars"},
        {"Who wrote 'Hamlet'?", "Charles Dickens", "J.K. Rowling", "William Shakespeare", "Mark Twain", "William Shakespeare"},
        {"What is the capital of Norway?", "Oslo", "Stockholm", "Copenhagen", "Berlin", "Oslo"},
        {"How many feet are there in an inch?", "0.0833", "0.083", "0.1", "1", "0.0833"},
        {"What is the capital of Sweden?", "Stockholm", "Oslo", "Helsinki", "Copenhagen", "Stockholm"},
        {"What is the square root of 64?", "6", "7", "8", "9", "8"},
        {"What is the capital of Austria?", "Vienna", "Zurich", "Budapest", "Berlin", "Vienna"},
        {"What is the third most populous country in the world?", "China", "India", "USA", "Indonesia", "USA"},
        {"What is the capital of Australia?", "Sydney", "Melbourne", "Canberra", "Brisbane", "Canberra"},
        {"What does OCD stand for?", "Obsessive Compulsive Disorder", "Over Compulsive Disorder", "Obsessive Conflict Disorder", "Occupational Compulsive Disorder", "Obsessive Compulsive Disorder"},
        {"What is 5 + 9?", "12", "14", "13", "15", "14"},
        {"What is 5 - 5?", "0", "1", "2", "5", "0"}
    };

    // Quiz UI elements
    JLabel questionLabel, attemptsLabel, scoreLabel, wrongAnswersLabel;
    JRadioButton option1, option2, option3, option4;
    ButtonGroup optionsGroup;
    JButton submitButton;
    int currentQuestion = 0;
    int attempts = 0;
    int score = 0;
    int wrongAnswers = 0;

    public QuizApp() {
        // Set window properties
        setTitle("Quiz Application");
        setSize(800, 600);  // Broad and long window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Use null layout for absolute positioning

        // Set green background color
        getContentPane().setBackground(Color.GREEN);

        // Heading label
        heading = new JLabel("QUIZ", SwingConstants.CENTER);
        heading.setBounds(150, 100, 500, 60);
        heading.setFont(new Font("Arial", Font.BOLD, 50));
        heading.setForeground(Color.BLACK);  // Black text
        add(heading);

        // Start quiz button
        startQuizButton = new JButton("START QUIZ");
        startQuizButton.setBounds(300, 300, 200, 80);
        startQuizButton.setFont(new Font("Arial", Font.BOLD, 24));
        startQuizButton.setBackground(Color.WHITE);  // White button
        startQuizButton.setForeground(Color.BLACK);  // Black text
        startQuizButton.addActionListener(this);
        add(startQuizButton);

        // Question label (hidden initially)
        questionLabel = new JLabel();
        questionLabel.setBounds(100, 150, 600, 30);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        questionLabel.setForeground(Color.BLACK);  // Black text
        add(questionLabel);
        questionLabel.setVisible(false);  // Hidden initially

        // Option buttons (hidden initially)
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        // Group radio buttons
        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        option1.setBounds(100, 200, 600, 30);
        option2.setBounds(100, 250, 600, 30);
        option3.setBounds(100, 300, 600, 30);
        option4.setBounds(100, 350, 600, 30);

        option1.setBackground(Color.GREEN);
        option2.setBackground(Color.GREEN);
        option3.setBackground(Color.GREEN);
        option4.setBackground(Color.GREEN);

        option1.setForeground(Color.BLACK);
        option2.setForeground(Color.BLACK);
        option3.setForeground(Color.BLACK);
        option4.setForeground(Color.BLACK);

        add(option1);
        add(option2);
        add(option3);
        add(option4);

        option1.setVisible(false);  // Hidden initially
        option2.setVisible(false);  // Hidden initially
        option3.setVisible(false);  // Hidden initially
        option4.setVisible(false);  // Hidden initially

        // Submit button (hidden initially)
        submitButton = new JButton("Submit");
        submitButton.setBounds(350, 400, 100, 30);
        submitButton.addActionListener(this);
        add(submitButton);
        submitButton.setVisible(false);  // Hidden initially

        // Result labels (hidden initially)
        attemptsLabel = new JLabel();
        attemptsLabel.setBounds(100, 450, 200, 30);
        attemptsLabel.setForeground(Color.BLACK);  // Black text
        add(attemptsLabel);
        attemptsLabel.setVisible(false);  // Hidden initially

        scoreLabel = new JLabel();
        scoreLabel.setBounds(300, 450, 200, 30);
        scoreLabel.setForeground(Color.BLACK);  // Black text
        add(scoreLabel);
        scoreLabel.setVisible(false);  // Hidden initially

        wrongAnswersLabel = new JLabel();
        wrongAnswersLabel.setBounds(500, 450, 200, 30);
        wrongAnswersLabel.setForeground(Color.BLACK);  // Black text
        add(wrongAnswersLabel);
        wrongAnswersLabel.setVisible(false);  // Hidden initially
    }

    // Display the next question
    public void displayQuestion() {
        questionLabel.setText(questions[currentQuestion][0]);
        option1.setText(questions[currentQuestion][1]);
        option2.setText(questions[currentQuestion][2]);
        option3.setText(questions[currentQuestion][3]);
        option4.setText(questions[currentQuestion][4]);
        optionsGroup.clearSelection();  // Clear previous selection
    }

    // Check the answer
    public void checkAnswer() {
        String correctAnswer = questions[currentQuestion][5];
        String selectedAnswer = "";

        if (option1.isSelected()) {
            selectedAnswer = option1.getText();
        } else if (option2.isSelected()) {
            selectedAnswer = option2.getText();
        } else if (option3.isSelected()) {
            selectedAnswer = option3.getText();
        } else if (option4.isSelected()) {
            selectedAnswer = option4.getText();
        }

        attempts++;
        if (selectedAnswer.equals(correctAnswer)) {
            score++;
        } else {
            wrongAnswers++;
        }

        // Update result labels
        attemptsLabel.setText("Total Attempts: " + attempts);
        scoreLabel.setText("Score: " + score);
        wrongAnswersLabel.setText("Wrong Answers: " + wrongAnswers);

        // Move to the next question
        currentQuestion++;
        if (currentQuestion < questions.length) {
            displayQuestion();
        } else {
            questionLabel.setText("Quiz Over!");
            option1.setEnabled(false);
            option2.setEnabled(false);
            option3.setEnabled(false);
            option4.setEnabled(false);
            submitButton.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startQuizButton) {
            // Hide start button and heading, show quiz components
            heading.setVisible(false);
            startQuizButton.setVisible(false);
            questionLabel.setVisible(true);
            option1.setVisible(true);
            option2.setVisible(true);
            option3.setVisible(true);
            option4.setVisible(true);
            submitButton.setVisible(true);
            attemptsLabel.setVisible(true);
            scoreLabel.setVisible(true);
            wrongAnswersLabel.setVisible(true);

            // Start displaying the first question
            displayQuestion();
        } else if (e.getSource() == submitButton) {
            checkAnswer();
        }
    }

    public static void main(String[] args) {
        QuizApp quizApp = new QuizApp();
        quizApp.setVisible(true);
    }
}
