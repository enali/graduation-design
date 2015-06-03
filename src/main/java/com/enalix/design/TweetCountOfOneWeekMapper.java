package com.enalix.design;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by enali on 6/3/15.
 */
public class TweetCountOfOneWeekMapper extends Mapper<Text, Text, Text, Text> {
    @Override
    public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        int N = 7;
        String delimiter = ":";
        String select_name = "o$user->s$screen_name";
        String select_time = "s$created_at";

        JSONArray jsonArray = new JSONArray(value.toString());
        int len = jsonArray.length();

        int[] temp = new int[N];
        for (int i=0; i<N; i++) temp[i] = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy");
        for (int i=0; i<len; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String timestr = JsonTools.parseSelect(obj, select_time);
            LocalDateTime time = LocalDateTime.parse(timestr, formatter);
            int dayofweek = time.getDayOfWeek().getValue();
            temp[dayofweek-1]++;
        }

        String sum = JsonTools.parseIntArrayToString(temp, delimiter);

        JSONObject obj =jsonArray.getJSONObject(0);
        String name = JsonTools.parseSelect(obj, select_name);

        context.write(new Text(name), new Text(sum));
    }
}
