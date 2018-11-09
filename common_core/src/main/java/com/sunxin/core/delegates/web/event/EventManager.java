package com.sunxin.core.delegates.web.event;

import java.util.HashMap;

import io.reactivex.annotations.NonNull;

/**
 * @author sunxin
 * @date 2018/11/9 10:42 AM
 * @desc 事件管理
 */
public class EventManager {

    private HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {
    }

    private static final class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }


    /**
     * 添加一个事件
     *
     * @param name
     * @param event
     * @return
     */
    public EventManager addEvent(@NonNull String name, @NonNull Event event) {
        EVENTS.put(name, event);
        return this;
    }


    /**
     * 创建
     * @param name
     * @return
     */
    public Event createEvent(@NonNull String name) {
        final Event event = EVENTS.get(name);
        if (event == null) {
            return new UndefineEvent();
        }
        return event;
    }


}
