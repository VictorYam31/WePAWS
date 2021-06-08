<br />
<p align="center">
  <a>
    <img src="https://user-images.githubusercontent.com/70901229/121122364-e3864380-c853-11eb-83f2-3bccce86fa55.png" alt="Logo" width="80" height="80">
  </a>
  <h3 align="center">We Paws</h3>
  <p align="center">
    An all-in-one information searching platform for Pet!
  </p>
</p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>


## About The Project

WePAWS is an all-in-one information searching platform for searching Pet’s related services. Users
can search animal clinics, shops, animal friendly hotels… etc different kinds of pet’s related
categories.

This platform’s database allows users to search for information with simple entries such as keywords
and districts. Users can share personal experiences after enjoyed the services. With those feedbacks
and recommendations, other users can get more information on the service providers.

WePAWS have a rating function in which users can rate the service of different companies. This
rating function makes the users easier to identify the reliability of the shops and services’ companies.

WePAWS want to construct a reliable social media platform for pet's service provider and pet's lovers.
Our mission is to help pet lovers to find the best services in the market and help them to give the best
care to their pets.


## Getting Start
This project uses the Gradle build system. To build this project, use the gradlew build command or use "Import Project" in Android Studio.

For more resources on learning Android development, visit the Developer Guides at [https://example.com](https://example.com).


## Application Architecture
WePAWS implement with 3-Tier architectural design. The advantage of this design is separating different function components and increased maintainability and flexibility. This design involves 3 components in this architecture:

<img src="https://user-images.githubusercontent.com/70901229/121121872-095f1880-c853-11eb-818e-d36257783f1f.png">

1. Presentation Tier is the user interface and communication layer of WePAWS, where the end-user interacts with WePAWS. User’s request and response (e.g., Comment) are collected and send to the Application Tier. All searched information is presented in the Presentation Tier in a user-friendly format.

2. Application Tier is the main process component in WePAWS and it became a bridge between Presentation Tier and Data Tier. All requests are processed from the presentation tier. After collect and process the data, Application Tier will acquire data information from the Database Tier. Finally, the Application Tier will send back the requested
information to presentation tier.

3. Data Tier is the database that stored user’s information and Services data. For user’s information, user can sign up as a member of WePAWS can send the login information including Login ID and Password. For services data, Data Tier stored the pet shop, restaurant animal’s clinic. When Data Tier receives the request from Application Tier.


## Screenshots

<img src="https://user-images.githubusercontent.com/70901229/121122116-7377bd80-c853-11eb-8f72-ab52b477dfd6.png">
<img src="https://user-images.githubusercontent.com/70901229/121122144-7c688f00-c853-11eb-8763-1210b8388d93.png" width=651"  height="622">
<img src="https://user-images.githubusercontent.com/70901229/121122187-8d190500-c853-11eb-98af-9667a9f86555.png" width=651"  height="622">


## Technology
<table>
  <tr>
    <th>Android Application Development</th>
  </tr>
  <tr>
    <th>Language</th>
    <th>Java</th>
  </tr>
    <tr>
    <th>Minimum SDK support</th>
    <th>API 21</th>
  </tr>  
  <tr>
    <th>Support Devices</th>
    <th>Android device with Android 5.0 or upper</th>
  </tr>
    <tr>
    <th>Development Emulator</th>
    <th>AVD emulator, Pixel 3a API30</th>
  </tr>
</table>
