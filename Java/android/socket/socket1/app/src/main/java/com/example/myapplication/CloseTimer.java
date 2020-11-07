package com.example.myapplication;

import androidx.fragment.app.FragmentManager;

import java.util.Timer;
import java.util.TimerTask;

class CloseTimer extends Timer implements Runnable{

    class CloseTimerTask extends TimerTask {
        private Thread thread;
        private FragmentManager fm;

        public CloseTimerTask(Thread thread, FragmentManager fm){
            super();
            this.thread = thread;
            this.fm = fm;
        }

        @Override
        public void run() {
            new AlertActivity("Connection timed out").show(fm,"Error");
            thread.interrupt();
        }
    }

    CloseTimerTask ctt;
    long delay;

    public CloseTimer(Thread thread, FragmentManager fm, long delay){
        super();
        this.delay = delay;
        ctt = new CloseTimerTask(thread,fm);
    }

    public void run() {
        schedule(ctt,delay);
    }

}