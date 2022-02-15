package pack.testes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
private AdRequest adRequest;
private AdView adView;
private int timer = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Inserido para inclusão do AdsMob*/
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        /*Inserido para inclusão do AdsMob*/

        Thread td = new Thread(new Runnable() {
            @Override
            public void run() {
                for(timer = 0; timer<5; timer++){
                    System.out.println(timer);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(timer == 4){
                        System.out.println("GO!");

                    }
                }
            }
        });
        td.start();


        /*Inserido para inclusão do AdsMob*/
        adView = new AdView(MainActivity.this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        adView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        /*Inserido para inclusão do AdsMob*/
    }
}