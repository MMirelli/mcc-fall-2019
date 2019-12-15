# Cloud-based task management project – Group 14

## Introduction and functionalities

This android application is a project management cloud platform developed, in one month, for the course of Mobile Cloud Computing held in Aalto University during the fall semester 2019. It provides with the following in-app functionalities:

* project creation/deletion;
* project report generation;
* task creation/deletion for a project;
* attach multi-format files to project (`txt`, `pdf`, `jpg`, `mp3`);
* adding members to a project;
* user settings and account management;
* notifications on project assignment.
 
---

## Components

These are the basic components of the application which was developed for the course project:

- _Backend_ - Runs in on a Dockerized environment in `GCP App Engine` Flex environment.
- _Frontend_ - Android Application targeting API v28.
- _Database_ - The database is hosted on GCP `Firestore Database`.
- _Storage_ - The files are stored in GCP `Cloud buckets`.
- _API Endpoints_ - The OpenAPI Endpoints are deployed on `Google Cloud Endpoints` using a ESP to our App engine.
- _Push Notification Service_ - Triggered by `Google Cloud Functions` and messages sent to the device via `Firebase Cloud Messaging`.
- _Email-Notification Service_ - Runs in `Google cloud platform compute engine` (Instance Provisioned by `Terraform`).
- _CI and CD of repo_ - The continuos integration and deployment of the git repo is handled by two private GitLab CI runners. (Hosted on GCP Compute engine and Oracle Cloud engine each).

In this repository we only include the part of the project which I was responsible for: android frontend application. 

## Frontend

The frontend has been developed using both Kotlin and Java. You can find the application logic in the [folder](app/src/main/java/mcc/group14/apiclientapp), 

```
.
├── api
├── data
├── utils
└── views
```

* [api](app/src/main/java/mcc/group14/apiclientapp/api):  classes and interfaces used to contact the backend;
* [data](app/src/main/java/mcc/group14/apiclientapp/data) data models, used to send the current state of the application to the server;
* [utils](app/src/main/java/mcc/group14/apiclientapp/utils): utility classes, used by other components;
* [views](app/src/main/java/mcc/group14/apiclientapp/views): UI components logic.

The graphical interface components (layouts, pictures, icons, etc.) can be found in the [res folder](/app/src/main/res), following the usual conventions for [android applications](https://developer.android.com/studio/projects).

### Libraries

As shown in [build.gradle](app/build.gradle), our app target SDK is 28 Android API (9.0 Pie). The minimum SDK API supported is 26. 

Below a list of used libraries:

* firebase:
	* auth: `firebase-auth:17.0.0`;
	* database: `firebase-database:17.0.0`;
	* messaging: `firebase-messaging:18.0.0`.

* api:
	* calls to BE: `retrofit:2.3.0`;
        * images download: `picasso:2.5.2`; 

* Android libraries: `support-v7:28.0.0`.

* UI:
	* theme: `nachos:1.1.1`;
	* toasts: `md-toast:0.9.0`.

## Acknowledgments

The whole project was conducted in cooperation with my group of peer students: Rohit Raj, Kibria Shuvo, Kirthivaasan Puniamurthy, Aleksandr Kurbatov.
