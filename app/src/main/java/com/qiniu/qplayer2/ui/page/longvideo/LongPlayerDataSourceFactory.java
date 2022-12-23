package com.qiniu.qplayer2.ui.page.longvideo;

import com.qiniu.qmedia.component.player.QMediaModelBuilder;
import com.qiniu.qmedia.component.player.QURLType;
import com.qiniu.qmedia.component.player.QVideoRenderType;
import com.qiniu.qplayer2ext.commonplayer.data.DisplayOrientation;
import com.qiniu.qplayer2ext.commonplayer.data.CommonPlayerDataSource;
import com.qiniu.qplayer2.repository.setting.PlayerSettingRespostory;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LongPlayerDataSourceFactory {

        public static CommonPlayerDataSource<LongPlayableParams, LongVideoParams> create() {
                CommonPlayerDataSource.DataSourceBuilder<LongPlayableParams, LongVideoParams> dataSourceBuilder =new CommonPlayerDataSource.DataSourceBuilder();
                LongVideoParams videoParams;

                QMediaModelBuilder builder = new QMediaModelBuilder();
                String url = "";
                String name = "";
                builder = new QMediaModelBuilder();

                builder.addElement(
                        "", QURLType.QAUDIO_AND_VIDEO, 1080,
                        "http://demo-videos.qnsdk.com/qiniu-1080p.mp4", true,"","",QVideoRenderType.NONE
                );
                builder.addElement(
                        "", QURLType.QAUDIO_AND_VIDEO, 720,
                        "http://demo-videos.qnsdk.com/qiniu-720p.mp4", false,"","",QVideoRenderType.NONE
                );
                builder.addElement(
                        "", QURLType.QAUDIO_AND_VIDEO, 480,
                        "http://demo-videos.qnsdk.com/qiniu-480p.mp4", false,"","",QVideoRenderType.NONE
                );
                builder.addElement(
                        "", QURLType.QAUDIO_AND_VIDEO, 360,
                        "http://demo-videos.qnsdk.com/qiniu-360p.mp4", false,"","",QVideoRenderType.NONE
                );
                builder.addElement(
                        "", QURLType.QAUDIO_AND_VIDEO, 240,
                        "http://demo-videos.qnsdk.com/qiniu-240p.mp4", false,"","",QVideoRenderType.NONE
                );
                name = "1-点播-http-mp4-30fps-多清晰度";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(new LongPlayableParams(
                                builder.build(false),
                                LongControlPanelType.Normal.type,
                                DisplayOrientation.LANDSCAPE,
                                LongEnviromentType.LONG.type,
                                PlayerSettingRespostory.getInstance().getStartPosition()
                        ))
                );

                //音视频分开2个流的视频要用精准seek
                builder = new QMediaModelBuilder();
                builder.addElement(
                        "", QURLType.QAUDIO, 100,
                        "http://demo-videos.qnsdk.com/only-audio.m4s", true,"","",QVideoRenderType.NONE
                );
                builder.addElement(
                        "", QURLType.QVIDEO, 1080,
                        "http://demo-videos.qnsdk.com/only-video-1080p-60fps.m4s", true,"","",QVideoRenderType.NONE
                );

                name = "2-点播-http-m4s-60fps-音视流分离";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));

                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        PlayerSettingRespostory.getInstance().getStartPosition()
                                )
                        )
                );

                builder =new QMediaModelBuilder();
                builder.addElement(
                        "", QURLType.QAUDIO_AND_VIDEO, 960,
                        "http://demo-videos.qnsdk.com/shortvideo/nike.mp4", true,"","",QVideoRenderType.NONE
                );
                name = "3-点播-http-mp4-28ps-竖屏";

                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        PlayerSettingRespostory.playerSettingRespostory.getStartPosition())
                        )
                );



//        builder = QMediaModelBuilder()
//        builder.addElement(
//            "", QURLType.QAUDIO_AND_VIDEO, 720,
//            "http://pili-hdl.qnsdk.com/sdk-live/timestamp-6M.flv", true
//        )
//
//        name = "4-直播-http-flv-60fps"
//        videoParams = LongVideoParams(name, name.hashCode().toLong())
//        dataSourceBuilder.addVideo(
//            videoParams,
//            arrayListOf<LongPlayableParams>(
//                LongPlayableParams(
//                    builder.build(true),
//                    LongControlPanelType.Normal.type,
//                    DisplayOrientation.LANDSCAPE,
//                    LongEnviromentType.LONG.type,
//                    0L
//                )
//            )
//        )

