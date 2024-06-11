/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projectj;

/**
 *
 * @author hp
 */
 import java.util.Scanner;

class Product {
    String name;
    double price;
    int quantity;
    String description;
    String category;

    Product() {
        this.name = "";
        this.price = 0.0;
        this.quantity = 0;
        this.description = "";
        this.category = "";
    }

    void displayProduct() {
        System.out.printf("Name: %s\tPrice: $%.2f\tQuantity: %d\nDescription: %s\tCategory: %s\n",
                name, price, quantity, description, category);
    }
}

class Inventory {
    static final int MAX_PRODUCTS = 100;
    Product[] products = new Product[MAX_PRODUCTS];
    int[] stockLevels = new int[MAX_PRODUCTS];
    int productCount = 0;

    void addProduct(Product product) {
        if (productCount < MAX_PRODUCTS) {
            products[productCount] = product;
            stockLevels[productCount] = product.quantity;
            productCount++;
        } else {
            System.out.println("Cannot add more products. Maximum limit reached.");
        }
    }

    void editProduct(int productId) {
        if (productId >= 0 && productId < productCount) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Editing product information for product ID " + productId + ":");
            System.out.print("Enter new product name: ");
            products[productId].name = sc.nextLine();
            System.out.print("Enter new product price: $");
            products[productId].price = sc.nextDouble();
            System.out.println("Product information updated successfully!");
        } else {
            System.out.println("Invalid product ID. No product found for ID " + productId + ".");
        }
    }

    void removeProduct(int productId) {
        if (productId >= 0 && productId < productCount) {
            for (int i = productId; i < productCount - 1; ++i) {
                products[i] = products[i + 1];
                stockLevels[i] = stockLevels[i + 1];
            }
            productCount--;
            System.out.println("Product removed successfully!");
        } else {
            System.out.println("Invalid product ID. No product found for ID " + productId + ".");
        }
    }

    void displayAllProducts() {
        if (productCount == 0) {
            System.out.println("No products available.");
        } else {
            System.out.println("\n===== All Products =====");
            for (int i = 0; i < productCount; ++i) {
                products[i].displayProduct();
                System.out.println("Stock Level: " + stockLevels[i]);
                System.out.println("-------------------------");
            }
        }
    }

    void displayProductsByCategory(String category) {
        boolean found = false;
        System.out.println("\n===== Products in Category: " + category + " =====");
        for (int i = 0; i < productCount; ++i) {
            if (products[i].category.equals(category)) {
                found = true;
                products[i].displayProduct();
                System.out.println("Stock Level: " + stockLevels[i]);
                System.out.println("-------------------------");
            }
        }

        if (!found) {
            System.out.println("No products found in category: " + category);
        }
    }

    void searchProductByName(String name) {
        boolean found = false;
        System.out.println("\n===== Search Results for Product: " + name + " =====");
        for (int i = 0; i < productCount; ++i) {
            if (products[i].name.contains(name)) {
                found = true;
                products[i].displayProduct();
                System.out.println("Stock Level: " + stockLevels[i]);
                System.out.println("-------------------------");
            }
        }

        if (!found) {
            System.out.println("No products found with the name: " + name);
        }
    }

    void receiveStock(int productId, int quantity) {
        if (productId >= 0 && productId < productCount) {
            stockLevels[productId] += quantity;
            System.out.println("Stock received successfully! Updated stock level for product ID " + productId + ": "
                    + stockLevels[productId]);
        } else {
            System.out.println("Invalid product ID. No product found for ID " + productId + ".");
        }
    }

}

class SalesOrder {
    String customerName;
    Product[] selectedProducts = new Product[Inventory.MAX_PRODUCTS];
    int[] quantities = new int[Inventory.MAX_PRODUCTS];
    int numProducts = 0;

    double calculateTotal() {
        double total = 0.0;
        for (int i = 0; i < numProducts; ++i) {
            total += selectedProducts[i].price * quantities[i];
        }
        return total;
    }

    void generateReceipt() {
        System.out.println("\n===== Sales Order Receipt =====");
        System.out.println("Customer Name: " + customerName);
        System.out.println("Ordered Products:");

        for (int i = 0; i < numProducts; ++i) {
            System.out.println("Product ID: " + i);
            selectedProducts[i].displayProduct();
            System.out.println("Ordered Quantity: " + quantities[i]);
            System.out.println("-------------------------");
        }

        double total = calculateTotal();
        System.out.printf("Order Total: $%.2f\n", total);
        System.out.println("Thank you for your purchase!");
    }
}

public class Projectj {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        SalesOrder salesOrder = new SalesOrder();

