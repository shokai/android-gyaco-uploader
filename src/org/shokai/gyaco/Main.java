package org.shokai.gyaco;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        trace("start");
        Uri soundUri = null;

        try{
            soundUri = Uri.parse(getIntent().getExtras().get("android.intent.extra.STREAM").toString());
            trace(soundUri.toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if (soundUri != null) {
            trace("from share menu");
            Cursor c = this.getContentResolver().query(soundUri, null, null, null, null);
            c.moveToFirst();
            String filename = c.getString(c.getColumnIndex(MediaStore.MediaColumns.DATA));
            trace(filename);
        }
        else{
            trace("run as standalone app");
        }
    }
    
    public void trace(String str){
        Log.v("GyacoUploader", str);
    }
}