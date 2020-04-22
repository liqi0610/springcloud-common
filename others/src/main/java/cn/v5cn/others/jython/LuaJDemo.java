package cn.v5cn.others.jython;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.*;

import java.net.URISyntaxException;

/**
 * 使用Lua执行shell命令，完成视频压缩和分片。
 * @author ZYW
 */
public class LuaJDemo {

    public static void main(String[] args) throws URISyntaxException {

        String path = LuaJDemo.class.getClassLoader().getResource("videoToM3u8.lua").toURI().getPath();

        Globals globals = JsePlatform.standardGlobals();
        LuaValue chunk = globals.loadfile(path).call();

        LuaValue func = chunk.get(LuaValue.valueOf("commonds"));
        LuaTable table = LuaTable.tableOf();

        LuaValue params = new LuaTable();
        params.set("cmd1","D:\\software\\ffmpeg-20200113-7225479-win64-static\\bin\\ffmpeg -i D:\\users\\Destop\\output\\20200421104857.mp4 -threads 2 -vf scale=1280:-2 -c:v libx264 -preset fast -crf 24 D:\\users\\Destop\\output\\FFMPEG_1280X720.mp4");
        //params.set("cmd2","D:\\software\\ffmpeg-20200113-7225479-win64-static\\bin\\ffmpeg -i D:\\users\\Destop\\output\\FFMPEG_1280X720.mp4 -codec copy -vbsf h264_mp4toannexb -map 0 -f segment -segment_list D:\\users\\Destop\\output\\output.m3u8 -segment_time 10 D:\\users\\Destop\\output\\out%03d.ts");

        //table.add();
        //table.add();
        boolean result = func.call(params).toboolean();

        System.out.println(result);
    }
}