/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

 import Modal.Article;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mohamed
 */
public class ServiceArticle implements IService<Article>{
private Connection cnx = ConnectionClass.getInstance().getCnx() ;
        ObservableList<Article> obList = FXCollections.observableArrayList();

   
    @Override
    public void ajouter(Article t) {
Date d1 = new Date();
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String date = dateFormat.format(d1);
String dc = date;
    try {
        String querry= "INSERT INTO article(`title`, `content` ,`image`, `date`,`user_id`) VALUES ('"+t.getTitle()+"','"+t.getContent()+"','"+t.getImage()+"','"+dc+"','"+t.getUser_id()+"')";
        Statement stm = cnx.createStatement();
 
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

    @Override
    public List<Article> afficher() {
     List<Article> postss = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `article` WHERE archived='"+0+"' ";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Article p = new Article();
            
            p.setId(rs.getInt(1));
            p.setTitle(rs.getString("title"));
            p.setContent(rs.getString(3));
            p.setDate(rs.getDate("date"));
            p.setIdcat(rs.getInt("idcat"));
            postss.add(p);
        }
        
        
        
        return postss;
    } catch (SQLException ex) {
        }
    return postss;
    }

    public ObservableList getArticle() {
 List<Article> postss = new ArrayList();
        ObservableList<Article> obList = FXCollections.observableArrayList();

        try {
       
            String querry = "SELECT * FROM `article` WHERE archived='" + 0 + "' ";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Article p = new Article();
            
            p.setId(rs.getInt(1));
            p.setTitle(rs.getString("title"));
            p.setContent(rs.getString("content"));
            p.setDate(rs.getDate("date"));
            p.setIdcat(rs.getInt("idcat"));
            obList.add(p);
        }
        
        
        
        return obList;
    } catch (SQLException ex) {
        }
    return obList;
    }

    
public Article findByArticleId(int id){
              try {
       String query2="SELECT * FROM `article` WHERE id="+id;

Statement stm = cnx.createStatement();
//                PreparedStatement smt = cnx.prepareStatement(query2);
//                smt.setInt(1, id);
                Article p = new Article();
                ResultSet rs= stm.executeQuery(query2);
                while(rs.next()){
                  // p= new Article(rs.getInt("id"),rs.getString("title"),rs.getString(3),rs.getDate("date"),rs.getInt("idcat"));
            p.setId(rs.getInt("id"));
            p.setTitle(rs.getString("title"));
            p.setContent(rs.getString(3));
            p.setDate(rs.getDate("date"));
            p.setIdcat(rs.getInt("idcat"));
            p.setImage(rs.getString("image"));
            p.setTotalReactions(rs.getInt("totalReactions"));
            p.setNbComments(rs.getInt("nbComments"));
            p.setNbShares(rs.getInt("nbShares"));

                              return p;

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                }
              return null;
    }

    @Override
    public void modifier(Article t) {
 
        try {
  String req ="UPDATE `article` SET `title`=?,`content`=? WHERE id=?";

            PreparedStatement ste = cnx.prepareStatement(req);
            
            ste.setString(1,t.getTitle());
            ste.setString(2,t.getContent());
            ste.setInt(3,t.getId());

            ste.executeUpdate();
            System.out.println(" Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
}

    @Override
    public void supprimer(Article t) {
    
  String req="UPDATE `article` SET `archived`='"+1+"' WHERE id=?";
         try {
             PreparedStatement ste = cnx.prepareStatement(req);
              ste.setInt(1,t.getId());
             ste.executeUpdate();
             System.out.println("post bien Archivé");
            
         }catch (SQLException ex) {
            System.out.println("Probléme d'archivage ");
            System.out.println(ex.getMessage());
        
         }
        
 
         }

public List<Article> search(String entry)throws SQLException{
        List<Article> arr=new ArrayList<>();
       
        
        PreparedStatement ps = cnx.prepareStatement ("SELECT article.id,article.title,article.content ,article.liked,article.idcat FROM article  where (title like ?) ");
        ps.setString(1, "%" + entry + "%");
        ResultSet res = ps.executeQuery();

              System.out.println(ps.toString());
       while (res.next()) { 
               int id=res.getInt("id");
               String title=res.getString("title");
               String content=res.getString("content");
               int liked=res.getInt("liked");
                              int category=res.getInt("idcat");


                Date d = new Date();               
               Article p=new Article(id,title,content,d,liked,category);
               System.out.println(p.toString());
               arr.add(p);
     }
    return arr;
        
}
public List<Article> recherche(String entry)throws SQLException{
        List<Article> arr=new ArrayList<>();
       
        
       List<Article> list = (ArrayList)afficher();

                        list.stream().filter(e -> e.getTitle().equals(entry)).forEach(e -> System.out.println(e));
    return list;
        
}



    public void likedarticle(Article t) {

        int isLiked = t.getLiked();

      if (isLiked == 0) {
            try {
                String req = "UPDATE `article` SET`liked`=1 WHERE id=?";
 
                PreparedStatement ste = cnx.prepareStatement(req);
                ste.setInt(1, t.getId());

                ste.executeUpdate();
                System.out.println(" l'article est liké !!!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
}
          if (isLiked == 1) {
            try {
                String req = "UPDATE `article` SET`liked`=0 WHERE id=?";
 
                PreparedStatement ste = cnx.prepareStatement(req);
                ste.setInt(1, t.getId());

                ste.executeUpdate();
                System.out.println(" l'article est disliké !!!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }


    }

 public void Dislikedarticle(Article t) {

  
          
            try {
                String req = "UPDATE `article` SET`liked`=0 WHERE id=?";
 
                PreparedStatement ste = cnx.prepareStatement(req);
                ste.setInt(1, t.getId());

                ste.executeUpdate();
                System.out.println(" l'article est disliké !!!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

      


    }
    public Article Maxliked() {

        Article post = new Article();
        try {

            String querry = "SELECT id,title,content,MAX(liked) FROM article";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(querry);
            
                Article p = new Article();

                p.setId(rs.getInt(1));
                p.setTitle(rs.getString("title"));
                p.setContent(rs.getString(3));
                p.setDate(rs.getDate("date"));
                p.setIdcat(rs.getInt("idcat"));
             
            return p;
        } catch (SQLException ex) {
        }
        return post;

    }

public void addtocategory(Article t,String category)
{
  try {
  String req ="UPDATE article A SET A.idcat= (SELECT idcat FROM category C WHERE C.nom='"+category+"') WHERE A.title=?";

            PreparedStatement ste = cnx.prepareStatement(req);
            
 
            ste.setString(1,t.getTitle());
 
            ste.executeUpdate();
            System.out.println(" Article affecte' to category !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 

}

public void setView(int id){
try {
  String req ="UPDATE article  SET nbShares=nbShares+1 WHERE id="+id;

            PreparedStatement ste = cnx.prepareStatement(req);

            ste.executeUpdate();
            System.out.println(" Article affecte' to category !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
}
}
    
