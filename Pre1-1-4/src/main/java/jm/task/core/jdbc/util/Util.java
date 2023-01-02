package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static Connection con = null;
    private static SessionFactory sessionFactory = null;

    static {
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&allowMultiQueries=true&serverTimezone=UTC";
        String user = "root";
        String pass = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url , user , pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public static SessionFactory getSessionFactory() {
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&allowMultiQueries=true&serverTimezone=UTC";
        String user = "root";
        String pass = "root";
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.url" , url);
            configuration.setProperty("hibernate.connection.username" , user);
            configuration.setProperty("hibernate.connection.password" , pass);
            configuration.addAnnotatedClass(User.class);
            configuration.setProperty("hibernate.c3p0.min_size" , "5");
            configuration.setProperty("hibernate.c3p0.max_size" , "100");
            configuration.setProperty("hibernate.c3p0.max_statements" , "100");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;

    }
}
