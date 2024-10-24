import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frontend extends JFrame {
    private RestaurantOrderManagerNew orderManager;  // Backend manager instance
    private JTextArea outputArea;                 // Area for displaying output messages

    public Frontend() {
        orderManager = new RestaurantOrderManagerNew(); // Initialize backend logic

        // Setup window properties
        setTitle("Restaurant Order Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // GUI components
        JLabel nameLabel = new JLabel("Customer Name:");
        JTextField nameField = new JTextField(20);

        JLabel orderLabel = new JLabel("Order:");
        JTextField orderField = new JTextField(20);

        JCheckBox specialCustomerBox = new JCheckBox("Special Customer");

        JButton submitButton = new JButton("Add Order");
        JButton processButton = new JButton("Process Order");

        // Text area to show output and updates
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        // Add components to the GUI window
        add(nameLabel);
        add(nameField);
        add(orderLabel);
        add(orderField);
        add(specialCustomerBox);
        add(submitButton);
        add(processButton);
        add(new JScrollPane(outputArea));

        // Action listener for the "Add Order" button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String order = orderField.getText();
                boolean isSpecial = specialCustomerBox.isSelected();

                // Call backend method to handle order submission
                String result = orderManager.takeOrder(name, order, isSpecial);
                outputArea.append(result + "\n");

                nameField.setText("");
                orderField.setText("");
                specialCustomerBox.setSelected(false);
            }
        });

        // Action listener for the "Process Order" button
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call backend method to process orders from the queues
                String result = orderManager.processOrder();
                outputArea.append(result + "\n");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RestaurantOrderManagementGUI().setVisible(true);
            }
        });
    }
}
