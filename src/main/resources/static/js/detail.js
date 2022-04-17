// 게시글 정보 불러오기
async function detail() {
    let postId = $("#postId").val();

    let response = await fetch(`/api/post/${postId}`);
    let responseParse = await response.json();

    if (responseParse.auth == true) {
        $("#auth-box").css("display", "block");
    }

    $("#username").text(responseParse.post.user.username);
    $("#title").text(responseParse.post.title);
    $("#content").html(responseParse.post.content);
}

detail();

// 삭제
$("#btn-delete").click(() => {
    deletePost();
});

async function deletePost() {
    let postId = $("#postId").val();

    let response = await fetch(`/s/api/post/${postId}`, {
        method: "DELETE" // delete는 body가 없다.
    });

    let responseParse = await response.json();

    if (responseParse === 1) {
        alert("삭제되었습니다.");
        location.href = "/";
    } else {
        alert("삭제에 실패했습니다.");
    }
}

