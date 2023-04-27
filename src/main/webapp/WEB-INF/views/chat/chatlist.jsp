<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<main class="main">
	<section class="container">
		<!--전체 리스트 시작-->
		<article>
			<div class="container">
				<div id="chat_layout">
					<div class="row row-cols-2" style="text-align: center">
						<div class="col" id="sender">Column1</div>
						<div class="col" id="reciver">Column2</div>
						<div class="col" id="chatlist">Column3</div>
						<div class="col" id="chatroom">Column4</div>
					</div>
				</div>
			</div>
		</article>
	</section>
</main>
<script src="/js/chatlist.js"></script>
</body>
</html>
