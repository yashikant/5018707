import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Smartphone", "Electronics"),
            new Product(3, "Shoes", "Footwear"),
            new Product(4, "Book", "Literature"),
            new Product(5, "Tablet", "Electronics")
        };

        // Linear Search
        Product result = SearchAlgorithms.linearSearch(products, "Shoes");
        System.out.println(result != null ? result : "Product not found");

        // Binary Search (Array must be sorted)
        Arrays.sort(products, Comparator.comparing(Product::getProductName));
        result = SearchAlgorithms.binarySearch(products, "Shoes");
        System.out.println(result != null ? result : "Product not found");
    }
}
