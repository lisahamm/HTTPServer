package com.lisahamm;

import java.util.Map;

/**
 * Created by lisahamm on 5/4/15.
 */
public interface Request {
    public void parse();
    public void setParsedDataFields(Map<String, String> parsedRequestComponents);
    public String getRawRequest();
}
