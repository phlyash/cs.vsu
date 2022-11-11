import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;

public class GUIApplication extends JFrame{
    private JPanel mainPanel;
    private final DefaultTableModel tableInputModel;
    private JTable tableInput;
    private JButton buttonLoadFile;
    private JButton buttonSave;
    private JButton buttonDeleteRow;
    private JButton buttonSolve;
    private JTable tableOutput;
    private final DefaultTableModel tableOutputModel;
    private JScrollPane JScrollPane;
    private JTextField moneyInput;
    private JTextField moneyOutput;
    private JButton buttonAddRow;
    private final String[] columns = {"Наименование", "Цена"};
    public GUIApplication() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setVisible(true);

        tableInputModel = new DefaultTableModel();
        tableOutputModel = new DefaultTableModel();
        tableInputModel.setColumnIdentifiers(columns);
        tableOutputModel.setColumnIdentifiers(columns);
        tableInput.setModel(tableInputModel);
        tableOutput.setModel(tableOutputModel);

        initializeButtons();

        setContentPane(mainPanel);
    }
    public void initializeButtons() {
        this.buttonAddRow.addActionListener(e -> this.tableInputModel.addRow(new Object[tableInput.getRowHeight()]));
        this.buttonDeleteRow.addActionListener(e -> {
            try {
                this.tableInputModel.removeRow(tableInput.getRowCount() - 1);
            } catch(Exception err) {
                JOptionPane.showMessageDialog(this, "No rows to delete.");
            }
        });
        this.buttonSolve.addActionListener(e -> {
            try {
                CandiesAndMoney answer = Logic.solve(UtilsGUI.castToListOfCandies(tableInputModel.getDataVector()), Integer.parseInt(moneyInput.getText()));
                moneyOutput.setText(Integer.toString(answer.money));
                UtilsGUI.fillTableWithObjects(UtilsGUI.castToObject(answer.candies), this.tableOutputModel);
            } catch(Exception err) {
                JOptionPane.showMessageDialog(this, "Money should be integer.");
            }
        });
        this.buttonLoadFile.addActionListener(e -> selectFile());
        this.buttonSave.addActionListener(e -> saveTableToFile());
    }
    public void selectFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            UtilsGUI.fillTableWithObjects(UtilsGUI.castToObject(FileHandler.readFile(f).candies), this.tableInputModel);
        }
    }
    public void saveTableToFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            FileHandler.writeFile(f, new CandiesAndMoney(UtilsGUI.castToListOfCandies(tableOutputModel.getDataVector()), Integer.parseInt(moneyOutput.getText())));
        }
    }
    public static void main(String[] args) {
        new GUIApplication();
    }
}
