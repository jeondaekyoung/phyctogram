<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="header">
    <div class="row nav">
        <div class="col-9 m-col-12">
            <div class="col-3">            
                <h1 class="fl">Phyctogram</h1>
            </div>
            <div class="col-9">
                <button type="button" class="hidden m-block btn-menu fr"><img src="<%=application.getContextPath()%>/resources/res/imgs/menu.svg" width="36" alt="menu"></button>
                <nav class="menu m-hidden">
                    <button type="button" class="hidden btn-close fr"><img src="<%=application.getContextPath()%>/resources/res/imgs/close.svg" width="36" alt="close"></button>
                    <ul class="row">
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="about.jsp">About Us</a></li>
                        <li><a href="media.jsp">Media</a></li>
                        <li><a href="contact.jsp">Contact Us</a></li>
                    </ul>
                </nav>            
            </div>
        </div>
        <div class="col-3">
            <div class="lang"><a href="<%=application.getContextPath()%>/index.jsp">KOR</a></div>
            <div class="lang"><a href="<%=application.getContextPath()%>/en/index.jsp">ENG</a></div>
            <div class="lang"><a href="<%=application.getContextPath()%>/cn/index.jsp">CHN</a></div>
        </div>
    </div>
</div>