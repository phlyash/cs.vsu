public class Holidays {
    boolean[][] holidaysArray = new boolean[12][31];
    public Holidays(){
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 31; j++){
                holidaysArray[i][j] = false;
            }
        }
        for(int i = 2; i < 7; i++) holidaysArray[0][i] = true;
        holidaysArray[1][22] = true;
        holidaysArray[2][6] = true;
        holidaysArray[2][7] = true;
        holidaysArray[4][1] = true;
        holidaysArray[4][2] = true;
        holidaysArray[4][8] = true;
        holidaysArray[4][9] = true;
        holidaysArray[5][12] = true;
        holidaysArray[10][3] = true;
    }
}