//        builder = QMediaModelBuilder()
//        name = "5-直播-http-m3u8-60fps"
//        builder.addElement(
//            "", QURLType.QAUDIO_AND_VIDEO, 720,
//            "http://pili-hls.qnsdk.com/sdk-live/timestamp-6M.m3u8", true
//        )
//        videoParams = LongVideoParams(name, name.hashCode().toLong())
//        dataSourceBuilder.addVideo(
//            videoParams,
//            arrayListOf<LongPlayableParams>(
//                LongPlayableParams(
//                    builder.build(true),
//                    LongControlPanelType.Normal.type,
//                    DisplayOrientation.LANDSCAPE,
//                    LongEnviromentType.LONG.type,
//                    0L
//                )
//            )
//        )

//        builder = QMediaModelBuilder()
//        url = "rtmp://pili-rtmp.qnsdk.com/sdk-live/timestamp-6M"
//        builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 720, url, true)
//        url = "rtmp://pili-rtmp.qnsdk.com/sdk-live/timestamp"
//        builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 480, url, false)
//        name = "6-直播-rtmp-多清晰度"
//        videoParams = LongVideoParams(name, name.hashCode().toLong())
//        dataSourceBuilder.addVideo(
//            videoParams,
//            arrayListOf<LongPlayableParams>(
//                LongPlayableParams(
//                    builder.build(true),
//                    LongControlPanelType.Normal.type,
//                    DisplayOrientation.LANDSCAPE,
//                    LongEnviromentType.LONG.type,
//                    0L
//                )
//            )
//        )

                builder =new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/song.mp3";
                builder.addElement("", QURLType.QAUDIO, 100, url, true,"","",QVideoRenderType.NONE);
                name = "8-点播-http-mp3-纯音频";

                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        PlayerSettingRespostory.playerSettingRespostory.getStartPosition()
                                )
                        )
                );

                builder =new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/only-video-1080p-60fps.m4s";
                builder.addElement("", QURLType.QVIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "9-点播-http-m4s-纯视频";
                videoParams =new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        PlayerSettingRespostory.playerSettingRespostory.getStartPosition()
                                )
                        )
                );



                builder =new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/bbk-bt709.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "10-点播-http-mp4-50fps";
                videoParams =new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        PlayerSettingRespostory.getInstance().getStartPosition()
                                )
                        )
                );


