package com.enalix.design;

import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by enali on 6/1/15.
 */
public class FilePathFilterTest {
    private Path path;

    @Before
    public void setup() {
        path = new Path("66-tweets.json");
    }

    @Test
    public void singleFileTest() {
        FilePathFilter fpFilter = new FilePathFilter(new String[] {"66-tweets.json"});
        assertThat(fpFilter.accept(this.path), is(false));
    }
}
