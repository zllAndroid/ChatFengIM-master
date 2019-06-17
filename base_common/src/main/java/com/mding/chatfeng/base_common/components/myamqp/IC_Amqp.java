package com.mding.chatfeng.base_common.components.myamqp;

import android.util.Log;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IMainThread;
import com.mding.chatfeng.base_common.request.create;


import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class IC_Amqp implements com.billy.cc.core.component.IComponent, IMainThread {
    private AtomicBoolean initialized = new AtomicBoolean(false);
    private final HashMap<String, IA_Amqp> map = new HashMap<>(4);

    private void initProcessors() {
    }

    private void add(IA_Amqp processor) {
        map.put(processor.getActionName(), processor);
    }

    @Override
    public String getName() {
        return  create.amqp().controllersName;
    }

    @Override
    public boolean onCall(CC cc) {

        if (initialized.compareAndSet(false, true)) {
            synchronized (map) {
                initProcessors();
            }
        }
        String actionName = cc.getActionName();

        IA_Amqp processor = map.get(actionName);

        if (processor != null) {
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