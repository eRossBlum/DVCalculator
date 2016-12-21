import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
/**
 * Created by eBlum on 20/12/2016.
 */
public class InputField extends JTextField implements DocumentListener {
    public InputField(int columns) {
        super(columns);
        this.getDocument().addDocumentListener(this);
    }

    public void removeUpdate(DocumentEvent e) {
        Main.update();
    }

    public void insertUpdate(DocumentEvent e) {
        Main.update();
    }

    public void changedUpdate(DocumentEvent e) {
        //System.out.println(e);
    }
}
