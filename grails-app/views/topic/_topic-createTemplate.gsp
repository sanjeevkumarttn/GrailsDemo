
<!--CREATE TOPIC-->
<div class="modal fade" id="create-topic" role="dialog">
    <div class="modal-dialog card border-primary mb-3">
        <div class="modal-content">
            <div class="modal-header card-header">
                <div class="modal-title card-title">Create Topic</div>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body card-body">
                <g:form url="[controller:'topic', action:'save']">
                    Name:* <input type="text" name="name" id="topic-name" placeholder="Topic Name">
                    Visibility:* <select name="visibility">
                    <option value="PUBLIC" name="PUBLIC">Public</option>
                    <option value="PRIVATE" name="PRIVATE">Private</option>
                </select>

                    <button type="submit">Save</button>
                </g:form>
            </div>
            <div class="modal-footer card-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
