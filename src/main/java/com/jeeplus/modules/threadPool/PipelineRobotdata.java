package com.jeeplus.modules.threadPool;

import java.io.IOException;
import java.text.DecimalFormat;


import com.jeeplus.modules.websoket.MyWebSocket;
import com.jeeplus.modules.websoket.utils.ToJsonUtil;

public class PipelineRobotdata {

    public static int checkAF = 0;
    public static int count = 0;
    public static int countfo = 0;
    static DecimalFormat df = new DecimalFormat("#0.00");

    public static void pipelineData(String data) throws IOException {

        System.out.println(count+=1);
        System.out.println("：  "+ data);

        // 一下发送数据
        String[] datas2 = data.split(",");
        if (datas2.length != 7) {
            return;
        }
        String str = datas2[0];
        String str1 = datas2[1];
        String str2 = datas2[2];
        String str3 = datas2[3];
        String str4 = datas2[4];
        String str5 = datas2[5];
        String str6 = datas2[6];



        String str0 = str.replaceAll("\\^", "");
        try {
            int valueOf = Integer.parseInt(str0);
            int valueOf1 = Integer.parseInt(str1);
            int valueOf2 = Integer.parseInt(str2);
            int valueOf3 = Integer.parseInt(str3);
            int valueOf4 = Integer.parseInt(str4);
            int valueOf5 = Integer.parseInt(str5);
            int valueOf6 = Integer.parseInt(str6);
            //x轴偏移量 y轴偏移量 z轴偏移量  测距仪距离 移动距离
            if (valueOf + valueOf1 + valueOf2 + valueOf3 + valueOf4 != valueOf5) {

                System.out.println("不同过：  "+ data);
                return;
            }
            String numberValue = df.format((double) valueOf / 100) + ","
                    + df.format((double) valueOf1 / 100) + "," + df.format((double) valueOf2 / 100) + ","
                    + df.format((double) valueOf3 / 100) + "," + df.format((double) valueOf4 / 100)+ "," + df.format((double) valueOf6 );

            MyWebSocket.sendInfo(ToJsonUtil.buildJsonRs(true, "numberValue", numberValue).toString());
//            System.out.println("通过时候数据：  "+numberValue);


        } catch (Exception e) {
            countfo+=1;
      System.out.println("格式不对：  "+ countfo);
        }
    }

}
