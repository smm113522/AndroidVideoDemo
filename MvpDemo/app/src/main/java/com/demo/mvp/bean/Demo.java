package com.demo.mvp.bean;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class Demo {

    String title;
    String imge;

    public Demo() {
    }

    public Demo(String title, String imge) {
        this.title = title;
        this.imge = imge;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
    }
}
