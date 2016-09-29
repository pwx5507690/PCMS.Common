<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Kode is a Premium Bootstrap Admin Template, It's responsive, clean coded and mobile friendly">
        <meta name="keywords" content="bootstrap, admin, dashboard, flat admin template, responsive," />
        <title>PCMS</title>
        <link href="css/root.css" rel="stylesheet">
    </head>
    <body style="background-color: #f5f5f5">
        <div class="loading" id="loading"><img src="img/loading.gif" alt="loading-img"></div>   
        <div class="content" style="margin-left: 0; margin-bottom: 40px;padding-top: 20px">
            <div class="page-header">
                <h1 class="title">语言库</h1>
                <ol class="breadcrumb">
                    <li class="active">增加各种语言版本.</li>
                </ol>
                <div class="right">
                    <div class="btn-group" role="group" aria-label="...">
                        <a href="#" class="btn btn-light"><i class="fa fa-refresh"></i></a>
                    </div>
                </div>
            </div>
            <div class="container-widget">              
                <div class="row">
                    <div class="col-md-6">
                        <div class="panel panel-default ">
                            <div class="panel-title">
                                添加语言
                            </div>
                            <div class="panel-body">
                                <form>
                                    <div class="form-group">
                                        <label for="languageType" class="form-label">语言类型</label>
                                        <select id="languageType" name="languageType" type="text" class="form-control form-control-line" >
                                            
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="name" class="form-label">名称</label>
                                        <input type="text" class="form-control form-control-line" name="name" id="name">
                                    </div>
                                    <div class="form-group">
                                        <label for="value" class="form-label">值</label>
                                        <input type="text" class="form-control form-control-line" id="value">
                                    </div>
                                    <button type="button" id="sub" class="btn btn-default">提交</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="panel panel-default">
                            <div class="panel-title">
                                语言列表
                            </div>
                            <div class="panel-body table-responsive">
                                <div class="searchform"><button class="btn btn-danger">删除</button></div>
                                <div class="searchform" style="float:right">
                                    <input type="text" class="form-control" id="searchbox" placeholder="Search">
                                    <span class="searchbutton"><i class="fa fa-search"></i></span>
                                </div>

                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <td class="text-center"><i class="fa fa-trash"></i></td>
                                            <td>名称</td>
                                            <td>值</td>
                                        </tr>
                                    </thead>
                                    <tbody id="langugeContent">
                                        <tr>
                                            <td class="text-center"><div class="checkbox margin-t-0">
                                                    <input id="checkbox1" type="checkbox"><label for="checkbox1"></label></div></td>
                                            <td># <b>9652</b></td>
                                            <td>Kode Gaming Laptop</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="row footer" style="position: fixed;width: 100%; bottom: 0; background: rgba(255, 255, 255,1)">
                <div class="col-md-6 text-left">
                    Copyright &copy; 2016.Company name All rights reserved.
                </div>
                <div class="col-md-6 text-right">
                    PCMS.language 
                </div> 
            </div>
        </div>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script src="js/bootstrap/bootstrap.min.js"></script>
        <script src="js/index.js" type="text/javascript"></script>
    </body>
</html>
