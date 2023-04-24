#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int DATABASE_SIZE = 100;
int CURRENT_LAST = 0;

enum Case {
    Student, Scientist, Worker
};
enum ScientistRank {
    Docent, Professor, Doctor, Assistant, Teacher
};
struct UniversityMember {
    Case cs;
    union {
        struct { char department[20]; int course, group, scholarship; } student;
        struct { char department[20]; int salary; ScientistRank rank; } scientist;
        struct { char job[20]; int salary; } worker;
    };
    char surname[20], name[20];
};

void printMenu();
void addRecord(UniversityMember dataBase[]);
void deleteRecord(UniversityMember dataBase[], int record_num);
void readFile(UniversityMember dataBase[], ifstream &file);
void enterRecordStudent(UniversityMember &record, istream &stream);
void enterRecordScientist(UniversityMember &record, istream &stream);
void enterRecordWorker(UniversityMember &record, istream &stream);
void enterRecordName(UniversityMember &record, istream &stream);
void outputDatabase(UniversityMember dataBase[], ostream &stream);
void outputRecordStudent(UniversityMember record, ostream &stream);
void outputRecordScientist(UniversityMember record, ostream &stream);
void outputRecordWorker(UniversityMember record, ostream &stream);
void outputString(char string[], ostream &stream);

int main() {
    setlocale(LC_ALL, "Rus");
    UniversityMember dataBase[DATABASE_SIZE];
    while (true) {
        printMenu();
        int operation;
        cin >> operation;
        char path[30] = {'\0'};
        ifstream ifile;
        ofstream ofile;
        switch(operation) {
            case 1:
                addRecord(dataBase);
                break;
            case 2:
                outputDatabase(dataBase, cout);
                break;
            case 3:
                cout << "Введите номер записи: ";
                cin >> operation;
                deleteRecord(dataBase, operation);
                break;
            case 4:
                cout << "Введите директорию: ";
                cin >> path;
                ifile.open(path);
                if(!ifile.is_open()) {
                    cout << "Файл не открыт";
                    break;
                }
                readFile(dataBase, ifile);
                break;
            case 5:
                cout << "Введите директорию: ";
                cin >> path;
                ofile.open(path);
                if (!ofile.is_open()) {
                    cout << "Файл не открыт";
                    break;
                }
                outputDatabase(dataBase, ofile);
                ofile.close();
                break;
            case 6:
                return 0;
            default:
                cout << "Wrong operation\n";
                break;
        }
    }
}
void printMenu() {
    cout << "Меню СУБД:\n\t"
         << "1. Ввод записи;\n\t"
         << "2. Вывод базы;\n\t"
         << "3. Удаление записи;\n\t"
         << "4. Чтение из файла;\n\t"
         << "5. Запись в файл;\n\t"
         << "6. Закончить работу.\n";
}
void readFile(UniversityMember dataBase[], ifstream &file) {
    int cs;
    while(!file.eof()) {
        UniversityMember record{};
        file >> cs;
        record.cs = static_cast<Case>(cs);
        switch(record.cs) {
            case Student:
                enterRecordStudent(record, file);
                break;
            case Scientist:
                enterRecordScientist(record, file);
                break;
            case Worker:
                enterRecordWorker(record, file);
                break;
        }
        enterRecordName(record, file);
        dataBase[CURRENT_LAST++] = record;
    }
    file.close();
}
void addRecord(UniversityMember dataBase[]) {
    UniversityMember record{};
    int cs;
    cout << "Введите тип человека\n";
    cin >> cs;
    record.cs = static_cast<Case>(cs);
    switch(cs) {
        case 0:
            enterRecordStudent(record, cin);
            break;
        case 1:
            enterRecordScientist(record, cin);
            break;
        case 2:
            enterRecordWorker(record, cin);
            break;
        default:
            break;
    }
    enterRecordName(record, cin);
    dataBase[CURRENT_LAST++] = record;
}
void enterRecordStudent(UniversityMember &record, istream &stream) {
    stream >> record.student.department >> record.student.course >> record.student.group >> record.student.scholarship;
}
void enterRecordScientist(UniversityMember &record, istream &stream) {
    int rank;
    stream >> record.scientist.department >> record.scientist.salary >> rank;
    record.scientist.rank = static_cast<ScientistRank>(rank);
}
void enterRecordWorker(UniversityMember &record, istream &stream) {
    stream >> record.worker.job >> record.worker.salary;
}
void enterRecordName(UniversityMember &record, istream &stream) {
    stream >> record.surname >> record.name;
}
void outputDatabase(UniversityMember dataBase[], ostream &stream) {
    UniversityMember record{};
    for(int i = 0; i < CURRENT_LAST; i++) {
        record = dataBase[i];
        switch(record.cs) {
            case Student:
                outputRecordStudent(record, stream);
                break;
            case Scientist:
                outputRecordScientist(record, stream);
                break;
            case Worker:
                outputRecordWorker(record, stream);
                break;
        }
        outputString(record.surname, stream);
        stream << ' ';
        outputString(record.name, stream);
        if (i + 1 < CURRENT_LAST || stream.rdbuf() == cout.rdbuf()) stream << '\n';
    }
}
void outputRecordStudent(UniversityMember record, ostream &stream) {
    stream << static_cast<int>(record.cs) << ' ';
    outputString(record.student.department, stream);
    stream << ' ' << record.student.course << ' ' << record.student.group << ' ' << record.student.scholarship << ' ';
}
void outputRecordScientist(UniversityMember record, ostream &stream) {
    stream << static_cast<int>(record.cs) << ' ';
    outputString(record.scientist.department, stream);
    stream << ' ' << record.scientist.salary << ' ' << static_cast<int>(record.scientist.rank) << ' ';
}
void outputRecordWorker(UniversityMember record, ostream &stream) {
    stream << static_cast<int>(record.cs) << ' ';
    outputString(record.worker.job, stream);
    stream << ' ' << record.worker.salary << ' ';
}
void outputString(char string[], ostream &stream) {
    int i = 0;
    while(string[i] != '\0') stream << string[i++];
}
void deleteRecord(UniversityMember dataBase[], int record_num) {
    for (int i = record_num + 1; i < CURRENT_LAST; i++) {
        dataBase[i - 1] = dataBase[i];
    }
    CURRENT_LAST--;
}
