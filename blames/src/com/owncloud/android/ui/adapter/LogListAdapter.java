5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200  1) package com.owncloud.android.ui.adapter;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200  2) 
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200  3) import java.io.File;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200  4) 
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200  5) import android.content.Context;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200  6) import android.content.Intent;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200  7) import android.net.Uri;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200  8) import android.os.Environment;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200  9) import android.view.LayoutInflater;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 10) import android.view.View;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 11) import android.view.View.OnClickListener;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 12) import android.view.ViewGroup;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 13) import android.widget.ArrayAdapter;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 14) import android.widget.TextView;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 15) 
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 16) import com.owncloud.android.R;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 17) 
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 18) 
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 19) public class LogListAdapter extends ArrayAdapter<String> {
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 20)     private Context context = null;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 21)     private String[] values;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 22)     private Uri fileUri = null;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 23)    
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 24)     
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 25)     public LogListAdapter(Context context, String[] values) {
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 26)         super(context, R.layout.log_item, values);
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 27)         this.context = context;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 28)         this.values = values;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 29)     }
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 30) 
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 31)     @Override
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 32)     public View getView(final int position, View convertView, ViewGroup parent) {
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 33)         LayoutInflater inflater = (LayoutInflater) context
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 34)                 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 35)         View rowView = inflater.inflate(R.layout.log_item, parent, false);
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 36)         TextView listText = (TextView) rowView.findViewById(R.id.log_item_single);
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 37)         listText.setText(values[position]);
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 38)         listText.setTextSize(15);
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 39)         fileUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory()+File.separator+"owncloud"+File.separator+"log"+File.separator+values[position]));
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 40)         listText.setOnClickListener(new OnClickListener() {
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 41)             @Override
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 42)             public void onClick(View v) {
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 43)                 Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 44)                 emailIntent.setType("text/rtf");
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 45)                 emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "OwnCloud Logfile");
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 46)                 emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "This is a automatic E-mail send by owncloud/android");
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 47)                 emailIntent.putExtra(android.content.Intent.EXTRA_STREAM, fileUri);
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 48)                 emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 49)                 context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 50)             }
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 51)         });
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 52)         return rowView;
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 53)     }
5a22c7e7 (zerginator 2013-03-31 22:43:46 +0200 54) }
