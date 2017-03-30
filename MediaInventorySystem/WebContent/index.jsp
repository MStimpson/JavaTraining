<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.media.Data"
    		 import="com.media.Services"
    		 import="com.media.Media"
    		 import="java.util.Map"
			 import="java.util.TreeMap" 
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css.css">

<title>Media Inventory System</title>

</head>
<body>
  <%   	
  	Data data = new Data();
	Services s = new Services();
	
	String query="SELECT * FROM media";
	Map<Integer, Media> med=null;
	int id = 0;
	boolean editTable=false;
	boolean searchTable=false;
	boolean sort=false;
	
  if ("POST".equalsIgnoreCase(request.getMethod())) {

		if(request.getParameter("action")!=null){

		 	if(request.getParameter("action").equals("Edit")){
		 		
		 		id = Integer.parseInt(request.getParameter("id"));	
		 		editTable=true;
		 	}
		 	if(request.getParameter("action").equals("Remove")){
		 		System.out.println("You want to remove this entry.");
		 		//data.deleteMedia(Integer.parseInt(request.getParameter("id")));
		 	}
		 	if(request.getParameter("action").equals("Watched")){
		 		data.watched(Integer.parseInt(request.getParameter("id")));
		 	}
		 	if(request.getParameter("action").equals("Search")){
		 		searchTable=true;
		 		String search = request.getParameter("searching");
		 		query ="SELECT * FROM media WHERE scan_code LIKE '%"+search+"%' "+
		 				"OR title LIKE '%"+search+"%' OR description LIKE '%"+search+"%' "+
		 				"OR genre LIKE '%"+search+"%' OR media_type LIKE '%"+search+"%' "+
		 				"OR year_released LIKE '%"+search+"%' OR purchased_price LIKE '%"+search+"%' "+
		 				"OR purchased_date LIKE '%"+search+"%' OR last_watched LIKE '%"+search+"%';";
		 		
		 		med=data.getMedia(query);
		 	}
		 	if(request.getParameter("action").equals("Add")){
		 		try{
		 		int scanCode     	  = Integer.parseInt(request.getParameter("scancode"));
		 		String title     	  = request.getParameter("title");
		 		String desc      	  = request.getParameter("desc");
		 		String mediatype 	  = request.getParameter("mediatype");
		 		String genre     	  = request.getParameter("genre");
		 		int minutes      	  = Integer.parseInt(request.getParameter("mins"));
		 		int yearReleased      = Integer.parseInt(request.getParameter("yearr"));
		 		String location       = request.getParameter("location");
		 		double purchasedPrice = Double.parseDouble(request.getParameter("price"));
		 		String purchasedDate  = request.getParameter("date");
		 		String lastWatched    = request.getParameter("watched");
		 		
				 query ="INSERT INTO `inventory`.`media` (`id`, `scan_code`, `title`, `description`,`media_type`,"
						+ " `genre`, `minutes`, `year_released`, `location`, `purchased_price`, `purchased_date`,"
						+ " `last_watched`) VALUES ('0', '"+scanCode+"', '"+title+"', '"+desc+"', '"+mediatype+"', '"+genre+"', "
						+ "'"+minutes+"', '"+yearReleased+"', '"+location+"', '"+purchasedPrice+"','"+purchasedDate+"', '"+lastWatched+"');";
						
		 		data.addMedia(query);
		 		
		 		}catch(NumberFormatException e){System.out.println("That is not a valid format.");}
		 	}
		 	if(request.getParameter("action").equals("EditForm")){
		 		try{
		 		int scanCode		  = Integer.parseInt(request.getParameter("scancode"));
		 		String title		  = request.getParameter("title");
		 		String desc   		  = request.getParameter("desc");
		 		String mediatype      = request.getParameter("mediatype");
		 		String genre		  = request.getParameter("genre");
		 		int minutes 		  = Integer.parseInt(request.getParameter("mins"));
		 		int yearReleased 	  = Integer.parseInt(request.getParameter("yearr"));
		 		String location 	  = request.getParameter("location");
		 		double purchasedPrice = Double.parseDouble(request.getParameter("price"));
		 		String purchasedDate  = request.getParameter("date");
		 		String lastWatched    = request.getParameter("watched");
				
				query="UPDATE `inventory`.`media` SET `scan_code`='"+scanCode+"', `title`='"+title+"', `description`='"+desc+"',"
					+ "`media_type`='Video_BLURAY', `genre`='"+genre+"', `minutes`='"+minutes+"',`location`='"+location+"', `purchased_price`"
					+ "='"+purchasedPrice+"', `purchased_date`='"+purchasedDate+"', `last_watched`='"+lastWatched+"' WHERE `id`='"+id+"';";
		 		data.editMedia(query);
		 		
		 		}catch(Exception e){System.out.println("That is not a valid format.");}
		 	}
		 	if(request.getParameter("action").equals("Sort")){
		 		sort=true;
		 		query="SELECT * FROM media WHERE genre='"+request.getParameter("genre")+"';";
		 		med=data.getMedia(query);
		 	}
		 	if(request.getParameter("action").equals("Import")){
		 		System.out.println("You want to import.");
		 	}
		 	if(request.getParameter("action").equals("Export")){
		 		//writes to a file on the desktop called export.csv.
		 		s.export();
		 	}
		}
	}
  data.getMedia();
  %>
