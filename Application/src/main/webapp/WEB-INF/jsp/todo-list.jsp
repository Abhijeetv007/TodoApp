
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>	
	<div class="container">
	<table class="table table-striped">
			<caption>Your Todos List</caption>
			<thead>
				<tr>
					
					<th>Description</th>
					<th>Target Date</th>
					<th>Is it Done?</th>
					<th>Update</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.desc}</td>
						<td><fmt:formatDate pattern="dd/mm/yyyy" value="${todo.targetDate}" /></td>
						<td>${todo.done}</td>
						<td><a type="button" class="btn btn-success" href="/update-todo?number=${todo.id }">Update</a></td>
						<td><a type="button" class="btn btn-warning" href="/delete-todo?number=${todo.id }">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
	</table>

	<div class="button">
	<a  href="/add-todo">Click to Add value</a> <br>
	
	</div>

	</div>
	
<%@ include file="common/footer.jspf" %>