package com.mding.chatfeng.login.components;

import android.util.Log;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.billy.cc.core.component.IMainThread;
import com.mding.chatfeng.login.components.task.IActionProcessor;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;


public class IC_CheckLogin implements IComponent, IMainThread {

    private AtomicBoolean initialized = new AtomicBoolean(false);
    private final HashMap<String, IActionProcessor> map = new HashMap<>(4);

    private void initProcessors() {
    }

    private void add(IActionProcessor processor) {
        map.put(processor.getActionName(), processor);
    }

    @Override
    public String getName() {
        return "login.IC_CheckLogin";
    }

    @Override
    public boolean onCall(CC cc) {
        Log.d("xx","````````11111###############`````");
        if (initialized.compareAndSet(false, true)) {
            synchronized (map) {
                initProcessors();
            }
        }
        String actionName = cc.getActionName();

        IActionProcessor processor = map.get(actionName);

        if (processor != null) {
            Log.d("xx",processor.getActionName());
            return processor.onActionCall(cc);
        }
        CC.sendCCResult(cc.getCallId(), CCResult.errorUnsupportedActionName());
        Log.d("xx","````````222222###############`````");
        return false;
    }

    @Override
    public Boolean shouldActionRunOnMainThread(String actionName, CC cc) {
        if ("login".equals(actionName)) {

            return true;
        }
        return null;
    }
}