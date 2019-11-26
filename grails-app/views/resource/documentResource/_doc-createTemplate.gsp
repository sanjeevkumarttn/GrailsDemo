

<!--SHARE DOCUMENT-->
<div class="modal fade" id="share-doc" role="dialog">
    <div class="modal-dialog card border-primary mb-3">
        <div class="modal-content">
            <div class="modal-header card-header">
                <div class="modal-title">Share Document</div>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <form action="#" method="post"
                  enctype="multipart/form-data">
                <div class="modal-body">
                    Document:* <br><input type="file" placeholder="Enter file" required name="path">
                    <br><br>
                    Description:* <br><textarea cols="22" rows=3 placeholder="Description" required
                                                name="description"></textarea>
                    <br><br>
                    Topic:* <select name="resourcetopic" required>
                    <g:each in = "${session.topicSubscribed}" var="topic">
                        <option value="${topic.name}">${topic.name}</option>
                    </g:each>
                </select>
                    <br><br>
                    <button type="submit">Share</button>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div>

    </div>
</div>

