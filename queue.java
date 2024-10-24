// Customer class representing a restaurant customer
class Customer {
    String name;  // Customer's name
    String order; // Customer's order
    boolean isSpecial; // True if customer is special (VIP), false otherwise

    // Constructor to initialize customer details
    public Customer(String name, String order, boolean isSpecial) {
        this.name = name;
        this.order = order;
        this.isSpecial = isSpecial;
    }

    // String representation of the customer, used when printing customer info
    @Override
    public String toString() {
        return name + " ordered " + order + (isSpecial ? " (Special Customer)" : "");
    }
}

// Custom queue class using an array to hold the customers
class CustomerQueue {
    private Customer[] queue;
    private int front;
    private int rear;
    private int capacity;

    // Constructor to initialize the custom queue
    public CustomerQueue(int capacity) {
        this.capacity = capacity;
        queue = new Customer[capacity];
        front = -1;
        rear = -1;
    }

    // Check if the queue is full
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return front == -1;
    }

    // Add a customer to the queue
    public void enqueue(Customer customer) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot add customer.");
            return;
        }
        if (front == -1) { // First element to be added
            front = 0;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = customer;
    }

    // Remove a customer from the queue and return the customer
    public Customer dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. No customer to serve.");
            return null;
        }
        Customer customer = queue[front];
        if (front == rear) { // Queue becomes empty after this dequeue
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        return customer;
    }
}

// Backend logic class to handle order management
public class RestaurantOrderManagerNew {
    private CustomerQueue normalQueue;  // Queue for regular customers
    private CustomerQueue specialQueue; // Queue for special customers
    private final int MAX_QUEUE_SIZE = 10; // Maximum capacity for both queues
    private String[] availableOrders;     // Available menu items

    // Constructor to initialize the queues and available menu items
    public RestaurantOrderManagerNew() {
        normalQueue = new CustomerQueue(MAX_QUEUE_SIZE);   // Initialize custom queue for normal customers
        specialQueue = new CustomerQueue(MAX_QUEUE_SIZE);  // Initialize custom queue for special customers
        availableOrders = new String[]{"Pizza", "Burger", "Coffee"}; // Menu items
    }

    // Method to check if the customer's order is available in the restaurant's menu
    private boolean isOrderAvailable(String order) {
        for (String availableOrder : availableOrders) {
            if (availableOrder.equalsIgnoreCase(order)) {
                return true;
            }
        }
        return false;
    }

    // Method to handle a new customer order
    public String takeOrder(String name, String order, boolean isSpecial) {
        Customer newCustomer = new Customer(name, order, isSpecial);

        if (isOrderAvailable(order)) {
            return "Order is available. Serving " + newCustomer.name + " immediately.";
        }

        if (isSpecial) {
            if (specialQueue.isFull()) {
                return "Special queue is full. Cannot add " + newCustomer.name + ".";
            } else {
                specialQueue.enqueue(newCustomer);
                return newCustomer.name + " (Special) is added to the special queue.";
            }
        } else {
            if (normalQueue.isFull()) {
                return "Normal queue is full. Cannot add " + newCustomer.name + ".";
            } else {
                normalQueue.enqueue(newCustomer);
                return newCustomer.name + " is added to the normal queue.";
            }
        }
    }

    // Method to process the next order in the queue
    public String processOrder() {
        if (!specialQueue.isEmpty()) {
            Customer specialCustomer = specialQueue.dequeue();
            return "Serving special customer: " + specialCustomer;
        } else if (!normalQueue.isEmpty()) {
            Customer normalCustomer = normalQueue.dequeue();
            return "Serving regular customer: " + normalCustomer;
        } else {
            return "No customers in the queue.";
        }
    }
}
