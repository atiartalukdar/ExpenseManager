package expensemanager.com.nothing2says.talukdar.expensemanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    protected final static int DEFAULT = 0;
    int temp = DEFAULT,currentMonthIncome = DEFAULT ;
    int income = DEFAULT;
    String firstIncomeAmmount="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = (EditText) findViewById(R.id.editText);
//Checking the current date for going to next page automatically when date == 1
        int yy,mm,dd;
        final Calendar c = Calendar.getInstance();
        //  yy = c.get(Calendar.YEAR);
        //mm = c.get(Calendar.MONTH);
        dd = c.get(Calendar.DAY_OF_MONTH);
//set the condition where app is started is first time or anyting, or app is started is on first time of month or not?

        if ((readSharedPreference("myBalance","firstIncomeBanalce")==DEFAULT)||(dd==1)){
            Toast.makeText(this, "New Month is Started", Toast.LENGTH_LONG).show();
            showMessage("New Month","Hey ! Please set your budget for new month");
        }
        else{
            Intent intent = new Intent(MainActivity.this,addExpense.class);
            startActivity(intent);
            finish();
        }


    }


    public void firstButton(View view){
        ed.setText("" + 2000);
    }
    public void secondButton(View view){
        ed.setText(""+5000);
    }
    public void threeButton(View view){
        ed.setText(""+8000);
    }
    public void fourthButton(View view){
        ed.setText(""+15000);
    }


    public void goButton(View view){
        firstIncomeAmmount = ed.getText().toString().trim();
        if(firstIncomeAmmount.length()==0){
            showMessage("Error", "Please Enter the Expense Limit");
            return;
        }
        else{
            writeSharedPreference(firstIncomeAmmount,"myBalance","firstIncomeBanalce");
            writeSharedPreference(firstIncomeAmmount,"FirstBalance","FirstIncomeBanalce");
            Intent intent = new Intent(MainActivity.this,addExpense.class);
            startActivity(intent);
            finish();
        }



    }




public int readSharedPreference(String spName,String key){
    SharedPreferences sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE);
    return temp = sharedPreferences.getInt(key,DEFAULT);
}

public void writeSharedPreference(String ammount,String spName,String key ){

        income = Integer.parseInt(ammount);
        SharedPreferences sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, income);
        editor.commit();
}

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok",null);
        builder.show();
    }

























    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
