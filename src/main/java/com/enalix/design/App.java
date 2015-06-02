package com.enalix.design;

import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String ele = "([a-z]+_)*[a-z]+";
        String pat = "^(o\\$ele->|a\\$ele->o\\$[0-9]+->)*[sbdil]\\$ele$";
        String abc = pat.replaceAll("ele", pat);
        String select = "o$user->o$entities->o$url->a$urls->o$0->s$expanded_url";
        if (select.matches(abc))
            System.out.println("true");
        else
            System.out.println("false");
    }
}
