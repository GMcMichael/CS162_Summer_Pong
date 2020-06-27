import processing.core.PApplet;
import processing.core.PFont;
import java.awt.Color;
import java.util.ArrayList;

public class Canvas extends PApplet{

    private static boolean AI;
    private static boolean darkMode;
    private static int width = 500;
    private static int height = 500;
    private static Paddle p1, p2;
    private ArrayList<Paddle> paddles = new ArrayList<Paddle>();
    private static Ball ball;
    private static boolean altBounce;
    private static int baseBallDiameter = 10;
    private static int baseBallXVelocity = -2;
    private static int baseBallYVelocity = 0;
    private static int paddleHeight = 25;
    private static int paddleWidth = 5;
    private static int p1Wins;
    private static int p2Wins;
    private static PFont font;

    public void settings() {
        size(width, height);
    }

    public void setup() {
        p1 = new Paddle(this, 50, height/2, paddleHeight, paddleWidth);
        p2 = new Paddle(this, width-50, height/2, paddleHeight, paddleWidth);
        paddles.add(p1);
        paddles.add(p2);
        ball = new Ball(this, width/2, height/2, baseBallDiameter, baseBallXVelocity, baseBallYVelocity);
        font = createFont("Arial",16);
        textFont(font);
        textAlign(CENTER);
    }

    public void draw() {
        if(darkMode){
            background(0);
            fill(Color.WHITE.getRGB());
        }
        else {
            background(255);
            fill(Color.BLACK.getRGB());
        }
        text(p1Wins, ((width/2)-(width/10)), (height/10));
        text(p2Wins, ((width/2)+(width/10)), (height/10));
        if(AI) p2.followBall();
        p1.draw();
        p2.draw();
        ball.draw();
        checkCollisions();
    }

    public void keyPressed(){
        if(key == 'w') p1.setMovingUp(true);
        if(key == 's') p1.setMovingDown(true);
        if(!AI){
            if(key == 'k') p2.setMovingUp(true);
            if(key == 'm') p2.setMovingDown(true);
        }
    }

    public void keyReleased() {
        if(key == 'w') p1.setMovingUp(false);
        if(key == 's') p1.setMovingDown(false);
        if(!AI){
            if(key == 'k') p2.setMovingUp(false);
            if(key == 'm') p2.setMovingDown(false);
        }
    }

    private void checkCollisions(){
        for(Paddle paddle: paddles){
            float x = ball.getX();
            float y = ball.getY();
            if(x < paddle.getLeft()) x = paddle.getLeft();
            else if(x > paddle.getRight()) x = paddle.getRight();
            if(y < paddle.getTop()) y = paddle.getTop();
            else if(y > paddle.getBottom()) y = paddle.getBottom();
            double dist = Math.sqrt( (Math.pow((ball.getX() - x), 2) + Math.pow((ball.getY() - y), 2)) );
            if(dist <= ball.getRadius()) {
                if (altBounce) ball.paddleCollision(paddle, x, y);
                else ball.paddleCollision(paddle);
            }
        }
    }

    public static void score(boolean left){
        if(left) p1Wins++;
        else p2Wins++;
    }

    public static Paddle getP1() {
        return p1;
    }

    public static Paddle getP2() {
        return p2;
    }

    public static Ball getBall() {
        return ball;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static boolean isAltBounce() {
        return altBounce;
    }

    public static void setAltBounce(boolean altBounce) {
        Canvas.altBounce = altBounce;
    }

    public static boolean isDarkMode() {
        return darkMode;
    }

    public static void setDarkMode(boolean darkMode) {
        Canvas.darkMode = darkMode;
    }

    public static boolean isAI() {
        return AI;
    }

    public static void setAI(boolean AI) {
        Canvas.AI = AI;
    }
}
