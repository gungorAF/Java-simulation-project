import java.awt.*;

public class Store extends Entity{
    Product Type;
    int Price, MaxStock, Stock;

    public Store(double x, double y, Product type, int price, int maxStock) {
        super(x,y);
        this.Type = type;
        this.Price = price;
        this.MaxStock = maxStock;
    }
    public void draw(Graphics2D g2d){
        String str;
        str = Type.getProductType() + "," + Stock + "," + Price;
        drawHelper(g2d, str, Color.ORANGE);
    }

    public String getProductType() {
        return Type.getProductType();
    }

    public int getPrice() {
        return Price;
    }

    public void step(){

    }

    public void sell() throws IllegalStateException{
        if(Stock == 0){
            throw new IllegalStateException("Out of Stock");
        }
        Stock-=1;
    }

    public void replenish() {
        Stock = MaxStock;
    }
}
