import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UtilsGUI {
    public static List<Integer> castToInteger(Vector<Vector> arr) {
        List<Integer> arrayList = new ArrayList<>();
        for (var vector : arr) {
            arrayList.add(Integer.parseInt(vector.get(0).toString()));
        }
        return arrayList;
    }
    public static void fillTableWithObjects(Object[][] arr, DefaultTableModel dtm) {
        dtm.setDataVector(arr, new String[] {"числа"});
        dtm.fireTableDataChanged();
    }
    public static Object[][] castToObject(List<Integer> arr) {
        Object[][] cast = new Object[arr.size()][1];
        for(int i = 0; i < arr.size(); i++) {
            cast[i][0] = arr.get(i);
        }
        return cast;
    }
}
