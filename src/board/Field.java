package board;

/**
 * Created by adam on 10/27/2017.
 */
public class Field {
    public final int x;
    public final int y;

    private int value;

    public Field(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == 0;
    }
}
