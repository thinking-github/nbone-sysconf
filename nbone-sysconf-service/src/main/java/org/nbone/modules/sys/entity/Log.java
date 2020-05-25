package org.nbone.modules.sys.entity;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author thinking
 * @version 1.0
 * @since 2020-04-28
 */
@Entity(name = "sys_log")
public class Log implements Serializable {

    private static final long serialVersionUID = 175688013628830207L;

    public final static String TABLE_NAME = "sys_log";


}
