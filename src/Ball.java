import processing.core.PApplet;
import java.awt.Color;

public class Ball {

    private PApplet p;
    private int radius, diameter, xVelocity, yVelocity;
    private float x, y;
    private int color;
    private Paddle lastPaddle;

    public Ball(PApplet p, int x, int y, int diameter, int xVelocity, int yVelocity){
        this.p = p;
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.radius = diameter/2;
        this.color = Color.BLACK.getRGB();
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public void draw() {
        checkCollisions();
        move();
        p.stroke(color);
        p.fill(Color.WHITE.getRGB());
        p.circle(x, y, diameter);
    }

    public void move(){
        x += xVelocity;
        y += yVelocity;
    }

    public void paddleCollision(Paddle paddle, float x, float y){
        if(lastPaddle != paddle) {
            lastPaddle = paddle;
            setxVelocity(getxVelocity() * -1);
            float xOffset = x - getX();
            float yOffset = y - getY();
            double hyp = Math.sqrt((Math.pow(xOffset, 2) + Math.pow(yOffset, 2)));
            double angle = Math.sin((xOffset / hyp));
            setVelocities(hyp, angle);
        }

    }

    public void paddleCollision(Paddle paddle){
        if(paddle != lastPaddle) {
            lastPaddle = paddle;
            setxVelocity(getxVelocity() * -1);
            float xOffset = paddle.getX() - getX();
            float yOffset = paddle.getY() - getY();
            double hyp = Math.sqrt(Math.pow(xOffset, 2) + Math.pow(yOffset, 2));
            double angle = Math.sin((xOffset / hyp));
            setVelocities(hyp, angle);
        }
    }

    private void setVelocities(double hyp, double angle){
        hyp = getxVelocity()+ getyVelocity();
        //int xVelocity = (int) (hyp * Math.sin(angle));
        int yVelocity = (int) (hyp * Math.cos(angle));
        //todo decide how to increase speed
        /*if(getxVelocity() < 0) setxVelocity(getxVelocity() - 1);
        else setxVelocity(getxVelocity() + 1);*/
        setyVelocity(yVelocity + 1);
    }

    private void checkCollisions(){
        if((getY() - radius) <= 0) {
            setyVelocity(getyVelocity() * -1);
            setY(radius);
        }
        else if((getY() + radius) >= Canvas.getHeight()) {
            setyVelocity(getyVelocity() * -1);
            setY(Canvas.getHeight() - radius);
        }
        if((getX() - radius) <= 0) {
            Canvas.score(false);
            reset();
        }
        else if((getX() + radius ) >= Canvas.getWidth()){
            Canvas.score(true);
            reset();
        }
    }

    private void reset(){
        setX(Canvas.getWidth()/2);
        setY(Canvas.getHeight()/2);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


}
