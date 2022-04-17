// 글쓰기 폼 열기 버튼 이벤트
$("#btn-post-write").click(() => {
    // $("#card-box").append(postWrite());
    writeForm();
});

// 글쓰기 버튼 이벤트
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

    if (responseParse == 1) {
        location.href = "/";
    } else {
        alert("글쓰기에 실패하였습니다.");
    }
}

let page = 0;
let keyword = $("#keyword").val(""); // 초기화

// 이전버튼
$("#btn-prev").click(() => {
    page--;
    $("#post-box").empty();
    let keyword = $("#keyword").val(); // 키워드 가지고 가야함
    list(keyword);
});

// 다음버튼 이벤트
$("#btn-next").click(() => {
    page++;
    $("#post-box").empty();
    let keyword = $("#keyword").val();
    list(keyword);
});

// 검색버튼 이벤트
$("#btn-search").click(() => {
    page = 0; // 페이지 초기화 검색한 페이지가 0번!
    keyword = $("#keyword").val();
    $("#post-box").empty();
    list(keyword);
});

async function list(keyword) {
    // alert(page);
    let response = await fetch(`/api/post/list?page=${page}&keyword=${keyword}`);
    let responseParse = await response.json();

    if (response.status === 200) {
        pagingDisabled(responseParse);
        responseParse.content.forEach((post) => {
            $("#post-box").append(postList(post));
        });
    } else {
        alert("잘못된 요청입니다.");
    }
}

function postList(post) {
    return `<a href="/post/${post.id}" class="card card_box">
                <div class="card-body">
                    <p id="post-id" type="hidden" value="${post.id}"></p>
                    <h4 class="card-title">${post.title}</h4>
                    <p class="card-text">${post.content}</p>
                    <p class="card-text">${post.createDate}</p>
                </div>
            </a>`;
}

function pagingDisabled(responseParse) {
    if (responseParse.first == true) {
        $("#li-prev").addClass("my_hidden");
        $("#li-next").removeClass("disabled");
    } else if (responseParse.last == true) {
        console.log(responseParse.last);
        $("#li-prev").removeClass("disabled");
        $("#li-next").addClass("my_hidden");
    } else {
        $("#li-prev").removeClass("my_hidden");
        $("#li-next").removeClass("my_hidden");
    }
}

list("");