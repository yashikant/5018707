public class Main {
    public static void main(String[] args) {
        Order[] orders = {
            new Order(1, "Alice", 250.0),
            new Order(2, "Bob", 150.0),
            new Order(3, "Charlie", 350.0),
            new Order(4, "David", 200.0),
            new Order(5, "Eve", 100.0)
        };

        // Bubble Sort
        System.out.println("Before Bubble Sort:");
        for (Order order : orders) {
            System.out.println(order);
        }
        SortingAlgorithms.bubbleSort(orders);
        System.out.println("After Bubble Sort:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Quick Sort
        Order[] ordersForQuickSort = {
            new Order(1, "Alice", 250.0),
            new Order(2, "Bob", 150.0),
            new Order(3, "Charlie", 350.0),
            new Order(4, "David", 200.0),
            new Order(5, "Eve", 100.0)
        };
        
        System.out.println("Before Quick Sort:");
        for (Order order : ordersForQuickSort) {
            System.out.println(order);
        }
        SortingAlgorithms.quickSort(ordersForQuickSort, 0, ordersForQuickSort.length - 1);
        System.out.println("After Quick Sort:");
        for (Order order : ordersForQuickSort) {
            System.out.println(order);
        }
    }
}
