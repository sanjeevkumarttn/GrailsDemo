<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
    <meta name="layout" content="main" />
</head>
<body>


%{--<g:emoticon happy="true">Hi John</g:emoticon>--}%



%{--<g:repeat times="3" var="j">
    Repeat this 3 times! Current repeat = ${j}
</g:repeat>--}%



<div class="container-fluid row">
    <div class="col-lg-12" style="margin: 50px 0 50px">
        <div class="col-lg-6">
            %{--<g:form name="loginForm" controller="login" action="loginHandler" method="post">
                Username: <input type="text" name="userName" />
                Password: <input type="password" name="password" />
                <button type="submit">Submit</button>
            </g:form>--}%


            <div class="card border-primary mb-3">
                <h5 class="card-header" id="login-form-header">Login</h5>
                <div class="container-fluid card-body" id="login-form-div">
                    <g:form name="loginForm" controller="login" action="loginHandler" method="post">
                        <div class="row">
                            <div class="col-12 col-sm-12 col-md-12 col-lg-4 col-xl-4">
                                <label>Username*</label>
                            </div>
                            <div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-8">
                                <input type="text" required name="userName" class="form-control">
                            </div>
                            <div class="col-12 col-sm-12 col-md-12 col-lg-4 col-xl-4">
                                <label>Password*</label>
                            </div>
                            <div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-8">
                                <input type="password" required name="password" class="form-control">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-9">
                                <a href="#">Forgot Password</a>
                            </div>
                            <div class="col-md-3">
                                <button type="submit" class="btn btn-primary" id="login-btn">Login</button>
                            </div>
                        </div>
                    </g:form>
                </div>
            </div>

        </div>
        <div class="col-lg-6">
            <div class="card border-primary mb-3">
       %{-- <g:if test="${user}">
                <g:each in="${user.errors}">
                    jkljklk;kml;ml
                </g:each>
        </g:if>--}%
                <h5 class="card-header" id="reg-form-header">Register</h5>
                <div class="container-fluid card-body" id="reg-form-div">



                    <g:uploadForm controller="login" action="register"
                           id="reg-form-form">
                        <div class="row">





                            <div class="col-md-12">
                                <div class="row">
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-4 col-xl-4">
                                        <label>First name*</label>
                                    </div>
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-8">
                                        <input type="text"  name="firstName" class="form-control"
                                               pattern="[a-zA-Z]{3,15}" title="minimum 3 digits and only letters allowed"
                                               value="${user?.firstName}">
                                        <g:if test="${user && user.hasErrors()}">
                                            <g:renderErrors bean="${user}" as="list" field="firstName" />
                                        </g:if>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-4 col-xl-4">
                                        <label>Last name*</label>
                                    </div>
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-8">
                                        <input type="text" name="lastName" class="form-control"
                                                 pattern="[a-zA-Z\s]+" title="only letters and white spaces allowed" value="${user?.lastName}">
                                        <g:if test="${user && user.hasErrors()}">
                                            <g:renderErrors bean="${user}" as="list" field="lastName" />
                                        </g:if>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-4 col-xl-4">
                                        <label>Email*</label>
                                    </div>
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-8">
                                        <input type="email" name="email" id="reg-email" class="form-control" value="${user?.email}">
                                        <g:if test="${user && user.hasErrors()}">
                                            <g:renderErrors bean="${user}" as="list" field="email" />
                                        </g:if>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-4 col-xl-4">
                                        <label>Username*</label>
                                    </div>
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-8">
                                        <input type="text"  name="userName" id="reg-username"
                                               class="form-control" pattern="[^' ']+" title="white spaces not allowed" value="${user?.userName}">
                                        <g:if test="${user && user.hasErrors()}">
                                            <g:renderErrors bean="${user}" as="list" field="userName" />
                                        </g:if>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-4 col-xl-4">
                                        <label>Password*</label>
                                    </div>
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-8">
                                        <input type="password"  name="password" id="password" class="password form-control"
                                               value="${user?.password}">
                                        <g:if test="${user && user.hasErrors()}">
                                            <g:renderErrors bean="${user}" as="list" field="password" />
                                        </g:if>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-4 col-xl-4">
                                        <label>Confirm Password*</label>
                                    </div>
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-8">
                                        <input type="password"  name="confirmPassword" id="confirmPassword"
                                               class="password form-control" value="${user?.confirmPassword}">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-4 col-xl-4">
                                        <label>Photo</label>
                                    </div>
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-8">
                                        <input type="file" name="photo" id="photo" class="form-control">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-primary" id="reg-btn">Register</button>
                            </div>
                        </div>
                    </g:uploadForm>

                </div>
            </div>
        </div>
    </div>


    <div class="col-lg-12">
        <div class="col-lg-6">
            <div>Top Post</div>
            <div>
                <g:each in="${resources}" var="resource">
                    <div class="" style="padding: 10px; background-color: whitesmoke">
                        <div col-lg-6>${"Topic Name: "+resource.topic.name}</div>
                        <div col-lg-6><g:userImage user="${resource.createdBy}" /> ${"Topic CreatedBy: "+resource.createdBy.name}</div>
                        <div col-lg-12>${"Topic description: "+resource.description}</div>
                    </div>
                </g:each>
            </div>
        </div>
        <div class="col-lg-6">
            <div>Recent Post</div>
            <div>
                <g:each in="${recentPosts}" var="resource">
                    <div class="" style="padding: 10px; background-color: whitesmoke">
                        <div col-lg-6>${"Topic Name: "+resource.topic.name}</div>
                        <div col-lg-6><g:userImage user="${resource.createdBy}" /> ${"Topic CreatedBy: "+resource.createdBy.name}</div>
                        <div col-lg-12>${"Topic description: "+resource.description}</div>
                    </div>
                </g:each>
            </div>
        </div>
    </div>

</div>
</body>
</html>