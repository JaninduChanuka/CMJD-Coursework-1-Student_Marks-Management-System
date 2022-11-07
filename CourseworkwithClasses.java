import java.util.*;

class Student {
    String id;
    String name;
    int prf;
    int dbms;

    Student(String id, String name) {
        this.id = id;
        this.name = name;
        prf = -1;
        dbms = -1;
    }

    Student(String id, String name, int prf, int dbms) {
        this.id = id;
        this.name = name;
        this.prf = prf;
        this.dbms = dbms;
    }

    public static int searchId(Student[] studentArray, String stId) {
        for (int i = 0; i < studentArray.length; i++) {
            if (studentArray[i].id.equals(stId)) {
                return i;
            }
        }
        return -1;
    }

    public static void sortStudentsByPRF(Student[] studentArray) {
        // sort prf marks in descending order
        for (int i = studentArray.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (studentArray[j].prf < studentArray[j + 1].prf) {
                    Student temp = studentArray[j];

                    studentArray[j] = studentArray[j + 1];

                    studentArray[j + 1] = temp;

                }
            }
        }
    }

    public static void sortStudentsByDBMS(Student[] studentArray) {
        // sort prf marks in descending order
        for (int i = studentArray.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (studentArray[j].dbms < studentArray[j + 1].dbms) {
                    Student temp = studentArray[j];

                    studentArray[j] = studentArray[j + 1];

                    studentArray[j + 1] = temp;
                }
            }
        }
    }

    public static int[] getTotalMarks(Student[] studentArray) {

        sortStudentsByPRF(studentArray);

        int[] total = new int[studentArray.length];

        for (int i = 0; i < studentArray.length; i++) {
            total[i] = studentArray[i].prf + studentArray[i].dbms;
        }

        // sort total marks in descending order
        for (int i = studentArray.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (total[j] < total[j + 1]) {
                    Student temp = studentArray[j];
                    int tempTotal = total[j];

                    studentArray[j] = studentArray[j + 1];
                    total[j] = total[j + 1];

                    studentArray[j + 1] = temp;
                    total[j + 1] = tempTotal;

                }
            }
        }
        return total;
    }
}

class Demo {
	public static Student[] studentArray = new Student[0];
	
	public static void main(String args[]) {
        mainMenu();
    }
   
    public static void mainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|\t\t       WELCOME TO GDSE MARKS MANAGEMENT SYSTEM        \t\t |");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("[1] Add New Student\t\t\t [2] Add New Student With Marks");
        System.out.println("[3] Add Marks\t\t\t\t [4] Update Student Details");
        System.out.println("[5] Update Marks\t\t\t [6] Delete Student");
        System.out.println("[7] Print Student Details\t\t [8] Print Student Ranks");
        System.out.println("[9] Best in Programming Fundementals\t [10] Best in Database Management System");
        System.out.println();

