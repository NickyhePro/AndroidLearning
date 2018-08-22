package com.example.nickyhe.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    int selected = 0;
    int correctLocation = 0;
    String[] answers = new String[4];

    public class UrlDownLoader extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String line = "";
            StringBuilder result = new StringBuilder();
            URL url;
            HttpURLConnection urlConnection;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                BufferedReader br = new BufferedReader(inputStreamReader);

                while ((line = br.readLine()) != null) {
                    result.append(line + "\n");
                }

                return result.toString();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Error", "sth wrong when reading");
            }
            return null;
        }
    }

    public class ImageDownLoader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        UrlDownLoader urlDownLoader = new UrlDownLoader();
        String result;
        try {
            result = urlDownLoader.execute("http://www.posh24.se/kandisar").get();
            String[] splitResult1 = result.split("<div class=\"sidebarContainer\">");
            String[] splitResults2 = splitResult1[0].split("<div class=\"container\">");

            Pattern p = Pattern.compile("<img src=\"(.*?)\"");
            Matcher m = p.matcher(splitResults2[1]);

            while (m.find()) {

                urls.add(m.group(1));
            }

            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitResults2[1]);

            while (m.find()) {
                names.add(m.group(1));
            }

            generateQuestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void generateQuestion() {
        Random rand = new Random();
        selected = rand.nextInt(urls.size());

        ImageDownLoader imageDownLoader = new ImageDownLoader();
        Bitmap image = null;
        try {
            image = imageDownLoader.execute(urls.get(selected)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        imageView.setImageBitmap(image);

        correctLocation = rand.nextInt(4);

        for (int i = 0; i < 4; i++) {
            if (i == correctLocation) {

                answers[i] = names.get(selected);

            } else {
                int incorrectLocation;

                do {
                    incorrectLocation = rand.nextInt(names.size());
                } while (incorrectLocation == selected);

                answers[i] = names.get(incorrectLocation);
            }
        }

        button1.setText(answers[0]);
        button2.setText(answers[1]);
        button3.setText(answers[2]);
        button4.setText(answers[3]);

    }

    public void chooseCelebrity(View view) {

        if (view.getTag().toString().equals(String.valueOf(correctLocation))) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong! It was " + names.get(selected), Toast.LENGTH_SHORT).show();
        }

        generateQuestion();
    }
}