//        builder = QMediaModelBuilder()
//        url = "http://demo-videos.qnsdk.com/bbk-H265-50fps.mp4"
//        builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, url, true)
//        videoParams = LongVideoParams(url, url.hashCode().toLong())
//        dataSourceBuilder.addVideo(
//            videoParams,
//            arrayListOf<LongPlayableParams>(
//                LongPlayableParams(
//                    builder.build(),
//                    LongControlPanelType.Normal.type,
//                    DisplayOrientation.LANDSCAPE,
//                    LongEnviromentType.LONG.type,
//                    false
//                )
//            )
//        )

                builder =new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/Sync-Footage-V1-H264.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "11-点播-http-mp4-60fps-音画同步测试";
                videoParams =new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        PlayerSettingRespostory.getInstance().getStartPosition()
                                )
                        )
                );


                builder =new QMediaModelBuilder();
                url = "https://img.qunliao.info:443/4oEGX68t_9505974551.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 360, url, true,"","",QVideoRenderType.NONE);
                name = "12-点播-https-mp4-25fps-端口443";

                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        PlayerSettingRespostory.playerSettingRespostory.getStartPosition()
                                )
                        )
                );

                builder =new QMediaModelBuilder();
                url = "rtmp://pili-publish.qnsdk.com/sdk-live/6666";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "13-1-直播-rtmp://pili-publish.qnsdk.com/sdk-live/6666";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(true),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "http://pili-hls.qnsdk.com/sdk-live/6666.m3u8";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "13-2-直播-http://pili-hls.qnsdk.com/sdk-live/6666.m3u8";
                videoParams =new LongVideoParams(name,Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(true),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder =new QMediaModelBuilder();
                url = "http://pili-hdl.qnsdk.com/sdk-live/6666.flv";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "13-3-直播-http://pili-hdl.qnsdk.com/sdk-live/6666.flv";
                videoParams =new LongVideoParams(name,Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(true),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );




                builder =new QMediaModelBuilder();
                url = "https://sdk-release.qnsdk.com/zeng.m3u8";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "14-点播-hhtp-m3u8-30fps";
                videoParams =new LongVideoParams(name,Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );


                builder =new QMediaModelBuilder();
                url = "https://sdk-release.qnsdk.com/H264-flv.flv";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 720, url, true,"","",QVideoRenderType.NONE);
                name = "15-点播-https-flv-30fps-H264";
                videoParams =new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "https://sdk-release.qnsdk.com/10701032_194625-hd%20%281%29.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "16-点播-https-mp4-30fps-竖屏";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );



                builder = new QMediaModelBuilder();
                url = "https://sdk-release.qnsdk.com/1599039859854_9242359.mp3";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "18-点播-https-mp3-纯音频-有封面";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "https://sdk-release.qnsdk.com/VID_20220207_144828.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "19-点播-https-mp4-30fps-旋转角度(180度)";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "https://sdk-release.qnsdk.com/10037108_065355-hd%20%281%29.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 720, url, true,"","",QVideoRenderType.NONE);
                name = "20-点播-https-mp4-30fps-非正常比例";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );




                builder = new QMediaModelBuilder();
                url = "https://sdk-release.qnsdk.com/test1.wma";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 720, url, true,"","",QVideoRenderType.NONE);
                name = "21-点播-https-wma-30fps-不支持的封装格式";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "https://sdk-release.qnsdk.com/flv.flv";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 720, url, true,"","",QVideoRenderType.NONE);
                name = "22-点播-https-flv-FLV1-不支持的编码格式";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "https://sdk-release.qnsdk.com/video1643265479033.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 720, url, true,"","",QVideoRenderType.NONE);
                name = "23-点播-https-mp4-不支持的像素格式-yuvj420p";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.VERTICAL,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

//        builder = QMediaModelBuilder()
//        name = "24-点播m3u8-加密"
//        builder.addElement(
//            "", QURLType.QAUDIO_AND_VIDEO, 720,
//            "http://cdn.qiniushawn.top/timeshift3.m3u8", true
//        )
//        videoParams = LongVideoParams(name, name.hashCode().toLong())
//        dataSourceBuilder.addVideo(
//            videoParams,
//            arrayListOf<LongPlayableParams>(
//                LongPlayableParams(
//                    builder.build(),
//                    LongControlPanelType.Normal.type,
//                    DisplayOrientation.LANDSCAPE,
//                    LongEnviromentType.LONG.type,
//                    0L,
//
//                    true
//                )
//            )
//        )


                builder = new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/VR-Panorama-Equirect-Angular-4500k.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 4000, url, true, "","",
                        QVideoRenderType.PANORAMA_EQUIRECT_ANGULAR);
                name = "25-点播-http-vr-Equirect-Angular-mp4";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "http://pili-playback.qnsdk.com/recordings/z1.sdk-live.6666/1652355051_1652355244.m3u8";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "26-点播-http-m3u8-SEI";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/long_movie.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "27-点播-http-电影";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

