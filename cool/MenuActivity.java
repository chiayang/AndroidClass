package com.yang.cool;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MenuActivity extends ActionBarActivity {

    private TextView storeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        storeInfo = (TextView) findViewById(R.id.storeInfo);
        storeInfo.setText(getIntent().getStringExtra("storeInfo"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    public String getData() {
        LinearLayout root = (LinearLayout) findViewById(R.id.root);
        int count = root.getChildCount();

        JSONObject data = new JSONObject();
        JSONArray result = new JSONArray();

        for (int i = 1 ; i < count - 1; i++) {
            LinearLayout child = (LinearLayout) root.getChildAt(i);
            TextView drinkNameTextView = (TextView) child.getChildAt(0);
            Button smallButton = (Button) child.getChildAt(1);
            Button mediumButton = (Button) child.getChildAt(2);
            Button largeButton = (Button) child.getChildAt(3);

            JSONObject drinkStatus = new JSONObject();

            String drinkName = drinkNameTextView.getText().toString();
            int small = Integer.parseInt(smallButton.getText().toString());
            int medium = Integer.parseInt(mediumButton.getText().toString());
            int large = Integer.parseInt(largeButton.getText().toString());

            try {
                drinkStatus.put("drinkName", drinkName);
                drinkStatus.put("s", small);
                drinkStatus.put("m", medium);
                drinkStatus.put("l", large);
                result.put(drinkStatus);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            data.put("result", result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data.toString();
/*
        {
            result: [
                {"drinkName": "black tea", "s": 0, "m": 1, "l":1},
                {"drinkName": "milk tea", "s": 0, "m": 1, "l":1},
                {"drinkName": "green tea", "s": 0, "m": 1, "l":1}
            ]
        }
*/
    }

    public void pick(View view) {
        Button button = (Button) view;
        String text = button.getText().toString();
        int count = Integer.parseInt(text) + 1;
        button.setText(String.valueOf(count));
    }

    public void done(View view) {
        Intent intent = new Intent();
        intent.putExtra("data", getData());

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}