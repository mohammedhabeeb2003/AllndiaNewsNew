package pingala.com.navigationdrawer1.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import pingala.com.navigationdrawer1.R;

public class NewsLive extends AppCompatActivity implements View.OnClickListener {
    private static WebView webView;
    private static ProgressBar webViewProgressBar;
    private static ImageView back, forward, refresh, close;
    TextView tv_channelName;
    String channelName;
    String webViewUrl;
    Bundle webViewBundle;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_live);
        tv_channelName = (TextView) findViewById(R.id.tv_channel);
        MobileAds.initialize(this, "ca-app-pub-6236508034083136~6574648803");
        mAdView = (AdView) findViewById(R.id.adView);

        webViewUrl = getIntent().getStringExtra("Links");
        channelName = getIntent().getStringExtra("Channel");
        tv_channelName.setText(channelName);
        webView = (WebView) findViewById(R.id.sitesWebView);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);


        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);


        if (webViewUrl != null && webViewUrl.startsWith("https://www.youtube.com/") || webViewUrl.startsWith("https://m.youtube.com/")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(webViewUrl)));


        }
        initViews();
        setUpWebView();
        setListeners();
        shareUrl();
    }

    private void initViews() {
        back = (ImageView) findViewById(R.id.webviewBack);
        forward = (ImageView) findViewById(R.id.webviewForward);
        refresh = (ImageView) findViewById(R.id.webviewReload);
        close = (ImageView) findViewById(R.id.webviewClose);
        webViewProgressBar = (ProgressBar) findViewById(R.id.webViewProgressBar);
    }

    private boolean shareUrl() {

        if (webViewUrl != null && webViewUrl.startsWith("whatsapp://")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(webViewUrl)));
            return true;

        } else {
            return false;
        }
    }

    private void setUpWebView() {
        webView = (WebView) findViewById(R.id.sitesWebView);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        LoadWebViewUrl(webViewUrl);
    }

    private void setListeners() {
        back.setOnClickListener(this);
        forward.setOnClickListener(this);
        refresh.setOnClickListener(this);
        close.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.webviewBack:
                isWebViewCanGoBack();
                break;
            case R.id.webviewForward:
                if (webView.canGoForward())
                    webView.goForward();
                break;
            case R.id.webviewReload:
                String url = webView.getUrl();
                LoadWebViewUrl(url);
                break;
            case R.id.webviewClose:
                finish();
                break;
        }
    }

    // To handle "Back" key press event for WebView to go back to previous screen.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            isWebViewCanGoBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void isWebViewCanGoBack() {
        if (webView.canGoBack())
            webView.goBack();
        else
            finish();
    }

    private void LoadWebViewUrl(String url) {
        if (isInternetConnected())
            webView.loadUrl(url);
        else {
            refresh.setVisibility(View.VISIBLE);
            Toast.makeText(NewsLive.this, "Oops!! There is no internet connection. Please enable your internet connection.", Toast.LENGTH_LONG).show();

        }
    }

    public boolean isInternetConnected() {
        // At activity startup we manually check the internet status and change
        // the text status
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();

            super.onDestroy();
            webView.destroy();

        }
    }

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null && url.startsWith("whatsapp://")) {
                view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                return true;

            }
            if (url != null && url.startsWith("https://www.youtube.com/") || url.startsWith("https://m.youtube.com/")) {
                onBackPressed();
                return true;

            } else {
                return false;
            }

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            refresh.setVisibility(View.GONE);
            if (!webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            refresh.setVisibility(View.VISIBLE);
            if (webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.GONE);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            refresh.setVisibility(View.VISIBLE);
            if (webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.GONE);
            Toast.makeText(NewsLive.this, "Unexpected error occurred.Reload page again.", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
            refresh.setVisibility(View.VISIBLE);
            if (webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.GONE);
            Toast.makeText(NewsLive.this, "Unexpected error occurred.Reload page again.", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            refresh.setVisibility(View.VISIBLE);
            if (webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.GONE);
            /*Toast.makeText(NewsLive.this, "Unexpected SSL error occurred.Reload page again.", Toast.LENGTH_SHORT).show();*/

        }

    }
}

