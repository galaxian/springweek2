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
                let title = post['title']
                let username = post['username'];
                let modifiedAt = post['modifiedAt'];
                let contents = post['contents'];
                addHTML(id, title, username, modifiedAt, contents);
            }
        }
    })
}

function addHTML(id, title, username, modifiedAt, contents) {
    let tempListHtml = `<tr onclick='$("#${id}").addClass("is-active")'>
                            <th>${title}</th>
                            <th>${username}</th>
                            <th>${modifiedAt}</th>
                            </tr>`;
    let tempPostHtml = `<div class="modal" id=${id}>
                            <div class="modal-background" onclick='$("#${id}").removeClass("is-active")'></div>
                            <div class="modal-content">
                                <div class="box">
                                    <article class="media">
                                        <div class="media-content">
                                            <div class="card">
                                                <div class="card-content">
                                                    <div class="media">
                                                        <div class="media-left">
                                                        </div>
                                                        <div class="media-content">
                                                            <p class="title is-4">${title}</p>
                                                            <p class="subtitle is-6" style="text-align: right">${username}</p>
                                                            <p class="subtitle is-6" style="text-align: right">${modifiedAt}</p>
                                                        </div>
                                                    </div>
                                                    <div class="content">
                                                        ${contents}
                                                    </div>
                                                </div>
                                            </div>
                                            <nav class="level is-mobile">
                                                <div class="level-right">
                                                    <div class="level-item">
                                                        <a class="button is-sparta" onclick="updateState(${id})">게시글 수정하기</a>
                                                    </div>
                                                    <div class="level-item">
                                                        <a class="button is-sparta" onclick="deletePost(${id})">게시글 삭제하기</a>
                                                    </div>
                                                    <div class="level-item">
                                                        <a class="button is-sparta is-outlined"
                                                           onclick='$("#${id}").removeClass("is-active")'>돌아가기</a>
                                                    </div>
                                                </div>
                                            </nav>
                                        </div>
                                    </article>
                                </div>
                            </div>
                            <button class="modal-close is-large" aria-label="close"
                                    onclick='$("#${id}").removeClass("is-active")'></button>
                        </div>`;
    $("#tbody").append(tempListHtml);
    $("#showModal").append(tempPostHtml);
}

function writePost() {
    let title = $("#post-title").val();
    let username = $("#post-username").val();
    let contents = $("#post-contents").val();

    let data = {"title": title, "username": username, "contents": contents};

    $.ajax({
        type: "POST",
        url: "/api/posts",
        contentType: "application/json", // JSON 형식으로 전달함을 알리기
        data: JSON.stringify(data),
        success: function (response) {
            alert('게시글이 성공적으로 작성되었습니다.');
            window.location.reload();
        }
    });
}