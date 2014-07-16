package pl.praktykiatrem.game.battleship;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.Icon;
import java.awt.EventQueue;


public class InputDialogFrame extends JFrame{
    
    private JTextArea tracker;
    
    //Using a standard Java icon
    private Icon optionIcon = UIManager.getIcon("FileView.computerIcon");
    
    //Application start point   
    public static void main(String[] args) {
     
     //Use the event dispatch thread for Swing components
     EventQueue.invokeLater(new Runnable()
     {
         public void run()
         {
             //create GUI frame
             new InputDialogFrame().setVisible(true);          
         }
     });
              
    }
    
    public InputDialogFrame()
    {
        //make sure the program exits when the frame closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dialog Box Example");
        setSize(500,300);
        
        //This will center the JFrame in the middle of the screen
        setLocationRelativeTo(null);
        
        
        //Using JTextArea to show clicks and responses
        tracker = new JTextArea("Click tracker:");
        add(tracker);
        setVisible(true);
        
        
        //If an icon is used then it overrides the icon from the
        //message type. Likewise if a null is entered for the selection values
        //the dialog box will use a text field
        
           
        String entered1 = (String)JOptionPane.showInputDialog(this
                , "Podaj imiê pdrugiego gracza:"
                , "Text Field Dialog", JOptionPane.QUESTION_MESSAGE
                , optionIcon, null, null);
        
        TrackResponse(entered1);
           
    }
    
    //Append the picked choice to the tracker JTextArea
    public void TrackResponse(String response)
    {
        //showInputDialog method returns null if the dialog is exited
        //without an option being chosen
        if (response == null)
        {
            tracker.append("\nYou closed the dialog without any input..");
        }
        else
        {
            tracker.append("\nYou picked " + response + "..");
        }
    }
}
