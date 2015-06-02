# what it is?

这是我的毕设,基于Hadoop做了个JSON文件信息提取工具.

# what specifial?

依赖`org.json:json:20141113`

提取特定路径的信息,如路径:`o$user->o$url->a$urls->o$0->s$expanded_url`, 会提取到`http://abcd.exjkd`信息.

路径是特定的格式,`$`左为类型,右为元素名.

类型:

* `o`: 表示值为`JSONObject`
* `a`: 表示值为`JSONArray`
* `s`: 表示值为`String`
* `b`: 表示值为`boolean`
* `d`: 表示值为`double`
* `i`: 表示值为`int`
* `l`: 表示值为`long`
