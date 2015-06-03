package com.enalix.design;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by enali on 6/2/15.
 */
public class JsonToolsTest {
    private String jsonData="[{\"name\":\"lzp\", \"age\":23, \"school\":\"beihang\"}," +
            "{\"name\":\"sjz\", \"age\":22, \"school\":\"beihang\"}," +
            "{\"name\":\"cjw\", \"age\":23, \"school\":\"beihang\"}," +
            "{\"name\":\"zbl\", \"age\":21, \"school\":\"beihang\"}]";

    private String[] select = {"s$name", "i$age", "s$school"};

    @Test
    public void parseSelectTest() {
        JSONArray jsonArray = new JSONArray(jsonData);
        JSONObject obj = jsonArray.getJSONObject(0);
        assertThat(JsonTools.parseSelect(obj, select[0]), is("lzp"));
        assertThat(JsonTools.parseSelect(obj, select[1]), is("23"));
        assertThat(JsonTools.parseSelect(obj, select[2]), is("beihang"));
    }

    @Test
    public void validateSelectTest() {
        String select = "s$name";
        assertThat(JsonTools.validateSelect(select), is(true));
        String select_wrong = "y$age";
        assertThat(JsonTools.validateSelect(select_wrong), is(false));
    }

    @Test
    public void parseIntArrayToStringTest() {
        int[] a = {1, 2, 3, 4, 5};
        String str = JsonTools.parseIntArrayToString(a, "===");
        assertThat(str, is("1===2===3===4===5"));
    }

    @Test
    public void parseStringToIntArrayTest() {
        String str = "1===2===3===4===5";
        int[] temp = JsonTools.parseStringToIntArray(str, "===");
        assertArrayEquals(temp, new int[] {1, 2, 3, 4, 5});
    }
}
