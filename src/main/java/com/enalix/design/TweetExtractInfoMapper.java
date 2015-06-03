package com.enalix.design;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by enali on 6/3/15.
 */
public class TweetExtractInfoMapper extends Mapper<Text, Text, NullWritable, Text> {

    @Override
    public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        String[] selects = {"o$user->s$screen_name", "s$text"};
        String delimiter = "\t";

        JSONArray jsonArray = new JSONArray(value.toString());
        int len = jsonArray.length();
        for (int i=0; i<len; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String[] info = JsonTools.parseSelect(obj, selects);
            StringBuilder sb = new StringBuilder(info[0]);
            for (int j=1; j<info.length; j++)
                sb.append(delimiter+info[j]);
            context.write(NullWritable.get(), new Text(sb.toString()));
        }
    }
}
