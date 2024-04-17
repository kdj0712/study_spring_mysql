<%@ page import="java.util.HashMap, java.util.ArrayList, com.example.co_templates.utils.Paginations" %>
    <%@ page contentType="text/html; charset=UTF-8" language="java" %>

        <!DOCTYPE html>
        <html lang="en">
        <%@ include file="/WEB-INF/views/templates/header.jsp" %>

            <body>

                <!-- Menu -->
                <%@ include file="/WEB-INF/views/templates/navigator.jsp" %>

                    <!-- Main Content -->
                    <form action="/q/board/list" method="get">
                        <div class="container mt-4">
                            <div class="row">
                                <div class="col-md-8">
                                    <h2>Search</h2>
                                    <% HashMap dataMap=(HashMap) request.getAttribute("dataMap"); String
                                        searchType=(String) dataMap.get("searchType"); String search=(String)
                                        dataMap.getOrDefault("search",""); %>

                                        <div class="input-group mb-3">
                                            <select class="form-select" id="searchType" name="searchType">
                                                <option selected>검색주제</option>
                                                <option value="PK_BOARDS" <%="PK_BOARDS" .equals(searchType)
                                                    ? "selected" : "" %>>PK_BOARDS</option>
                                                <option value="TITLE" <%="TITLE" .equals(searchType) ? "selected" : ""
                                                    %>>TITLE</option>
                                                <option value="CONTENTS" <%="CONTENTS" .equals(searchType) ? "selected"
                                                    : "" %>>CONTENTS</option>
                                                <option value="WRITER_ID" <%="WRITER_ID" .equals(searchType)
                                                    ? "selected" : "" %>>WRITER_ID</option>
                                                <option value="CREATE_DATE" <%="CREATE_DATE" .equals(searchType)
                                                    ? "selected" : "" %>>CREATE_DATE</option>
                                                <option value="PARENT_BOARDS" <%="PARENT_BOARDS" .equals(searchType)
                                                    ? "selected" : "" %>>PARENT_BOARDS</option>

                                            </select>
                                            <!-- name 이 controller에서 key 역할을 함 -->
                                            <input type="text" class="form-control" name="search" value="<%= search %>"
                                                placeholder="Search..." id="keydownEnter">
                                            <button class="btn btn-primary">Go</button>
                                        </div>
                                </div>
                                <div class="col-12">
                                    <table class="table">
                                        <thead>
                                            <th>Check</th>
                                            <th>PK_BOARDS</th>
                                            <th>TITLE</th>
                                            <th>CONTENTS</th>
                                            <th>WRITER_ID</th>
                                            <th>CREATE_DATE</th>
                                            <th>PARENT_BOARDS</th>
                                        </thead>
                                        <tbody>
                                            <% HashMap setlist=(HashMap) request.getAttribute("list"); ArrayList
                                                resultList=(ArrayList) setlist.get("resultList"); for(Object obj :
                                                resultList){ HashMap record=(HashMap)obj; %>
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" class="form-check-input" name="deleteIds"
                                                            value='<%= record.get("PK_BOARDS") %>'>
                                                    </td>
                                                    <td>
                                                        <%= record.get("PK_BOARDS") %>
                                                    </td>
                                                    <td>
                                                        <%= record.get("TITLE") %>
                                                    </td>
                                                    <td>
                                                        <%= record.get("CONTENTS") %>
                                                    </td>
                                                    <td>
                                                        <%= record.get("WRITER_ID") %>
                                                    </td>
                                                    <td>
                                                        <%= record.get("CREATE_DATE") %>
                                                    </td>
                                                    <td>
                                                        <%= record.get("PARENT_BOARDS") %>
                                                    </td>
                                                </tr>
                                                <% } %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!-- Pagination with buttons and query parameters -->
                        <% Paginations paginations=(Paginations) setlist.get("paginations"); %>

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
                    </form>
                    <!-- Footer -->
                    <%@ include file="/WEB-INF/views/templates/footer.jsp" %>
            </body>

        </html>