//        builder = QMediaModelBuilder()
//        url = "https://sdk-release.qnsdk.com/4K_25_21514.mp4"
//        builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 3840, url, true)
//        name = "27-点播-https-4K-25FPS-22937kb"
//        videoParams = LongVideoParams(name, name.hashCode().toLong())
//        dataSourceBuilder.addVideo(
//            videoParams,
//            arrayListOf<LongPlayableParams>(
//                LongPlayableParams(
//                    builder.build(false),
//                    LongControlPanelType.Normal.type,
//                    DisplayOrientation.LANDSCAPE,
//                    LongEnviromentType.LONG.type,
//                    0L
//                )
//            )
//        )

                builder = new QMediaModelBuilder();
                url = "https://sdk-release.qnsdk.com/2K_60_6040.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 2560, url, true,"","",QVideoRenderType.NONE);
                name = "28-点播-https-2K-60FPS-6333kb";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "https://sdk-release.qnsdk.com/2K_25_11700.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 2560, url, true,"","",QVideoRenderType.NONE);
                name = "29-点播-https-2K-25FPS-12053kb";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "https://sdk-release.qnsdk.com/1080_60_5390.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "30-点播-https-1080-60FPS-5656kb";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "https://sdk-release.qnsdk.com/4K_3840%2A1920.m4s";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 3840, url, true,"","",QVideoRenderType.NONE);
                name = "31-点播-https-4k-30FPS-8534kb";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );


                builder = new QMediaModelBuilder();
                url = "https://demo-qnrtc-files.qnsdk.com/six_second.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 480, url, true,"","",QVideoRenderType.NONE);
                name = "32-点播-https-480-30FPS-6秒视频";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );


                builder = new QMediaModelBuilder();
                url = "rtmp://pili-rtmp.qnsdk.com/sdk-live/audioonly";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "33-1-直播-纯音频-rtmp://pili-rtmp.qnsdk.com/sdk-live/audioonly";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(true),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "http://pili-hls.qnsdk.com/sdk-live/audioonly.m3u8";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "33-2-直播-纯音频-http://pili-hls.qnsdk.com/sdk-live/audioonly.m3u8";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(true),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "http://pili-hdl.qnsdk.com/sdk-live/audioonly.flv";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "33-3-直播-纯音频-http://pili-hdl.qnsdk.com/sdk-live/audioonly.flv";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(true),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "rtmp://pili-rtmp.qnsdk.com/sdk-live/videoonly";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "34-1-直播-纯视频-rtmp://pili-rtmp.qnsdk.com/sdk-live/videoonly";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(true),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "http://pili-hls.qnsdk.com/sdk-live/videoonly.m3u8";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "34-2-直播-纯视频-http://pili-hls.qnsdk.com/sdk-live/videoonly.m3u8";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(true),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "http://pili-hdl.qnsdk.com/sdk-live/videoonly.flv";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "34-3-直播-纯视频-http://pili-hdl.qnsdk.com/sdk-live/videoonly.flv";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(true),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/only-audio-wav.wav";
                builder.addElement("", QURLType.QAUDIO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "35-点播-纯音频-wav-http://demo-videos.qnsdk.com/only-audio-wav.wav";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

                builder = new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/audio-only-flac.flac";
                builder.addElement("", QURLType.QAUDIO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "36-点播-纯音频-flac-http://demo-videos.qnsdk.com/audio-only-flac.flac";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(false),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );

//        builder = QMediaModelBuilder()
//        url = "https://live-vod.huxiu.com/20220817_10582_playback_759.mp3"
//        builder.addElement("", QURLType.QAUDIO, 1080, url, true)
//        name = "38-点播-mp3-长 -https://live-vod.huxiu.com/20220817_10582_playback_759.mp3"
//        videoParams = LongVideoParams(name, name.hashCode().toLong())
//        dataSourceBuilder.addVideo(
//            videoParams,
//            arrayListOf<LongPlayableParams>(
//                LongPlayableParams(
//                    builder.build(false),
//                    LongControlPanelType.Normal.type,
//                    DisplayOrientation.LANDSCAPE,
//                    LongEnviromentType.LONG.type,
//                    0L
//                )
//            )
//        )



                builder = new QMediaModelBuilder();
                url = "srt://180.101.136.81:1935?streamid=#!::h=pilidemo/timestamp,m=request,domain=live-pilidemo.cloudvdn.com";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 1080, url, true,"","",QVideoRenderType.NONE);
                name = "39-直播-SRT-srt://180.101.136.81:1935?streamid=#!::h=pilidemo/timestamp,m=request,domain=live-pilidemo.cloudvdn.com";
                videoParams = new LongVideoParams(name, Long.valueOf(name.hashCode()));
                dataSourceBuilder.addVideo(
                        videoParams,
                        Arrays.asList(
                                new LongPlayableParams(
                                        builder.build(true),
                                        LongControlPanelType.Normal.type,
                                        DisplayOrientation.LANDSCAPE,
                                        LongEnviromentType.LONG.type,
                                        0L
                                )
                        )
                );


                return dataSourceBuilder.build();
        }
}