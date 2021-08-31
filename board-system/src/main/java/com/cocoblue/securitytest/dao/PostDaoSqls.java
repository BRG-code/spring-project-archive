package com.cocoblue.securitytest.dao;

public class PostDaoSqls {
    public static String SELECT_ALL_BY_BOARD_NAME = "SELECT p.id as id, p.title as title, p.writer_id as writer_id, p.write_time as write_time, p.board_id as board_id, p.view_number as view_number, m.name as writer_name, b.name as board_name\n" +
            "FROM post as p\n" +
            "JOIN board as b\n" +
            "ON p.board_id = b.id\n" +
            "JOIN member as m\n" +
            "ON p.writer_id = m.id\n" +
            "WHERE b.name = :boardName\n" +
            "ORDER BY p.write_time DESC";

    public static String SELECT_BY_POST_ID = "SELECT p.id as id, p.title as title, p.content as content, p.writer_id as writer_id, p.write_time as write_time, p.view_number as view_number, m.name as writer_name\n" +
            "FROM post as p\n" +
            "JOIN member as m\n" +
            "ON p.writer_id = m.id\n" +
            "WHERE p.id = :id\n" +
            "ORDER BY p.write_time DESC";

    public static String UPDATE_VIEW_NUMBER = "UPDATE post SET view_number = view_number + 1 WHERE id = :postId";

    public static String SELECT_COUNT_BY_BOARD_NAME = "SELECT count(*) as post_count FROM post as p JOIN board as b ON p.board_id = b.id WHERE b.name = :boardName GROUP BY p.board_id";

    public static String SELECT_POSTS_BY_PAGE = "SELECT p.id as id, p.title as title, p.writer_id as writer_id, p.write_time as write_time, p.board_id as board_id, p.view_number as view_number, m.name as writer_name, b.name as board_name\n" +
            "FROM post as p\n" +
            "JOIN board as b\n" +
            "ON p.board_id = b.id\n" +
            "JOIN member as m\n" +
            "ON p.writer_id = m.id\n" +
            "WHERE b.name = :boardName\n" +
            "ORDER BY p.write_time DESC LIMIT :start, :end";

    public static String SELECT_POSTS_BY_KEYWORD = "SELECT p.id as id, p.title as title, p.writer_id as writer_id, p.write_time as write_time, p.board_id as board_id, p.view_number as view_number, m.name as writer_name, b.name as board_name\n" +
            "FROM post as p\n" +
            "JOIN board as b\n" +
            "ON p.board_id = b.id\n" +
            "JOIN member as m\n" +
            "ON p.writer_id = m.id\n" +
            "WHERE b.name = :boardName AND (p.content LIKE :keyword OR p.title LIKE :keyword)\n" +
            "ORDER BY p.write_time DESC \n" +
            "LIMIT :start, :end";

    public static String SELECT_COUNT_BY_KEYWORD = "SELECT count(*) as post_count FROM post as p JOIN board as b ON p.board_id = b.id WHERE b.name = :boardName AND (p.title LIKE :keyword OR p.content LIKE :keyword) GROUP BY p.board_id";

    public static String DELETE_BY_POST_ID = "DELETE FROM post WHERE id = :postId";

    public static String UPDATE_BY_POST_ID = "UPDATE post SET content = :content, title = :title WHERE id = :postId";
}
