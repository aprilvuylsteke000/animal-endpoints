<h1>Animal Image Endpoints Application</h1>
<h2>Overview</h2>
<p>The Animal Image Endpoints Application is a Spring Boot microservice that allows users to retrieve and store images of animals such as cats, dogs, and bears. The application provides a set of RESTful endpoints for managing animal images, supports dynamic HTML responses, and uses PostgreSQL for data storage.</p>

<h2>Features</h2>
<ul>
<li>Retrieve and store images of cats, dogs, and bears.</li>
<li>Display images and related metadata dynamically using HTML.</li>
<li>Error handling with consistent HTML-styled pages.</li>
<li>RESTful API design.</li>
<li>Integrated with PostgreSQL for persistent image storage.</li>
</ul>

<h2>Technologies Used:</h2>
<ul>
<li>Backend: Spring Boot (Java)</li>
<li>Database: Heroku hosted PostgreSQL</li>
<li>Dependency Management: Maven</li>
<li>Containerization: Docker</li>
<li>HTTP Client: RestTemplate</li>
<li>Tools: Postman for API testing, IntelliJ IDEA for development.</li>
</ul>

<h2>Endpoints</h2>

<table>
  <tr>
    <th>Endpoint</th>
    <th>Method</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>/api/images/</td>
    <td>GET</td>
    <td>Displays the home page.</td>
  </tr>
  <tr>
    <td>/api/images/retrieveandstore</td>
    <td>GET</td>
    <td>Retrieve and store animal images.</td>
  </tr>
  <tr>
    <td>/api/images/get/{id}</td>
    <td>GET</td>
    <td>Retrieve an image by its ID.</td>
  </tr>
</table>

<p><strong>Note:</strong> Replace <code>{id}</code> with a valid image ID.</p>

<h2>Setup and Installation</h2>
<h3>Prerequisites</h3>
   Java: JDK 17 or higher.
   Maven: Version 3.6+.
   Docker: Installed and running.
   PostgreSQL: Installed and running.
<h3>Clone the Repository</h3>
   <code>git clone <repository-url>
   cd animal-endpoints</code>
<h3>Build the Application</h3>
   <code>mvn clean package</code>
<h3>Run the Application Locally</h3>
   <code>java -jar target/animal-endpoints.jar</code>
<h3>Run with Docker</h3>
   Build the Docker Image:
    <code>docker build -t animal-endpoints ./<code>
<h4>Run the Container:</h4>
<code>docker run -d --name animal-endpoints-container -p 8080:8080 animal-endpoints</code>
<h4>Access the Application: Visit http://localhost:8080/api/images/.</h4>

<h2>Testing the Endpoints</h2>
<h3>Using Postman</h3>
Home Page:

Method: GET
URL: http://localhost:8080/api/images/
Retrieve and Store Images:

Method: GET
URL: http://localhost:8080/api/images/retrieveandstore?animal=cat&count=3
Get Image by ID:

Method: GET
URL: http://localhost:8080/api/images/get/{id}
Replace {id} with a valid image ID.
Error Handling
Invalid animal types (e.g., lion) return a styled HTML page with an error message.
Missing required parameters return a 400 Bad Request error with an HTML response.
Server errors return a 500 Internal Server Error with an appropriate error message.

<h2>Future Enhancements</h2>
<ul>
<li>Add support for additional animal types.</li>
<li>Implement user authentication and authorization.</li>
<li>Create a frontend UI for easier interaction.</li>
<li>Add automated tests for endpoints.</li>
<li>Support image retrieval based on filters (e.g., date range).</li>
</ul>

<h2>Contact</h2>
<p>For questions or support, please contact: April Vuylsteke<br/>
Email: april.vuylsteke.work@gmail.com or april.vuylsteke@csuglobal.edu<br/>
GitHub: aprilvuylsteke000<br/></p>