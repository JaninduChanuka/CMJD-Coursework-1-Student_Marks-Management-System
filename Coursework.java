import java.util.*;

class Coursework {

	public static String[] studentName = new String[0];
	public static String[] studentId = new String[0];
	public static int[] prfMarks = new int[0];
	public static int[] dbmsMarks = new int[0];

	public static int searchId(String stId) {
		for (int i = 0; i < studentId.length; i++) {
			if (studentId[i].equals(stId)) {
				return i;
			}
		}
		return -1;
	}

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
			case 1: addNewStudent(); break;
			case 2: addNewStudentWithMarks(); break;
			case 3: addMarks(); break;
			case 4: updateStudentDetails(); break;
			case 5: updateMarks(); break;
			case 6: deleteStudent(); break;
			case 7: printStudentDetails(); break;
			case 8: printStudentRanks(); break;
			case 9: bestInPRF(); break;
			case 10: bestInDBMS(); break;
			default: System.out.println("Invalid Input. Try again.\n"); mainMenu(); break;
		}

	}

	public static void bestInDBMS() {
		Scanner input = new Scanner(System.in);
		System.out.println("-------------------------------------------------------");
		System.out.println("|\t  BEST IN DATABASE MANAGEMENT SYSTEM\t      |");
		System.out.println("-------------------------------------------------------");

		System.out.println("+------+----------------------+------------+----------+");
		System.out.printf("|%-6s|%-22s|%-12s|%-10s|\n", "ID", "Name", "DBMS Marks", "PRF Marks");
		System.out.println("+------+----------------------+------------+----------+");

		sortStudentsByDBMS();

		int index = 0;
		for (int i = 0; i < prfMarks.length; i++) {
			if (prfMarks[i] == -1) {
				index = i;
				break;
			}
		}

		if (index != 0) {
			for (int j = 0; j < index; j++) {
				System.out.printf("|%-6s|%-22s|%-12d|%-10d|\n", studentId[j], studentName[j], dbmsMarks[j],
						prfMarks[j]);
			}
			System.out.println("+------+----------------------+------------+----------+");

		} else {
			for (int i = 0; i < prfMarks.length; i++) {
				System.out.printf("|%-6s|%-22s|%-12d|%-10d|\n", studentId[i], studentName[i], dbmsMarks[i],
						prfMarks[i]);
			}
			System.out.println("+------+----------------------+------------+----------+");
		}

		L1: do {
			System.out.println();
			System.out.print("Do you want to go back to main menu? (y/n) : ");
			char option = input.next().charAt(0);
			switch (option) {
				case 'y': case 'Y': clearConsole();mainMenu(); break;
				case 'n': case 'N': clearConsole();bestInDBMS(); break;
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

		sortStudentsByPRF();

		int index = 0;
		for (int i = 0; i < prfMarks.length; i++) {
			if (prfMarks[i] == -1) {
				index = i;
				break;
			}
		}

		if (index != 0) {
			for (int j = 0; j < index; j++) {
				System.out.printf("|%-6s|%-22s|%-10d|%-12d|\n", studentId[j], studentName[j], prfMarks[j],
						dbmsMarks[j]);
			}
			System.out.println("+------+----------------------+----------+------------+");

		} else {
			for (int i = 0; i < prfMarks.length; i++) {
				System.out.printf("|%-6s|%-22s|%-10d|%-12d|\n", studentId[i], studentName[i], prfMarks[i],
						dbmsMarks[i]);
			}
			System.out.println("+------+----------------------+----------+------------+");
		}

		
		L1: do {
			System.out.println();
			System.out.print("Do you want to go back to main menu? (y/n) : ");
			char option = input.next().charAt(0);
			switch (option) {
				case 'y': case 'Y': clearConsole();mainMenu(); break;
				case 'n': case 'N': clearConsole();bestInPRF(); break;
				default: System.out.println("Invalid Input. Try again."); continue L1;
			}
		} while (true);
	}

	public static void printStudentRanks() {
		Scanner input = new Scanner(System.in);
		System.out.println("------------------------------------------------------------");
		System.out.println("|\t\t    PRINT STUDENT RANKS\t\t\t   |");
		System.out.println("------------------------------------------------------------");

		System.out.println("+-----+------+----------------------+-----------+----------+");
		System.out.printf("|%-5s|%-6s|%-22s|%-11s|%-10s|\n", "Rank", "ID", "Name", "Total Marks", "Avg. Marks");
		System.out.println("+-----+------+----------------------+-----------+----------+");

		int[] total = totalMarks();

		int index = 0;
		for (int i = 0; i < prfMarks.length; i++) {
			if (total[i] == -2) {
				index = i;
				break;
			}
		}

		if (index != 0) {
			for (int j = 0; j < index; j++) {
				System.out.printf("|%-5d|%-6s|%-22s|%11d|%10.2f|\n", j + 1, studentId[j], studentName[j], total[j],
						(double) total[j] / 2);
			}
			System.out.println("+-----+------+----------------------+-----------+----------+");

		} else {
			for (int i = 0; i < prfMarks.length; i++) {
				System.out.printf("|%-5d|%-6s|%-22s|%11d|%10.2f|\n", i + 1, studentId[i], studentName[i], total[i],
						(double) total[i] / 2);
			}
			System.out.println("+-----+------+----------------------+-----------+----------+");
		}

		L1: do {
			System.out.println();
			System.out.print("Do you want to go back to main menu? (y/n) : ");
			char option = input.next().charAt(0);
			switch (option) {
				case 'y': case 'Y': clearConsole();mainMenu(); break;
				case 'n': case 'N': clearConsole();printStudentRanks(); break;
				default: System.out.println("Invalid Input. Try again."); continue L1;
			}
		} while (true);

	}

	public static void printStudentDetails() {
		Scanner input = new Scanner(System.in);
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("|\t\t\tPRINT STUDENT DETAILS\t\t\t\t|");
		System.out.println("-------------------------------------------------------------------------");

		boolean run = true;

		L1: do {
			int[] total = totalMarks();

			System.out.print("\nEnter Student ID	: ");
			String stId = input.next();

			int index = searchId(stId);

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

			} else if (prfMarks[index] == -1) {
				System.out.println("Student Name		: " + studentName[index]);
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
				for (int i = 0; i < prfMarks.length; i++) {
					if (total[i] == -2) {
						check = i;
						break;
					}
				}

				String rank;
				if (check != 0) {
					rank = (index + 1) == 1 ? "(First)": (index + 1) == 2 ? "(Second)" : (index + 1) == 3 ? "(Third)" : (index+1) == (check) ? "(Last)" : "";
				} else {
					rank = (index + 1) == 1 ? "(First)" : (index + 1) == 2 ? "(Second)" : (index + 1) == 3 ? "(Third)" : (index + 1) == prfMarks.length ? "(Last)" : "";
				}

				System.out.println("Student Name		: " + studentName[index]);
				System.out.println();
				System.out.println("+----------------------------------+--------------------+");
				System.out.printf("|Programming Fundamentals Marks    |%20d|\n", prfMarks[index]);
				System.out.printf("|Database Manegment System Marks   |%20d|\n", dbmsMarks[index]);
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

	public static void deleteStudent() {
		Scanner input = new Scanner(System.in);
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("|\t\t\t\tDELETE STUDENT\t\t\t\t\t |");
		System.out.println("----------------------------------------------------------------------------------");

		boolean run = true;

		L1: do {
			System.out.print("\nEnter Student ID	: ");
			String stId = input.next();

			int index = searchId(stId);

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
				for (int i = index; i < studentId.length - 1; i++) {
					studentId[i] = studentId[i + 1];
					studentName[i] = studentName[i + 1];
					prfMarks[i] = prfMarks[i + 1];
					dbmsMarks[i] = dbmsMarks[i + 1];
				}

				String[] temp1 = new String[studentId.length - 1];
				String[] temp2 = new String[studentName.length - 1];
				int[] temp3 = new int[prfMarks.length - 1];
				int[] temp4 = new int[dbmsMarks.length - 1];

				for (int i = 0; i < temp1.length; i++) {
					temp1[i] = studentId[i];
					temp2[i] = studentName[i];
					temp3[i] = prfMarks[i];
					temp4[i] = dbmsMarks[i];
				}

				studentId = temp1;
				studentName = temp2;
				prfMarks = temp3;
				dbmsMarks = temp4;

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

	public static void updateMarks() {
		Scanner input = new Scanner(System.in);
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("|\t\t\t\tUPDATE MARKS\t\t\t\t\t |");
		System.out.println("----------------------------------------------------------------------------------");

		boolean run = true;

		L1: do {
			System.out.print("\nEnter Student ID	: ");
			String stId = input.next();

			int index = searchId(stId);

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

			} else if (prfMarks[index] == -1) {
				System.out.println("Student Name		: " + studentName[index]);
				System.out.print("\nThis student's marks yet to be added.");
				L5: do {
					System.out.print("\nDo you want to update marks for another student? (y/n) : ");
					char option = input.next().charAt(0);
					switch (option) {
						case 'y': case 'Y': clearConsole(); updateMarks(); break;
						case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
						default: System.out.println("Invalid Input. Try again."); continue L5;
					}
				} while (true);

			} else {
				System.out.println("Student Name		: " + studentName[index]);
				System.out.println("Programming Fundamentals Marks		: " + prfMarks[index]);
				System.out.println("Database Management Systems Marks	: " + dbmsMarks[index]);

				System.out.println();
				L2: do {
					System.out.print("Enter new Programming Fundamentals Marks	: ");
					int prf = input.nextInt();

					if (prf >= 0 && prf <= 100) {
						prfMarks[index] = prf;
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
						dbmsMarks[index] = dbms;
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

	public static void updateStudentDetails() {
		Scanner input = new Scanner(System.in);
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("|\t\t\t\tUPDATE STUDENT DETAILS  \t\t\t |");
		System.out.println("----------------------------------------------------------------------------------");

		boolean run = true;

		L1: do {
			System.out.print("\nEnter Student ID	: ");
			String stId = input.next();

			int index = searchId(stId);

			if (index != -1) {
				System.out.println("Student Name		: " + studentName[index]);
				System.out.print("\nEnter the new Student Name	: ");
				String stName = input.next();
				studentName[index] = stName;
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

	public static void addMarks() {
		Scanner input = new Scanner(System.in);
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("|\t\t\t\t ADD MARKS \t\t\t\t\t |");
		System.out.println("----------------------------------------------------------------------------------");

		boolean run = true;

		L1: do {
			System.out.print("\nEnter Student ID	: ");
			String stId = input.next();

			int index = searchId(stId);

			if (index != -1) {

				System.out.println("Student Name		: " + studentName[index]);

				if (prfMarks[index] == -1 && dbmsMarks[index] == -1) {

					System.out.println();
					L2: do {
						System.out.print("Programming Fundamentals Marks		: ");
						int prf = input.nextInt();

						if (prf >= 0 && prf <= 100) {
							prfMarks[index] = prf;
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
							dbmsMarks[index] = dbms;
							break;

						} else {
							System.out.println("Invalid Marks, Please enter correct marks.\n");
							continue L3;
						}
					} while (true);
					System.out.println();
					System.out.print("Marks have been added successfully.");
					L4:do {
						System.out.print("Do you want to add marks for another student (y/n) : ");
						char option = input.next().charAt(0);
						switch (option) {
							case 'y': case 'Y': clearConsole(); addMarks(); break;
							case 'n': case 'N': clearConsole(); run = false; mainMenu(); break;
							default: System.out.println("\nInvalid Input. Try again."); continue L4;
						}
					} while (true);
				} else {
					System.out.println("\nThis Student's marks have been already added.\nIf you want to update the marks, please use [4] Update Marks option.");
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

	public static void addNewStudent() {
		Scanner input = new Scanner(System.in);

		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("|\t\t\t\t ADD NEW STUDENT \t\t\t\t |");
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println();

		boolean run = true;

		L1: do {
			String[] temp1 = new String[studentId.length + 1];
			String[] temp2 = new String[studentName.length + 1];
			int[] temp3 = new int[prfMarks.length + 1];
			int[] temp4 = new int[dbmsMarks.length + 1];

			for (int i = 0; i < studentId.length; i++) {
				temp1[i] = studentId[i];
				temp2[i] = studentName[i];
			}

			for (int j = 0; j < prfMarks.length; j++) {
				temp3[j] = prfMarks[j];
				temp4[j] = dbmsMarks[j];
			}

			System.out.print("Enter Student ID	: ");
			String stId = input.next();

			for (int k = 0; k < studentId.length; k++) {
				if (studentId[k].equals(stId)) {
					System.out.println(stId + " is already entered.");
					continue L1;
				}
			}

			System.out.print("Enter Student Name	: ");
			String stName = input.next();

			int prf = -1;
			int dbms = -1;

			temp1[temp1.length - 1] = stId;
			temp2[temp2.length - 1] = stName;
			temp3[temp3.length - 1] = prf;
			temp4[temp4.length - 1] = dbms;

			studentId = temp1;
			studentName = temp2;
			prfMarks = temp3;
			dbmsMarks = temp4;

			System.out.println();
			System.out.print("Student has been added successfully.");
			L2:do {
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
			// increment array size using temp array
			String[] temp1 = new String[studentId.length + 1];
			String[] temp2 = new String[studentName.length + 1];
			int[] temp3 = new int[prfMarks.length + 1];
			int[] temp4 = new int[dbmsMarks.length + 1];

			// copy data
			for (int i = 0; i < studentId.length; i++) {
				temp1[i] = studentId[i];
				temp2[i] = studentName[i];
			}

			for (int j = 0; j < prfMarks.length; j++) {
				temp3[j] = prfMarks[j];
				temp4[j] = dbmsMarks[j];
			}

			System.out.print("Enter Student ID    : ");
			String stId = input.next();

			for (int k = 0; k < studentName.length; k++) {
				if (studentId[k].equals(stId)) {
					System.out.println(stId + " is already entered.");
					continue L1;
				}
			}

			System.out.print("Enter Student Name  : ");
			String stName = input.next();

			temp1[temp1.length - 1] = stId;
			temp2[temp2.length - 1] = stName;

			// assign new array address
			studentId = temp1;
			studentName = temp2;

			System.out.println();
			L2: do {
				System.out.print("Programming Fundamentals Marks		: ");
				int prf = input.nextInt();

				if (prf >= 0 && prf <= 100) {
					temp3[temp3.length - 1] = prf;
					prfMarks = temp3;
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
					temp4[temp4.length - 1] = dbms;
					dbmsMarks = temp4;
					break;

				} else {
					System.out.println("Invalid Marks, Please enter correct marks.\n");
					continue L3;
				}
			} while (true);

			System.out.println();
			System.out.print("Student has been added successfully.");
			L4:do {
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

	public static void sortStudentsByPRF() {
		// sort prf marks in descending order
		for (int i = prfMarks.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (prfMarks[j] < prfMarks[j + 1]) {
					int tempPrf = prfMarks[j];
					String tempId = studentId[j];
					String tempName = studentName[j];
					int tempDbms = dbmsMarks[j];

					prfMarks[j] = prfMarks[j + 1];
					studentId[j] = studentId[j + 1];
					studentName[j] = studentName[j + 1];
					dbmsMarks[j] = dbmsMarks[j + 1];

					prfMarks[j + 1] = tempPrf;
					studentId[j + 1] = tempId;
					studentName[j + 1] = tempName;
					dbmsMarks[j + 1] = tempDbms;
				}
			}
		}
	}

	public static void sortStudentsByDBMS() {
		// sort prf marks in descending order
		for (int i = prfMarks.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (dbmsMarks[j] < dbmsMarks[j + 1]) {
					int tempPrf = prfMarks[j];
					String tempId = studentId[j];
					String tempName = studentName[j];
					int tempDbms = dbmsMarks[j];

					prfMarks[j] = prfMarks[j + 1];
					studentId[j] = studentId[j + 1];
					studentName[j] = studentName[j + 1];
					dbmsMarks[j] = dbmsMarks[j + 1];

					prfMarks[j + 1] = tempPrf;
					studentId[j + 1] = tempId;
					studentName[j + 1] = tempName;
					dbmsMarks[j + 1] = tempDbms;
				}
			}
		}
	}

	public static int[] totalMarks() {

		sortStudentsByPRF();

		int[] total = new int[prfMarks.length];

		for (int i = 0; i < prfMarks.length; i++) {
			total[i] = prfMarks[i] + dbmsMarks[i];
		}

		// sort total marks in descending order
		for (int i = prfMarks.length - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (total[j] < total[j + 1]) {
					String tempId = studentId[j];
					String tempName = studentName[j];
					int tempPrf = prfMarks[j];
					int tempDbms = dbmsMarks[j];
					int tempTotal = total[j];

					studentId[j] = studentId[j + 1];
					studentName[j] = studentName[j + 1];
					prfMarks[j] = prfMarks[j + 1];
					dbmsMarks[j] = dbmsMarks[j + 1];
					total[j] = total[j + 1];

					studentId[j + 1] = tempId;
					studentName[j + 1] = tempName;
					prfMarks[j + 1] = tempPrf;
					dbmsMarks[j + 1] = tempDbms;
					total[j + 1] = tempTotal;
				}
			}
		}
		return total;

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
