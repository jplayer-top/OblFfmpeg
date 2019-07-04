package com.m4399.videoeditor.core;


import android.os.Environment;
import android.util.Log;

import com.m4399.ffmpeg_cmd.CmdList;
import com.m4399.ffmpeg_cmd.FFmpegCmd;

import java.io.File;

import top.jplayer.oblffmpeglib.CmdInvoke;

public class VideoEditor
{
    private static final String TAG = "VideoEditor";

    public static void cropVideo(String videoPath, long startTime, long endTime, CmdInvoke.OnCmdExecListener listener)
    {
        long duration = endTime - startTime;
        CmdList cmd = new CmdList();
        cmd.append("ffmpeg");
        cmd.append("-y");
        cmd.append("-ss").append(startTime/ 1000).append("-t").append(duration / 1000).append("-accurate_seek");
        cmd.append("-i").append(videoPath);
        cmd.append("-codec").append("copy").append(getSavePath());

        execCmd(cmd, duration, listener);
    }

    private static void execCmd(CmdList cmd, long duration, final CmdInvoke.OnCmdExecListener listener)
    {
        String[] cmds = cmd.toArray(new String[cmd.size()]);
        String cmdLog = "";
        for (String ss : cmds)
        {
            cmdLog = cmdLog + " " + ss;
        }
        Log.i("00000000000000000000", "cmd:" + cmdLog);
        CmdInvoke.exec(cmds, duration, new CmdInvoke.OnCmdExecListener()
        {
            @Override
            public void onSuccess()
            {
                listener.onSuccess();
            }

            @Override
            public void onFailure()
            {
                listener.onFailure();
            }

            @Override
            public void onProgress(float progress)
            {
                listener.onProgress(progress);
            }
        });
    }

    private static String getSavePath()
    {
        String savePath = Environment.getExternalStorageDirectory().getPath() + "/VideoEditor/";
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return savePath + "out.mp4";
    }
}
