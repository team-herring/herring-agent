package org.herring.agent.generator;

import java.util.Random;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 4.
 * Time: 오전 3:02
 */

public class IISLogGeneratorToString {
    final int genSize = 5;
    final int genCount = 100;
    String sampleLog;
    String[] sampleLogs;
    Random random;


    public IISLogGeneratorToString() {
        random = new Random();
        sampleLog = "#Software: Microsoft Internet Information Services 6.0\n" +
                "#Version: 1.0\n" +
                "#Date: 2013-05-10 00:24:19\n" +
                "#Fields: date time s-sitename s-ip cs-method cs-uri-stem cs-uri-query s-port cs-username c-ip cs(User-Agent) sc-status sc-substatus sc-win32-status \n" +
                "2013-05-10 00:24:19 W3SVC1 165.132.34.47 GET /robots.txt - 80 - 65.55.215.45 msnbot-media/1.1+(+http://search.msn.com/msnbot.htm) 200 0 0\n" +
                "2013-05-10 00:24:19 W3SVC1 165.132.34.47 GET /img/03_edu/03_05_tab_01.gif - 80 - 65.55.215.45 msnbot-media/1.1+(+http://search.msn.com/msnbot.htm) 200 0 0\n" +
                "2013-05-10 00:25:30 W3SVC1 165.132.34.47 GET /05_commu/commu_02_list.asp bbs_code=9 80 - 66.249.74.220 Mozilla/5.0+(iPhone;+U;+CPU+iPhone+OS+4_1+like+Mac+OS+X;+en-us)+AppleWebKit/532.9+(KHTML,+like+Gecko)+Version/4.0.5+Mobile/8B117+Safari/6531.22.7+(compatible;+Googlebot-Mobile/2.1;++http://www.google.com/bot.html) 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /index.asp - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /_lib/js/custom/cookies.js - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /_tomatodream/status/js/status.js - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /_lib/js/custom/common_function.js - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /css/text.css - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /_module/bbs/skin/default/css/basic.css - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /_lib/js/custom/jsHelper.js - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /_lib/js/jquery/jquery-1.3.2.js - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /_tomatodream/status/inc/status_ok.asp screenX=720&screenY=1280&Url=/index.asp&ei=OTHER%20BROWSER&eiVer=0&os=Linux&referrer= 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/common_img/logout.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/common_img/mypage.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/01_notice_title_04.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/01_notice_title_02.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/01_notice_title_05.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/01_notice_title_03.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/01_notice_title_r_01.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/02_notice_title_r_01.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/common_img/bot_menu_02.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/main_copyright_bg.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/common_img/bot_menu_05.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/common_img/bot_menu_03.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/02_notice_title_02.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/02_notice_title_03.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/common_img/bot_menu_06.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/common_img/btn_serch.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/common_img/bot_ser_title_01.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/common_img/bot_ser_title_02.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/main_bot_banner3.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/main_banner_01.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/main_banner_02.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/main_banner_04.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/main_banner_03.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/common_img/copyright.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /_module/popup/_layer.asp idx=undefined|277|800a000d|Type_mismatch:_'CDbl' 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 500 0 0\n" +
                "2013-05-10 00:25:33 W3SVC1 165.132.34.47 GET /img/index_img/main_banner_05.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:35 W3SVC1 165.132.34.47 GET /img/index_img/main_bg.gif - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:35 W3SVC1 165.132.34.47 GET /favicon.ico - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 404 0 2\n" +
                "2013-05-10 00:25:36 W3SVC1 165.132.34.47 GET /03_edu/edu_04.asp - 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0\n" +
                "2013-05-10 00:25:36 W3SVC1 165.132.34.47 GET /_tomatodream/status/inc/status_ok.asp screenX=720&screenY=1280&Url=/03_edu/edu_04.asp&ei=OTHER%20BROWSER&eiVer=0&os=Linux&referrer=http://geo.yonsei.ac.kr/ 80 - 211.36.134.20 Mozilla/5.0+(Linux;+U;+Android+4.1.2;+ko-kr;+SHV-E250L+Build/JZO54K)+AppleWebKit/534.30+(KHTML,+like+Gecko)+Version/4.0+Mobile+Safari/534.30 200 0 0";
        sampleLogs = sampleLog.split("\n");
    }

    public String getSampleLog() {
        StringBuilder builder = new StringBuilder();
        for (int count = 0; count < genCount; count++) {
            for (int i = 0; i < genSize; i++) {
                int randNum = random.nextInt(sampleLogs.length);
                builder.append(sampleLogs[randNum] + "\n");
            }
        }
        return builder.toString();
    }

    public String getSampleLog(int logRow) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < logRow; i++) {
            int randNum = random.nextInt(sampleLogs.length);
            builder.append(sampleLogs[randNum] + "\n");
        }
        return builder.toString();
    }
}
