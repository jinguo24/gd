<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        .box {width:820px;margin:0 auto;border:1px solid #000;background: #c5d9f1;position: relative;}
        .head {height:100px;border-bottom: 1px solid #000;}
        .head h3{text-align: center;font-size: 20px;line-height: 40px;}
        table{    width: 100%; border-collapse: collapse;}
        table td{border: 1px solid #000;height: 32px;width: 202px;text-align: center;}


        table  tr:nth-child(even)  {background-color:#cde9cf;}
        table  tr:nth-child(odd)  {background-color:#c5bd97;}

        h4{height: 32px;background: #fde9d9;text-align:center;line-height: 32px;}

        .box_top{width: 394px;height: 226px;background: #fff;position: absolute;top:105px;right: 10px;}
    </style>
</head>
<body>
    <div class="box">
        <div class="box_top">${gsr.bujigeCounts}>>${gsr.jigeCounts}>>${gsr.lianghaoCounts}>>${gsr.youxiuCounts}</div>
        <div class="head">
            <h3>${tanentName}</h3>
            <h3>安立方工地安全体验馆综合评测报告</h3>
        </div>
        <table>
            <tr>
                <td>制表日期:</td>
                <td>${gsr.judgeDate}</td>
                <td>23</td>
                <td>2</td>
            </tr>
            <tr>
                <td>签到人数:</td>
                <td>${gsr.signCounts}人</td>
                <td>2</td>
                <td>2</td>
            </tr>
            <tr>
                <td>培训人数:</td>
                <td>${gsr.homeworkCounts}人</td>
                <td>2</td>
                <td>2</td>
            </tr>
            <tr>
                <td>问卷通过:</td>
                <td>${gsr.homeworkPass}人</td>
                <td>2</td>
                <td>2</td>
            </tr>
            <tr>
                <td>评价通过:</td>
                <td>${gsr.judgePass}人</td>
                <td>2</td>
                <td>2</td>
            </tr>
            <tr>
                <td>总得分率:</td>
                <td>100人</td>
                <td>2</td>
                <td>2</td>
            </tr>
            <tr>
                <td>累计培训人数:</td>
                <td>${allpersons}人</td>
                <td>2</td>
                <td>2</td>
            </tr>
        </table>
        <h4>培训内容</h4>

        <table>
            <tr>
                <td>制表日期:</td>
                <td>${gsr.judgeDate}</td>
                <td>23</td>
                <td>2</td>
            </tr>
            <tr>
                <td>签到人数:</td>
                <td>${gsr.signCounts}人</td>
                <td>2</td>
                <td>2</td>
            </tr>
            <tr>
                <td>培训人数:</td>
                <td>${gsr.homeworkCounts}人</td>
                <td>2</td>
                <td>2</td>
            </tr>
            <tr>
                <td>问卷通过:</td>
                <td>${gsr.homeworkPass}人</td>
                <td>2</td>
                <td>2</td>
            </tr>
            <tr>
                <td>评价通过:</td>
                <td>${gsr.judgePass}人</td>
                <td>2</td>
                <td>2</td>
            </tr>
            <tr>
                <td>总得分率:</td>
                <td>100人</td>
                <td>2</td>
                <td>2</td>
            </tr>
            <tr>
                <td>累计培训人数:</td>
                <td>100人</td>
                <td>2</td>
                <td>2</td>
            </tr>
        </table>
    </div>
</body>
</html>
