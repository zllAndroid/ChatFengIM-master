package debug;

import android.app.Application;
import android.util.Log;

import com.billy.cc.core.component.BuildConfig;
import com.billy.cc.core.component.CC;
import com.mding.chatfeng.base_common.request.create;

public class DebugApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CC.enableDebug(BuildConfig.DEBUG);
        CC.enableVerboseLog(BuildConfig.DEBUG);
        CC.enableRemoteCC(BuildConfig.DEBUG);
        create.init(getApplicationContext());
    }
}