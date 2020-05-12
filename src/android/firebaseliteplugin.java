package com.hoangtran.firebaseliteplugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This class echoes a string called from JavaScript.
 */
public class firebaseliteplugin extends CordovaPlugin {

  private static final String TAG = "FirebaseLite";

  private static final String ACTION_GET_FCM_TOKEN = "get_fcm_token";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (ACTION_GET_FCM_TOKEN.equals(action)) {
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "getInstanceId failed", task.getException());
                                return;
                            }
                            // Get new Instance ID token
                            String token = task.getResult().getToken();
                            callbackContext.success(token);
                            Log.d(TAG, token);
                        }
                    });
          // need to return as this call is async.
          return true;
        }
        return false;
    }
}
