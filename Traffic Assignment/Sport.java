import java.awt.Color;
import java.awt.Graphics;

public class Sport extends Vehicle {

  public Sport(int newx, int newy){

    super(newx, newy);
    width = 30;
    height = 20;
    speed = 25;
  }

  public void paintMe(Graphics g){
  g.setColor(Color.RED);
  g.fillRect(x,y,width,height);

  }
}
