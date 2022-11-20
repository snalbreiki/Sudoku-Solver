import com.formdev.flatlaf.*;
import javax.swing.*;

public class Driver {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf() {
            });
        } catch( Exception ex ){
            System.err.println( "Failed to initialize LaF" );
        }
        //
        Object[] selections = { "Light", "Dark" };
        Object val = JOptionPane.showInputDialog(null, "Choose theme",
                "Application Theme", JOptionPane.INFORMATION_MESSAGE, null,
                selections, selections[0]);
        //
        try {
            if (val == selections[0])
                UIManager.setLookAndFeel(new FlatIntelliJLaf() {});
            else
                UIManager.setLookAndFeel(new FlatDarculaLaf() {});
        } catch( Exception ex ){
            System.err.println( "Failed to initialize LaF" );
        }
        GUI s1 = new GUI();
        //GUI2 s2 = new GUI2();
    }

}