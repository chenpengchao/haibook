package com.hyjz.hnovel.bean;

import org.greenrobot.eventbus.Subscribe;

public class MessageEvent {
        public String message;
        public  MessageEvent(String message){
            this.message=message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
}
