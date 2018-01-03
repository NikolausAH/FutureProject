package com.blibli.pos_minimarket.Scheduled;

import com.blibli.pos_minimarket.Services.PromoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ScheduleTasks {

    @Scheduled(fixedRate = 10000) //pilih mau berapa milisecond refresh statusnya
    public void updateStatus(){
        PromoService promoService = new PromoService();
        Long currentTime = System.currentTimeMillis();
        promoService.updateStatus(currentTime);
    }
}
