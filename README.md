# 에브리타임 클론코딩 프로젝트 - 백엔드

### 의존성
- devtools
- spring web (mvc)
- mustache
- jpa
- mariaDB
- lombok
- validation
- java mail sender

### 모델링
```text
User
id
name
username
password
nickname
email
school
studentNo
createDate
updateDate

Post
id
title
content
thumnail
userId
comments
boardNo
likeCount
anonyCheck
hashTag
createDate
updateDate

PostReport
id
reason
postId
createDate

Comment
id
content
userId
postId
likeCount
anonyCheck
createDate

CommentReport
id
reason
commentId
createDate

Scrap
id
postId
userId
createDate

Block
id
ownerUserId
blockUserId
createDate

Certificate
id
userId
imgUrl
certificateNo
createDate

Message
id
fromUserId
toUserId
postId
msg
createDate

```


### boardNo 게시판 분류
- 1 : 자유게시판
- 2 : 비밀게시판
- 3 : 졸업생게시판
- 4 : 새내기게시판
- 5 : 시사·이슈
- 6 : 장터게시판
- 7 : 홍보게시판
- 8 : 동아리·학회
- 9 : 취업·진로

### certificateNo 증명서 분류
- 1 : 학교별웹메일
- 2 : 졸업증명서
- 3 : 학생증사진
