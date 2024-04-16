package com.study.Ex23Scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// 스케줄러: 주기적인 작업을 할 때
//         예) 은행 정산: 밤 11시 45분 ~ 밤 12시 (주기는 1일)
//         예) 생일 이벤트: 내일 생일자에게 쿠폰 날리고 싶다.

@Component
@Slf4j
@EnableAsync // 비동기적으로 처리하도록 제공하는 스프링 어노테이션
             // 각기 다른 스레드에서 실행된다.
public class Scheduler {
    @Autowired
    RestTemplate restTemplate;

    @Scheduled(fixedRate = 1000)
    public void task1() throws InterruptedException {
        log.info("FixedRate task - {}", System.currentTimeMillis()/1000);

        String url = "http://localhost:8080/api/batch-job";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println(response.getBody());

        log.info("dead!");
    }

    // @Scheduled(fixedRate = 1000)
    // public void task2() throws InterruptedException {
    //     log.info("FixedRate task - {}", System.currentTimeMillis()/1000);
    //     Thread.sleep(5000);
    //     log.info("dead!");
    // }

    // @Async
    // @Scheduled(fixedRate = 1000*60*60*24, initialDelay = 5000)
    // public void task3() throws InterruptedException {
    //     log.info("FixedRate task - {}", System.currentTimeMillis()/1000);
    //     Thread.sleep(5000);
    //     log.info("dead!");
    // }

    // Cron 표현식
    // 초(0~59) 분(0~59) 시간(0~23) 일(1~31) 월(1~12) 요일(0~7)
    // Spring @Scheduled cron 은 6자리 설정만 허용하며, 연도 설정은 할 수 없음.
    //               0초 45분 11시 매일 매월 매요일
    // @Scheduled(cron="0 45 11 * * *")
    // public void task4 (){
    //     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //     Date now = new Date();
    //     String strDate = sdf.format(now);
    //     log.info("cron task - {}", strDate);
    //
    // }


    // 해당 메소드 로직이 시작되는 시간을 기준으로 milli sec(밀리초) 간격으로 실행
    // 이전 작업이 완료될 때까지 다음 작업이 진행되지 않음.
    // @Async
    // @Scheduled(fixedRate = 1000)
    // public void scheduleFixedRateTask() throws InterruptedException{
    //     log.info("Fixed rate task - {}", System.currentTimeMillis()/1000);
    //     Thread.sleep(5000);
    // }
}
