package com.adorgolap.assunahtrustadmin;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;
    ArrayList<QA> allData;
    Button bStart;
    TextView tvUpdate;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        bStart = (Button) findViewById(R.id.button);
        tvUpdate = (TextView ) findViewById(R.id.tv);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bStart.setClickable(false);
                DataHndlerTask task = new DataHndlerTask();
                task.execute();
            }
        });


    }
    class DataHndlerTask extends AsyncTask<Void,Void,Void>
    {
//        ProgressDialog progress;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progress = new ProgressDialog(context);
//            progress.setMessage("Uploading data");
//            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            progress.setMax(1260);
//            progress.show();
        }
        int count = 1;
        @Override
        protected Void doInBackground(Void... voids) {
            DatabaseHelper dbHelper = new DatabaseHelper(context);
            try {
                dbHelper.createDataBase();
                dbHelper.openDataBase();
                allData = dbHelper.getAllQA();
//                Toast.makeText(context, "#QA - " + allData.size(), Toast.LENGTH_LONG).show();

                for (QA item : allData) {
                    ParseObject qa = new ParseObject("QA_TABLE");
                    qa.put(DataBaseConst.ID, item.id);
                    qa.put(DataBaseConst.QUESTION, item.question);
                    qa.put(DataBaseConst.ANSWER, item.answer);
                    qa.put(DataBaseConst.CATEGORY, item.category);
                    qa.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null)
                            {
                                updateLog("Procecced " + count++);
                            }else {
                                final String msg = e.getLocalizedMessage();
                                updateLog(msg);
                            }
                        }
                    });
//                    progress.incrementProgressBy(1);
                    Thread.sleep(200);

                }
            }catch (IOException e)
            {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            progress.dismiss();
        }
        private void updateLog(String logMsg)
        {
            final String message = "\n" + logMsg;
            tvUpdate.post(new Runnable() {
                @Override
                public void run() {
                    tvUpdate.append(message);
                    scrollView.fullScroll(View.FOCUS_DOWN);
                }
            });
        }
    }
}
