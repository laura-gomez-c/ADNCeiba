package com.ceiba.adnparquedero.domain.common.constant;

import java.lang.annotation.Retention;

//TODO: Class to be deleted
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
public @interface DateFormat {

    String DATE_TIME = "yyyy-MM-dd'T'HH:mm:ssZ";
}
