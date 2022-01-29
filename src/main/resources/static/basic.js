$(document).ready(function () {
    showPost();
})

function showPost() {
    $.ajax({
        type: 'GET',
        url: '/api/posts',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let post = response[i];
                let id = post['id'];
                let title = post['title'];
                let username = post['username'];
                let modifiedAt = post['modifiedAt'];
                let contents = post['contents'];
                addHTML(id, title, username, modifiedAt, contents);
            }
        }
    })
}

function addHTML(id, title, username, modifiedAt) {
    let tempListHtml = `<tr onclick='$("#${id}").addClass("is-active")'>
                            <th>${title}</th>
                            <th>${username}</th>
                            <th>${modifiedAt}</th>
                            </tr>`;
    $("#tbody").append(tempListHtml);
}