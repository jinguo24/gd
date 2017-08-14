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
        .box {width: 542px;margin:0 auto;}
        .head {border:1px solid #000;border-bottom: 0px;}
        .head h3{text-align: center;font-size: 20px;line-height: 40px;}

        table{   width: 100%; border-collapse: collapse;}
        table td{border: 1px solid #000;height: 32px;text-align: center;}
        table tr td:nth-of-type(1){width: 142px;}
        table tr td:nth-of-type(2){width: 170px;}
        table tr td:nth-of-type(3){width: 140px;}
        table tr td:nth-of-type(4){width: 90px;}
    </style>
    <script type="text/javascript" charset="utf-8" src="${projectpath}/cmsapi/js/report/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="${projectpath}/cmsapi/js/report/jq.qrcode.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="${projectpath}/cmsapi/js/report/core.js"></script>
</head>
<body>
		<input type="hidden" value="${gsw.cardNo}" id="cardNo" />
        <div class="box">
            <div class="head">
                <h3>${tanentName}</h3>
                <h3>安立方工地安全体验馆综合评测报告</h3>
            </div>
            <table>
                <tr>
                    <td colspan="2">基本信息</td>

                    <td colspan="2" rowspan="5"><img src="${gsw.cardImage}" /></td>
                </tr>
                <tr>

                    <td>姓名</td>
                    <td>${gsw.name}</td>
                </tr>
                <tr>
                    <td>身份证号</td>
                    <td>${gsw.cardNo}</td>
                </tr>
                <tr>

                    <td>考试时间</td>
                    <td>${homeworkTime}</td>
                </tr>


                <tr>
                    <td colspan="2">考试成绩</td>
                </tr>
                <tr>
                    <td>机考成绩</td>
                    <td>${jkcj}</td>
                    <td colspan="2" rowspan="5"><div id = "code"></div></td>
                </tr>
                <c:if test="${not empty judgeItems}">
                	<c:forEach items="${judgeItems}" var="judmap">
                		<tr>
	                    	<td>${judmap.key}</td>
	                    	<td>${judmap.value}</td>
	                	</tr>
                	</c:forEach>
	                
                </c:if>
                <tr>
                    <td>综合成绩</td>
                    <td>${zonghe}</td>
                </tr>
                <tr>
                    <td colspan="4">培训内容</td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox" checked></td>
                    <td>体检</td>
                    <td><input type="checkbox" checked></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox" checked></td>
                    <td>1</td>
                    <td><input type="checkbox" checked></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox" checked></td>
                    <td>1</td>
                    <td><input type="checkbox" checked></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox" checked></td>
                    <td>1</td>
                    <td><input type="checkbox" checked></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox" checked></td>
                    <td>1</td>
                    <td><input type="checkbox" checked></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox" checked></td>
                    <td>1</td>
                    <td><input type="checkbox" checked></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox" checked></td>
                    <td>1</td>
                    <td><input type="checkbox" checked></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox" checked></td>
                    <td>1</td>
                    <td><input type="checkbox" checked></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox" checked></td>
                    <td>1</td>
                    <td><input type="checkbox" checked></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox" checked></td>
                    <td>1</td>
                    <td><input type="checkbox" checked></td>
                </tr>
                <tr>
                    <td colspan="2">评测人:工地管理员</td>
                    <td colspan="2">发证日期:${maketime}</td>
                </tr>
            </table>
        </div>
</body>
</html>

<script>
var cardNo = $("#cardNo").val();
$("#code").qrcode({
        render: "canvas", //table方式
        width: 125, //宽度
        height:125, //高度
        text: "http://gd.class.zhongguoanquanjiaoyu.com/out.html?cardNo="+cardNo //任意内容
});
</script>