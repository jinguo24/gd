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
</head>
<body>
        <div class="box">
            <div class="head">
                <h3>${tanentName}</h3>
                <h3>安立方工地安全体验馆综合评测报告</h3>
            </div>
            <table>
                <tr>
                    <td colspan="2">基本信息</td>

                    <td colspan="2" rowspan="5">${gsw.cardImage}</td>
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
                    <td colspan="2" rowspan="5">二维码</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>1</td>
                </tr>

                <tr>
                    <td colspan="4">培训内容</td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox"></td>
                    <td>1</td>
                    <td><input type="checkbox"></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox"></td>
                    <td>1</td>
                    <td><input type="checkbox"></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox"></td>
                    <td>1</td>
                    <td><input type="checkbox"></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox"></td>
                    <td>1</td>
                    <td><input type="checkbox"></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox"></td>
                    <td>1</td>
                    <td><input type="checkbox"></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox"></td>
                    <td>1</td>
                    <td><input type="checkbox"></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox"></td>
                    <td>1</td>
                    <td><input type="checkbox"></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox"></td>
                    <td>1</td>
                    <td><input type="checkbox"></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox"></td>
                    <td>1</td>
                    <td><input type="checkbox"></td>
                </tr>
                <tr>
                    <td>体检</td>
                    <td><input type="checkbox"></td>
                    <td>1</td>
                    <td><input type="checkbox"></td>
                </tr>
                <tr>
                    <td colspan="2">评测人:aaaa</td>
                    <td colspan="2">发证日期:aaaa</td>
                </tr>
            </table>
        </div>
</body>
</html>