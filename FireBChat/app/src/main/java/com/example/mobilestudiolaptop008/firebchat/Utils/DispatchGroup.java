package com.example.mobilestudiolaptop008.firebchat.Utils;

public class DispatchGroup {

    private int count;
    private Runnable runnable;

    public DispatchGroup() {

    super();
            count = 0;


    }

    public synchronized  void enter()
    {

        count ++;

    }

    public synchronized  void leave()
    {

        count --;

    }

    public void notify(Runnable r)
    {
        runnable = r;
        NotifyGroup();
    }


    private void NotifyGroup()
    {

        if (count<=0 && runnable != null)
        {

            runnable.run();

        }

    }


}
