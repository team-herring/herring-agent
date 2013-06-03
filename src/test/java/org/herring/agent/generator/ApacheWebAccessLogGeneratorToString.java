package org.herring.agent.generator;

import java.util.Random;

/**
 * << Description >>
 * User: hyunje
 * Date: 13. 6. 4.
 * Time: 오전 3:02
 */

public class ApacheWebAccessLogGeneratorToString {
    final int genSize = 5;
    final int genCount = 100;
    String sampleLog;
    String[] sampleLogs;
    Random random;


    public ApacheWebAccessLogGeneratorToString() {
        random = new Random();
        sampleLog ="121.124.124.170 - - [11/May/2013:14:07:49 -0400] \"GET / HTTP/1.0\" 301 -\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /index.html HTTP/1.0\" 200 6354\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /ext-3.4.1/adapter/ext/ext-base.js HTTP/1.0\" 200 30294\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /ext-3.4.1/resources/css/xtheme-gray.css HTTP/1.0\" 200 38949\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /ext-3.4.1/resources/css/ext-all.css HTTP/1.0\" 200 116366\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /style/201304101614/sonatype-all.css HTTP/1.0\" 200 10227\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /js/201304101614/sonatype-lib.js HTTP/1.0\" 200 30539\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /static/css/nexus-indexer-lucene-plugin-all.css HTTP/1.0\" 200 75\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /healthcheck/201303141545/style/healthcheck.css HTTP/1.0\" 200 2423\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /ext-3.4.1/ext-all.js HTTP/1.0\" 200 719692\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /favicon.png HTTP/1.0\" 200 1160\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /images/header_branding.png HTTP/1.0\" 200 3071\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /js/201304101614/nx-all.js?nexusVersion=2.4.0-09 HTTP/1.0\" 200 5786\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /ext-3.4.1/resources/images/gray/panel/white-top-bottom.gif HTTP/1.0\" 200 860\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /images/header_bg.gif HTTP/1.0\" 200 441\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /ext-3.4.1/resources/images/default/grid/loading.gif HTTP/1.0\" 200 771\n" +
                "121.124.124.170 - - [11/May/2013:14:07:50 -0400] \"GET /js/201304101614/sonatype-all.js?nexusVersion=2.4.0-09 HTTP/1.0\" 200 475018\n" +
                "121.124.124.170 - - [11/May/2013:14:07:51 -0400] \"GET /static/js/nexus-lvo-plugin-all.js?2.4.0-09&nexusVersion=2.4.0-09 HTTP/1.0\" 200 2592\n" +
                "121.124.124.170 - - [11/May/2013:14:07:51 -0400] \"GET /static/js/nexus-plugin-console-plugin-all.js?2.4.0-09&nexusVersion=2.4.0-09 HTTP/1.0\" 200 4576\n";
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
