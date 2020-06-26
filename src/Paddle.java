import processing.core.PApplet;
import processing.core.PConstants;

import java.awt.Color;

public class Paddle {

    private PApplet p;
    private int x, y, height, width;
    private static int moveSpeed = 2;
    private boolean movingUp, movingDown;
    private int color;

    public Paddle(PApplet p, int x, int y, int height, int width){
        this.p = p;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = Color.BLACK.getRGB();
    }

    public void draw(){
        move();
        p.stroke(color);
        p.rectMode(PConstants.CENTER);
        p.fill(Color.WHITE.getRGB());
        p.rect(x, y, width*2, height*2);
    }

    public void move(){
        if(movingUp)
            if((getTop() - moveSpeed) < 0) setY(0 + getHeight());
            else setY(getY() - moveSpeed);
        if(movingDown)
            if (getBottom() > Canvas.getHeight() ) setY(Canvas.getHeight() - getHeight());
            else setY(getY() + moveSpeed);
    }

    public void followBall(){
        float newY = Canvas.getBall().getY();
        if(newY <= 0) newY = 0;
        if((newY + height) > Canvas.getHeight())  newY = Canvas.getHeight() - height;
        setY((int) newY);
    }

    public int getLeft(){
        return getX() - getWidth();
    }

    public int getRight(){
        return getX() + getWidth();
    }

    public int getTop(){
        return getY() - getHeight();
    }

    public int getBottom(){
        return getY() + getHeight();
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public static int getMoveSpeed() {
        return moveSpeed;
    }

    public static void setMoveSpeed(int moveSpeed) {
        Paddle.moveSpeed = moveSpeed;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }
}
