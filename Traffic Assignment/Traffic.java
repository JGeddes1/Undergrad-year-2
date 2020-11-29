import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Traffic implements ActionListener, Runnable {

JFrame frame = new JFrame("Traffic Simulation");


Road road = new Road();
//South Container
JButton start = new JButton("start");
JButton stop = new JButton("Stop");



Container south = new Container();
JLabel throughput = new JLabel("Throughput: 0");
JButton overtaking = new JButton("Toggle Overtaking");

//
JButton semi = new JButton("Add Semi");
JButton suv = new JButton("Add SUV");
JButton sport = new JButton("Add Sports");
Container west = new Container();

boolean Overtaking = true;
boolean running = false;
int carCount = 0;
long startTime = 0;
  public Traffic() {

    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.setVisible(true);
    frame.add(road, BorderLayout.CENTER);




    south.setLayout(new GridLayout(1,3));
    south.add(start);
    start.addActionListener(this);
    south.add(stop);
    south.add(overtaking);
    overtaking.addActionListener(this);
    stop.addActionListener(this);
    south.add(throughput);
    frame.add(south, BorderLayout.SOUTH);


    west.setLayout(new GridLayout(3, 1));
    west.add(semi);
    semi.addActionListener(this);

    west.add(suv);
    suv.addActionListener(this);

    west.add(sport);
    sport.addActionListener(this);
    frame.add(west, BorderLayout.WEST);





  }



public static void main(String[] args) {
  new Traffic();
}

@Override
public void actionPerformed(ActionEvent event) {
  if (event.getSource().equals(start)) {
    if(running == false) {
      running = true;
      road.resetCarCount();
      startTime = System.currentTimeMillis();
      Thread t = new Thread(this);
      t.start();
    }
  }
  if (event.getSource().equals(stop)) {
    running = false;
  }
  if (event.getSource().equals(overtaking)) {
    road.Overtaking = !road.Overtaking;
  }
  if (event.getSource().equals(semi)) {
      Semi semi = new Semi(0,30);
      road.addCar(semi);
    for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
      for (int y = 40; y < 480; y = y + 120) {
        semi.setX(x);
        semi.setY(y);
        if(road.collision(x, y, semi) == false){
          frame.repaint();
          return;
        }
      }
    }
  }

  if (event.getSource().equals(suv)) {
      SUV suv = new SUV(0,30);
      road.addCar(suv);
    for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
      for (int y = 40; y < 480; y = y + 120) {
        suv.setX(x);
        suv.setY(y);
        if(road.collision(x, y, suv) == false){
          frame.repaint();
          return;
        }
      }
    }
  }

  if (event.getSource().equals(sport)) {
      Sport sport = new Sport(0,30);
      road.addCar(sport);
    for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
      for (int y = 40; y < 480; y = y + 120) {
        sport.setX(x);
        sport.setY(y);
        if(road.collision(x, y, sport) == false){
          frame.repaint();
          return;
        }
      }
    }
  }



}
@Override
public void run() {
  while (running == true){
    road.step();
    carCount = road.getCarCount();
    double throughputCalc = carCount / (1000*(double)(System.currentTimeMillis() - startTime));
    throughput.setText("throughput"+throughputCalc);
    frame.repaint();
    try {
        Thread.sleep(500);
    } catch (Exception e){
      e.printStackTrace();
    }
  }

}




}
