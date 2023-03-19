import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    private JTextField tfTotalData;
    private JTextField tfHarga;
    private JTextField tfResult;
    private JButton submitBtn;
    private JPanel Main;

    public MainFrame(){

        setContentPane(Main);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String totalData = tfTotalData.getText();
                String harga = String.valueOf(Integer.parseInt(tfHarga.getText()));

                tfResult.setText("Total Data : " + totalData + " Harga : " + harga);
            }
        });
    }


}
