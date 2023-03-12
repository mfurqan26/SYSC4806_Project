public class Checkout {
    //private Cart userCart;
    private User customer;

    public Checkout(){

    }

    public void userAddress(User customer){
        return customer.address;
    }

    public float cartTotal(User customer){
        float total = 0;
        for(Book b: customer.cart){
            total += b.getPrice();
        }
        return total;
    }
}
