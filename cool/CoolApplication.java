package com.yang.cool;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by msi-1 on 2015/6/22.
 */
public class CoolApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "0NTFN7U5JRhi3Dr4DEOdh5wDIpjjce9KingMIt6D", "tYst4zT3tT2iCYu9VCYdv5fldmmFDMEmgTackSkp");
    }
}
