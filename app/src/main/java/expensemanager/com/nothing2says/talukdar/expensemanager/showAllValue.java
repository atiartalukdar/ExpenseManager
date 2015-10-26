package expensemanager.com.nothing2says.talukdar.expensemanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class showAllValue extends AppCompatActivity {
TextView tv;
    int DEFAULT = 0;
    String DEFAULT1 = "";
    int temp=DEFAULT;
    int count = DEFAULT;
    String data="";
    String dt="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_value);
        tv = (TextView) findViewById(R.id.showAllValue);
        count = readSharedPreference("count","countKey");
        for(int i=0;i<count;i++){
            dt = readSharedPreferenceString("category"+i,"select"+i)+
                    readSharedPreference("amount"+i,"key+i")+
                    readSharedPreferenceString("note"+i,"note"+i);
        }

    }

    public int readSharedPreference(String spName,String key){
        SharedPreferences sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE);
        return temp = sharedPreferences.getInt(key,DEFAULT);
    }

    public String readSharedPreferenceString(String spName,String key){
        SharedPreferences sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE);
        return data = sharedPreferences.getString(key, DEFAULT1);
    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_all_value, menu);
        return true;
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
