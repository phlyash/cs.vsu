import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UtilsGUI {
    public static List<Candy> castToListOfCandies(Vector<Vector> arr) {
        List<Candy> candies = new ArrayList<>();
        for (var vector : arr) {
            candies.add(new Candy(vector.get(0).toString(), Integer.parseInt(vector.get(1).toString())));
        }
        return candies;
    }
    public static void fillTableWithObjects(Object[][] arr, DefaultTableModel dtm) {
        dtm.setDataVector(arr, new String[] {"Наименование", "Цена"});
        dtm.fireTableDataChanged();
    }
    public static Object[][] castToObject(List<Candy> candies) {
        Object[][] cast = new Object[candies.size()][2];
        for(int i = 0; i < candies.size(); i++) {
            cast[i][0] = candies.get(i).name;
            cast[i][1] = candies.get(i).price;
        }
        return cast;
    }
}
