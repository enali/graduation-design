package com.enalix.design;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

/**
 * Created by enali on 6/1/15.
 */
public class FilePathFilter implements PathFilter {

    private final String[] regexps;

    public FilePathFilter(String[] nofiles) {
        this.regexps = nofiles;
    }

    public boolean accept(Path path) {
        String file = path.toString();
        for (int i=0; i< regexps.length; i++)
            if (file.matches(regexps[i]))
                return false;
        return true;
    }
}
