package com.cybtech.saytahminoyunu;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView txtKalanHak,txtSonuc;
    private EditText editTextSayi;
    private String gelenDeger;
    private int kalanHak=3,randomSayi;
    private Random rndRandom;
    private boolean tahminDogruMu=false;

    //Button clickini xml üstünden yapıcaz

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtKalanHak=(TextView)findViewById(R.id.txtKalanHak);
        txtSonuc=(TextView)findViewById(R.id.txtSonuc);
        editTextSayi=(EditText)findViewById(R.id.editTxtSayi);
        //Bu kısım program ilk çalıştığında oluşturulacağı için random 1 kere üretilmeli o yüzden burada
        rndRandom=new Random();
        randomSayi=rndRandom.nextInt(5);
        System.out.println("Random sayı: "+randomSayi);
    }

    public void btnTahminEt(View v){
        gelenDeger=editTextSayi.getText().toString();

        if (!TextUtils.isEmpty(gelenDeger)){
            if (kalanHak>0 && !tahminDogruMu){
                if (gelenDeger.equals(String.valueOf(randomSayi))){
                    sonucGoster("Tebrikler Doğru Tahminde Bulundunuz");
                    tahminDogruMu=true;
                }
                else {
                    txtSonuc.setText("Yanlış Tahminde Bulundunuz");
                    editTextSayi.setText(null);
                }
                kalanHak--;
                txtKalanHak.setText("Kalan Hak: "+kalanHak);

                if (kalanHak==0 && !tahminDogruMu){
                    sonucGoster("Tahmin Hakkınız Bitti");
                    editTextSayi.setText(" ");
                }

            }
            else {
                txtSonuc.setText("Oyun Bitti");
            }
        }
        else {
            txtSonuc.setText("Girilen Değer Boş Olamaz");
        }


    }

    private void sonucGoster(String mesaj) {
        editTextSayi.setEnabled(false);
        txtSonuc.setText(mesaj);
    }


}