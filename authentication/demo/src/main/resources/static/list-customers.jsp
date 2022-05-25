<%--
  Created by IntelliJ IDEA.
  User: Mohamed Marzouk
  Date: 19/04/2022
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Customers</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>

</head>
<body>

    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manger</h2>
        </div>
    </div>

    <div id="container">
        <div id="content">

<%--put new button: Add Customer--%>
    <input type="button" value="Add Customer"
        onclick="window.location.href = 'showFormForAdd'; return false"
        class = "add-button"
    />

            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                <c:forEach var = "tempCustomer" items = "${customers}">
                    <%--construct an update link with cutomer id--%>
                    <c:url var="updateLink" value="/customer/showFormForUpdate">
                        <c:param name="customerId" value="${tempCustomer.id}"></c:param>
                    </c:url>

                    <%--construct an delete link with cutomer id--%>
                    <c:url var="deleteLink" value="/customer/delete">
                        <c:param name="customerId" value="${tempCustomer.id}"></c:param>
                    </c:url>

                    <tr>
                        <td>${tempCustomer.firstName}</td>
                        <td>${tempCustomer.lastName}</td>
                        <td>${tempCustomer.email}</td>

                        <td><a href="${updateLink}">Update</a>
                            |
                            <a href="${deleteLink}"
                               onclick="if(!(confirm('Are you sure you want to delete this customer ?')))return false">Delete</a>
                        </td>


                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>


</body>
</html>
