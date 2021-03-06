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
    int temp=DEFAULT,count = DEFAULT;
    String data="",dt="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_value);
        tv = (TextView) findViewById(R.id.showAllValue);
        count = readSharedPreference("count","countKey");

        for(int i=0;i<count;i++){
            dt += ""+(i+1)+". "+readSharedPreferenceString("category"+i,"select"+i)+"   "+
                    readSharedPreference("amount"+i,"key"+i)+"tk   "+
                    readSharedPreferenceString("note"+i,"note"+i)+"   \n";
        }

        tv.setText(dt);

    }


    public void writeSharedPreference(String string,String spName,String key ){

        //income = Integer.parseInt(ammount);
        SharedPreferences sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, string);
        editor.commit();
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
