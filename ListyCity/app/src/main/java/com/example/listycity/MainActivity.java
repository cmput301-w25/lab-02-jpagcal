package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int removePos;
    Button confirm;
    EditText enterCity;
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);

        dataList = new ArrayList<>();

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        Button addCity = findViewById(R.id.add_button);
        confirm = findViewById(R.id.confirm);
        enterCity = findViewById(R.id.enter_city);

        addCity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /**
                 * Mechanism to handle click events for the 'Add City' button
                 */
                // do this when the addCity button has been clicked

                // make the confirm Button and the enterCity field visible
                confirm.setVisibility(View.VISIBLE);
                enterCity.setVisibility(View.VISIBLE);
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /**
                 * Mechanism to handle click events for the 'confirm' button
                 */
                // do this when the confirm button is clicked

                // get the text from the enterCity text field and convert it to a string
                String city = enterCity.getText().toString();
                // add the city to the dataList
                dataList.add(city);
                // notify the adapter to update the reference to the dataList
                cityAdapter.notifyDataSetChanged();
                // set the text for the EditText field to an empty string
                enterCity.setText("");

                // hide the confirm Button and EditText field
                confirm.setVisibility(View.GONE);
                enterCity.setVisibility(View.GONE);
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set the potential item to be removed to the item clicked
                removePos = position;
            }
        });

        Button removeCity = findViewById(R.id.remove_button);
        removeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // remove the clicked city from the dataList
                dataList.remove(removePos);
                // notify the adapter to update the reference to the dataList
                cityAdapter.notifyDataSetChanged();
                // reset the removal position
                removePos=-1;
            }
        });
    }
}