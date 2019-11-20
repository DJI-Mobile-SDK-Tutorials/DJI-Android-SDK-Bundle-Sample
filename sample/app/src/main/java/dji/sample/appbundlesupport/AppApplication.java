package dji.sample.appbundlesupport;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import com.google.android.play.core.splitcompat.SplitCompat;

public class AppApplication extends Application {
    private static Context sContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        SplitCompat.install(this);
        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }
}
