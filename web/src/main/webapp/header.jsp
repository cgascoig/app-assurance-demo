<%@ page language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Car sales</title>
        <link rel = "icon" href="favicon.png" type = "image/x-icon"> 
        <meta name="google" content="notranslate">
        <link rel="stylesheet" type="text/css" href="cars.css" />
    </head>
<body>
<div id="container">

    <div id="nav">
        <ul>
            <li><a href="<s:url action='home'/>">Home</a></li>
            <li><a href="<s:url action='inventory'/>">Cars</a></li>
            <li><a href="<s:url action='about'/>">About</a></li>
        </ul>
    </div>
