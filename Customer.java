import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Entity{
    Strategy strategy;
    ArrayList<Product> list;

    public Customer(double x, double y, Strategy strategy, ArrayList<Product> list) {
        super(x,y);
        this.strategy = strategy;
        this.list = list;
    }
    public void draw(Graphics2D g2d){
        String str;
        if (list.size() >= 3){
            str = strategy.getStrategyType() + "," + list.get(0).getProductType() + "," + list.get(1).getProductType() + "," + list.get(2).getProductType();
        } else if (list.size() == 2) {
            str = strategy.getStrategyType() + "," + list.get(0).getProductType() + "," + list.get(1).getProductType();
        } else if (list.size() == 1) {
            str = strategy.getStrategyType() + "," + list.get(0).getProductType();
        } else {
            str = strategy.getStrategyType();
        }
        drawHelper(g2d, str, Color.GRAY);
    }

    @Override
    public void step() {

        if (list.isEmpty()) {
            if (position.getIntX() < Common.getWindowWidth() - position.getIntX()) {
                position.setX(position.getX() - Common.getCustomerMovementSpeed());
            } else {
                position.setX(position.getX() + Common.getCustomerMovementSpeed());
            }
        } else if (!strategy.isTargetSet()) {

            strategy.setNextTarget(this, Common.getStoreList());

        } else {
            double dist = this.getPosition().distanceTo(strategy.getTargetPosition());
            if (dist <= 2 * Common.getEntityDiameter()) {
                strategy.tryToBuy(this);
            } else {
                double x = strategy.getTargetPosition().getX() - this.getPosition().getX();
                double y = strategy.getTargetPosition().getY() - this.getPosition().getY();
                if (dist > Common.getCustomerMovementSpeed()) {
                    double vx = x / dist * Common.getCustomerMovementSpeed();
                    double vy = y / dist * Common.getCustomerMovementSpeed();
                    getPosition().setX(getPosition().getX() + vx);
                    getPosition().setY(getPosition().getY() + vy);
                } else {
                    getPosition().setX(x);
                    getPosition().setY(y);
                }

            }
        }
    }

    public ArrayList<Product> getShoppingList() {
        return list;
    }

}
