<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="utf-8">
  <style>
    form input, form select{
      padding:5px;
    }
    form input[type=text]{
      width:400px;
    }
    form select{
      width:200px;
    }
    </style>
    <script src="/js/jquery-3.7.1.min.js"></script>
    <script>
      $.ajax({
        type:'post',
        url:'/getWordList',
        dataType:'json',
        success:function(response){
          response.wordList.forEach(function(word){
            $('#wordList').append($('<option>').val(word).text(word));
          });
        },
        error:function(request,e){
          alert('에러 발생');
          console.log(e);
        }
      });
    </script>
</head>
<body>
  <h3>HanGyub의 Url Shortener</h3>
  긴 URL을 기억하기 쉬운 영단어로 줄여보세요!<br>
  <form action="/addWord" method="post">
    <input type="text" id="url" name="url" placeholder="줄이려는 URL을 입력해주세요">
    <select id="wordList" name="selectWord">
    </select>
    <input type="submit" id="urlSubmit" value="단축하기">
  </form>
</body>
</html>