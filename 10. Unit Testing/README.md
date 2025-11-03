# 10. Unit Testing 

Pada kesempatan kali ini, diberikan sebuah code dari buku [Object First with Java](https://drive.google.com/file/d/1QCe18ywplwear2IlUoBrYnkO_gxKenr7/view) yang berada pada Chapter 7, Code 7.1. Untuk hasil dari codenya seperti ini :

### SalesItem.java
```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

public class SalesItem{
    private String name;
    private int price; // in cents
    private ArrayList<Comment> comments;
    
    /**
    * Create a new sales item.
    */
    public SalesItem(String name, int price)
    {
        this.name = name;
        this.price = price;
        comments = new ArrayList<Comment>();
    }
    
    /**
    * Return the name of this item.
    */
    public String getName(){
        return name;
    }
    
    /**
    * Return the price of this item.
    */
    public int getPrice(){
        return price;
    }
    
    /**
    * Return the number of customer comments for this item.
    */
    public int getNumberOfComments(){
        return comments.size();
    }
    
    /**
    * Check whether the given rating is invalid. Return true if it is
    * invalid. Valid ratings are in the range [1..5].
    **/
    private boolean ratingInvalid(int rating)
    {
        return rating < 0 || rating > 5;
    }
    
    /**
    * Find the comment by the author with the given name.
    *
    * @return The comment if it exists, null if it doesnt.
    **/
    private Comment findCommentByAuthor(String author)
    {
        for(Comment comment : comments) {
            if(comment.getAuthor().equals(author)) {
                return comment;
                }
            }
        return null;
    }
    
    /**
    * Add a comment to the comment list of this sales item. Return
    * true if successful, false if the comment was rejected.
    *
    * The comment will be rejected if the same author has already
    * left a comment or if the rating is invalid. Valid ratings are
    * numbers between 1 and 5 (inclusive).
    */
    public boolean addComment(String author, String text, int rating){
        if(ratingInvalid(rating)) { // reject invalid ratings
            return false;
        }
        
        if(findCommentByAuthor(author) != null) {
            // reject multiple comments by same author
            return false;
        }
        
        comments.add(new Comment(author, text, rating));
        return true;
    }
    
    /**
    * Remove the comment stored at the index given. If the index is
    * invalid, do nothing.
    */
   
    public void removeComment(int index)
    {
        if(index >=0 && index < comments.size()) { // index is valid
            comments.remove(index);
        }
    }
    
    /**
    * Upvote the comment at 'index'. That is: count this comment as
    * more helpful. If the index is invalid, do nothing.
    */
    
    public void upvoteComment(int index)
    {
        if(index >=0 && index < comments.size()) { // index is valid
            comments.get(index).upvote();
        }
    }
    
    /**
    * Downvote the comment at 'index'. That is: count this comment as
    * less helpful. If the index is invalid, do nothing.
    */
    public void downvoteComment(int index)
    {
        if(index >=0 && index < comments.size()) { // index is valid
            comments.get(index).downvote();
        }
    }
    
    /**
    * Show all comments on screen. (Currently, for testing purposes:
    * print to the terminal. Modify later for web display.)
    */
    public void showInfo()
    {
        System.out.println("*** " + name + " ***");
        System.out.println("Price: " + priceString(price));
        System.out.println();
        System.out.println("Customer comments:");
        for(Comment comment : comments) {
            System.out.println("-----------------------------------");
            System.out.println(comment.getFullDetails());
        }
        System.out.println();
        System.out.println("=====================================");
    }
    
    /**
    * Return the most helpful comment. The most useful comment is the
    * one with the highest vote balance. If there are multiple
    * comments with equal highest balance, return any one of them.
    */
    public Comment findMostHelpfulComment()
    {
        Iterator<Comment> it = comments.iterator();
        Comment best = it.next();
        while(it.hasNext()) {
            Comment current = it.next();
            if(current.getVoteCount() > best.getVoteCount()) {
                best = current;
            }
        }
        return best;
    }
    
    
    /**
    * For a price given as an int, return a readable String
    * representing the same price. The price is given in whole cents.
    * For example for price==12345, the following String
    * is returned: $123.45
    */
    private String priceString(int price)
    {
        int dollars = price / 100;
        int cents = price - (dollars*100);
        if(cents <= 9) {
            return "$" + dollars + ".0" + cents; // zero padding
        }
        else {
            return "$" + dollars + "." + cents;
        }
    }
}
```

Pada kode diatas, akan mengatur setiap item yang tersedia, dimana setiap item akan terdapat variabel nama berupa `name`, harga berupa `price`, dan isi komentar terhadap item tersebut berupa `comments` dengan tipe Array.

### Comment.java
```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

public class Comment
{
    // instance variables - replace the example below with your own
    private String author;
    private String text;
    private int rating;
    private int votes;

    public Comment(String author, String text, int rating)
    {
        this.author = author;
        this.text = text;
        this.rating = rating;
        this.votes = 0;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getText()
    {
        return text;
    }

    public int getRating()
    {
        return rating;
    }

    public int getVoteCount()
    {
        return votes;
    }

    public void upvote()
    {
        votes++;
    }

    public void downvote()
    {
        votes--;
    }

    public String getFullDetails() {
        return author + " (" + rating + "): " + text + " [" + votes + "]";
    }
}
```

Pada class **Comment** ini akan mengatur isi dari comment yang ada. Dengan ada beberapa pengaruh kualitas dengan `upvote` dan `downvote` yang disesuakan dengan seberapa manfaat comment tersebut kepada orang lain. Untuk aturan-aturan yang telah dibuat pada pada `SalesItem` yaitu :
1. Comment hanya bisa dikirim satu saja per author
2. Rating diisi hanya dari rentang 1-5
3. Apabila terdapat orang yang merasa comment dari orang lain berguna, mereka bisa menaikkan comment tersebut hingga bisa disandarkan comment paling bermanfaat
4. Sebaliknya, jika dirasa comment tersebut tidak terlalu relevan bisa dikurangi nilai votenya

### SalesItemTest.java
```java
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SalesItemTest {
    private SalesItem item;

    @Before
    public void setUp() {
        item = new SalesItem("Laptop", 15000000); 
    }

    @Test
    public void testAddValidComment() {
        boolean result = item.addComment("Alice", "Bagus banget!", 5);
        assertTrue(result);
        assertEquals(1, item.getNumberOfComments());
    }

    @Test
    public void testRejectDuplicateAuthor() {
        item.addComment("Bob", "Mantap", 4);
        boolean result = item.addComment("Bob", "Keren lagi!", 3);
        assertFalse(result); 
        assertEquals(1, item.getNumberOfComments());
    }

    @Test
    public void testRejectInvalidRating() {
        boolean result = item.addComment("Charlie", "Buruk", 10); 
        assertFalse(result);
        assertEquals(0, item.getNumberOfComments());
    }

    @Test
    public void testRemoveComment() {
        item.addComment("Dina", "Biasa aja", 3);
        item.removeComment(0);
        assertEquals(0, item.getNumberOfComments());
    }

    @Test
    public void testFindMostHelpfulComment() {
        item.addComment("Eka", "Mantap", 5);
        item.addComment("Fajar", "Bagus", 4);
        item.upvoteComment(0); // Eka +1
        item.upvoteComment(0); // Eka +2
        item.upvoteComment(1); // Fajar +1

        Comment best = item.findMostHelpfulComment();
        assertEquals("Eka", best.getAuthor());
    }
}
```

Untuk unit testingnya, disini digunakan jenis Item Laptop dengan harga 15.000.000 menggunakan `@before` yang terdapat pada framework `JUnit` <br>

Contoh : <br> 
Pada contoh Test 2 <br>
Disitu mengetes apakah untuk bagian duplikat comment sesuai dengan aturan yang ada. Hasilnya dikarenakan harus gagal, maka digunakan assertFalse untuk menyimpannya. Lalu dilanjutkan commentnya dibandingkan lagi berdasarkan totalnya yang harus sama dengan 1.

## Dokumentasi
<img width="1116" height="748" alt="image" src="https://github.com/user-attachments/assets/83e43943-fe12-4024-97c3-725bac9cd5c7" />





