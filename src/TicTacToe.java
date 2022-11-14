import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TicTacToe {
    private JFrame frame;
    private JPanel contentPane;
    private JButton exitButton;
    private JButton resetButton;
    private JTextField playerXScore;
    private JTextField playerOScore;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private int xScore;
    private int oScore;
    private String player;

    private ArrayList<JButton> buttons;

    public TicTacToe() {
        xScore = 0;
        oScore = 0;
        player = "X";
        buttons = new ArrayList<JButton>();

        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btn7);
        buttons.add(btn8);
        buttons.add(btn9);

        render();
        initHandlers();

        playerXScore.setText(String.valueOf(xScore));
        playerOScore.setText(String.valueOf(oScore));
    }

    private void render() {
        frame = new JFrame();
        frame.setContentPane(contentPane);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initHandlers() {
        resetButton.addActionListener(e -> onReset());
        exitButton.addActionListener(e -> onExit());

        buttons.forEach(button -> button.addActionListener(e -> {
            if (!button.getText().equalsIgnoreCase("")) return;

            button.setText(player);

            if (player.equalsIgnoreCase("X")) button.setForeground(Color.RED);
            else button.setForeground(Color.BLUE);

            choosePlayer();
            verifyWin();
            verifyTie();
        }));
    }

    private void verifyTie() {
        AtomicInteger sum = new AtomicInteger(0);

        buttons.forEach(button -> {
            if (!button.getText().equalsIgnoreCase("")) sum.getAndIncrement();
        });

        System.out.println(sum);

        boolean compare = sum.intValue() == 9;

        System.out.println(compare);

        if (compare) {
            JOptionPane.showMessageDialog(frame, "It's a tie!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);

            onReset();
        }
    }

    private void verifyWin() {
        ArrayList<String> players = new ArrayList<>();
        players.add("X");
        players.add("O");

        players.forEach(player -> {
            if (btn1.getText().equals(player) && btn2.getText().equals(player) && btn3.getText().equals(player)) {
                handleWin(player);

                return;
            }

            if (btn4.getText().equals(player) && btn5.getText().equals(player) && btn6.getText().equals(player)) {
                handleWin(player);

                return;
            }

            if (btn7.getText().equals(player) && btn8.getText().equals(player) && btn9.getText().equals(player)) {
                handleWin(player);

                return;
            }

            if (btn1.getText().equals(player) && btn4.getText().equals(player) && btn7.getText().equals(player)) {
                handleWin(player);

                return;
            }

            if (btn2.getText().equals(player) && btn5.getText().equals(player) && btn8.getText().equals(player)) {
                handleWin(player);

                return;
            }

            if (btn3.getText().equals(player) && btn6.getText().equals(player) && btn9.getText().equals(player)) {
                handleWin(player);

                return;
            }

            if (btn1.getText().equals(player) && btn5.getText().equals(player) && btn9.getText().equals(player)) {
                handleWin(player);

                return;
            }

            if (btn3.getText().equals(player) && btn5.getText().equals(player) && btn7.getText().equals(player)) {
                handleWin(player);

                return;
            }
        });
    }

    private void handleWin(String player) {
        JOptionPane.showMessageDialog(frame, "Player " + player + " wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);

        if(player.equalsIgnoreCase("X")) xScore++;
        else oScore++;

        onReset();
    }

    private void onReset() {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");

        playerXScore.setText(String.valueOf(xScore));
        playerOScore.setText(String.valueOf(oScore));
    }

    private void onExit() {
        if (JOptionPane.showConfirmDialog(frame, "Do you really want to exit?", "Tic Tac Toe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }
    }

    private void choosePlayer() {
        if (player.equalsIgnoreCase("X")) player = "O";
        else player = "X";
    }
}