        System.out.print("Enter an option to continue > ");
        int option = input.nextInt();
        clearConsole();
        switch (option) {
            case 1:addNewStudent();break;
            case 2:addNewStudentWithMarks();break;
            case 3:addMarks();break;
            case 4: updateStudentDetails(); break;
            case 5: updateMarks(); break;
            case 6: deleteStudent(); break;
            case 7: printStudentDetails(); break;
            case 8: printStudentRanks(); break;
            case 9: bestInPRF(); break;
            case 10: bestInDBMS(); break;
			default: System.out.println("Invalid Input. Try again.\n");mainMenu(); break;
        }

    }

    public static void addNewStudent() {
        Scanner input = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t ADD NEW STUDENT \t\t\t\t |");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println();

        boolean run = true;

        L1: do {
            Student[] tempStudentArray = new Student[studentArray.length + 1];

            for (int i = 0; i < studentArray.length; i++) {
                tempStudentArray[i] = studentArray[i];
            }

            System.out.print("Enter Student ID	: ");
            String stId = input.next();

            for (int k = 0; k < studentArray.length; k++) {
                if (studentArray[k].id.equals(stId)) {
                    System.out.println(stId + " is already entered.");
                    continue L1;
                }
            }

            System.out.print("Enter Student Name	: ");
            String stName = input.next();

            Student stu = new Student(stId, stName);

            tempStudentArray[tempStudentArray.length - 1] = stu;
            studentArray = tempStudentArray;

            System.out.println();
            System.out.print("Student has been added successfully.");
            L2: do {
                System.out.print("Do you want to add a new student (y/n) : ");
                char option = input.next().charAt(0);
                switch (option) {
					case 'y': case 'Y': clearConsole(); addNewStudent(); break;
                    case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
                    default: System.out.println("\nInvalid Input. Try again."); continue L2;
                }
            } while (true);
        } while (run);

    }

    public static void addNewStudentWithMarks() {
        Scanner input = new Scanner(System.in);

        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|\t\t\t ADD NEW STUDENT WITH MARKS \t\t\t\t |");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println();

        boolean run = true;

        L1: do {
            Student[] tempStudentArray = new Student[studentArray.length + 1];

            for (int i = 0; i < studentArray.length; i++) {
                tempStudentArray[i] = studentArray[i];
            }

            System.out.print("Enter Student ID	: ");
            String stId = input.next();

            for (int k = 0; k < studentArray.length; k++) {
                if (studentArray[k].id.equals(stId)) {
                    System.out.println(stId + " is already entered.");
                    continue L1;
                }
            }

            System.out.print("Enter Student Name	: ");
            String stName = input.next();

            int prf, dbms;
            System.out.println();
            L2: do {
                System.out.print("Programming Fundamentals Marks		: ");
                prf = input.nextInt();

                if (prf >= 0 && prf <= 100) {
                    break;

                } else {
                    System.out.println("Invalid Marks, Please enter correct marks.");
                    continue L2;
                }
            } while (true);

            L3: do {
                System.out.print("Database Management System Marks	: ");
                dbms = input.nextInt();

                if (dbms >= 0 && dbms <= 100) {
                    break;

                } else {
                    System.out.println("Invalid Marks, Please enter correct marks.\n");
                    continue L3;
                }
            } while (true);

            Student stu = new Student(stId, stName, prf, dbms);

            tempStudentArray[tempStudentArray.length - 1] = stu;
            studentArray = tempStudentArray;

            System.out.println();
            System.out.print("Student has been added successfully.");
            L4: do {
                System.out.print("Do you want to add a new student (y/n) : ");
                char option = input.next().charAt(0);
                switch (option) {
                    case 'y': case 'Y': clearConsole(); addNewStudentWithMarks(); break;
                    case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
                    default: System.out.println("\nInvalid Input. Try again."); continue L4;
                }
            } while (true);

        } while (run);

    }

    public static void addMarks() {
        Scanner input = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t ADD MARKS \t\t\t\t\t |");
        System.out.println("----------------------------------------------------------------------------------");

        boolean run = true;

        L1: do {
            System.out.print("\nEnter Student ID	: ");
            String stId = input.next();

            int index = Student.searchId(studentArray,stId);

            if (index != -1) {

                System.out.println("Student Name		: " + studentArray[index].name);

                if (studentArray[index].prf == -1 && studentArray[index].dbms == -1) {

                    System.out.println();
                    L2: do {
                        System.out.print("Programming Fundamentals Marks		: ");
                        int prf = input.nextInt();

                        if (prf >= 0 && prf <= 100) {
                            studentArray[index].prf = prf;
                            break;

                        } else {
                            System.out.println("Invalid Marks, Please enter correct marks.");
                            continue L2;
                        }
                    } while (true);

                    L3: do {
                        System.out.print("Database Management System Marks	: ");
                        int dbms = input.nextInt();

                        if (dbms >= 0 && dbms <= 100) {
                            studentArray[index].dbms = dbms;
                            break;

                        } else {
                            System.out.println("Invalid Marks, Please enter correct marks.\n");
                            continue L3;
                        }
                    } while (true);
                    System.out.println();
                    System.out.print("Marks have been added successfully.");
                    L4: do {
                        System.out.print("Do you want to add marks for another student (y/n) : ");
                        char option = input.next().charAt(0);
                        switch (option) {
                            case 'y': case 'Y': clearConsole(); addMarks(); break;
                            case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
                            default: System.out.println("\nInvalid Input. Try again."); continue L4;
                        }
                    } while (true);
                } else {
                    System.out.println(
                            "\nThis Student's marks have been already added.\nIf you want to update the marks, please use [4] Update Marks option.");
                    L5: do {
                        System.out.print("\nDo you want to add marks for another student (y/n) : ");
                        char option = input.next().charAt(0);
                        switch (option) {
                            case 'y': case 'Y': clearConsole(); addMarks(); break;
                            case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
                            default: System.out.println("Invalid Input. Try again."); continue L5;
                        }
                    } while (true);
                }

            } else {
                L6: do {
                    System.out.print("\nInvalid Student ID. Do you want to search again ? (y/n) : ");
                    char option = input.next().charAt(0);
                    switch (option) {
                        case 'y': case 'Y': continue L1;
                        case 'n': case 'N': run = false; clearConsole(); mainMenu(); break;
                        default: System.out.println("Invalid Input. Try again."); continue L6;
                    }
                } while (true);
            }
        } while (run);
    }

    public static void updateStudentDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tUPDATE STUDENT DETAILS  \t\t\t |");
        System.out.println("----------------------------------------------------------------------------------");

        boolean run = true;

        L1: do {
            System.out.print("\nEnter Student ID	: ");
            String stId = input.next();

            int index = Student.searchId(studentArray, stId);

            if (index != -1) {
                System.out.println("Student Name		: " + studentArray[index].name);
                System.out.print("\nEnter the new Student Name	: ");
                String stName = input.next();
                studentArray[index].name = stName;
                System.out.print("\nStudent details has been updated Successfully.");
                L2: do {
                    System.out.print("\nDo you want to update another student's details? (y/n) : ");
                    char option = input.next().charAt(0);
                    switch (option) {
                        case 'y': case 'Y': clearConsole(); updateStudentDetails(); break;
                        case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
                        default: System.out.println("Invalid Input. Try again."); continue L2;
                    }
                } while (true);
            } else {
                L3: do {
                    System.out.println();
                    System.out.print("Invalid Student ID. Do you want to search again? (y/n) : ");
                    char option = input.next().charAt(0);
                    switch (option) {
                        case 'y': case 'Y': continue L1;
                        case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
                        default: System.out.println("Invalid Input. Try again."); continue L3;
                    }
                } while (true);
            }

        } while (run);
    }

    public static void updateMarks() {
        Scanner input = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tUPDATE MARKS\t\t\t\t\t |");
        System.out.println("----------------------------------------------------------------------------------");

        boolean run = true;

        L1: do {
            System.out.print("\nEnter Student ID	: ");
            String stId = input.next();

            int index = Student.searchId(studentArray, stId);

            if (index == -1) {
                L4: do {
                    System.out.println();
                    System.out.print("Invalid Student ID. Do you want to search again? (y/n) : ");
                    char option = input.next().charAt(0);
                    switch (option) {
                        case 'y': case 'Y': continue L1;
                        case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
                        default: System.out.println("Invalid Input. Try again."); continue L4;
                    }
                } while (true);

            } else if (studentArray[index].prf == -1) {
                System.out.println("Student Name		: " + studentArray[index].name);
                System.out.print("\nThis student's marks yet to be added.");
                L5: do {
                    System.out.print("\nDo you want to update marks for another student? (y/n) : ");
                    char option = input.next().charAt(0);
                    switch (option) {
                        case 'y': case 'Y': clearConsole(); updateMarks(); break;
                        case 'n': case 'N': clearConsole(); run = false; mainMenu(); break; default: System.out.println("Invalid Input. Try again."); continue L5;
                    }
                } while (true);

            } else {
                System.out.println("Student Name		: " + studentArray[index].name);
                System.out.println("Programming Fundamentals Marks		: " + studentArray[index].prf);
                System.out.println("Database Management Systems Marks	: " + studentArray[index].dbms);

                System.out.println();
                L2: do {
                    System.out.print("Enter new Programming Fundamentals Marks	: ");
                    int prf = input.nextInt();

                    if (prf >= 0 && prf <= 100) {
                        studentArray[index].prf = prf;
                        break;

                    } else {
                        System.out.println("Invalid Marks, Please enter correct marks.");
                        continue L2;
                    }
                } while (true);

                L3: do {
                    System.out.print("Enter New Database Management System Marks	: ");
                    int dbms = input.nextInt();

                    if (dbms >= 0 && dbms <= 100) {
                        studentArray[index].dbms = dbms;
                        break;

                    } else {
                        System.out.println("Invalid Marks, Please enter correct marks.\n");
                        continue L3;
                    }
                } while (true);

                System.out.println();
                System.out.print("Marks have been updated successfully.");
                L6: do {
                    System.out.print("\nDo you want to update marks for another student? (y/n) : ");
                    char option = input.next().charAt(0);
                    switch (option) {
                        case 'y': case 'Y': clearConsole(); updateMarks(); break;
                        case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
                        default: System.out.println("Invalid Input. Try again."); continue L6;
                    }
                } while (true);
            }

        } while (run);
    }

    public static void deleteStudent() {
        Scanner input = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tDELETE STUDENT\t\t\t\t\t |");
        System.out.println("----------------------------------------------------------------------------------");

        boolean run = true;

        L1: do {
            System.out.print("\nEnter Student ID	: ");
            String stId = input.next();

            int index = Student.searchId(studentArray, stId);

            if (index == -1) {
                L2: do {
                    System.out.println();
                    System.out.print("Invalid Student ID. Do you want to search again? (y/n) : ");
                    char option = input.next().charAt(0);
                    switch (option) {
                        case 'y': case 'Y': continue L1;
                        case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
                        default: System.out.println("Invalid Input. Try again."); continue L2;
                    }
                } while (true);
            } else {
                for (int i = index; i < studentArray.length - 1; i++) {
                    studentArray[i] = studentArray[i + 1];
                }

                Student[] tempStudentArray = new Student[studentArray.length - 1];

                for (int i = 0; i < tempStudentArray.length; i++) {
                    tempStudentArray[i] = studentArray[i];
                }

                studentArray = tempStudentArray;

                System.out.println();
                System.out.println("Student has been deleted successfully.");
                L3: do {
                    System.out.print("\nDo you want to delete another student? (y/n) : ");
                    char option = input.next().charAt(0);
                    switch (option) {
                        case 'y': case 'Y': clearConsole(); deleteStudent(); break;
                        case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
                        default: System.out.println("Invalid Input. Try again."); continue L3;
                    }
                } while (true);
            }

        } while (run);
    }

    public static void printStudentDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\t\t\tPRINT STUDENT DETAILS\t\t\t\t|");
        System.out.println("-------------------------------------------------------------------------");

        boolean run = true;

        L1: do {
            int[] total = Student.getTotalMarks(studentArray);

            System.out.print("\nEnter Student ID	: ");
            String stId = input.next();

            int index = Student.searchId(studentArray, stId);

            if (index == -1) {

                L2: do {
                    System.out.println();
                    System.out.print("Invalid Student ID. Do you want to search again? (y/n) : ");
                    char option = input.next().charAt(0);
                    switch (option) {
                        case 'y': case 'Y': continue L1;
                        case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
                        default: System.out.println("Invalid Input. Try again."); continue L2;
                    }
                } while (true);

            } else if (studentArray[index].prf == -1) {
                System.out.println("Student Name		: " + studentArray[index].name);
                System.out.print("\nMarks yet to be added.");

                L3: do {
                    System.out.print("\n\nDo you want to search another student details? (y/n) : ");
                    char option = input.next().charAt(0);
                    switch (option) {
                        case 'y': case 'Y': clearConsole(); printStudentDetails(); break;
                        case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
                        default: System.out.println("Invalid Input. Try again."); continue L3;
                    }
                } while (true);

            } else {
                int check = 0;
                for (int i = 0; i < studentArray.length; i++) {
                    if (total[i] == -2) {
                        check = i;
                        break;
                    }
                }

                String rank;
                if (check != 0) {
                    rank = (index + 1) == 1 ? "(First)"
                            : (index + 1) == 2 ? "(Second)"
                                    : (index + 1) == 3 ? "(Third)" : (index + 1) == (check) ? "(Last)" : "";
                } else {
                    rank = (index + 1) == 1 ? "(First)"
                            : (index + 1) == 2 ? "(Second)"
                                    : (index + 1) == 3 ? "(Third)" : (index + 1) == studentArray.length ? "(Last)" : "";
                }

                System.out.println("Student Name		: " + studentArray[index].name);
                System.out.println();
                System.out.println("+----------------------------------+--------------------+");
                System.out.printf("|Programming Fundamentals Marks    |%20d|\n", studentArray[index].prf);
                System.out.printf("|Database Manegment System Marks   |%20d|\n", studentArray[index].dbms);
                System.out.printf("|Total Marks			   |%20d|\n", total[index]);
                System.out.printf("|Avg. Marks  			   |%20.2f|\n", (double) total[index] / 2);
                System.out.printf("|Rank				   |%12d%8s|\n", (index + 1), rank);
                System.out.println("+----------------------------------+--------------------+");
                System.out.println();

                L4: do {
                    System.out.print("Do you want to search another student details? (y/n) : ");
                    char option = input.next().charAt(0);
                    switch (option) {
                        case 'y': case 'Y': clearConsole(); printStudentDetails(); break;
                        case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
                        default: System.out.println("Invalid Input. Try again."); continue L4;
                    }
                } while (true);
            }

        } while (run);
    }

    public static void printStudentRanks() {
        Scanner input = new Scanner(System.in);
        System.out.println("------------------------------------------------------------");
        System.out.println("|\t\t    PRINT STUDENT RANKS\t\t\t   |");
        System.out.println("------------------------------------------------------------");

        System.out.println("+-----+------+----------------------+-----------+----------+");
        System.out.printf("|%-5s|%-6s|%-22s|%-11s|%-10s|\n", "Rank", "ID", "Name", "Total Marks", "Avg. Marks");
        System.out.println("+-----+------+----------------------+-----------+----------+");

        int[] total = Student.getTotalMarks(studentArray);

        int index = 0;
        for (int i = 0; i < studentArray.length; i++) {
            if (total[i] == -2) {
                index = i;
                break;
            }
        }

        if (index != 0) {
            for (int j = 0; j < index; j++) {
                System.out.printf("|%-5d|%-6s|%-22s|%11d|%10.2f|\n", j + 1, studentArray[j].id, studentArray[j].name,
                        total[j],
                        (double) total[j] / 2);
            }
            System.out.println("+-----+------+----------------------+-----------+----------+");

        } else {
            for (int i = 0; i < studentArray.length; i++) {
                System.out.printf("|%-5d|%-6s|%-22s|%11d|%10.2f|\n", i + 1, studentArray[i].id, studentArray[i].name,
                        total[i],
                        (double) total[i] / 2);
            }
            System.out.println("+-----+------+----------------------+-----------+----------+");
        }

        L1: do {
            System.out.println();
            System.out.print("Do you want to go back to main menu? (y/n) : ");
            char option = input.next().charAt(0);
            switch (option) {
                case 'y': case 'Y': clearConsole(); mainMenu(); break;
                case 'n': case 'N': clearConsole(); printStudentRanks(); break;
                default: System.out.println("Invalid Input. Try again."); continue L1;
            }
        } while (true);
    }

    public static void bestInPRF() {
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------");
        System.out.println("|\t     BEST IN PROGRAMMING FUNDEMENTALS\t      |");
        System.out.println("-------------------------------------------------------");

        System.out.println("+------+----------------------+----------+------------+");
        System.out.printf("|%-6s|%-22s|%-10s|%-12s|\n", "ID", "Name", "PRF Marks", "DBMS Marks");
        System.out.println("+------+----------------------+----------+------------+");

        Student.sortStudentsByPRF(studentArray);

        int index = 0;
        for (int i = 0; i < studentArray.length; i++) {
            if (studentArray[i].prf == -1) {
                index = i;
                break;
            }
        }

        if (index != 0) {
            for (int j = 0; j < index; j++) {
                System.out.printf("|%-6s|%-22s|%-10d|%-12d|\n", studentArray[j].id, studentArray[j].name,
                        studentArray[j].prf,
                        studentArray[j].dbms);
            }
            System.out.println("+------+----------------------+----------+------------+");

        } else {
            for (int i = 0; i < studentArray.length; i++) {
                System.out.printf("|%-6s|%-22s|%-10d|%-12d|\n", studentArray[i].id, studentArray[i].name,
                        studentArray[i].prf,
                        studentArray[i].dbms);
            }
            System.out.println("+------+----------------------+----------+------------+");
        }

        L1: do {
            System.out.println();
            System.out.print("Do you want to go back to main menu? (y/n) : ");
            char option = input.next().charAt(0);
            switch (option) {
                case 'y': case 'Y': clearConsole(); mainMenu(); break;
                case 'n': case 'N': clearConsole(); bestInPRF(); break;
                default: System.out.println("Invalid Input. Try again."); continue L1;
            }
        } while (true);
    }

    public static void bestInDBMS() {
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------");
        System.out.println("|\t  BEST IN DATABASE MANAGEMENT SYSTEM\t      |");
        System.out.println("-------------------------------------------------------");

        System.out.println("+------+----------------------+------------+----------+");
        System.out.printf("|%-6s|%-22s|%-12s|%-10s|\n", "ID", "Name", "DBMS Marks", "PRF Marks");
        System.out.println("+------+----------------------+------------+----------+");

        Student.sortStudentsByDBMS(studentArray);

        int index = 0;
        for (int i = 0; i < studentArray.length; i++) {
            if (studentArray[i].prf == -1) {
                index = i;
                break;
            }
        }

        if (index != 0) {
            for (int j = 0; j < index; j++) {
                System.out.printf("|%-6s|%-22s|%-12d|%-10d|\n", studentArray[j].id, studentArray[j].name,
                        studentArray[j].dbms,
                        studentArray[j].prf);
            }
            System.out.println("+------+----------------------+------------+----------+");

        } else {
            for (int i = 0; i < studentArray.length; i++) {
                System.out.printf("|%-6s|%-22s|%-12d|%-10d|\n", studentArray[i].id, studentArray[i].name,
                        studentArray[i].dbms,
                        studentArray[i].prf);
            }
            System.out.println("+------+----------------------+------------+----------+");
        }

        L1: do {
            System.out.println();
            System.out.print("Do you want to go back to main menu? (y/n) : ");
            char option = input.next().charAt(0);
            switch (option) {
                case 'y': case 'Y': clearConsole(); mainMenu(); break;
                case 'n': case 'N': clearConsole(); bestInDBMS(); break;
                default: System.out.println("Invalid Input. Try again."); continue L1;
            }
        } while (true);
    }

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }
}
