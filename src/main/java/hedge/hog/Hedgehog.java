package hedge.hog;

import hedge.entity.Garden;

public abstract class Hedgehog {
     /**
     * 
     * @param garden
     * @param x
     * @param y
     * @return true if hedgehog have to go to the right, false if hedgehog have to go to the down
     */
    protected boolean whetherGoToTheRightOrGoToTheDown(Garden garden, int x, int y, int borderX, int borderY) {
        int xSum = garden.sumOfAplesAcrossXline(x, y);
        int ySum =  garden.sumOfAplesAcrossYline(x, y);
        if (xSum == ySum) {
            if (x == borderX) {
                return false;
            } else if (y == borderY) {
                return true;
            }
            return whetherGoToTheRightOrGoToTheDown(garden, x + 1, y + 1, borderX, borderY);
        } else {
            return xSum > ySum;
        }
    }
    
    public abstract int go(Garden garden, int startPointX, int startPointY, String logPathFileLocation);
}
