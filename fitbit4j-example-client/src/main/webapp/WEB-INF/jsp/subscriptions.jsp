<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="layout" uri="http://fitbit.com/api/layout" %>
<%--@elvariable id="activities" type="com.fitbit.api.common.model.activities.Activities"--%>
<%--@elvariable id="foods" type="com.fitbit.api.common.model.foods.Foods"--%>
<%--@elvariable id="showSubscriptions" type="java.lang.Boolean"--%>
<%--@elvariable id="isAuthorized" type="java.lang.Boolean"--%>
<%--@elvariable id="isSubscribed" type="java.lang.Boolean"--%>
<%--@elvariable id="exampleBaseUrl" type="java.lang.String"--%>
<%--@elvariable id="encodedUserId" type="java.lang.String"--%>
<%--@elvariable id="userProfileURL" type="java.lang.String"--%>
<layout:shell>

    <c:if test="${isAuthorized}">
    <h2>Complete list of subscriptions in this application</h2>

    <div class="parag left">
        <p>This application uses your local user ID to identify your subscription with Fitbit.
                ${isSubscribed ? "Your subscription is highlighted." : "You are presently not subscribed."}
        </p>
    </div>
    <div class="parag left">
        <table class="blue">
            <thead>
            <tr>
                <th width="13%">Subscription ID</th>
                <th width="13%">Fitbit Owner Type</th>
                <th width="13%">Fitbit Owner ID</th>
                <th width="13%">Feed type</th>
                <th width="19%">Last Notification</th>
                <th width="19%">Notes</th>
            </tr>
            </thead>
            <c:forEach items="${subscriptions}" var="subscription">
                <tr
                        <c:if test="${actionBean.ourUser.userId == subscription.subscriptionDetail.subscriptionId}">
                            style="background-color: lightyellow; font-weight: bold;"
                        </c:if>
                        >
                    <td>${subscription.subscriptionDetail.subscriptionId}</td>
                    <td>${subscription.subscriptionDetail.owner.resourceOwnerType}</td>
                    <td>${subscription.subscriptionDetail.owner.id}</td>
                    <td>${subscription.subscriptionDetail.collectionType}</td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty subscription.lastUpdateNotificationDate}">
                                <fmt:formatDate value="${subscription.lastUpdateNotificationDate}"
                                                type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </c:when>
                            <c:otherwise>
                                Never
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${not subscription.knownSubscription}">
                            Auto-created after receiving update notification.
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </c:if>
    <div class="parag">
        <p><a href="${exampleBaseUrl}/">Back to your activities</a></p>
    </div>
</layout:shell>