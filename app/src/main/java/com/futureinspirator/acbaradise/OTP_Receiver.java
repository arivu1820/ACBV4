package com.futureinspirator.acbaradise;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.EditText;

/**
 * Created by Shubham Mahalkar on 17-Dec-19
 */

public class OTP_Receiver extends BroadcastReceiver
{
    private static EditText editText1;
    private static EditText editText2;
    private static EditText editText3;
    private static EditText editText4;
    private static EditText editText5;
    private static EditText editText6;


    public void setEditText1(EditText editText)
    {
        OTP_Receiver.editText1 = editText;
    }
    public void setEditText2(EditText editText)
    {
        OTP_Receiver.editText2 = editText;
    }
    public void setEditText3(EditText editText)
    {
        OTP_Receiver.editText3 = editText;
    }
    public void setEditText4(EditText editText)
    {
        OTP_Receiver.editText4 = editText;
    }
    public void setEditText5(EditText editText)
    {
        OTP_Receiver.editText5 = editText;
    }
    public void setEditText6(EditText editText)
    {
        OTP_Receiver.editText6 = editText;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        int i=1;
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

        for (SmsMessage sms : messages)
        {
            String message = sms.getMessageBody();
            editText1.setText(Character.toString(message.charAt(0)));
            editText2.setText(Character.toString(message.charAt(1)));
            editText3.setText(Character.toString(message.charAt(2)));
            editText4.setText(Character.toString(message.charAt(3)));
            editText5.setText(Character.toString(message.charAt(4)));
            editText6.setText(Character.toString(message.charAt(5)));

        }
    }
}