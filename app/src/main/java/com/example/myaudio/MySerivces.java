package com.example.myaudio;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;

public class MySerivces extends Service {
    String[] mCursorCols = new String[] {
          //  "audio._id AS _id", // index must match IDCOLIDX below
            MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.MIME_TYPE, MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST_ID, MediaStore.Audio.Media.DURATION };
    private MediaPlayer mMediaPlayer; // 声明播放器
    private Cursor mCursor; // 声明游标
    private int mPlayPosition = 0; // 当前播放的歌曲

    // 注册意图
    public static final String PLAY_ACTION = "com.wyl.music.PLAY_ACTION";
    public static final String PAUSE_ACTION = "com.wyl.music.PAUSE_ACTION";
    public static final String NEXT_ACTION = "com.wyl.music.NEXT_ACTION";
    public static final String PREVIOUS_ACTION = "com.wyl.music.PREVIOUS_ACTION";


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer=new MediaPlayer();
        Uri MUSIC_URL= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        mCursor=getContentResolver().query(MUSIC_URL,mCursorCols,null,null,null);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 根据不同的action，做不同的相应
        String action = intent.getAction();
        //播放
        if (action.equals(PLAY_ACTION)) {
            play();


        }
        return super.onStartCommand(intent, flags, startId);
    }


        // 播放音乐
     public void play() {
            //初始化音乐播放器
            inite();
     }
    // 初始化播放器
    public void inite() {
        //充值MediaPlayer
        mMediaPlayer.reset();
        // 获取歌曲位置
        String dataSource = getDateByPosition(mCursor, mPlayPosition);
        // 歌曲信息
       // String info = getInfoByPosition(mCursor, mPlayPosition);
        // 用Toast显示歌曲信息
//        Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT)
//                .show();
        try {
            // 播放器绑定资源
            mMediaPlayer.setDataSource(dataSource);
            // 播放器准备
            mMediaPlayer.prepare();
            // 播放
            mMediaPlayer.start();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    // 根据位置来获取歌曲位置
    public String getDateByPosition(Cursor c, int position) {
        //c.moveToPosition(2);
        String  dataColumn=null;
        int count=c.getCount();
        System.out.println("音频文件一共有："+count);
        if(c.moveToFirst()){
            String music_data=MediaStore.Audio.Media.DATA;
            dataColumn = c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));
        }
        //c.moveToPosition(2);
//        String music_data=MediaStore.Audio.Media.DATA;
//        String  dataColumn = c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));
       // String data = c.getString(dataColumn);
        return dataColumn;
    }

    // 获取当前播放歌曲演唱者及歌名
    public String getInfoByPosition(Cursor c, int position) {
//        c.moveToPosition(position);
//        int titleColumn = c.getColumnIndex(MediaStore.Audio.Media.TITLE);
//        int artistColumn = c.getColumnIndex(MediaStore.Audio.Media.ARTIST);
//        String info = c.getString(artistColumn) + " "
//                + c.getString(titleColumn);
        return "abc";

    }

}