<div class="tab">
  <button class="tablinks" onclick="openTab(event, 'Initial')" id="defaultOpen">Initial view</button>
  <button class="tablinks" onclick="openTab(event, 'View')">View</button>
  <button class="tablinks" onclick="openTab(event, 'Search')">Search</button>
  <button class="tablinks" onclick="openTab(event, 'Add')">Add new</button>
  <button class="tablinks" onclick="openTab(event, 'ImpExp')">Import/Export</button>
</div>

<div id="Initial" class="tabcontent">
  <h3>Control View</h3>
  <p>Here is the Control view.</p>
  <table id="genre">
  	<%= 
  		s.buildTable(editTable, searchTable, sort, id)
  	%>
  </table>
  <table class="viewTable">
	<% 
		if(med !=null){
			out.println("<tr><th>Title</th><th>Desc</th><th>Genre</th><th>Mins</th><th>Year</th><th>Location</th><th>Price</th><th>Date Bought</th><th>Last Watched</th></tr>");
			for(Integer key:med.keySet()){
				out.println(med.get(key).toString());
			}
		}
	%>
	</table> 
</div>

<div id="View" class="tabcontent">
  <h3>View</h3>
  
  <form action="index.jsp" method="POST">Search for a genre:
  	<select name="genre">
  		<%= data.genreSort()%>
  	</select>
  <input type='submit' value='Sort' name='action'></form>
  <br/>
  
  <table class="viewTable">
    <tr>
    <th>Title</th>
    <th>Desc</th>
    <th>Media</th>
    <th>Genre</th>
    <th>Mins</th>
    <th>Year</th>
    <th>Location</th>
    <th>Price</th>
    <th>Date Bought</th>
    <th>Last Watched</th>
  </tr>
  	<% 
  		query="SELECT * FROM media";
  		Map<Integer, Media> records = data.getMedia(query);
		for(Integer key:records.keySet()){
			out.println(records.get(key).toString());
		}
	%>
 
  </table>
</div>

<div id="Search" class="tabcontent">
  <h3>Search</h3>
 <form action="index.jsp" method="POST"> 
	  <table>
	  <tr>
	    <td><input type="text" name="searching">&ensp;<input type="submit" value="Search" name="action"></td>
	  </tr>
	</table>

</form>
</div>

<div id="Add" class="tabcontent">
  <h3>Add new item</h3>
  <p>Here you can add a new item.</p>
  <form action="index.jsp" method="POST">
  <table>
  	<tr>
  		<td>Scan Code</td>
  		<td><input type="text" name="scancode"></td>
  	</tr>
  	  	<tr>
  		<td>Movie Title</td>
  		<td><input type="text" name="title"></td>
  	</tr>
  	  	<tr>
  		<td>Description</td>
  		<td><input type="text" name="desc"></td>
  	</tr>
  	  	<tr>
  		<td>Media Type</td>
  		<td>
  		  	<select name="mediatype">
  				<option>Video_DVD</option>
  				<option>Video_BLURAY</option>
  				<option>Audio_CD</option>
  				<option>Video_DIGITAL</option>
  				<option>Audio_DIGITAL</option>
  			</select>
  		</td>
  	</tr>
  	<tr>
  		<td>Genre</td>
  		<td><input type="text" name="genre"></td>
  	</tr>
  	  	<tr>
  		<td>Minutes</td>
  		<td><input type="text" name="mins"></td>
  	</tr>
  	<tr>
  		<td>Year Released</td>
  		<td><input type="text" name="yearr"></td>
  	</tr>
  	<tr>
  		<td>Location</td>
  		<td><input type="text" name="location"></td>
  	</tr>
  	  	<tr>
  		<td>Purchased Price</td>
  		<td><input type="text" name="price"></td>
  	</tr>
  	<tr>
  		<td>Purchased Date</td>
  		<td><input type="text" name="date" placeholder="yyyy-mm-dd"></td>
  	</tr>
  	<tr>
  		<td>Last Watched</td>
  		<td><input type="text" name="watched" placeholder="yyyy-mm-dd"></td>
  		<td><input type="submit" value="Add" name="action"></td>
  	</tr>
  </table>
  </form>
</div>
<div id="ImpExp" class="tabcontent">
  <h3>Import/Export</h3>
   
  <form action="index.jsp" method="POST">
  	<p>Use the box below to import.</p>
  	<input type="file" name="file" accept=".csv">&ensp;&ensp;
  	<input type="submit" name="action" value="Import"><br/><br/>
  </form>
  <form action="index.jsp" method="POST">
  	<input type="submit" value="Export" id="export" name="action">	
  </form>
  
</div>
<script>
	function openTab(evt,tabName){
		var i,tabcontent,tablinks;
		
	    tabcontent = document.getElementsByClassName("tabcontent");
	    for (i = 0; i < tabcontent.length; i++) {
	        tabcontent[i].style.display = "none";
	    }
		
		tablinks = document.getElementsByClassName("tablinks");
		for(i=0;i<tablinks.length;i++){
			tablinks[i].className=tablinks[i].className.replace(" active","");
		}
		document.getElementById(tabName).style.display="block";
		evt.currentTarget.className +=" active";
	}
	document.getElementById("defaultOpen").click();
</script>
</body>
</html>