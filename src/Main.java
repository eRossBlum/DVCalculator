import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.JLabel;
/**
 * Created by eBlum on 20/12/2016.
 */
public class Main extends JFrame {
    GridBagLayout gbl = new GridBagLayout();
    private JPanel panel = new JPanel(gbl);
    private GridBagConstraints gbc = new GridBagConstraints();
    private static InputField ispInput, wetMassInput, dryMassInput;
    private static JLabel dvOutput;
    public Main() {
        //basic setup
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setResizable(true);
        //add labels
        JLabel ispLabel = new JLabel("ISP: ");
        addComponentAtPosition(0,0,ispLabel);
        JLabel wetMassLabel = new JLabel("Wet Mass: ");
        addComponentAtPosition(0,1,wetMassLabel);
        JLabel dryMassLabel = new JLabel("Dry Mass: ");
        addComponentAtPosition(0,2,dryMassLabel);
        JLabel dvLabel = new JLabel("Delta V: ");
        addComponentAtPosition(0,3,dvLabel);
        dvOutput = new JLabel("###");
        addComponentAtPosition(1,3,dvOutput);

        //add inputs
        ispInput = new InputField(6);
        addComponentAtPosition(1,0,ispInput);
        wetMassInput = new InputField(6);
        addComponentAtPosition(1,1,wetMassInput);
        dryMassInput = new InputField(6);
        addComponentAtPosition(1,2,dryMassInput);

        add(panel);
        pack();
        setVisible(true);
    }

    public void addComponentAtPosition(int x, int y, Component c) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(c, gbc);
        panel.add(c);
    }

    public static void update() {
        double[] values = parseAndCalculate();
        String dv = calculateDv(values);
        dvOutput.setText(dv);
    }
    //if invalid inputs, outputs "###", if valid inputs, outputs DV
    public static String calculateDv(double[] values) {
        if (values.length == 1 || values[1] == 0 || values[2] == 0) {
            return "###";
        }
        double ve = 9.81*values[0];
        return "" + Math.round(ve*Math.log(values[1]/values[2]));
    }
    //returns an array of three doubles representing, in order, ISP, Wet Mass, and Dry Mass
    public static double[] parseAndCalculate() {
        String ispInputRaw = ispInput.getText();
        String wetMassInputRaw = wetMassInput.getText();
        String dryMassInputRaw = dryMassInput.getText();
        double[] array;
        try {
            double ispInputDouble = Double.parseDouble(ispInputRaw);
            double wetMassInputDouble = Double.parseDouble(wetMassInputRaw);
            double dryMassInputDouble = Double.parseDouble(dryMassInputRaw);
            array = new double[] {ispInputDouble,wetMassInputDouble,dryMassInputDouble};
        } catch (NumberFormatException e) {
            array = new double[1];
        }
        return array;
    }

    public static void main(String[] args) {
        new Main();
    }
}
