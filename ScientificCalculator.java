import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator {
    private JFrame frame;
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton, eqButton, clrButton;
    private JButton sinButton, cosButton, tanButton, expButton, sqrtButton, powButton;
    private JPanel panel;

    private double num1, num2, result;
    private char operator;

    public ScientificCalculator() {
        frame = new JFrame("Scientific Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(30, 30, 330, 50);
        textField.setEditable(false);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new NumberButtonListener());
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        eqButton = new JButton("=");
        clrButton = new JButton("C");

        functionButtons = new JButton[]{addButton, subButton, mulButton, divButton, eqButton, clrButton};
        for (JButton button : functionButtons) {
            button.addActionListener(new FunctionButtonListener());
        }

        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        tanButton = new JButton("tan");
        expButton = new JButton("exp");
        sqrtButton = new JButton("sqrt");
        powButton = new JButton("pow");

        JButton[] scientificButtons = {sinButton, cosButton, tanButton, expButton, sqrtButton, powButton};
        for (JButton button : scientificButtons) {
            button.addActionListener(new ScientificButtonListener());
        }

        panel = new JPanel();
        panel.setBounds(30, 100, 330, 300);
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        panel.add(numberButtons[0]);
        panel.add(eqButton);
        panel.add(clrButton);
        panel.add(divButton);

        panel.add(sinButton);
        panel.add(cosButton);
        panel.add(tanButton);
        panel.add(expButton);
        panel.add(sqrtButton);
        panel.add(powButton);

        frame.add(textField);
        frame.add(panel);

        frame.setVisible(true);
    }

    class NumberButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String currentText = textField.getText();
            JButton button = (JButton) e.getSource();
            textField.setText(currentText + button.getText());
        }
    }

    class FunctionButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();

            if (buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("*") || buttonText.equals("/")) {
                num1 = Double.parseDouble(textField.getText());
                operator = buttonText.charAt(0);
                textField.setText("");
            } else if (buttonText.equals("=")) {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            textField.setText("Error");
                            return;
                        }
                        break;
                }
                textField.setText(String.valueOf(result));
            } else if (buttonText.equals("C")) {
                textField.setText("");
            }
        }
    }

    class ScientificButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            double value = Double.parseDouble(textField.getText());
            double result = 0;

            switch (buttonText) {
                case "sin":
                    result = Math.sin(value);
                    break;
                case "cos":
                    result = Math.cos(value);
                    break;
                case "tan":
                    result = Math.tan(value);
                    break;
                case "exp":
                    result = Math.exp(value);
                    break;
                case "sqrt":
                    result = Math.sqrt(value);
                    break;
                case "pow":
                    num1 = value;
                    operator = '^';
                    textField.setText("");
                    return;
            }

            textField.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}
