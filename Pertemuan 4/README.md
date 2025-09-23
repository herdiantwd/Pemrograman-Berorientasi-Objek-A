# Implementasi Clock Display Pada BlueJ

Pada pertemuan kali ini, saya berlatih mengimplemetasikan dan memahami bagaimana cara kerja Clock Display pada `BlueJ`. Untuk membuat Clock Display membutuhan 3 class yaitu : `clock`, `ClockDisplay`, `NumberDisplay`.

<img width="983" height="740" alt="image" src="https://github.com/user-attachments/assets/e66322ed-b6af-4cd1-b948-5574af6917d7" />

<br>
<br>

Class NumberDisplay
```java
public class NumberDisplay
{
    private int currValue;
    private int maxValue;
    
    public NumberDisplay(int max){
        maxValue = max;
        currValue = 0;
    }
    
    public void setValue(int newValue){
        if((newValue >= 0) && (newValue < maxValue)){
            currValue = newValue;
        }
    }
    
    public int getValue(){
        return currValue;
    }
    
    public String getDisplayValue(){
        if(currValue < 10){
            return "0" + currValue;
        } else {
            return "" + currValue;
        }
        
    }
    
    public void increment(){
        currValue = (currValue + 1) % maxValue;
    }
}
```

<br>
<br>

Class Clock Display
```java
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private NumberDisplay seconds;
    private String displayString;
    
    private void updateDisplay(){
        displayString = hours.getDisplayValue() + ":" + minutes.getDisplayValue() + ":" + seconds.getDisplayValue();
        
    }
    
    public void setTime(int hour, int minute, int second){
        hours.setValue(hour);
        minutes.setValue(minute);
        seconds.setValue(second);
        updateDisplay();
    }
    
    public String getTime(){
        return displayString;
    }
    
    public ClockDisplay(){
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        updateDisplay();
    }
    
    public ClockDisplay(int hour,int minute, int second){
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        setTime(hour, minute, second);
    }
    
    
    public void timeTick(){
        seconds.increment();
        if (seconds.getValue() == 0) {
            minutes.increment();
            if (minutes.getValue() == 0) {
                hours.increment();
            }
        }
        updateDisplay();
    }
}
```

<br>
<br>

Class clock
```java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class clock
{
    private JFrame frame;
    private JLabel label;
    private ClockDisplay clock;
    private boolean clockRunning = false;
    private TimerThread timerThread;
    
    public clock()
    {
        
        makeFrame();
        clock = new ClockDisplay();
    }
    
    public void setTime(int hour, int minute, int second) {
        clock.setTime(hour, minute,second);
        label.setText(clock.getTime());
    }
    
    public void showClock() {
        if (frame != null) {
            frame.setVisible(true);
        }
    }

    
     private void start()

    {

        clockRunning = true;

        timerThread = new TimerThread();

        timerThread.start();

    }

    

    private void stop()

    {

        clockRunning = false;

    }

    

    private void step()

    {

        clock.timeTick();

        label.setText(clock.getTime());

    }

    

    private void showAbout()

    {

        JOptionPane.showMessageDialog(frame,

            "Clock Version 1.0\n" +

            "A simple interface for the 'Objects First' clock display project",

            "About Clock",

            JOptionPane.INFORMATION_MESSAGE);

    }

    

    private void quit()

    {

        System.exit(0);

    }

    

    private void makeFrame()

    {

        frame = new JFrame("Clock");

        JPanel contentPane = (JPanel)frame.getContentPane();

        contentPane.setBorder(new EmptyBorder(1, 60, 1, 60));

        

        makeMenuBar(frame);

        contentPane.setLayout(new BorderLayout(12,12));

        

        label = new JLabel("00:00:00", SwingConstants.CENTER);

        Font displayFont = label.getFont().deriveFont(96.0f);

        label.setFont(displayFont);

        contentPane.add(label,BorderLayout.CENTER);

        

        JPanel toolbar = new JPanel();

        toolbar.setLayout(new GridLayout(1,0));

        

        JButton startButton = new JButton("Start");

        startButton.addActionListener(e -> start());

        toolbar.add(startButton);

        

        JButton stopButton = new JButton("Stop");

        stopButton.addActionListener(e -> stop());

        toolbar.add(stopButton);

        

        JButton stepButton = new JButton("Step");

        stepButton.addActionListener(e -> step());

        toolbar.add(stepButton);

        

        JPanel flow = new JPanel();

        flow.add(toolbar);

        

        contentPane.add(flow, BorderLayout.SOUTH);

        

        frame.pack();

        

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);

        frame.setVisible(true);        

    }

    

    private void makeMenuBar(JFrame frame) 

    {

        final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        

        JMenuBar menubar = new JMenuBar();

        frame.setJMenuBar(menubar);

        

        JMenu menu;

        JMenuItem item;

        

        menu = new JMenu("File");

        menubar.add(menu);

        

        item = new JMenuItem("About Clock...");

        item.addActionListener(e->showAbout());

        menu.add(item);

        

        menu.addSeparator();

        

        item = new JMenuItem("Quit");

        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));

        item.addActionListener(e -> quit());

        menu.add(item);

    }

    

    class TimerThread extends Thread 

    {

        public void run() 

        {

            while(clockRunning) 

            {

                step();

                pause();

            }

        }

        

        private void pause() 

        {

            try 

            {

                Thread.sleep(1000);

            } catch (InterruptedException exc) 

            {

            }

        }

        }
}
```


