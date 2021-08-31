<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 가입</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <SCRIPT type="text/javascript">
        function fn_emailCheck(obj) {
            if(obj.email.value === "") {
                alert("이메일을 입력하세요.");
                return false
            }

            const form = {
                email: obj.email.value
            }

            $.ajax({
                url : "emailCheck",
                type : "POST",
                dataType: 'json',
                data : form,

                success : function(data){
                    if(data) {
                        obj.emailChk.setAttribute("value", "Y")
                        obj.email.readOnly = true;
                        obj.emailChk.disabled = true;

                        alert("사용가능한 아이디입니다.");
                    } else {
                        alert("중복된 아이디입니다.");
                    }
                }
            })
        }
    </SCRIPT>

    <!-- 중복 확인, 비밀번호 일치 확인용 함수 -->
    <script type="text/javascript">
        function isValidate(obj) {
            if(obj.emailChk.getAttribute("value") === "N") {
                alert("이메일 중복 확인을 수행하세요.")
                return false;
            }

            if(obj.password.value !== obj.password_re.value) {
                obj.password.focus()
                alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.")
                return false;
            }

            return true;
        }
    </script>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
        <form class="form-horizontal" method="post" action="/member/register" onsubmit="return isValidate(this);">
            <fieldset>
                <div id="legend">
                    <legend class="">회원 가입</legend>
                </div>

                <div class="control-group">
                    <label class="control-label">이름</label>
                        <div class="controls">
                            <input type="text" name="name" class="input-xlarge" required>
                            <p class="help-block"></p>
                        </div>
                </div>

                <div class="control-group">
                    <label class="control-label">이메일</label>
                    <div class="controls">
                        <input type="text" name="email" class="input-xlarge" required/>
                        <button name="emailChk" type="button" onclick="fn_emailCheck(this.form);" value="N" >중복 확인</button>
                        <p class="help-block">아이디로 사용됩니다.</p>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label">비밀번호</label>
                    <div class="controls">
                        <input type="password" name="password"  class="input-xlarge" required>
                        <p class="help-block">비밀번호를 입력해주세요.</p>
                    </div>
                </div>

                <div>
                    <label class="control-label">비밀번호 확인</label>
                    <div class="controls">
                        <input type="password" name="password_re" class="input-xlarge" required>
                        <p class="help-block">비밀번호를 한번 더 입력해주세요.</p>
                    </div>
                </div>

                <div class="control-group">
                    <div class="controls">
                        <input type="submit" value="회원 가입" class="btn btn-success">
                    </div>
                </div>
            </fieldset>
        </form>


</body>



</html>