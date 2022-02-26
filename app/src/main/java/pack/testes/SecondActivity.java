package pack.testes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class SecondActivity extends AppCompatActivity {
private AdView adView;
private AdRequest adRequest;
private InterstitialAd mInterstitialAd;
int countClicks = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        Button btnMostrarInterstitial = findViewById(R.id.btnMostrarInterstitial);
        /*Inserido para inclusão do AdsMob*/
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        adView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        btnMostrarInterstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Deixando o .loadAd(adRequest) dentro do setOnClickListener, o anúncio só será carregado quando o botão for pressionado.
                countClicks++;

                //Verifica se o contador é maior ou igual a 5, true: apresenta o Interestitial e zera o contador; false: incrementa o contador
                if(countClicks >= 5){

                    AdRequest adRequest = new AdRequest.Builder().build();

                    InterstitialAd.load(SecondActivity.this,"ca-app-pub-3940256099942544/1033173712", adRequest,
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
                        mInterstitialAd.show(SecondActivity.this);
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                    }

                    countClicks = 0;
                }
            }
        });
        /*Inserido para inclusão do AdsMob*/
    }
}