package net.raisama.nfc.mfoc;

import java.lang.Runnable;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ScrollView;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcA;
import net.raisama.nfc.mfoc.NativeImplementation;

public class AndroidMfocActivity extends Activity {
	FakeStdio fake_stdio;
	NfcAdapter mAdapter;
	//IntentFilter[] intentFiltersArray;
	//String[][] techListsArray;
	PendingIntent pendingIntent;
	Tag mTag;
	
	private void setupNfc()
	{
		mAdapter = NfcAdapter.getDefaultAdapter(this);
		pendingIntent = PendingIntent.getActivity(
			    this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		
		IntentFilter ifilter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
	    try {
	        ifilter.addDataType("*/*");    /* Handles all MIME based dispatches.
	                                       You should specify only the ones that you need. */
	    }
	    catch (MalformedMimeTypeException e) {
	        throw new RuntimeException("fail", e);
	    }
	   //intentFiltersArray = new IntentFilter[] {ndef, };
	   //techListsArray = new String[][] { new String[] { NfcA.class.getName() }, new String[] { MifareClassic.class.getName() } };
	}
	
	public void onPause() {
	    super.onPause();
	    mAdapter.disableForegroundDispatch(this);
	}

	public void onResume() {
	    super.onResume();
	    mAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
	}

	public Tag tag()
	{
		return mTag;
	}
	
	public void gotNewTag(Intent intent)
	{
	    //do something with tagFromIntent
	    printUiMessage("got new tag!\n");
	    Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
	    mTag = tag;
	    if (tag == null) {
	    	printUiMessage("weird. No tag info on intent data?\n");
	    	return;
	    }
	    NfcA nfca = NfcA.get(tag);
	    if (nfca == null) {
	    	printUiMessage("This tag is not of type NFC-A. Sorry.\n");
	    	return;
	    }
	    
	    printUiMessage("running mfoc...\n");
	    NativeImplementation.singleton().callMain();
	}
	
	public void onNewIntent(Intent intent) {
		String action = intent.getAction();
		if (action.equals(NfcAdapter.ACTION_TECH_DISCOVERED) || action.equals(NfcAdapter.ACTION_TAG_DISCOVERED) || action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED))
			gotNewTag(intent);
		else
			printUiMessage(String.format("Got an intent of unsupported type: %s\n", action));
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupNfc();
        
        NativeImplementation.singleton().setRunningActivity(this);
        printUiMessage("Ready. Please place the card near the device.\n");
    }
    
	public void printUiMessage(String s)
	{
		TextView t = (TextView)findViewById(R.id.textOutput);
		final ScrollView sv = (ScrollView)this.findViewById(R.id.scroll);
		t.append(s);
		t.post(new Runnable() {
			public void run(){
				sv.fullScroll(ScrollView.FOCUS_DOWN);
			}
		});
	}
}