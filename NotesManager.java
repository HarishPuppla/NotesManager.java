import java.io.*;
import java.util.Scanner;

public class NotesManager {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Notes Manager ---");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addNote(scanner);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Exiting Notes Manager.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 3);

        scanner.close();
    }

    // Use FileWriter to append a note
    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(note + System.lineSeparator());
            System.out.println("Note saved.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the note: " + e.getMessage());
        }
    }

    // Use FileReader and BufferedReader to read notes
    private static void viewNotes() {
        System.out.println("\n--- Your Notes ---");

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean isEmpty = true;

            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
                isEmpty = false;
            }

            if (isEmpty) {
                System.out.println("(No notes found.)");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No notes file found. Add a note first.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading notes: " + e.getMessage());
        }
    }
}
