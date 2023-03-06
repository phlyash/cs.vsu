import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GUIApplication extends JFrame implements Runnable {
    private JTable tableInput;
    private final DefaultTableModel tableInputModel;
    private JButton buttonLoadFile;
    private JButton buttonSaveToFile;
    private JButton buttonAddRow;
    private JButton buttonDeleteRow;
    private JButton buttonSolve;
    private JTable tableOutput;
    private JPanel mainPanel;
    private final DefaultTableModel tableOutputModel;
    private final String[] columns = {"число"};
    public GUIApplication() {
        tableInputModel = new DefaultTableModel();
        tableOutputModel = new DefaultTableModel();
        tableInputModel.setColumnIdentifiers(columns);
        tableOutputModel.setColumnIdentifiers(columns);
        tableInput.setModel(tableInputModel);
        tableOutput.setModel(tableOutputModel);
    }
    public void initializeButtons() {
        this.buttonLoadFile.addActionListener(e -> selectFile());
        this.buttonSaveToFile.addActionListener(e -> saveTableToFile());
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
                List<Integer> answer = Logic.createNewList(UtilsGUI.castToInteger(tableInputModel.getDataVector()));
                UtilsGUI.fillTableWithObjects(UtilsGUI.castToObject(answer), this.tableOutputModel);
            } catch(Exception err) {
                JOptionPane.showMessageDialog(this, "It should be Integer.");
            }
        });
    }
    public void selectFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            UtilsGUI.fillTableWithObjects(UtilsGUI.castToObject(FileHandler.readFile(f)), this.tableInputModel);
        }
    }
    public void saveTableToFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            FileHandler.writeFile(f, new ArrayList<>(UtilsGUI.castToInteger(tableOutputModel.getDataVector())));
        }
    }
    @Override
    public void run() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setVisible(true);

        initializeButtons();

        setContentPane(mainPanel);
    }
}
