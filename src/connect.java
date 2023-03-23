import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class connect {
        public static void main(String[] args) throws IOException {

//        REQUEST
            ConnectURI koneksiSaya = new ConnectURI();
            URL myAddress = koneksiSaya.buildURL("https://farmasi.mimoapps.xyz/mimoqss2auyqD1EAlkgZCOhiffSsFl6QqAEIGtM");

//        RESPONSE

            String response = koneksiSaya.getResponseFromHttpUrl(myAddress);
            System.out.println(response);

//        decoding JSON

            assert response != null;
            JSONArray responsedJSON = new JSONArray(response);
            ArrayList<FarmasiData> dataList = new ArrayList<>();

            for (int i = 0; i < responsedJSON.length(); i++){
                FarmasiData Data = new FarmasiData();

                JSONObject myJSONObject = responsedJSON.getJSONObject(i);

                Data.setI_name(myJSONObject.getString("i_name"));
                Data.setI_sell(myJSONObject.getString("i_sell"));
                Data.setI_qty(myJSONObject.getString("i_qty"));

                dataList.add(Data);
            }

            System.out.println("Response are : ");
            for (int index = 0; index<dataList.size();index++){

                Long stok = Long.parseLong(dataList.get(index).getI_qty());

                int harga = Integer.parseInt(dataList.get(index).getI_sell());

                String name = String.valueOf(dataList.get(index).getI_name().charAt(0));

                    if(name.contains("S")&& harga < 7000){

                        if (stok > 0) {
                            System.out.println("NAME : " + dataList.get(index).getI_name());
                            System.out.println("HARGA : " + dataList.get(index).getI_sell());
                            System.out.println("Stok Tersedia : " + dataList.get(index).getI_qty());

                        }

                    }

            }

        }

    }


