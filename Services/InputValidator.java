package Services;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class InputValidator implements KeyListener {
    JTextField field;
    public InputValidator(JTextField field){
        this.field = field;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        this.check();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // this.check();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        this.check();
    }
    
    public void check(){
        String txt;
        txt = field.getText( );
        txt= txt.replaceAll("[^0-9]", "");
        field.setText(txt);
       }

}
