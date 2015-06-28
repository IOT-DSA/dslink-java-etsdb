package org.dsa.iot.etsdb.utils.atomic;

import org.vertx.java.core.Handler;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Samuel Grenier
 */
public class NotifyAtomicLong {

    private final AtomicLong aLong = new AtomicLong();
    private Handler<Long> handler;

    public void setHandler(Handler<Long> handler) {
        this.handler = handler;
    }

    public long get() {
        return aLong.get();
    }

    public long addAndGet(long l) {
        return notifyHandler(aLong.addAndGet(l));
    }

    public long incrementAndGet() {
        return notifyHandler(aLong.incrementAndGet());
    }

    private long notifyHandler(long l) {
        if (handler != null) {
            handler.handle(l);
        }
        return l;
    }
}
