package com.csy.design.defult;

/**
 * @author : chensy
 * Date : 2020-03-01 12:08
 */
public class VideoPlayer extends MediaPlayAdapter {
    @Override
    public void playVideo() {
        System.out.println("播放video");
    }

    public static void main(String[] args) {
        VideoPlayer videoPlayer = new VideoPlayer();
        videoPlayer.playVideo();
    }
}
