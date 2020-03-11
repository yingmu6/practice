package relative.design.defult;

/**
 * @author : chensy
 * Date : 2020-03-01 12:08
 */

public class Mp3Player extends MediaPlayAdapter {
    @Override
    public void playMp3() {
        System.out.println("播放Mp3");
    }

    public static void main(String[] args) {
        MediaPlay mediaPlay = new Mp3Player();
        mediaPlay.playMp3();
    }
}

//
//public class Mp3Player implements MediaPlay {
//    @Override
//    public void playMp3() {
//
//    }
//
//    @Override
//    public void playVideo() {
//
//    }
//}
