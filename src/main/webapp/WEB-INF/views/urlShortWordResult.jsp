<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="utf-8">
  <script src="/js/jquery-3.7.1.min.js"></script>
  <script>
    $().ready(function(){
      let shortUrl= window.location.protocol + "//" + window.location.host + "/word/${param.selectWord}";
      $('#shortUrl').text(shortUrl);

      $('#copyShortUrl').click(function(){

        if(window.navigator.clipboard !== undefined){
          window.navigator.clipboard.writeText(shortUrl);
          alert("단축 단어 URL이 복사되었습니다!\n원하는 위치에 붙여넣기 해주세요.");
        }else{
          const textArea=document.createElement('textarea');
          textArea.value=shortUrl;
          document.body.appendChild(textArea);
          textArea.select();
          textArea.setSelectionRange(0,99999);
          try{
            document.execCommand('copy');
            alert('단축 단어 URL이 복사되었습니다!\n원하는 위치에 붙여넣기 해주세요.');
          }catch(e){
            console.error("복사 실패 : "+e);
          }

          textArea.setSelectionRange(0,0);
          document.body.removeChild(textArea);
        }
      });
    });
  </script>
</head>
<body>
  <h3>단어형 단축 URL 생성이 성공적으로 완료되었습니다!</h3>
  <div>
    생성하신 단축 URL은 아래와 같습니다.
    <ul>
      <li>선택하신 단축 단어 : ${param.selectWord}</li>
      <li>원본 URL : ${param.url}</li>
      <li><strong>단축 URL : <span id="shortUrl"></span></strong> <button id="copyShortUrl">복사하기</button><br>
        (위 URL로 접속하시면 원본 URL로 자동 이동 됩니다.)</li>
    </ul>
    <a href="/">◀ 메인 페이지로 돌아가기</a>
  </div>
</body>
</html>