<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>

<body>
<g:render template="/topic/topic-createTemplate"/>
<g:render template="/resource/documentResource/doc-createTemplate"/>
<g:render template="/resource/linkResource/link-createTemplate"/>
<g:render template="/topic/emailTemplate"/>

<nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" role="navigation">
    <a class="navbar-brand" href="/#"><asset:image src="grails.svg" alt="Grails Logo"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
            aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
        <ul class="nav navbar-nav ml-auto">
            <g:pageProperty name="page.nav"/>
        </ul>
    </div>
    <g:if test="${session.user}">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active" style="color: white">${session.user.name}</li>
            <li class="nav-item active" data-toggle="modal" data-target="#create-topic">
                <i class="fa fa-comment nav-link"
                   title="CreateTopic"></i>
            </li>
            <li class="nav-item">
                <i class="fa fa-envelope-o nav-link" data-toggle="modal" data-target="#invite-topic"
                   title="SendInvitation"></i>
            </li>
            <li class="nav-item">
                <i class="fa fa-link nav-link" data-toggle="modal" data-target="#share-link"
                   title="ShareLink"></i>
            </li>
            <li class="nav-item">
                <i class="fa fa-file-text nav-link" data-toggle="modal" data-target="#share-doc"
                   title="ShareDocument"></i>
            </li>
            <li class="nav-item">
                <g:link controller="login" action="logout">Logout</g:link>
            </li>
        </ul>
    </g:if>
</nav>
<br>

<div style="text-align: center; color: #005cbf; font-size: larger">
    <g:if test="${flash.message}">
        <div><%=flash.message%></div>
    </g:if>
    <g:if test="${flash.error}">
        <div><%=flash.error%></div>
    </g:if>
</div>

<g:layoutBody/>
</body>
</html>


%{--

<div class="footer row" role="contentinfo">
    <div class="col">
        <a href="http://guides.grails.org" target="_blank">
            <asset:image src="advancedgrails.svg" alt="Grails Guides" class="float-left"/>
        </a>
        <strong class="centered"><a href="http://guides.grails.org" target="_blank">Grails Guides</a></strong>

        <p>Building your first Grails app? Looking to add security, or create a Single-Page-App? Check out the <a
                href="http://guides.grails.org" target="_blank">Grails Guides</a> for step-by-step tutorials.</p>

    </div>

    <div class="col">
        <a href="http://docs.grails.org" target="_blank">
            <asset:image src="documentation.svg" alt="Grails Documentation" class="float-left"/>
        </a>
        <strong class="centered"><a href="http://docs.grails.org" target="_blank">Documentation</a></strong>

        <p>Ready to dig in? You can find in-depth documentation for all the features of Grails in the <a
                href="http://docs.grails.org" target="_blank">User Guide</a>.</p>

    </div>

    <div class="col">
        <a href="https://grails-slack.cfapps.io" target="_blank">
            <asset:image src="slack.svg" alt="Grails Slack" class="float-left"/>
        </a>
        <strong class="centered"><a href="https://grails-slack.cfapps.io" target="_blank">Join the Community</a>
        </strong>

        <p>Get feedback and share your experience with other Grails developers in the community <a
                href="https://grails-slack.cfapps.io" target="_blank">Slack channel</a>.</p>
    </div>
</div>


<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>
--}%

%{--<asset:javascript src="application.js"/>--}%






