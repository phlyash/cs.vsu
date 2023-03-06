import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class GUIApplication extends JFrame{
    private final Object[][] a = new String[][] {{}};
    DefaultTableModel dtm = new DefaultTableModel(a, createHeader(a[0].length));
    JTable mainTable = new JTable(dtm);
    ActionListener addRowButtonOnClick = e -> dtm.addRow(new Object[mainTable.getRowHeight()]);
    ActionListener addColumnButtonOnClick = e -> dtm.addColumn(new Object[mainTable.getColumnCount()]);
    ActionListener deleteRowButtonOnClick = e -> dtm.removeRow(mainTable.getRowCount() - 1);
    ActionListener deleteColumnButtonOnClick = e -> dtm.setColumnCount(mainTable.getColumnCount() - 1);
    ActionListener openFileButtonOnClick = e -> selectFile();
    ActionListener runButtonOnClick = e -> run();
    ActionListener saveButtonOnClick = e -> saveTableToFile();
    public void selectFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            fillTableWithObjects(castPrimitives(FileHandler.readFile(f)));
        }
    }
    public void fillTableWithObjects(Object[][] arr) {
        dtm.setDataVector(arr, createHeader(arr.length));
        dtm.fireTableStructureChanged();
        dtm.fireTableDataChanged();
    }
    public Object[][] castPrimitives(int[][] arr) {
        Object[][] cast = new Object[arr.length][];
        for(int i = 0; i < arr.length; i++) {
            cast[i] = new Object[arr[i].length];
            for(int j = 0; j < arr[i].length; j++) {
                cast[i][j] = arr[i][j];
            }
        }
        return cast;
    }
    public int[][] castToPrimitives(Vector<Vector> arr) {
        int[][] primitives = new int[arr.capacity()][];
        for(int i = 0; i < arr.size(); i++) {
            primitives[i] = new int[arr.elementAt(i).capacity()];
            for(int j = 0; j < arr.elementAt(i).size(); j++) {
                primitives[i][j] = Integer.parseInt(arr.elementAt(i).elementAt(j).toString());
            }
        }
        return primitives;
    }
    public void run() {
        fillTableWithObjects(castPrimitives(Logic.solve(castToPrimitives(dtm.getDataVector()))));
    }
    public void saveTableToFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            FileHandler.writeFile(f, castToPrimitives(dtm.getDataVector()));
        }
    }
    public Object[] createHeader(int num) {
        return new String[num];
    }
    public void initializeButtons(JButton addRow, JButton addCol, JButton delRow, JButton delCol, JButton selF, JButton run, JButton save, Box contents) {
        addRow.addActionListener(addRowButtonOnClick);
        addCol.addActionListener(addColumnButtonOnClick);
        delRow.addActionListener(deleteRowButtonOnClick);
        delCol.addActionListener(deleteColumnButtonOnClick);
        selF.addActionListener(openFileButtonOnClick);
        run.addActionListener(runButtonOnClick);
        save.addActionListener(saveButtonOnClick);

        contents.add(addRow);
        contents.add(addCol);
        contents.add(delRow);
        contents.add(delCol);
        contents.add(selF);
        contents.add(run);
        contents.add(save);
    }
    public GUIApplication() {
        super("Task 8 GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Box contents = new Box(BoxLayout.Y_AXIS);

        mainTable.setTableHeader(null);

        contents.add(new JScrollPane(mainTable));

        JButton addRowButton = new JButton("add row");
        JButton addColumnButton = new JButton("add column");
        JButton deleteRowButton = new JButton("delete row");
        JButton deleteColumnButton = new JButton("delete column");
        JButton selectFileButton = new JButton("select file");
        JButton runButton = new JButton("run");
        JButton saveButton = new JButton("save");

        initializeButtons(addRowButton, addColumnButton, deleteRowButton, deleteColumnButton, selectFileButton, runButton, saveButton, contents);

        setSize(500, 400);
        setVisible(true);
        setContentPane(contents);
    }
    public static void main(String[] args) {
        new GUIApplication();
    }
}