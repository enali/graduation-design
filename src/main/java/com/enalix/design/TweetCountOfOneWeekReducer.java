package com.enalix.design;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by enali on 6/1/15.
 */
public class TweetCountOfOneWeekReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        int N = 7;
        String delimiter = ":";

        int[] count = new int[N];
        for (int i=0; i<N; i++) count[i] = 0;

        int filenum = 0;

        for (Text value : values) {
            int[] temp = JsonTools.parseStringToIntArray(value.toString(), delimiter);
            for (int i=0; i<N; i++)
                count[i] += temp[i];
            filenum++;
        }

        String sum = JsonTools.parseIntArrayToString(count, delimiter);

        context.write(key, new Text(sum + "##" + filenum));
    }
}
