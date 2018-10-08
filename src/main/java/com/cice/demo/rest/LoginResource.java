package com.cice.demo.rest;


import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController //anotacion para que sepa spring lo que tiene que hacer
public class LoginResource {

    @RequestMapping(method = RequestMethod.POST,path="/login") //mapeo de peticion.
                                                               // estoy mandando información por lo que es un post
    public String login(@RequestParam(name="user") String user,
                        @RequestParam(name="pass")String pass) {
                                    //metod publico q me va a devolver un string
        String respuesta=null;
        //respuesta= user+ " "+pass;


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection(
                     "jdbc:mysql://localhost:8889",
                    "root",
                     "root");

            Statement statement=connection.createStatement();
            ResultSet busqueda=statement.executeQuery("SELECT * from usuarios where user='"+user+"' and pass='"+pass+"' ");
            if(busqueda.first()){
                respuesta="usuario encontrado";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return respuesta;
    }
}
