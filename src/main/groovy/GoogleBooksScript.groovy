import groovy.json.JsonSlurper
import groovy.sql.Sql


def url = "https://www.googleapis.com/books/v1/volumes?q=groovy"
def feed = new URL(url).text

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

//Create connection
def sql = Sql.newInstance("jdbc:mysql://localhost:3306/techreads", "root", "root+1", "com.mysql.jdbc.Driver")

def booksTable = sql.dataSet("book")
books.each {
    booksTable.add(it)
}