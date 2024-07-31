<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Playlist</title>
</head>
<body>
    <h1>Create Playlist</h1>
    <form action="/create-playlist" method="post">
        <label for="userId">User ID:</label>
        <input type="text" id="userId" name="userId" required>
        <br><br>
        <label for="name">Playlist Name:</label>
        <input type="text" id="name" name="name" required>
        <br><br>
        <label for="description">Description:</label>
        <textarea id="description" name="description" required></textarea>
        <br><br>
        <input type="submit" value="Create Playlist">
    </form>
</body>
</html>
