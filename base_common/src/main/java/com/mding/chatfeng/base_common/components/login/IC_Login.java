package com.mding.chatfeng.base_common.components.login;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IMainThread;
import com.mding.chatfeng.base_common.request.create;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class IC_Login implements com.billy.cc.core.component.IComponent, IMainThread {
    private AtomicBoolean initialized = new AtomicBoolean(false);
    private final HashMap<String, IA_Login> map = new HashMap<>(4);

    private void initProcessors() {
    }

    private void add(IA_Login processor) {
        map.put(processor.getActionName(), processor);
    }

    @Override
    public String getName() {
        return  create.login().controllersName;
    }

    @Override
    public boolean onCall(CC cc) {
        if (initialized.compareAndSet(false, true)) {
            synchronized (map) {
                initProcessors();
            }
        }
        String actionName = cc.getActionName();

        IA_Login processor = map.get(actionName);

        if (processor != null) {
            try {
                return processor.onActionCall(cc);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        CC.sendCCResult(cc.getCallId(), CCResult.errorUnsupportedActionName());
        return false;
    }

    @Override
    public Boolean shouldActionRunOnMainThread(String actionName, CC cc) {

        return null;
    }
}