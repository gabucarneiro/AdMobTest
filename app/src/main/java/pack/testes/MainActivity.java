package pack.testes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {
private AdRequest adRequest;
private AdView adView;
//private int timer = 0;
private InterstitialAd mInterstitialAd;
int countClicks = 5; //Contador para apresentação do InterstitialAd

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

        /*Thread td = new Thread(new Runnable() {
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
        td.start();*/

        Button btn_secondActivity = findViewById(R.id.btn_Call);
        btn_secondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countClicks++;

                //Verifica se o contador é maior ou igual a 5, true: apresenta o Interestitial e zera o contador; false: incrementa o contador
                if(countClicks >= 5){

                    AdRequest adRequest = new AdRequest.Builder().build();

                    InterstitialAd.load(MainActivity.this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                            new InterstitialAdLoadCallback() {
                                @Override
                                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                    // The mInterstitialAd reference will be null until
                                    // an ad is loaded.
                                    mInterstitialAd = interstitialAd;
                                    Log.i("Tag", "onAdLoaded");
                                }

                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    // Handle the error
                                    Log.i("Tag", loadAdError.getMessage());
                                    mInterstitialAd = null;
                                }
                            });

                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(MainActivity.this);
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                    }



                    countClicks = 0;
                }
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

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