package yale.task;

import java.util.ArrayList;

/**
 * Customised ArrayList class called TaskList
 * to contain Task objects
 */
public class TaskList {
    /**
     * ArrayList that can contain
     * elements of type Task
     */
    ArrayList<Task> list;

    /**
     * Constructor method
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds Task item into the ArrayList
     * @param taskItem
     */
    public void addTo(Task taskItem) {
        list.add(taskItem);
    }

    /**
     * Lists out tasks added to ArrayList
     * @return List of tasks
     */
    public String listOut() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += i+1 + "."
                    + list.get(i) + "\n";
        }
        return output;
    }
    public String exportOut() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += list.get(i).export() + "\n";
        }
        return output;
    }

    public void importIn(String fileData) {
        if (fileData.equals("")) {
            return;
        }
        String[] lines = fileData.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] tokens = lines[i].split(" \\| ");
            String name = tokens[2];
            boolean isMarked = tokens[1].equals("1");
            if (tokens[0].equals("T")) {
                ToDo newTodo = new ToDo(name, isMarked);
                list.add(newTodo);
            } else if (tokens[0].equals("D")) {
                String by = tokens[3];
                Deadline newDeadline = new Deadline(name, isMarked, by);
                list.add(newDeadline);
            } else if (tokens[0].equals("E")) {
                String at = tokens[3];
                Event newEvent = new Event(name, isMarked, at);
                list.add(newEvent);
            }
        }
    }
    /**
     * Getter method to retrieve Item
     * from specific position in the list
     * @param itemNo
     * @return Task at specified position in list
     */
    public Task getItem(int itemNo) {
        return list.get(itemNo);
    }

    /**
     * Getter method to retrieve size
     * of list
     * @return Size of list
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Deletes item at specified position
     * in list
     * @param itemNo
     */
    public void deleteItem(int itemNo) {
        list.remove(itemNo);
    }
}
