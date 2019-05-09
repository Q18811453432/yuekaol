package weektest.baway.com.yuekaolx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class wbview extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wbview);
        webView=findViewById(R.id.web);
        webView.loadUrl("http://172.17.8.100/small/commodity/v1/commodityList");
    }
}
