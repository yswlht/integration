package com.example.shirodemo.quartz;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling
public class ScheduledTask {

    /**
     * 每天12点调用
     * 参数分别表示 秒 分 时 日 月 周 年(可省略)
     * *表示不关心
     * 日和周需要有一个是?
     * */
    @Scheduled(cron = "* * 12 * * ? ")
    public void everyTenSeconds() {
        String date = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println(date);
    }
}
