# Music Streaming Library

A lightweight RESTful API for a music streaming service.

## Overview

This is a backend project that manages music metadata and playback logic. It is built using **Spring Boot** and **Gradle**, following the **Controller-Service-Repository** architectural pattern.

The project is organized using a **Package-by-Feature** structure for better modularity.

**Current Status:** Work in Progress (WIP)

## Technologies

*   **Language:** Java / Groovy
*   **Framework:** Spring Boot
*   **Build Tool:** Gradle
*   **Database:** PostgreSQL

## Features

*   **Songs:** Upload, retrieve, and manage song metadata.
*   **Playlists:** Create custom playlists and add/remove tracks.
*   **Users:** User profiles and authentication.

## Project Structure

The source code uses a **Package-by-Feature** organization. All source code is located in `src/main/java/com/sail/musiclibrary`.

Key packages include:
*   `.../media`: Contains the Controller, Service, and Repository for Media management.
*   `.../playlist`: Contains the logic for Playlist management.
*   `.../user`: Contains the logic for User management.

The main entry point is `MusicLibraryApplication.java`.

## Documentation

Architecture diagrams and additional documentation can be found in the `docs` folder.
