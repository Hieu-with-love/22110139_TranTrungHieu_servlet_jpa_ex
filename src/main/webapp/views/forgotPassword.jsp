<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/20/2024
  Time: 3:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="Yinka Enoch Adedokun">
    <meta name="description" content="Simple Forgot Password Page Using HTML and CSS">
    <meta name="keywords" content="forgot password page, basic html and css">
    <title>Forgot Password Page - HTML + CSS</title>
    <STYLE>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "segoe ui", verdana, helvetica, arial, sans-serif;
            font-size: 16px;
            transition: all 500ms ease;
        }

        body {
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
            text-rendering: optimizeLegibility;
            -moz-font-feature-settings: "liga" on;
        }

        .row {
            background-color: rgba(20, 120, 200, 0.6);
            color: #fff;
            text-align: center;
            padding: 2em 2em 0.5em;
            width: 90%;
            margin: 2em auto;
            border-radius: 5px;
        }

        .row h1 {
            font-size: 2.5em;
        }

        .row .form-group {
            margin: 0.5em 0;
        }

        .row .form-group label {
            display: block;
            color: #fff;
            text-align: left;
            font-weight: 600;
        }

        .row .form-group input, .row .form-group button {
            display: block;
            padding: 0.5em 0;
            width: 100%;
            margin-top: 1em;
            margin-bottom: 0.5em;
            background-color: inherit;
            border: none;
            border-bottom: 1px solid #555;
            color: #eee;
        }

        .row .form-group input:focus, .row .form-group button:focus {
            background-color: #fff;
            color: #000;
            border: none;
            padding: 1em 0.5em;
            animation: pulse 1s infinite ease;
        }

        .row .form-group button {
            border: 1px solid #fff;
            border-radius: 5px;
            outline: none;
            -moz-user-select: none;
            user-select: none;
            color: #333;
            font-weight: 800;
            cursor: pointer;
            margin-top: 2em;
            padding: 1em;
        }

        .row .form-group button:hover, .row .form-group button:focus {
            background-color: #fff;
        }

        .row .form-group button.is-loading::after {
            animation: spinner 500ms infinite linear;
            content: "";
            position: absolute;
            margin-left: 2em;
            border: 2px solid #000;
            border-radius: 100%;
            border-right-color: transparent;
            border-left-color: transparent;
            height: 1em;
            width: 4%;
        }

        .row .footer h5 {
            margin-top: 1em;
        }

        .row .footer p {
            margin-top: 2em;
        }

        .row .footer p .symbols {
            color: #444;
        }

        .row .footer a {
            color: inherit;
            text-decoration: none;
        }

        .information-text {
            color: #ddd;
        }

        @media screen and (max-width: 320px) {
            .row {
                padding-left: 1em;
                padding-right: 1em;
            }

            .row h1 {
                font-size: 1.5em !important;
            }
        }

        @media screen and (min-width: 900px) {
            .row {
                width: 50%;
            }
        }

        * {
            box-sizing: border-box
        }

        /* Add padding to containers */
        .container {
            padding: 16px;
        }

        /* Full-width input fields */
        input[type=text], input[type=password] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        input[type=text]:focus, input[type=password]:focus {
            background-color: #ddd;
            outline: none;
        }

        /* Overwrite default styles of hr */
        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }
    </STYLE>
</head>
<body>
<div class="row">
    <h1>Forgot Password</h1>
    <h6 class="information-text">Enter your username to reset your password.</h6>

    <form class="form-group" action="/Exercise_war_exploded/forgot-password" method="post">
        <c:if test="${alertMsg !=null}">
            <h3 class="alert alert-danger">${alertMsg}</h3>
        </c:if>

        <div class="container">
            <label for="username"><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="username" id="username" required>

            <label for="password"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" id="password" required>

            <label for="password-repeat"><b>Repeat Password</b></label>
            <input type="password" placeholder="Repeat Password" name="password-repeat" id="password-repeat" required>

            <button type="submit">Reset Password</button>
        </div>
    </form>

    <div class="footer">
        <h5>New here? <a href="/Exercise_war_exploded/register">Sign Up.</a></h5>
        <h5>Already have an account? <a href="/Exercise_war_exploded/login">Sign In.</a></h5>
        <p class="information-text"><span class="symbols" title="Lots of love from me to YOU!">&hearts; </span><a
                href="https://www.facebook.com/adedokunyinka.enoch" target="_blank" title="Connect with me on Facebook">Yinka
            Enoch Adedokun</a></p>
    </div>
</div>
</body>
</html>
