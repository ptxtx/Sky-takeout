package com.sky.controller.admin;

import com.sky.mapper.OrderMapper;
import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

//数据统计相关接口
@RestController
@RequestMapping("admin/report")
@Slf4j
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("/turnoverStatistics")
    public Result<TurnoverReportVO> turnOverStatistics(@DateTimeFormat(pattern ="yyyy-MM-dd" ) LocalDate begin,@DateTimeFormat(pattern ="yyyy-MM-dd" )LocalDate end){
        log.info("营业额统计");
        TurnoverReportVO turnoverStatistics = reportService.getTurnoverStatistics(begin, end);
        return Result.success(turnoverStatistics);
    }

    @GetMapping("/userStatistics")
    public Result<UserReportVO> userStatistics(@DateTimeFormat(pattern ="yyyy-MM-dd" ) LocalDate begin,@DateTimeFormat(pattern ="yyyy-MM-dd" )LocalDate end){
        log.info("用户统计");
        UserReportVO userStatistics = reportService.getUserStatistics(begin, end);
        return Result.success(userStatistics);
    }

    @GetMapping("/ordersStatistics")
    public Result<OrderReportVO> ordersStatistics(@DateTimeFormat(pattern ="yyyy-MM-dd" ) LocalDate begin,@DateTimeFormat(pattern ="yyyy-MM-dd" )LocalDate end){
        log.info("订单统计");
        OrderReportVO ordersStatistics = reportService.getOrdersStatistics(begin, end);
        return Result.success(ordersStatistics);
    }

    @GetMapping("/top10")
    public Result<SalesTop10ReportVO> top10(@DateTimeFormat(pattern ="yyyy-MM-dd" ) LocalDate begin, @DateTimeFormat(pattern ="yyyy-MM-dd" )LocalDate end){
        log.info("销量排名");
        return Result.success(reportService.getTop10(begin, end));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        log.info("导出数据");
        reportService.exportBusinessData(response);
    }


}
