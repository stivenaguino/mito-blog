package com.stivenaguino.bean;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class PushBean {

    @Inject
    @Push(channel = "notify")
    private PushContext pushContext;

    public void sendMessage() {
        this.pushContext.send("");
    }

}
