package com.mycompany.firstapp.ejb.util;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by  Alexey Gorovoy
 * Date:    16.09.13
 * Time:    9:51
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class Log {
    private static FileHandler fileTxt;

    // Get the global logger to configure it
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    static public void setup() throws IOException {

        logger.setLevel(Level.INFO);
        //fileTxt = new FileHandler("C:\\Logging.txt");
        //fileTxt.setFormatter(new SimpleFormatter());
        //logger.addHandler(fileTxt);

        logger.info("initialized");
    }

    static public Logger  getLogger() {
        return logger;
    }


}
