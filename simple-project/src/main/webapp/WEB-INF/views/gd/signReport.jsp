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
    <div class="box" style="margin-top:30px">
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
                <td>${gsr.allpersons}人</td>
            </tr>
        </table>
        <h4>受训工种</h4>
        <table>
            <tr>
                <td>钢筋工</td>
                <td>√</td>
                <td>混凝土工</td>
                <td>√</td>
            </tr>
            <tr>
                <td>木工</td>
                <td>√</td>
                <td>电工</td>
                <td>√</td>
            </tr>
            <tr>
                <td>水暖工</td>
                <td>√</td>
                <td>架子工</td>
                <td>√</td>
            </tr>
            <tr>
                <td>测量放线工</td>
                <td>√</td>
                <td>杂工</td>
                <td>√</td>
            </tr>
            <tr>
                <td>砌筑工</td>
                <td>√</td>
                <td>油漆工</td>
                <td>√</td>
            </tr>
            <tr>
                <td>机械工</td>
                <td>√</td>
                <td></td>
                <td></td>
            </tr>
        </table>
        <h4>培训内容</h4>
        <table>
            <tr>
                <td>洞口坠落体验</td>
                <td>√</td>
                <td>安全防护用品体验</td>
                <td>√</td>
            </tr>
            <tr>
                <td>安全绳缓降体验</td>
                <td>√</td>
                <td>钢丝绳使用方法</td>
                <td>√</td>
            </tr>
            <tr>
                <td>平台倾倒体验</td>
                <td>√</td>
                <td>止血包扎</td>
                <td>√</td>
            </tr>
            <tr>
                <td>防护栏推到体验</td>
                <td>√</td>
                <td>爬梯体验</td>
                <td>√</td>
            </tr>
            <tr>
                <td>安全帽撞击体验</td>
                <td>√</td>
                <td>小型配电箱防护网</td>
                <td>√</td>
            </tr>
            <tr>
                <td>安全鞋防砸体验</td>
                <td>√</td>
                <td>尝试认识与学习</td>
                <td>√</td>
            </tr>
            <tr>
                <td>重物搬运体验</td>
                <td>√</td>
                <td>心肺复苏体验</td>
                <td>√</td>
            </tr>
            <tr>
                <td>挡土墙体验</td>
                <td>√</td>
                <td>平衡木体验设备</td>
                <td>√</td>
            </tr>  
            <tr>
                <td>工地用电安全体验</td>
                <td>√</td>
                <td></td>
                <td></td>
            </tr>                       
        </table>
        <h4>培训人员名单</h4>
        <table>
        	<tr>
                <td>姓名</td>
                <td>性别</td>
                <td>身份证号</td>
                <td>综合成绩</td>
            </tr>
            <c:if test="${not empty gsr.workers }">
               	<c:forEach items="${gsr.workers}" var="workers">
               		<tr>
                    	<td>${workers.name}</td>
                    	<td>${workers.sex}</td>
                    	<td>${workers.cardNo}</td>
                    	<c:if test="${workers.zonghe==0}">
                    		<td>优秀</td>
                    	</c:if>
                    	<c:if test="${workers.zonghe==70}">
                    		<td>不及格</td>
                    	</c:if>
                    	<c:if test="${workers.zonghe==80}">
                    		<td>及格</td>
                    	</c:if> 
                    	<c:if test="${workers.zonghe==90}">
                    		<td>良好</td>
                    	</c:if>                    	
                    	<c:if test="${workers.zonghe==100}">
                    		<td>优秀</td>
                    	</c:if>                     	
                	</tr>
               	</c:forEach>
            </c:if>
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
