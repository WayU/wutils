package net.wiphire.wUtils.event;

import java.util.ArrayList;

import net.wiphire.wUtils.event.Event;
import net.wiphire.wUtils.event.Listener;

public interface UpdateListener extends Listener
{
    public void onUpdate();

    public static class UpdateEvent extends Event<UpdateListener>
    {
        public static final UpdateEvent INSTANCE = new UpdateEvent();

        @Override
        public void fire(ArrayList<UpdateListener> listeners)
        {
            for(UpdateListener listener : listeners)
                listener.onUpdate();
        }

        @Override
        public Class<UpdateListener> getListenerType()
        {
            return UpdateListener.class;
        }
    }
}