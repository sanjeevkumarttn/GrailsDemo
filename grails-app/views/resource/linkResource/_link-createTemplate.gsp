
<!--SHARE LINK-->
<div class="modal fade" id="share-link" role="dialog">
    <div class="modal-dialog card border-primary mb-3">
        <div class="modal-content">
            <div class="modal-header card-header">
                <div class="modal-title card-title">Share Link</div>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <g:form  controller="resource" action="save">
                <div class="modal-body card-body">
                    Link:*<br> <input type="text" placeholder="Enter link" required name="linkUrl"
                                     ><br>
                    <br>
                    Description:*<br> <textarea cols="22" rows=3 placeholder="Description" required
                                                name="description" ></textarea><br>
                    <br>
                    Topic:*
                    <select name="topic" required>
                    <g:each in = "${session.topicSubscribed}" var="topic">
                        <option value="${topic.id}">${topic.name}</option>
                    </g:each>
                    </select>
                    <br><br><br>
                    <button type="submit" class="btn btn-primary">Share</button>
                </div>
                <div class="modal-footer card-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </g:form>
        </div>

    </div>
</div>
