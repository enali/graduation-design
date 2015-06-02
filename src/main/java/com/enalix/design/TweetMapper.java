package com.enalix.design;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by enali on 6/1/15.
 */
public class TweetMapper extends Mapper<Text, Text, Text, Text> {

    @Override
    public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        String jsonData = value.toString();
        JSONArray jsonArray = new JSONArray(jsonData);
        int len = jsonArray.length();
        String[] select = {"s$text", "s$lang"};
        for (int i=0; i<len; i++) {
            StringBuilder sb = new StringBuilder();
            JSONObject obj = jsonArray.getJSONObject(i);
            for (int j=0; j<select.length; j++) {
                String temp = JsonTools.parseSelect(obj, select[j]);
                sb.append(temp);
            }
            context.write(key, new Text(sb.toString()));
        }

    }
}
