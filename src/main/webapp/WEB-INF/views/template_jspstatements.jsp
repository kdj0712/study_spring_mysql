<% int day = 3; %> 
<html> 
   <head><title>IF...ELSE Example</title></head> 
   
   <body>
      <% if (day == 1 || day == 7) { %>
         <p> Today is weekend</p>
      <% } else { %>
         <p> Today is not weekend</p>
      <% } %>
      <div>
         <% String name = (String)request.getAttribute("name"); %>
        <div>name : <%= name %></div>
        <% String age = (String)request.getAttribute("age"); %>
        <div>age : <%= age %></div>
      </div>
   </body> 
</html> 
