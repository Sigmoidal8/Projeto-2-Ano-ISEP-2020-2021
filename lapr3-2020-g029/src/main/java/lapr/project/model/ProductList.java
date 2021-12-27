/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.controller.AplicationPOT;

public class ProductList {
    
    /**
     * The list of Products
     */
    private final List<Product> listProducts;
    
    /**
     * Creates an instance of ProductList
     * initializing the listProducts
     */
    public ProductList() {
        listProducts = new ArrayList<>();
    }
    
    /**
     * Returns the list of Products
     * 
     * @return 
     */
    public List<Product> getProductList() {
        return new ArrayList<>(listProducts);
    }
    
    /**
     * Returns the Product with the
     * ID received by parameter in
     * the list
     * 
     * @param id
     * @return 
     */
    public Product getProduct(String id) {
        for (Product p : listProducts) {
            if (p.getID().equals(id)) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Creates a new instance of Product
     * with the name, price, and weight
     * received by parameter
     * @param name
     * @param price
     * @param weight
     * @return 
     */
    public Product newProduct(String name, float price, float weight) {
        return new Product(generateId(name), name, price, weight);
    }
    
    /**
     * Verifies if the Product is already
     * on the list and if not returns if
     * it was added to the list of Products
     * in the database
     * 
     * @param prod the Product
     * @return 
     */
    public boolean registerProduct(Product prod) {
        if (validateProduct(prod)) {

            AplicationPOT.getInstance().getPlatform().getPrdb().addProduct(prod);
            return addProduct(prod);
        }
        return false;
    }
    
    /**
     * Returns if the the Product
     * was added the list of  the
     * Products
     * 
     * @param p
     * @return 
     */
    public boolean addProduct(Product p) {
        if (!listProducts.contains(p)) {
            listProducts.add(p);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Returns if the Product received
     * by parameter is already in the
     * list of Products or its name,id,
     * price and weight are valid
     * 
     * @param prod
     * @return 
     */
    public boolean validateProduct(Product prod) {
        return !listProducts.contains(prod) && prod.getID() != null && prod.getNome() != null
                && prod.getPrice() > 0 && prod.getWeight() > 0;
    }
    
    /**
     * Generates a id to the Product
     *
     * @param name
     * @return the id
     */
    public String generateId(String name) {
        StringBuilder stb = new StringBuilder();
        String[] names = name.split(" ");
        for (int i = 0; i < names.length; i++) {
            stb.append(names[i]);
        }
        String id = stb.toString();
        id += Integer.toString(id.length());

        return id;
    }
}
