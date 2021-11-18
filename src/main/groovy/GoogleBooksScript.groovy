import groovy.json.JsonSlurper
import groovy.sql.Sql

// retrieve JSON from endpoint
def searchTerm = "groovy"
def url = "https://www.googleapis.com/books/v1/volumes?q=${searchTerm}"
def feed = new URL(url).text
// convert retrieved JSON into maps that match structure of SQL table
def jsonSlurper = new JsonSlurper()
def apiData = jsonSlurper.parseText(feed)
def books = []
apiData['items'].each {
    bookInfo = it['volumeInfo']
    books << [
            'title': bookInfo['title'],
            'coverURL': bookInfo['imageLinks']['thumbnail'],
            'author':  bookInfo['authors']?.join(", "),
            'rating': bookInfo['averageRating']
    ]
}
// create SQL connection
def sql = Sql.newInstance("jdbc:mysql://localhost:3306/techreads", "root", "root+1", "com.mysql.jdbc.Driver")
// grab handle on book table and use it to insert each book mapping into the database
def booksTable = sql.dataSet("book")
books.each {
    booksTable.add(it as Map<String, Object>)
}