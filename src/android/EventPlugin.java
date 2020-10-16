package cn.com.geovis;

import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class EventPlugin extends CordovaPlugin {

    private static final String TAG = "EventPlugin";

    private static final List<EventListener> eventListeners = new ArrayList<>();

    public static class Event{

        public Event(String type,String data)  {
            this.type = type;
            this.data = data;
        }

        public String type;
        public String data;
    }

    public static void onEvent(EventListener listener){
        eventListeners.add(listener);
    }

    public static interface EventListener{

        public String type();

        public boolean handleEvent(Event event);

    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if(action.equals("event")){

            final String type = args.getString(0);
            final String data = args.getString(1);
            final Event event = new Event(type,data);
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    for(EventListener listener : eventListeners){
                        if(listener.type().equals(event.type)){
                            if(listener.handleEvent(event)){
                                break;
                            }
                        }
                    }
                }
            });

            callbackContext.success();
            return true;
        }

        return false;
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.v(TAG,"initialized");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "success destory");
    }


}
