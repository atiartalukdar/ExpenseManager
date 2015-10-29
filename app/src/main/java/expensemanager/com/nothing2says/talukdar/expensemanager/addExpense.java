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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class addExpense extends AppCompatActivity {
    protected final static int DEFAULT = 0;

    TextView tvMonth,tvFirstCurrentBalance;
    EditText expenseAmmountEd,expenseNoteEd;
    Spinner ctgrySpinner;
    int income = DEFAULT;
    int firstBalance = DEFAULT;
    //MainActivity mA = new MainActivity();

    private String[] categoryString;
    private String selectedCategory="";

    int count = 0;
    int temp = DEFAULT,currentMonthIncome = DEFAULT;
    String[] month = {"January","February","March","April","May","June","Julay","August","September","October","November","December"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        initialize();
        spinner();

        firstBalance = readSharedPreference("FirstBalance","FirstIncomeBanalce");

        int yy,mm,dd;
        final Calendar c = Calendar.getInstance();
        //  yy = c.get(Calendar.YEAR);
        mm = c.get(Calendar.MONTH);
        //  dd = c.get(Calendar.DAY_OF_MONTH);

        // set current Month into textview
        tvMonth.setText(month[mm]);


        temp = readSharedPreference("myBalance","firstIncomeBanalce");
        setCurrentBalance(temp);



    }

    //set the current balance to textView
    public void setCurrentBalance(int val){
        if (val==DEFAULT){
            Toast.makeText(this, "Data can't Load", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Load Successfull",Toast.LENGTH_LONG).show();
            tvFirstCurrentBalance.setText("Current Balance "+val+"Tk");
        }
    }

    //To save expenses
    public  void save(View view){
        if ((expenseAmmountEd.getText().toString().trim().length()==0)||(selectedCategory.equals("Select One"))||(expenseNoteEd.getText().toString().trim().length()==0)){
            Toast.makeText(this, "Please Enter All Value", Toast.LENGTH_LONG).show();
            return;
        }

        int amount = Integer.parseInt(expenseAmmountEd.getText().toString().trim());
        int balance = temp-amount;

        count=readSharedPreference("count","countKey");

       // count = readSharedPreference("count","countKey");


        writeSharedPreference(amount,"amount"+count,"key"+count);
        writeSharedPreference(selectedCategory,"category"+count,"select"+count);
        writeSharedPreference(expenseNoteEd.getText().toString(),"note"+count,"note"+count);

        writeSharedPreference(balance,"myBalance","firstIncomeBanalce");
        temp = readSharedPreference("myBalance","firstIncomeBanalce");

        if(temp<(firstBalance-(firstBalance*0.25))){
            showMessage("Warning!!!","You already spent 25% of your total amount");
        }
        setCurrentBalance(temp);
        count++;
        writeSharedPreference(count, "count", "countKey");

    }






    //Showing all the expenses
    public void showExpenseCl(View view){
        Intent intent = new Intent(addExpense.this,showAllValue.class);
        startActivity(intent);
    }
    //work with the spinners
    public void spinner() {

        ctgrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                   @Override
                                                   public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {

                                                       //indexExam = examSpinnerSE.getSelectedItemPosition();
                                                       selectedCategory = arg0.getSelectedItem().toString();
                                                       //Toast.makeText(ChalangeView.this, mSelectedItemExam+" is Selected", Toast.LENGTH_LONG).show();
                                                   }

                                                   @Override
                                                   public void onNothingSelected(AdapterView<?> arg0) {
                                                   }
                                               }
        );

    }





    //Read the shareadPreference data
    public int readSharedPreference(String spName,String key){
        SharedPreferences sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE);
        return temp = sharedPreferences.getInt(key,DEFAULT);
    }


    //write shared preferences in integer
    public void writeSharedPreference(int ammount,String spName,String key ){

        //income = Integer.parseInt(ammount);
        SharedPreferences sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(key, ammount);
        editor.commit();
    }


    //write Shared Preferences in String
    public void writeSharedPreference(String string,String spName,String key ){

        //income = Integer.parseInt(ammount);
        SharedPreferences sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, string);
        editor.commit();
    }















    public void initialize(){
        //initialize view

        ctgrySpinner = (Spinner)findViewById(R.id.categorySpinner);

        tvMonth = (TextView) findViewById(R.id.monthTV);
        tvFirstCurrentBalance = (TextView) findViewById(R.id.currentBalanceTV);
        expenseAmmountEd= (EditText) findViewById(R.id.expenseAdd);
        expenseNoteEd= (EditText) findViewById(R.id.noteExpense);

        //initialize data source
        categoryString = getResources().getStringArray(R.array.initialCategory);

        // confirmButtonSE


        //initialize view
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, categoryString);

        //bind adapter and view
        ctgrySpinner.setAdapter(adapter);

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
        getMenuInflater().inflate(R.menu.menu_add_expense, menu);
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
