import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGameGUI extends JFrame {
    private int randomNumber;
    private int attemptsLeft = 5;
    private JLabel promptLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;

    public NumberGameGUI() {
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(4, 1));

        promptLabel = new JLabel("Enter your guess (1-100):");
        guessField = new JTextField();
        guessButton = new JButton("Guess");
        resultLabel = new JLabel("");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userGuess = Integer.parseInt(guessField.getText());
                compareGuess(userGuess);
            }
        });

        add(promptLabel);
        add(guessField);
        add(guessButton);
        add(resultLabel);

        generateRandomNumber();

        setVisible(true);
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
    }

    private void compareGuess(int userGuess) {
        if (userGuess == randomNumber) {
            resultLabel.setText("Congratulations! You guessed the number.");
            guessButton.setEnabled(false);
        } else if (userGuess < randomNumber) {
            resultLabel.setText("Too low! Attempts left: " + (--attemptsLeft));
        } else {
            resultLabel.setText("Too high! Attempts left: " + (--attemptsLeft));
        }

        if (attemptsLeft == 0) {
            resultLabel.setText("Out of attempts. The number was: " + randomNumber);
            guessButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGameGUI();
            }
        });
    }
}
