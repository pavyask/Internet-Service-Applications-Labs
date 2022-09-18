# Files API documentation:  
**How to run the app**  
1. Clone **master** or **Lab6_dockerized**  
2. Docker must be started  
3. Open terminal inside the project
4. Run the command `docker compose up`
5. When all 3 containers will be started you can use the API  

**Accessing database**  
Use the adminer (http://localhost:9301)  
Username=admin  
Password=adminadmin  
Dbname=files

**Uploading file:**  
All uploaded files can be seen inside **/upload** folder  
POST http://localhost:9300/uploadFile  

![image](https://user-images.githubusercontent.com/81424607/190914339-bd69759a-b2d6-4c4c-8db6-a9caa9423c1b.png)


**Downloading file:**  
GET http://localhost:9300/downloadFile/{id}  

![image](https://user-images.githubusercontent.com/81424607/190914491-39cae3f7-5041-470d-b22b-7f352c94e473.png)
