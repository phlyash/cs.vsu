#include <iostream>
#include <fstream>

using namespace std;

enum Case {
    Student, Scientist, Worker
};
enum ScientistRank {
    Docent, Professor, Doctor, Assistant, Teacher
};
struct UniversityMember {
    UniversityMember *next = nullptr;
    Case cs;
    union {
        struct { char department[20]; int course, group, scholarship; } student;
        struct { char department[20]; int salary; ScientistRank rank; } scientist;
        struct { char job[20]; int salary; } worker;
    };
    char surname[20], name[20];
};

void printMenu();
void addRecord();
void deleteRecord(int record_num);
void readFile(ifstream &file);
void enterRecordStudent(UniversityMember &record, istream &stream);
void enterRecordScientist(UniversityMember &record, istream &stream);
void enterRecordWorker(UniversityMember &record, istream &stream);
void enterRecordName(UniversityMember &record, istream &stream);
void outputDatabase(ostream &stream);
void outputRecordStudent(UniversityMember record, ostream &stream);
void outputRecordScientist(UniversityMember record, ostream &stream);
void outputRecordWorker(UniversityMember record, ostream &stream);
void outputString(char string[], ostream &stream);

UniversityMember *startDataBase = nullptr, *endDataBase = nullptr;

int main() {
    setlocale(LC_ALL, "Rus");
    while (true) {
        printMenu();
        int operation;
        cin >> operation;
        char path[30] = {'\0'};
        ifstream ifile;
        ofstream ofile;
        switch(operation) {
            case 1:
                addRecord();
                break;
            case 2:
                outputDatabase(cout);
                break;
            case 3:
                cout << "Введите номер записи: ";
                cin >> operation;
                deleteRecord(operation);
                break;
            case 4:
                cout << "Введите директорию: ";
                cin >> path;
                ifile.open(path);
                if(!ifile.is_open()) {
                    cout << "Файл не открыт";
                    break;
                }
                readFile(ifile);
                break;
            case 5:
                cout << "Введите директорию: ";
                cin >> path;
                ofile.open(path);
                if (!ofile.is_open()) {
                    cout << "Файл не открыт";
                    break;
                }
                outputDatabase(ofile);
                ofile.close();
                break;
            case 6:
                while(startDataBase != nullptr) {
                    endDataBase = startDataBase;
                    startDataBase = startDataBase->next;
                    delete endDataBase;
                }
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
void readFile(ifstream &file) {
    int cs;
    while(!file.eof()) {
        auto *record = new UniversityMember;
        file >> cs;
        record->cs = static_cast<Case>(cs);
        switch(record->cs) {
            case Student:
                enterRecordStudent(*record, file);
                break;
            case Scientist:
                enterRecordScientist(*record, file);
                break;
            case Worker:
                enterRecordWorker(*record, file);
                break;
        }
        enterRecordName(*record, file);
        if(startDataBase == nullptr) {
            startDataBase = record;
            endDataBase = record;
        }
        else {
            endDataBase->next = record;
            endDataBase = endDataBase->next;
        }
    }
    file.close();
}
void addRecord() {
    auto *record = new UniversityMember;
    int cs;
    cout << "Введите тип человека\n";
    cin >> cs;
    record->cs = static_cast<Case>(cs);
    switch(cs) {
        case 0:
            enterRecordStudent(*record, cin);
            break;
        case 1:
            enterRecordScientist(*record, cin);
            break;
        case 2:
            enterRecordWorker(*record, cin);
            break;
        default:
            break;
    }
    enterRecordName(*record, cin);
    if(startDataBase == nullptr) {
        startDataBase = record;
        endDataBase = record;
    }
    else {
        endDataBase->next = record;
        endDataBase = endDataBase->next;
    }
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
void outputDatabase(ostream &stream) {
    UniversityMember *record = startDataBase;
    while(record != nullptr) {
        switch(record->cs) {
            case Student:
                outputRecordStudent(*record, stream);
                break;
            case Scientist:
                outputRecordScientist(*record, stream);
                break;
            case Worker:
                outputRecordWorker(*record, stream);
                break;
        }
        outputString(record->surname, stream);
        stream << ' ';
        outputString(record->name, stream);
        if (record->next != nullptr || stream.rdbuf() == cout.rdbuf()) stream << '\n';
        record = record->next;
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
void deleteRecord(int record_num) {
    UniversityMember *record = startDataBase;
    if(record_num == 1) {
        startDataBase = startDataBase->next;
        delete record;
        return;
    }
    for (int i = 0; i < record_num - 2; i++) {
        record = record->next;
        if (record->next == nullptr) return;
    }
    UniversityMember *pointerToDelete = record->next;
    if(record->next != nullptr)  record->next = record->next->next;
    else record->next = nullptr;
    delete pointerToDelete;
}
