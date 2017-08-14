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
    <script type="text/javascript" charset="utf-8" src="${projectpath}/cmsapi/js/report/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="${projectpath}/cmsapi/js/report/core.js"></script>
    <script type="text/javascript" charset="utf-8" src="${projectpath}/cmsapi/js/report/echarts.min.js"></script>
</head>
<body>
	<input type="hidden" value="${gsr.bujigeCounts}" id = "bujigeCounts">
	<input type="hidden" value="${gsr.jigeCounts}" id = "jigeCounts">
	<input type="hidden" value="${gsr.lianghaoCounts}" id = "lianghaoCounts">
	<input type="hidden" value="${gsr.youxiuCounts}" id = "youxiuCounts">
    <div class="box">
        <div class="box_top" id ="_chart"></div>
        <div class="head">
            <h3>${gsr.tanentName}</h3>
            <h3>安立方工地安全体验馆综合评测报告</h3>
        </div>
        <table style="width: 50%">
            <tr>
                <td>制表日期:</td>
                <td>${gsr.judgeDate}</td>
                
            </tr>
            <tr>
                <td>签到人数:</td>
                <td>${gsr.signCounts}人</td>
                
            </tr>
            <tr>
                <td>培训人数:</td>
                <td>${gsr.homeworkCounts}人</td>
                
            </tr>
            <tr>
                <td>问卷通过:</td>
                <td>${gsr.homeworkPass}人</td>
                
            </tr>
            <tr>
                <td>评价通过:</td>
                <td>${gsr.judgePass}人</td>
               
            </tr>
            <tr>
                <td>优秀人数:</td>
                <td>${gsr.youxiuCounts}人</td>
                
            </tr>
            <tr>
                <td>累计培训人数:</td>
                <td>${allpersons}人</td>
            </tr>
        </table>
        <h4>培训内容</h4>

        <table>
            <tr>
                <td>制表日期:</td>
                <td><input type="checkbox" checked></td>
                <td>23</td>
                <td><input type="checkbox" checked></td>
            </tr>
            <tr>
                <td>签到人数:</td>
                <td><input type="checkbox" checked></td>
                <td>2</td>
                <td><input type="checkbox" checked></td>
            </tr>
            <tr>
                <td>培训人数:</td>
                <td><input type="checkbox" checked></td>
                <td>2</td>
                <td><input type="checkbox" checked></td>
            </tr>
            <tr>
                <td>问卷通过:</td>
                <td><input type="checkbox" checked></td>
                <td>2</td>
                <td><input type="checkbox" checked></td>
            </tr>
            <tr>
                <td>评价通过:</td>
                <td><input type="checkbox" checked></td>
                <td>2</td>
                <td><input type="checkbox" checked></td>
            </tr>
            <tr>
                <td>总得分率:</td>
                <td><input type="checkbox" checked></td>
                <td>2</td>
                <td><input type="checkbox" checked></td>
            </tr>
            <tr>
                <td>累计培训人数:</td>
                <td><input type="checkbox" checked></td>
                <td>2</td>
                <td><input type="checkbox" checked></td>
            </tr>
        </table>
    </div>
    <script>
    var myChart = echarts.init(document.getElementById("_chart"));
    function setOption(a,b,c,d){
        var option  = {
            title : {
                text: '综合统计',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['不及格','及格','良好','优秀']
            },
            series : [
                {
                    name: '成绩',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:[
                        {value:a, name:'不及格'},
                        {value:b, name:'及格'},
                        {value:c, name:'良好'},
                        {value:d, name:'优秀'}
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        myChart.setOption(option);
    }
    var bujigeCounts = $("#bujigeCounts").val();
    var jigeCounts = $("#jigeCounts").val();
    var lianghaoCounts = $("#lianghaoCounts").val();
    var youxiuCounts = $("#youxiuCounts").val();
    setOption(bujigeCounts,jigeCounts,lianghaoCounts,youxiuCounts);
</script>
</body>
</html>
