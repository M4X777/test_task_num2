# Test task
A test task to determine the total number of sheets in documents such as docx and pdf
REST-service for counting the number of pages in documents in the directory tree
Accepts the path to the root folder as input (http://localhost:8080/api/filepath/[path to the directory]):

 ![image](https://user-images.githubusercontent.com/98019131/227762825-92bf48aa-230f-4864-b42e-82ee106e0bb4.png)

Outputs the total number of documents and the total number of pages in them, recursively process nested directories if available:

 ![image](https://user-images.githubusercontent.com/98019131/227762881-57c28f32-db5d-45c6-a79c-7f35f51a3c80.png)

Works with formats .docx and .pdf, thanks to the use of the factory, can be expanded to work with other types of documents.
Spring Boot, Apache POI, Apache Tika are used from libraries and frameworks, logging and unit tests (Junit, Mockito) are added. The project is being built using Maven, an executable JAR file has been created.
If you enter an incorrect path to the folder, the corresponding error is written:
 
 ![image](https://user-images.githubusercontent.com/98019131/227762921-559f5777-601a-463b-9cfa-5cfe49fdbd53.png)
