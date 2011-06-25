package com.willybarro.projectzipper;

/**
 *
 * @author Willy Barro
 */
public class IgnorePattern {
    public String pattern;
    public int type;
    
    public static IgnorePattern factory(String pattern, int type)
    {
        IgnorePattern ip = new IgnorePattern();
        ip.type = type;
        ip.pattern = pattern;
        
        return ip;
    }
}
