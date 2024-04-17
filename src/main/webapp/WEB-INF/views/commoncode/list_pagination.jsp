<%@ page import="java.util.HashMap, java.util.ArrayList, com.example.co_templates.utils.Paginations" %>
    <!DOCTYPE html>
    <html lang="en">
    <%@ include file="/WEB-INF/views/templates/header.jsp" %>

        <body>

            <!-- Menu -->
            <%@ include file="/WEB-INF/views/templates/navigator.jsp" %>
                <!-- Main Content -->
                <form action="/CommonCode/list_pagination" method="get">
                    <div class="container mt-4">
                        <div class="row">
                            <div class="col-md-8">
                                <h2>Search</h2>
                                <% 
                                HashMap dataMap=(HashMap) request.getAttribute("dataMap"); String searchType=(String)
                                dataMap.get("searchType"); String search=(String) dataMap.getOrDefault("search","");
                                %>

                                    <div class="input-group mb-3">
                                        <!-- 셀렉트 박스 추가 -->
                                        <select class="form-select" id="searchType" name="searchType">
                                            <option selected>Choose...</option>
                                            <option value="CODE_NAME" <%="CODE_NAME" .equals(searchType) ? "selected"
                                                : "" %>>CODE_NAME</option>
                                            <option value="DESCRIPTION" <%="DESCRIPTION" .equals(searchType)
                                                ? "selected" : "" %>>DESCRIPTION</option>
                                        </select>
                                        <!-- name 이 controller에서 key 역할을 함  -->
                                        <input type="text" class="form-control" name="search" value="<%= search %>"
                                            placeholder="Search..." id="keydownEnter">
                                        <button class="btn btn-primary">Go</button>
                                    </div>
                            </div>
                            <div class="col-12">
                                <table class="table">
                                    <thead>
                                        <th>Del</th>
                                        <th>PK_UNIQUE</th>
                                        <th>CODE_NAME</th>
                                        <th>DESCRIPTION</th>
                                    </thead>
                                    <tbody>
                                        <% HashMap result=(HashMap) request.getAttribute("result"); 
                                        ArrayList itemList=(ArrayList) result.get("resultList");
                                         for(Object obj : itemList) {
                                            HashMap record=(HashMap)obj; %>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" class="form-check-input" name="deleteIds"
                                                        value='<%= record.get("PK_UNIQUE") %>'>
                                                </td>
                                                <td>
                                                    <%= record.get("PK_UNIQUE") %>
                                                </td>
                                                <td>
                                                    <%= record.get("CODE_NAME") %>
                                                </td>
                                                <td>
                                                    <%= record.get("DESCRIPTION") %>
                                                </td>
                                            </tr>
                                            <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- Pagination with buttons and query parameters -->
                        <% Paginations paginations=(Paginations) result.get("paginations"); %>

                            <nav aria-label="Page navigation">
                                <div>
                                    Total Count : <%= paginations.getTotalCount() %>
                                </div>
                                <ul class="pagination justify-content-center">
                                    <li class="page-item"><button class="page-link" type="submit" name="currentPage"
                                            value="${dataMap.previousPage}">Previous</button></li>
                                    <% for(int i=paginations.getBlockStart(); i <=paginations.getBlockEnd(); i++){ %>
                                        <li class="page-item">
                                            <button class="page-link" type="submit" name="currentPage" value="<%= i %>">
                                                <%= i %>
                                            </button>
                                        </li>
                                        <% } %>
                                            <!-- <li class="page-item"><button class="page-link" type="submit" name="currentPage" value="1">1</button>
                        </li>
                        <li class="page-item"><button class="page-link" type="submit" name="currentPage" value="2">2</button>
                        </li>
                        <li class="page-item"><button class="page-link" type="submit" name="currentPage" value="3">3</button>
                        </li> -->
                                            <li class="page-item"><button class="page-link" type="submit"
                                                    name="currentPage" value="${dataMap.nextPage}">Next</button>
                                            </li>
                                </ul>
                            </nav>
                    </div>
                </form>

                <!-- Footer -->
                <%@ include file="/WEB-INF/views/templates/footer.jsp" %>
        </body>

    </html>