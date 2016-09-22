import acm.graphics.GLine;
import acm.graphics.GPolygon;
import acm.program.GraphicsProgram;
import java.awt.Color;
import acm.graphics.GLabel;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.lang.*;
import acm.graphics.GOval;
import acm.graphics.GRect;

/**
 * This class creates several balls and bounces them around in
 * a box using GRect and GOval
*/
public class BouncingBalls_Salom extends GraphicsProgram
{
   public static final int APPLICATION_WIDTH = 700;
   public static final int APPLICATION_HEIGHT = 500;

   public void run()
   {
      /**
       * Here we collect Data
      */
      Slider slider = new Slider();
      int numBalls = slider.showSlider("How many balls do you want? [Recommended: 10-40]");
      double numSeconds = Double.parseDouble("" + slider.showSlider("How many seconds do you want to waste watching balls?"));
      int speed = slider.showSlider("How fast do you want your balls to move? [Recommended: 10]");
      int size = slider.showSlider("How big do you want your balls to be? [Recommended: 20]");
      System.out.println(numBalls + numSeconds);

      /**
       * Here we create a GRect
      */
      GRect border = new GRect(10, 10, 680, 450);
      add(border);
      Ball[] balls = new Ball[numBalls];

      /**
       * Create Balls
      */
      for(int i = 0; i < balls.length - 1; i++)
      {
         balls[i] = new Ball(size);
         add(balls[i].obj());
      }

      /**
       * Move the balls while measuring time
       * Calls to check each ball's position and move it.
      */
      for(double i = 0; i < (numSeconds*1000); i+= 1) {
         for(int x = 0; x < balls.length - 1; x++) {
            balls[x].checkPos();
            balls[x].move(speed/10);
         }
         pause(1);
      }
   }

   public static void main(String[] args)
   {
      new BouncingBalls_Salom().start(args);
   }



}

/**
* A quick class to simplify the process of creating sliders
* Most of the code in this class is from the interwebs.
*/
class Slider {

   /**
   * This method creates an option pane with a slider
   * @param message message to display before the slider
   * @return the slider value
   */
   public int showSlider(String message) {
      JFrame parent = new JFrame();
      JOptionPane optionPane = new JOptionPane();
      JSlider slider = getSlider(optionPane);
      optionPane.setMessage(new Object[] { message + " ", slider });
      optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
      optionPane.setOptionType(JOptionPane.OK_OPTION);
      JDialog dialog = optionPane.createDialog(parent, "");
      dialog.setVisible(true);

      /**
       * Because sliders return a string if not moved
       * by the user, we can try to see if it is a number
       * or a string (if it is a string we return 50)
      */
      try
      {
         return Integer.parseInt("" + optionPane.getInputValue());
      }
      catch(NumberFormatException e)
      {
         return 50;
      }
   }

   /**
   * Some JSlider stuff.
   */
   static JSlider getSlider(final JOptionPane optionPane) {
      JSlider slider = new JSlider();
      slider.setMajorTickSpacing(10);
      slider.setPaintTicks(true);
      slider.setPaintLabels(true);
      ChangeListener changeListener =
         new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
               JSlider theSlider = (JSlider) changeEvent.getSource();
               if (!theSlider.getValueIsAdjusting()) {
                  optionPane.setInputValue(new Integer(theSlider.getValue()));
               }
            }
         };
      slider.addChangeListener(changeListener);
      return slider;
   }
}

/**
* A class for each ball
*/
class Ball extends GraphicsProgram{
   private double angle;
   private Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
   private int MAX_X;
   private int MAX_Y;
   public static final int MIN_X = 10;
   public static final int MIN_Y = 10;
   GOval thisBall;

   /**
    * A constructor for each ball
    * Sets ball size, limits, color, and angle
    * @param size the desired size of the ball
   */
   Ball(int size)
   {
      MAX_X = 690-size;
      MAX_Y = 460-size;
      thisBall = new GOval(Math.random()*(MAX_X-MIN_X), Math.random()*(MAX_Y-MIN_Y), size, size);
      thisBall.setFilled(true);
      thisBall.setFillColor(colors[(int)((Math.random())*(colors.length))]);
      angle = Math.random()*360;
   }

   /**
   * Checks the position of this ball
   * If it is invalid, it will call bounce()
   */
   public void checkPos()
   {
      if(thisBall.getX() > MAX_X || thisBall.getY() > MAX_Y || thisBall.getX() < MIN_X || thisBall.getY() < MIN_Y)
         bounce();
   }

   /**
   * Really complex math to determine the ball's new angle after a bounce
   */
   private void bounce()
   {
      angle -= 90;

   }

   /**
   * A shorter alternative to writing movePolar()
   * This will just call movePolar
   * @param dist the distance you want the ball to move at
   */
   public void move(int dist)
   {
      thisBall.movePolar(dist, angle);
   }

   /**
   * Allows access to the GOval object from other classes
   * @return the GOval object for the ball
   */
   public GOval obj()
   {
      return thisBall;
   }
}
