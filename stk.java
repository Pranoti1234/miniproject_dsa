//backend part of stack 
public class stk {
    private String[] directoryStack; // Array to hold directories
    private int top; // Pointer to track the top of the stack
    private final int MAX_SIZE = 5; // Max size of the stack
    private final String ROOT_DIRECTORY = "root"; // Root directory

    // Constructor to initialize the stack
    public stk() {
        directoryStack = new String[MAX_SIZE]; // Create the array for stack
        top = -1; // Stack is empty initially
        pushDirectory(ROOT_DIRECTORY); // Push the root directory
    }

    // Push a new directory onto the stack (move forward)
    public void pushDirectory(String directory) {
        if (top >= MAX_SIZE - 1) {
            System.out.println("Stack overflow, cannot add more directories.");
            return;
        }
        top++; // Move the top pointer up
        directoryStack[top] = directory; // Add the new directory to the stack
        System.out.println("Moved to: " + directory);
    }

    // Pop the last directory from the stack (move backward)
    public String popDirectory() {
        if (top > 0) { // Ensure we don't pop the root directory
            String poppedDir = directoryStack[top]; // Get the top directory
            top--; // Move the top pointer down (remove directory)
            System.out.println("Moved back to: " + getCurrentDirectory());
            return poppedDir;
        } else {
            System.out.println("Already at root directory. Cannot move back.");
            return null;
        }
    }

    // Get the current directory (top of the stack)
    public String getCurrentDirectory() {
        if (top >= 0) { // Ensure there is something in the stack
            return directoryStack[top]; // Return the top directory
        } else {
            return null; // Stack is empty
        }
    }

    // Check if the stack is empty (you should be in the root directory in this
    // case)
    public boolean isEmpty() {
        return top == -1; // If top is -1, the stack is empty
    }

    // Utility function to print the entire stack (directories)
    public String printStack() {
        StringBuilder path = new StringBuilder("Current Path: ");
        for (int i = 0; i <= top; i++) {
            path.append(directoryStack[i]).append("/");
        }
        return path.toString();
    }

    // Main method to demonstrate the functionality
    public static void main(String[] args) {
        stk navigator = new stk();
        navigator.pushDirectory("Documents");
        navigator.pushDirectory("Pictures");
        navigator.popDirectory(); // Moves back to "Documents"
        navigator.printStack(); // Displays the current stack path
    }
}
