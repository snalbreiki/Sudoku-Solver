import com.formdev.flatlaf.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private Font font1 = new Font("SansSerif", Font.BOLD, 20);
    private JTextField[][] grid = new JTextField[9][9];
    private JMenuBar menuBar;
    private JMenu optionsMenu,HelpMenu;
    private JMenuItem solveItem,clearItem,InfoItem,setThemeItem;

    private SolveItemHandler solveItemHandler;
    private ClearItemHandler clearItemHandler;
    private InfoItemHandler infoItemHandler;
    private SetThemeItemHandler setThemeItemHandler;

    public GUI() {
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600, 600);
        setLocationRelativeTo(null);
        //Pane
        Container Pane = getContentPane();
        setLayout(new GridLayout(9, 9, 0, 1));
        //TextFields
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                JTextField field = new JTextField(10);
                field.setLocation(5,5);
                field.setSize(150,20);
                field.setFont(font1);
                field.setHorizontalAlignment(JTextField.CENTER);
                grid[i][j] = field;
                add(grid[i][j]);
            }
        }
        //menuBar
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        optionsMenu = new JMenu("Options");
        menuBar.add(optionsMenu);

        solveItem = new JMenuItem("Solve");
        optionsMenu.add(solveItem);
        solveItemHandler = new SolveItemHandler();
        solveItem.addActionListener(solveItemHandler);

        clearItem = new JMenuItem("Clear Board");
        optionsMenu.add(clearItem);
        clearItemHandler = new ClearItemHandler();
        clearItem.addActionListener(clearItemHandler);

        HelpMenu = new JMenu("Help");
        menuBar.add(HelpMenu);
        InfoItem = new JMenuItem("Info");
        HelpMenu.add(InfoItem);
        infoItemHandler = new InfoItemHandler();
        InfoItem.addActionListener(infoItemHandler);

        setThemeItem = new JMenuItem("Set Theme");
        optionsMenu.add(setThemeItem);
        setThemeItemHandler = new SetThemeItemHandler();
        setThemeItem.addActionListener(setThemeItemHandler);
        //Pane
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                add(grid[i][j]);
            }
        }
        setVisible(true);
    }

    int savedGrid[][] = new int[9][9];

    private class SolveItemHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    try {
                        savedGrid[i][j] = Integer.parseInt(grid[i][j].getText());
                    } catch (Exception e1) {
                        savedGrid[i][j] = 0;
                    }
                }
            }
            if (SudokuSolver.solveBoard(savedGrid)) {
                for (int i = 0; i < 9; ++i) {
                    for (int j = 0; j < 9; ++j) {
                        grid[i][j].setText(String.valueOf(savedGrid[i][j]));
                    }
                }
                JOptionPane.showMessageDialog(null, "Solved successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Cannot Be Solved");

            }
        }
    }

    private class ClearItemHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    grid[i][j].setText(null);
                }
            }
        }
    }

    private class InfoItemHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Sudoku Solver by S3eed;");
        }
    }

    private class SetThemeItemHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] selections = { "Light", "Dark" };
            Object val = JOptionPane.showInputDialog(null, "Choose theme",
                    "Application Theme", JOptionPane.INFORMATION_MESSAGE, null,
                    selections, selections[0]);
            //

            try {
                if (val == selections[0])
                    UIManager.setLookAndFeel(new FlatIntelliJLaf() {});
                else
                    UIManager.setLookAndFeel(new FlatDarculaLaf() {});
            } catch( Exception ex ){
                System.err.println( "Failed to initialize LaF" );
            }
            GUI s1 = new GUI();
        }
    }
}
