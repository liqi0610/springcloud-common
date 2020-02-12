## 视频mp4转码到m3u8
```shell script
ffmpeg -i output.mp4 -codec copy -vbsf h264_mp4toannexb -map 0 -f segment -segment_list output.m3u8 -segment_time 10 out%03d.ts
```
其中`output.mp4`是需要转码的文件，`output.m3u8`是最后生成的`m3u8`文本文件的名称，`-segment_time 10`是10秒分一片，`out%03d.ts`是最后生成的ts格式的视频分片。

## 视频截图
```shell script
./ffmpeg -i  1.mp4 -f image2 -an -ss 0:0:0.0 -an -r 1 -vframes 1 -y -s 600x450 4.jpg
```
其中`1.mp4`是需要截图的视频文件，`-ss 0:0:0.0`是需要截图的位置（秒数），`-s 600x450`截图大小，`4.jpg`是截图图片文件的名称。