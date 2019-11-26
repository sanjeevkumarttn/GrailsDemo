<%@ page import="grailsdemo.enums.Visibility" %>

<!--SEND INVITATION-->
<div class="modal fade" id="invite-topic" role="dialog">
    <div class="modal-dialog card border-primary mb-3">
        <div class="modal-content">
            <div class="modal-header card-header">
                <div class="modal-title card-title">Send Invitation</div>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body card-body">
                <form action="#" method="get" id="invite">

                    Email:* <input id="inviteEmail" type="email" name="receiverMail" placeholder="Email"
                                   style="position:relative;margin-left:40%;">
                    <div class="panel-body bg-warning text-center" id="email-msg"></div>
                    <br><br>
                    Topic:* <select style="position:relative;margin-left:40%;" name="topicId">
                    <g:each in = "${session.topicSubscribed}" var="topic">
                        <g:if test="${topic.visibility== Visibility.PRIVATE}">
                        <option value="${topic.name}">${topic.name}</option>
                        </g:if>
                    </g:each>
                </select>
                    <br><br>
                    <button type="submit" style="position:relative; margin-left:48%;">Invite</button>
                </form>
            </div>
            <div class="modal-footer card-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

%{--
<div class="modal fade" id="myModal22" role="dialog">
    <div class="modal-dialog card border-primary mb-3">
        <div class="modal-content">
            <div class="modal-header card-header">
                <div class="modal-title card-title">Send Invitation</div>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body card-body">
                <form name="invite" action="/sendInvitation" method="get" id="invite">

                    Email:* <input id="inviteEmail" type="email" name="receiverMail" placeholder="Email"
                                   style="position:relative;margin-left:40%;">
                    <div class="panel-body bg-warning text-center" id="email-msg"></div>
                    <br><br>
                    <input type="text" name="topicId" id="hiddenTopicId" style="" form="invite" />

                    <br><br>
                    <button type="submit" style="position:relative; margin-left:48%;">Invite</button>
                </form>
            </div>
            <div class="modal-footer card-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>--}%
