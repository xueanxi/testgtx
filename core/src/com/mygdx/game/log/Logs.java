package com.mygdx.game.log;

import com.badlogic.gdx.utils.Logger;

/**
 * Created by user on 3/20/18.
 */

public class Logs {

    public static Logger log = new Logger("anxigame");
    public static void e(String content){
        //System.out.println(content);
        log.error(content);

    }
}