        Scanner sc = new Scanner(System.in);
        while (true) {
            displayMenu();
            int choice = getMenuChoice(sc);
            executeMenuChoice(choice, inventory, salesOrder, sc);
        }
    }

    static void displayMenu() {
        System.out.println("\n===== Shopping Management System =====");
        System.out.println("1. Add New Product");
        System.out.println("2. Edit Product Information");
        System.out.println("3. Remove Product");
        System.out.println("4. View All Products");
        System.out.println("5. View Products by Category");
        System.out.println("6. Search for Products by Name");
        System.out.println("7. Receive Stock");
        System.out.println("8. Set Reorder Point");
        System.out.println("9. Create Sales Order");
        System.out.println("10. Exit");
    }

    static int getMenuChoice(Scanner sc) {
        System.out.print("Enter your choice (1-10): ");
        return sc.nextInt();
    }

    static void executeMenuChoice(int choice, Inventory inventory, SalesOrder salesOrder, Scanner sc) {
        switch (choice) {
            case 1 -> {
                sc.nextLine(); // Consume newline
                Product newProduct = new Product();
                System.out.print("Enter product name: ");
                newProduct.name = sc.nextLine();
                System.out.print("Enter product price: $");
                newProduct.price = sc.nextDouble();
                System.out.print("Enter product quantity: ");
                newProduct.quantity = sc.nextInt();
                sc.nextLine(); // Consume newline
                System.out.print("Enter product description: ");
                newProduct.description = sc.nextLine();
                System.out.print("Enter product category: ");
                newProduct.category = sc.nextLine();

                inventory.addProduct(newProduct);
                System.out.println("Product added successfully!");
            }

            case 2 -> {
                System.out.print("Enter product ID to edit: ");
                int editProductId = sc.nextInt();
                inventory.editProduct(editProductId);
            }

            case 3 -> {
                System.out.print("Enter product ID to remove: ");
                int removeProductId = sc.nextInt();
                inventory.removeProduct(removeProductId);
            }

            case 4 -> inventory.displayAllProducts();

            case 5 -> {
                sc.nextLine(); // Consume newline
                System.out.print("Enter product category to view: ");
                String category = sc.nextLine();
                inventory.displayProductsByCategory(category);
            }

            case 6 -> {
                sc.nextLine(); // Consume newline
                System.out.print("Enter product name to search: ");
                String name = sc.nextLine();
                inventory.searchProductByName(name);
            }

            case 7 -> {
                System.out.print("Enter product ID to receive stock: ");
                int receiveProductId = sc.nextInt();
                System.out.print("Enter quantity to receive: ");
                int quantity = sc.nextInt();
                inventory.receiveStock(receiveProductId, quantity);
            }

            case 8 -> {
                System.out.print("Enter product ID to set reorder point: ");
                int reorderProductId = sc.nextInt();
                System.out.print("Enter reorder point: ");
                int reorderPoint = sc.nextInt();
                inventory.setReorderPoint(reorderProductId, reorderPoint);
            }

            case 9 -> {
                salesOrder.numProducts = 0;
                sc.nextLine(); // Consume newline
                System.out.print("Enter customer name: ");
                salesOrder.customerName = sc.nextLine();

                while (true) {
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

            }
public class Projectj {
    private static Inventory inventory = new Inventory();
    private static SalesOrder salesOrder = new SalesOrder();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shopping Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Shopping Management System");
        titleLabel.setBounds(10, 20, 300, 25);
        panel.add(titleLabel);

        JButton addButton = new JButton("Add New Product");
        addButton.setBounds(10, 50, 160, 25);
        panel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        JButton editButton = new JButton("Edit Product");
        editButton.setBounds(10, 80, 160, 25);
        panel.add(editButton);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editProduct();
            }
        });

        JButton removeButton = new JButton("Remove Product");
        removeButton.setBounds(10, 110, 160, 25);
        panel.add(removeButton);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeProduct();
            }
        });

        JButton viewAllButton = new JButton("View All Products");
        viewAllButton.setBounds(10, 140, 160, 25);
        panel.add(viewAllButton);
        viewAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventory.displayAllProducts();
            }
        });

        JButton viewByCategoryButton = new JButton("View by Category");
        viewByCategoryButton.setBounds(10, 170, 160, 25);
        panel.add(viewByCategoryButton);
        viewByCategoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String category = JOptionPane.showInputDialog("Enter product category:");
                inventory.displayProductsByCategory(category);
            }
        });

        JButton searchButton = new JButton("Search by Name");
        searchButton.setBounds(10, 200, 160, 25);
        panel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter product name:");
                inventory.searchProductByName(name);
            }
        });

        JButton receiveStockButton = new JButton("Receive Stock");
        receiveStockButton.setBounds(10, 230, 160, 25);
        panel.add(receiveStockButton);
        receiveStockButton.addActionListener(new
