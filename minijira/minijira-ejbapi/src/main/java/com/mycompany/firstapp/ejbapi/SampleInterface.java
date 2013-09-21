package com.mycompany.firstapp.ejbapi;

import javax.ejb.Local;

/**
 * Created by  Alexey Gorovoy
 * Date:    06.09.13
 * Time:    16:18
 * Email:   alexey.gorovoy.work@gmail.com
 */

@Local
public interface SampleInterface {
    String JNDI_NAME = "ejblocal:" + SampleInterface.class.getName();

    public String doSomething();
}
