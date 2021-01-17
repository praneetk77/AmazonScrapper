package com.example.amazonscraper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;


public class MainActivity extends AppCompatActivity {

    private ImageView imageview;
    private Button button;
    private TextView textView;
    private EditText editText;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        imageview = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

        editText.setText("");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String search = editText.getText().toString();
                search = search.trim().replace(' ','+');
                new upload().execute(search);
            }
        });

    }


    class upload extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... strings) {
            String w1 = "https://www.amazon.in/s?k=", w3 = "&ref=nb_sb_noss_2";
            String websiteURL = w1 + strings[0] + w3;

            try {
                Document doc = Jsoup.connect(websiteURL).get();

                Element div = doc.select("div[class=\"a-section aok-relative s-image-fixed-height\"]").first();
                if(div==null){
                    String[] x = {"Please enter a valid product", "https://hesolutions.com.pk/wp-content/uploads/2019/01/picture-not-available.jpg"};
                    return x;
                }
                Element img = div.select("img[class=\"s-image\"]").first();
                String src = img.attr("src");

                Element span = doc.select("span[class=\"a-size-medium a-color-base a-text-normal\"]").first();
                String name = span.text();

                String[] ans = {name,src};
                return ans;
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] ans) {
            textView.setText(ans[0]);
            Glide.with(MainActivity.this)
                    .asBitmap()
                    .load(ans[1])
                    .into(imageview);
        }
    }
}