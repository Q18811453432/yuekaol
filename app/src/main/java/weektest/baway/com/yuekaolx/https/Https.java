package weektest.baway.com.yuekaolx.https;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import weektest.baway.com.yuekaolx.frag.CallBack;

public class Https {
    public static Https https=new Https();
    private CallBack callback;

    public static Https getInstance(){
        return https;
    }
    public String gethttp(String path) throws Exception {
        URL url=new URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(5000);
        urlConnection.setReadTimeout(5000);
        int responseCode = urlConnection.getResponseCode();
        if (responseCode==200){
            InputStream inputStream = urlConnection.getInputStream();
            Read instance = Read.getInstance();
            String s = instance.getread(inputStream);
         return s;
        }
        return null;
    }
    public void getAsynTask(final String path){
        AsyncTask<String,Void,String> asyncTask=new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String http=null;

                try {
                    http = gethttp(path);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return http;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                callback.getjson(s);
                Log.i("TAG",s);
            }
        }.execute(path);
    }
    public void getcallback(CallBack callBack){
        this.callback=callBack;
    }
}
