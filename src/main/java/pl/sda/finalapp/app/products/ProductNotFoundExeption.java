package pl.sda.finalapp.app.products;

public class ProductNotFoundExeption extends RuntimeException {
    public ProductNotFoundExeption(Integer productId) {
        super("Nie znaleziono produktu o ID: " + productId);
    }
}
