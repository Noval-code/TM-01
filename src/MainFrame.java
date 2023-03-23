import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainFrame extends JFrame{
    private JTextField tfCariBarang;
    private JButton submitBtn;
    private JPanel Main;
    private JTextArea textResult;
    private JTextField tfStok;

    public MainFrame(){

        setContentPane(Main);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    public static void main(String[] args) throws IOException {
        MainFrame myframe = new MainFrame();

        ConnectURI koneksiSaya = new ConnectURI();
        URL myAddress = koneksiSaya.buildURL("https://farmasi.mimoapps.xyz/mimoqss2auyqD1EAlkgZCOhiffSsFl6QqAEIGtM");

//        RESPONSE

        String response = koneksiSaya.getResponseFromHttpUrl(myAddress);

//        decoding JSON

        assert response != null;
        JSONArray responsedJSON = new JSONArray(response);
        ArrayList<FarmasiData> dataList = new ArrayList<>();

        for (int i = 0; i < responsedJSON.length(); i++){
            FarmasiData myData = new FarmasiData();

            JSONObject myJSONObject = responsedJSON.getJSONObject(i);

            myData.setI_name(myJSONObject.getString("i_name"));
            myData.setI_sell(myJSONObject.getString("i_sell"));
            myData.setI_qty(myJSONObject.getString("i_qty"));

            dataList.add(myData);
        }

//         disini membuat action listener ketika buttonnya di klik
//        maka button di bawahnya akan tereksekusi
       myframe.submitBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               for (int index = 0; index<dataList.size();index++){

//                   Disini mengambil nilai i_name dan mentarget char indeks ke 0 / huruf awal
                   String name = String.valueOf(dataList.get(index).getI_name().charAt(0));

//                   Disini mengubah tipe data string menjadi int
                   int harga = Integer.parseInt(dataList.get(index).getI_sell());

//                   disini mengambil nilai i_qty yang berbentuk String di ubah menjadi tipe data Long
                   Long stok = Long.parseLong( dataList.get(index).getI_qty());


//                   Disini disaring nama produk yang mengandung char/huruf S
//                   dan harga produk dengan nominal lebih kecil dari 7000

                      if (name.contains("S")&& harga < 7000){

//                          Jika si stok lebih besar dari 0 maka perintah akan di eksekusi
                          if (stok > 0) {
                              myframe.textResult.append("\nProduk : " + dataList.get(index).getI_name() +
                                                        "\nHarga : " + dataList.get(index).getI_sell() +
                                                         "\nStok : " + dataList.get(index).getI_qty());


                          }
                      }
               }

           }
       });

    }
}
