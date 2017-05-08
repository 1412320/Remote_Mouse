package bluetoothpc;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class MyRobot {
    private Robot aRobot;
    
    public MyRobot(){
        try {
            aRobot = new Robot();
        } catch (AWTException ex) {

        }
    }
    
    public int commandHandler(String msg)
    {
        if(msg == null)
        {
            return 1;	
        }
       
        else
        {
            msg = msg.substring(0, msg.indexOf("@"));
            if(Objects.equals(msg, "Disconnect"))
            {
                System.out.println(msg);
                return 0;
            }
            else if (msg.equals("LC"))
            {
                leftclick(msg);
               
            }
            else if (msg.equals("HLC"))
            {
                holdleftclick(msg);
               
            }
            else if (msg.equals("RLC"))
            {
                releaseleftclick(msg);
               
            }
            else if (msg.equals("RC"))
            {
                rightclick(msg);

            }
            else if (msg.contains("SC"))
            {
                scroll(msg);
            }
            else if (msg.contains("ZI"))                       
            {
                zoomIn(msg);
            }
            else if (msg.contains("ZO"))
            {
                zoomOut(msg);
            }
            else if(!msg.equals(""))
            {
                move(msg);
            }
            return 0;
        }    
    }
    
    private void zoomIn(String msg)
    {
        String part[] = msg.split(":");
        float diff = Float.parseFloat(part[1]) /4;
        System.out.println(msg + ":" + diff);
        aRobot.keyPress(KeyEvent.VK_CONTROL);   
        for(int i = 0; i < Math.round((int)diff); i+= 2){
            aRobot.mouseWheel(1);
            try{ Thread.sleep(10); }catch(InterruptedException e){}
        }
        aRobot.keyRelease(KeyEvent.VK_CONTROL);

    }
    
    private void zoomOut(String msg)
    {
        String part[] = msg.split(":");
        float diff = Float.parseFloat(part[1]) / 4;
        System.out.println(msg + ":" + diff);
        aRobot.keyPress(KeyEvent.VK_CONTROL);            
        for(int i = 0; i <  Math.round((int)diff); i+= 2){
            aRobot.mouseWheel(-1);
            try{ Thread.sleep(10); }catch(InterruptedException e){}
        }
        aRobot.keyRelease(KeyEvent.VK_CONTROL);
    }
        
    private void move(String msg)
    {
        System.out.println(msg);
        String temp = msg.substring(1, msg.length() - 2);
        String [] part = temp.split(",");
        
        float xvalue = 0;
        float yvalue = 0;
        try
        {
            xvalue = Float.parseFloat(part[0]);
            yvalue = Float.parseFloat(part[1]);
            int xValue = Math.round(xvalue);
            int yValue = Math.round(yvalue);
            Point point = MouseInfo.getPointerInfo().getLocation(); 
            float nowx = point.x;
            float nowy = point.y;
            aRobot.mouseMove((int)(nowx + xValue),(int)(nowy + yValue));
        }
        catch (NumberFormatException e)
        {
                
        }
    }
    
    private void scroll(String msg)
    {
        System.out.println(msg);
        String [] part = msg.split(":");
        float temp = 0;
        int scrool = 0;
        try
        {
            temp = Float.parseFloat(part[1]);
            scrool = (int) temp;
            for(int i = 0; i < Math.abs(scrool)/2; i++)
            {
                if (scrool > 0)
                    aRobot.mouseWheel(1);
                else 
                    aRobot.mouseWheel(-1);
                try{ Thread.sleep(50); }catch(InterruptedException e){}
            }
        }
        catch(NumberFormatException e){}
    }
    
    private void leftclick(String msg)
    {
        System.out.println(msg);
        aRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        aRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    private void holdleftclick(String msg)
    {
        System.out.println(msg);
        aRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void releaseleftclick(String msg)
    {
        System.out.println(msg);
        aRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void rightclick(String msg)
    {
        System.out.println(msg);
        aRobot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        aRobot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }    
}
