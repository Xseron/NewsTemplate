package db;

import model.Comment;
import model.News;
import model.NewsCategory;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "1q2w3e4r"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get all news categories
    public static List<NewsCategory> getNewsCategories() {
        List<NewsCategory> categories = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM news_categories"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categories.add(new NewsCategory(resultSet.getInt("id"), resultSet.getString("name")));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public static void deleteNews(int newsId) throws SQLException {
        try {
            // Delete comments associated with the news article
            deleteCommentsForNews(newsId);

            // Now, delete the news article
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM news WHERE id = ?"
            );
            statement.setInt(1, newsId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static String getCategoryNameId(int categoryId) throws SQLException {
        String category = null;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, name FROM news_categories WHERE id = ?"
            );
            statement.setInt(1, categoryId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Assuming you have a constructor in the Category class
                category = resultSet.getString("name");
            }

            statement.close();
        } catch (SQLException e) {
            throw e;
        }

        return category;
    }

    // Helper method to delete comments associated with a news article
    private static void deleteCommentsForNews(int newsId) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM comments WHERE news_id = ?"
            );
            statement.setInt(1, newsId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void updateNews(News updatedNews) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE news SET title = ?, column1 = ?, category_id = ? WHERE id = ?"
            );
            statement.setString(1, updatedNews.getTitle());
            statement.setString(2, updatedNews.getContent());
            statement.setInt(3, updatedNews.getCategoryId());
            statement.setInt(4, updatedNews.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void updateUserProfile(User user) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET email = ?, password = ?, full_name = ? WHERE id = ?"
            );
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void createNewsCategory(String categoryName) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO news_categories (name) VALUES (?)"
            );
            statement.setString(1, categoryName);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static List<NewsCategory> getAllNewsCategories() throws SQLException {
        List<NewsCategory> categories = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM news_categories"
            );
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int categoryId = resultSet.getInt("id");
                String categoryName = resultSet.getString("name");

                NewsCategory category = new NewsCategory(categoryId, categoryName);
                categories.add(category);
            }

            statement.close();
        } catch (SQLException e) {
            throw e;
        }

        return categories;
    }

    public static void deleteNewsCategory(long categoryId) throws SQLException {
        try {
            // Assuming you have a PreparedStatement to delete a news category by ID
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM news_categories WHERE id = ?"
            );
            statement.setLong(1, categoryId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static List<Comment> getAllCommentsFromNews(News news) {
        int newsId = news.getId();
        List<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM comments WHERE news_id = ?"
            );
            statement.setInt(1, newsId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setCommentText(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date"));
                comment.setUserId(resultSet.getInt("user_id"));
                comment.setNewsId(resultSet.getInt("news_id"));
                comments.add(comment);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }
    public static List<Comment> getAllCommentsFromNews(int newsId) {
        List<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM comments WHERE news_id = ?"
            );
            statement.setInt(1, newsId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setCommentText(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date"));
                comment.setUserId(resultSet.getInt("user_id"));
                comment.setNewsId(resultSet.getInt("news_id"));
                comments.add(comment);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static News getNewsById(long newsId) throws SQLException {
        News news = null;
        try {
            // Assuming you have a PreparedStatement to get a news article by ID
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM news WHERE id = ?"
            );
            statement.setLong(1, newsId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Assuming you have a constructor in the News class
                news = new News(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("post_date"),
                        resultSet.getInt("category_id"),
                        resultSet.getString("title"),
                        resultSet.getString("content")
                );
            }

            statement.close();
        } catch (SQLException e) {
            throw e;
        }
        return news;
    }

    // Method to add a news category
    public static void addNewsCategory(NewsCategory category) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO news_categories(name) VALUES(?)"
            );
            statement.setString(1, category.getName());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to add news
    public static void addNews(News news) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO news(category_id, title, content) VALUES(?, ?, ?)"
            );
            statement.setInt(1, news.getCategoryId());
            statement.setString(2, news.getTitle());
            statement.setString(3, news.getContent());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to add a comment
    public static void addComment(Comment comment) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO comments(comment, user_id, news_id) VALUES(?, ?, ?)"
            );
            statement.setString(1, comment.getCommentText());
            statement.setInt(2, comment.getUserId());
            statement.setInt(3, comment.getNewsId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users(email, password, full_name, role_id) " +
                            "VALUES(?, ?, ?, '1')"
            );
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<News> getAllNews() throws SQLException {
        List<News> newsList = new ArrayList<>();

        try {
            // Assuming you have a PreparedStatement to get all news articles
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM news"
            );

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Assuming you have a constructor in the News class
                News news = new News(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("post_date"),
                        resultSet.getInt("category_id"),
                        resultSet.getString("title"),
                        resultSet.getString("content")
                        // Add other fields as needed
                );
                newsList.add(news);
            }

            statement.close();
        } catch (SQLException e) {
            throw e;
        }

        return newsList;
    }

    public static User getCommentSender(int userId) throws SQLException {
        User user = null;

        try {
            // Assuming you have a PreparedStatement to get user details by ID
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE id = ?"
            );

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Assuming you have a constructor in the User class
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("role_id")
                        // Add other fields as needed
                );
            }

            statement.close();
        } catch (SQLException e) {
            throw e;
        }

        return user;
    }

    // Method to retrieve a user by email and password
    public static Long checkUser(String email, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT id FROM users WHERE email = ? AND password = ?"
        );
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getLong("id");
        }
        return null;
    }

    // Method to retrieve a user by ID
    public static User getUser(String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM users WHERE email = ?"
        );
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
            return new User(resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("full_name"),
                    resultSet.getString("role_id")
            );

        return null;
    }
}
