package com.enalix.design;

import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by enali on 6/3/15.
 */
public class TweetOnefileOnenameMapper extends Mapper<Text, Text, Text, BooleanWritable> {

    @Override
    public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        String select = "o$user->s$screen_name";
        BooleanWritable toValue = new BooleanWritable(true);

        JSONArray jsonArray = new JSONArray(value.toString());
        int len = jsonArray.length();

        JSONObject obj = jsonArray.getJSONObject(0);
        String name = JsonTools.parseSelect(obj, select);
        for (int i=1; i<len; i++) {
            obj = jsonArray.getJSONObject(i);
            String temp = JsonTools.parseSelect(obj, select);
            if (!temp.equals(name)) toValue.set(false);
        }
        context.write(key, toValue);
    }
}
