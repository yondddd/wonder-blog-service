package com.yond.log;

import java.util.Map;

/**
 * LogContextLoader
 *
 * @created 2020/12/15 17:03
 **/
public interface LogContextLoader {

    Map<String, String> getContext();
}