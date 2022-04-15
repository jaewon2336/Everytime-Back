$("#btn-post-write").click(() => {
    // $("#card-box").append(postWrite());
    writeForm();
});

$("#submit").click(() => {
    write();
});

// 글쓰기 페이지 열기
function writeForm() {

    if ($("#write-form").hasClass("my_hidden") == true) {
        $("#write-form").removeClass("my_hidden");
        $("#btn-post-write").text("글쓰기 취소");
    } else {
        $("#write-form").addClass("my_hidden");
        $("#btn-post-write").text("글쓰기");
    }
}

// 글쓰기
async function write() {

    let likeCount = 0

    let writeDto = {
        title: $("#title").val(),
        content: $("#content").val(),
        boardNo: $("#board-no").val(),
        likeCount: likeCount,
        anonyCheck: $("#anony").hasClass("anony_active"),
        hashTag: null
    }

    let response = await fetch("/s/post", {
        method: "POST",
        body: JSON.stringify(writeDto),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    });

    let responseParse = await response.json();

    console.log(responseParse);

    if (responseParse == 1) {
        // location.href = "/post/list";
    } else {
        alert("글쓰기에 실패하였습니다.");
    }
}

function postList() {
    return `<a href="#" class="card card_box">
                <div class="card-body">
                    <h4 class="card-title">제목입니다.</h4>
                    <p class="card-text">내용입니다.</p>
                    <p>Card link</p>
                    <p>Another link</p>
                </div>
            </a>`;
}