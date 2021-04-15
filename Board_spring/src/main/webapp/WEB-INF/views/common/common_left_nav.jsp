<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    


			<nav class="col-md-2 d-none d-md-block bg-light sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
			
						<li class="nav-item">
							<a class="nav-link active" href="${pageContext.request.contextPath}/board/main">
								<span data-feather="home"></span> 
								Main
								<span class="sr-only">(current)</span>
							</a>
						</li>
						<!-- <li class="nav-item">
							<a class="nav-link" href="#"> 
								<span data-feather="file-text"></span>
								게시판 생성
							</a>
						</li> -->
			
			
					</ul>
			
			
					 <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
			              <span>게시판</span>
			<!--
				          <a class="d-flex align-items-center text-muted" href="#">
			                <span data-feather="plus-circle"></span>
			              </a> 
			-->
			            </h6>
			            <ul class="nav flex-column mb-2">
							<c:forEach items="${boardList}" var="board">
								<c:if test="${board.bd_act == 1}">
									<li class="nav-item">
										<a class="nav-link" href="${pageContext.request.contextPath}/board/boardListingPage?boardNo=${board.bd_no}"> 
											<span data-feather="users"></span> ${board.bd_nm}
										</a>
									</li>
								</c:if>
							</c:forEach>

						</ul>
			
				</div>
			</nav>	