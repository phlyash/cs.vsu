#include <iostream>

using namespace std;
const int WORD_SIZE = 100;

void initialize(char** arr, int n);
void sort(char** arr, int n);
void print(char** arr, int n);
int stringComparator(char* str, char* str2);
void deleteMatrix(char** arr, int n);
int main() {
    int n;
    cout << "Enter a number of words: ";
    cin >> n;

    char** arr = new char* [n];
    initialize(arr, n);
    sort(arr, n);
    print(arr, n);

    deleteMatrix(arr, n);

    return 0;
}
void initialize(char** arr, int n) {
    for (int i = 0; i < n; i++) {
        char word[WORD_SIZE] = { '\0' };
        cin >> word;
        int j = 0;
        while (word[j] != '\0') j++;
        arr[i] = new char[j + 1];
        arr[i][0] = ++j;
        j = 0;
        while (word[j] != '\0') arr[i][j + 1] = word[j++];
    }
}
int stringComparator(char* str1, char* str2) {
    int size = (str1[0] > str2[0]) ? str1[0] : str2[0];
    for (int i = 1; i < size; i++) {
        if (i > str1[0]) return -1;
        if (i > str2[0]) return 1;
        if (str1[i] > str2[i]) return 1;
        if (str1[i] < str2[i]) return -1;
    }
    return 0;
}
void sort(char** arr, int n) {
    double factor = 1.24;
    int step = n - 1;

    while (step >= 1) {
        for (int i = 0; i + step < n; i++) {
            if (stringComparator(arr[i], arr[i + step]) > 0) {
                swap(arr[i], arr[i + step]);
            }
        }
        step /= factor;
    }
}
void print(char** arr, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 1; j < arr[i][0]; j++) {
            cout << arr[i][j];
        }
        cout << endl;
    }
}
void deleteMatrix(char** arr, int n) {
    for (int i = 0; i < n; i++) {
        delete[] arr[i];
    }
    delete[] arr;
}
