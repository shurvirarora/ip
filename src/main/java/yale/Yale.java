package yale;

import java.util.Scanner;

import yale.command.Parser;
import yale.command.Storage;
import yale.command.Ui;
import yale.task.TaskList;



/**
 * Represents a chatbot that tracks
 * tasks for users.
 */
public class Yale {
    /**
     * Creates Ui, Storage, Parser
     * Scanner and TaskList objects.
     */

    private static final String FILE_PATH = "data/yale.txt";
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList list;

    /**
     * Constructor method.
     */
    public Yale() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(FILE_PATH);
        try {
            String fileData = storage.loadFileContents();
            list = new TaskList();
            list.importIn(fileData);
        } catch (Exception e) {
            ui.showExceptionError(e);
            list = new TaskList();
        }

    }

    /**
     * Takes in the user input and responds to it accordingly,
     * as well as writes the necessary data into a specified file
     * for storage.
     * @param input User input of type String.
     * @return Customised String response to each user input
     */
    public String getResponse(String input) {
        String output = parser.performAction(input, list);
        storage.writeTextTo(list);
        return output;
    }

    /**
     * Starts the chatbot program.
     */
    public void run() {
        ui.welcomePrompt();
        Scanner scanner = new Scanner(System.in);
        String fileData = storage.loadFileContents();
        list.importIn(fileData);

        while (true) {
            String command = ui.receiveInput(scanner);
            parser.performAction(command, list);
            storage.writeTextTo(list);
            if (ui.checkExit(command)) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Yale().run();
    }
}

