package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

/**
 * Created by Harry on 27-Dec-16.
 */
import java.util.ArrayList;
import android.telephony.SmsManager;
import android.util.Log;

// The code for dealing with the SMS manager; called from the GUI code.
public class SendSMS {

    static String TAG = "SendSMS";

    SmsManager mSMSManager = null;
    // The list of message parts our message gets broken up into by SmsManager
    ArrayList<String> msgPartList = null;
    // Service Center - not used
    String mServiceCentreAddr = null;

    SendSMS() {
        mSMSManager = SmsManager.getDefault();
    }

    // Called from the GUI to send one message to one destination
    public boolean sendSMSMessage(String aDestinationAddress, String aMessageText) {
        if (mSMSManager == null) {
            return (false);
        }
        msgPartList = mSMSManager.divideMessage(aMessageText);
        int partCount = msgPartList.size();
        if (partCount > 1) {
            Log.d(TAG, "Sending " + partCount + " parts");
            mSMSManager.sendMultipartTextMessage(aDestinationAddress, mServiceCentreAddr,
                    msgPartList, null, null);
        } else {
            Log.d(TAG, "Sending one part");
            mSMSManager.sendTextMessage(aDestinationAddress, mServiceCentreAddr,
                    aMessageText, null, null);
        }
        return true;
    }
}





