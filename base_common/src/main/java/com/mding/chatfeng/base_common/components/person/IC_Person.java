package com.mding.chatfeng.base_common.components.person;

import android.util.Log;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IMainThread;

import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.request.create;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class IC_Person implements com.billy.cc.core.component.IComponent, IMainThread {
    private AtomicBoolean initialized = new AtomicBoolean(false);
    private final HashMap<String, IA_Person> map = new HashMap<>(4);

    private void initProcessors() {
    }

    private void add(IA_Person processor) {
        map.put(processor.getActionName(), processor);
    }

    @Override
    public String getName() {
        return  create.person().controllersName;
    }

    @Override
    public boolean onCall(CC cc) {
        AppConfig.logs("-----");
        if (initialized.compareAndSet(false, true)) {
            synchronized (map) {
                initProcessors();
            }
        }
        String actionName = cc.getActionName();

        IA_Person processor = map.get(actionName);

        if (processor != null) {
            Log.d("xx",processor.getActionName());
            return processor.onActionCall(cc);
        }
        CC.sendCCResult(cc.getCallId(), CCResult.errorUnsupportedActionName());
        return false;
    }

    @Override
    public Boolean shouldActionRunOnMainThread(String actionName, CC cc) {

        return null;
    }
}