package com.arobs.shopApplication.model;

import com.arobs.shopApplication.dto.ProductDTO;

import java.util.Objects;

public class CartLine {

    private Product product;
    private int quantityToOrder;

    public CartLine() {
    }

    public CartLine(Product product, int quantity) {
        this.product = product;
        this.quantityToOrder = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductQuantity(){
        return getProduct().getQuantity();
    }

    public int getQuantityToOrder() {
        return quantityToOrder;
    }

    public void setQuantityToOrder(int quantity) {
        this.quantityToOrder = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartLine cartLine = (CartLine) o;
        return quantityToOrder == cartLine.quantityToOrder &&
                Objects.equals(product, cartLine.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantityToOrder);
    }

    @Override
    public String toString() {
        return "Item{" +
                "product=" + product +
                ", quantity=" + quantityToOrder +
                '}';
    }
}
