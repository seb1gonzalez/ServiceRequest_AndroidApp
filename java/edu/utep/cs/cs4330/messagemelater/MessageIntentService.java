package edu.utep.cs.cs4330.messagemelater;

import android.app.IntentService;
import android.content.Intent;
import android.telephony.SmsManager;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MessageIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "edu.utep.cs.cs4330.messagemelater.action.FOO";
    public static final String ACTION_BAZ = "edu.utep.cs.cs4330.messagemelater.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "edu.utep.cs.cs4330.messagemelater.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "edu.utep.cs.cs4330.messagemelater.extra.PARAM2";

    public MessageIntentService() {
        super("MessageIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent!=null) {
        int delay = intent.getIntExtra("delay",0);
            try {
                Thread.sleep(delay * 1000);
            } catch (NumberFormatException e) {
            } catch (InterruptedException e) {

            }
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage( intent.getStringExtra("phone"),null,intent.getStringExtra("msg"),null, null);
        }





    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
