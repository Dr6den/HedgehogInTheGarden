package hedge.hog;

public class DeikstraStep {
    private int x;
    private int y;
    private int nextX;
    private int nextY;
    private int stepCost;
    private int wayCost;

    public DeikstraStep(int x, int y, int wayCost) {
        this.x = x;
        this.y = y;
        this.wayCost = wayCost;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNextX() {
        return nextX;
    }

    public void setNextX(int nextX) {
        this.nextX = nextX;
    }

    public int getNextY() {
        return nextY;
    }

    public void setNextY(int nextY) {
        this.nextY = nextY;
    }

    public int getStepCost() {
        return stepCost;
    }

    public void setStepCost(int stepCost) {
        this.stepCost = stepCost;
    }

    public int getWayCost() {
        return wayCost;
    }

    public void setWayCost(int wayCost) {
        this.wayCost = wayCost;
    }

    @Override
    public String toString() {
        return "DeikstraStep{" + "x=" + x + ", y=" + y + ", nextX=" + nextX + ", nextY=" + nextY + ", stepCost=" +
                stepCost + ", wayCost=" + wayCost + '}';
    }
    
    
}
