package com.test.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button table1;
    private Button table2;
    private Button table3;
    private Button table4;
    private Button table5;
    private Button table6;
    private Button table7;
    private Button table8;
    private Button table9;
    private Button table10;

    private MainActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_main));

        this.activity = this;
        List<String> liste =  new ArrayList<String>() ;
        for(int i = 1; i < 11; i++){
            liste.add(String.valueOf(i));
        }
        table1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb = "1";
                AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
                myPopup.setTitle("Table de multiplication de "+ nb);
                for (String L : liste){
                    myPopup.setMessage(nb + " * " + L + " = " + String.valueOf(Integer.valueOf(L)*Integer.valueOf(nb)) + "\n");
                }
                myPopup.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Ok", Toast.LENGTH_SHORT).show();
                    }
                });
                myPopup.show();
            }
        });

        table2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb = "2";
                AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
                myPopup.setTitle("Table de multiplication de "+ nb);
                for (String L : liste){
                    myPopup.setMessage(nb + " * " + L + " = " + String.valueOf(Integer.valueOf(L)*Integer.valueOf(nb)) + "\n");
                }
                myPopup.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Ok", Toast.LENGTH_SHORT).show();
                    }
                });
                myPopup.show();
            }
        });

        table3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb = "3";
                AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
                myPopup.setTitle("Table de multiplication de "+ nb);
                for (String L : liste){
                    myPopup.setMessage(nb + " * " + L + " = " + String.valueOf(Integer.valueOf(L)*Integer.valueOf(nb)) + "\n");
                }
                myPopup.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Ok", Toast.LENGTH_SHORT).show();
                    }
                });
                myPopup.show();
            }
        });

        table4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb = "4";
                AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
                myPopup.setTitle("Table de multiplication de "+ nb);
                for (String L : liste){
                    myPopup.setMessage(nb + " * " + L + " = " + String.valueOf(Integer.valueOf(L)*Integer.valueOf(nb)) + "\n");
                }
                myPopup.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Ok", Toast.LENGTH_SHORT).show();
                    }
                });
                myPopup.show();
            }
        });

        table5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb = "5";
                AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
                myPopup.setTitle("Table de multiplication de "+ nb);
                for (String L : liste){
                    myPopup.setMessage(nb + " * " + L + " = " + String.valueOf(Integer.valueOf(L)*Integer.valueOf(nb)) + "\n");
                }
                myPopup.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Ok", Toast.LENGTH_SHORT).show();
                    }
                });
                myPopup.show();
            }
        });

        table6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb = "6";
                AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
                myPopup.setTitle("Table de multiplication de "+ nb);
                for (String L : liste){
                    myPopup.setMessage(nb + " * " + L + " = " + String.valueOf(Integer.valueOf(L)*Integer.valueOf(nb)) + "\n");
                }
                myPopup.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Ok", Toast.LENGTH_SHORT).show();
                    }
                });
                myPopup.show();
            }
        });

        table7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb = "7";
                AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
                myPopup.setTitle("Table de multiplication de "+ nb);
                for (String L : liste){
                    myPopup.setMessage(nb + " * " + L + " = " + String.valueOf(Integer.valueOf(L)*Integer.valueOf(nb)) + "\n");
                }
                myPopup.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Ok", Toast.LENGTH_SHORT).show();
                    }
                });
                myPopup.show();
            }
        });

        table8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb = "8";
                AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
                myPopup.setTitle("Table de multiplication de "+ nb);
                for (String L : liste){
                    myPopup.setMessage(nb + " * " + L + " = " + String.valueOf(Integer.valueOf(L)*Integer.valueOf(nb)) + "\n");
                }
                myPopup.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Ok", Toast.LENGTH_SHORT).show();
                    }
                });
                myPopup.show();
            }
        });

        table9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb = "9";
                AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
                myPopup.setTitle("Table de multiplication de "+ nb);
                for (String L : liste){
                    myPopup.setMessage(nb + " * " + L + " = " + String.valueOf(Integer.valueOf(L)*Integer.valueOf(nb)) + "\n");
                }
                myPopup.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Ok", Toast.LENGTH_SHORT).show();
                    }
                });
                myPopup.show();
            }
        });

        table10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb = "10";
                AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
                myPopup.setTitle("Table de multiplication de "+ nb);
                for (String L : liste){
                    myPopup.setMessage(nb + " * " + L + " = " + String.valueOf(Integer.valueOf(L)*Integer.valueOf(nb)) + "\n");
                }
                myPopup.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Ok", Toast.LENGTH_SHORT).show();
                    }
                });
                myPopup.show();
            }
        });
    }
}

