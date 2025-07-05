package  Input_Output;

public interface OutputHandler {
    void print(String message);
    void printError(String errorMessage);
    void printSuccess(String successMessage);
    void printWarning(String warningMessage);
    void clear();
}


public interface MenuHandler {
    void displayMainMenu();
    void displayProductMenu(List<Product> products);
    void displayCartMenu(Cart cart);
    int getMenuChoice();
}