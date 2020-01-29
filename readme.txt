Задание Focus Start: Реализация ORM для работника библиотеки.

Программа обладает следующей функциональностью:

1. Добавление книг в базу данных.
2. Поиск книг по разным условиям.
3. Удаление книг по разным условиям.
4. Поиск авторов всех книг.
5. Добавление пользователей в базу данных.
6. Возможность взять книгу пользователем.
7. Возможность сдать книгу пользователю.
8. Контроль за книгами:
    а) можно получить все книги, которые были взяты пользователями.
    б) можно посмотреть всю историю взятых и сданных книг по конкретному пользователю.
    в) можно получить все книги, которые были взяты пользователями.
    в) в программе постоянно крутится Scheduled, который отслеживает все книги, которые были
    взяты более чем один месяц назад и отправляет пользователям извещение о том, что книги нужно сдать.


10 Для взаимодействия с ORM доступен следующий интерфейс:


1. Добавить несколько книг:
	метод - POST; 
	адрес - http://localhost:8080/add/books; 
	пример Body -
[
	{
	    "name": "55 устных тем по английскому языку",
	    "volume": "155",
	    "dateOfPublishing": "2003-11-05",
	    "dateOfWriting": "2002-05-05",
	    "authors": [
	        {
	            "name": "Татьяна",
	            "surname": "Журина",
	            "birthday": "1996-06-17"
	        },
	        {
	            "name": "Неизвестный",
	            "surname": "Автор",
	            "birthday": "1999-06-17"
	        }
	    ]
	},
	{
	    "name": "Java. Полное руководство",
	    "volume": "1486",
	    "dateOfPublishing": "2019-10-16",
	    "dateOfWriting": "2002-05-05",
	    "authors": [
	        {
	            "name": "Герберт",
	            "surname": "Шилдт",
	            "birthday": "1977-06-17"
	        }
	    ]
	},
	{
	    "name": "Неизвестная книга",
	    "volume": "10",
	    "dateOfPublishing": "2019-10-16",
	    "dateOfWriting": "2002-05-05",
	    "authors": [
	        {
	            "name": "Неизвестный",
	            "surname": "Автор",
	            "birthday": "1999-06-17"
	        }
	    ]
	}
]

2. Получить все книги
	метод - GET; 
	адрес - http://localhost:8080/books/by/fromBookId/and/toBookId?fromBookId={fromBookId}&toBookId={toBookId}; 
	Body - пусто

3. Найти книги по названию и объему книги
	метод - GET;
	адрес - http://localhost:8080/book/by/name/and/volume?name={название}&volume={объем};
	Body - пусто

4. Найти книги по имени и фамилии автора.
    метод GET
    адрес - http://localhost:8080/books/by/author/name/and/surname?name=Неизвестный&surname=Автор
    Body пуст

5. Удалить книгу по нахзванию книги и объему книги
	метод DELETE;
	адрес - http://localhost:8080/book/by/name/and/volume?name=Spring 5 для профессионалов&volume=1120;
	Body - пуст

6. Добавить читательский билет
    метод - POST
    адрес - http://localhost:8080/add/libraryCards
    пример Body:
[
	{
	"client":
		{
	   	 "phone": "89533576500",
	   	 "email": "postnov-90@mail.ru",
	    	"passport":
	        	{
	            	"name": "Петя",
	           	"surname": "Бубликов",
	            	"birthday": "1964-06-15",
	            	"number": "4567",
	            	"series": "1553445",
	            	"authorityIssuer": "Piter",
	            	"dateSigning": "1990-05-05"
	        	}
		}
	}
]

7. Получить все зарегистрированные читательские билеты
    метод - GET
    адрес - http://localhost:8080/libraryCards/fromLibraryCardsId/and/toLibraryCardId?fromLibraryCardsId={...}&toLibraryCardsId={...}
    Body пуст.

8. Найти конкретный читательский билет по номеру и серии паспорта пользователя
    метод - GET
    адрес - http://localhost:8080/get/libraryCard?number={номер паспорта}&series={номер серии паспорта}
    Body пуст.

9. Удалить читательский билет
    метод - DELETE
    адрес - http://localhost:8080/libraryCard/by/passport/number/and/series?number={...}&series={...}
    Body пуст

10. Дать книжку пользователю
    метод POST
    адрес - http://localhost:8080/received/book
    Пример Body:
{
	"book":
		{
		    "name": "Java. Полное руководство",
		    "volume": "1486",
		    "dateOfPublishing": "2019-10-16",
		    "dateOfWriting": "2002-05-05",
		    "authors": [
		        {
		            "name": "Герберт",
		            "surname": "Шилдт",
		            "birthday": "1977-06-17"
		        }
		    ]
		},
	"libraryCard":
		{
		"client":
			{
			    "phone": "89533576500",
			    "email": "postnov-90@mail.ru",
			    "passport":
			        {
			            "name": "Петя",
			            "surname": "Бубликов",
			            "birthday": "1964-06-15",
			            "number": "4567",
			            "series": "1553445",
			            "authorityIssuer": "Piter",
			            "dateSigning": "1990-05-05"
			        }
			}
		}
}

11. Вернуть книжку пользователем по названию книжки
    метод - POST
    адрес - http://localhost:8080/return/books/by/book/name?number={номер паспорта пользователя}&series={номер серии пользователя}&name={Название книжки}
    Body пуст.

12. Получить всю историю взятия книжек конкретным пользователем.
    метод - GET
    адре - http://localhost:8080/get/history/received/books/by/passportS/number/and/series?number={номер паспорта}&series={серия паспорта}
    Body пуст.

13. Получить вообще все книжки которые в данный момент находятся у пользователей.
    метод - GET
    адрес - http://localhost:8080/all/received/books?fromReceivedBookId={...}&toReceivedBookId={...}
    Body пуст.

14. Получить вообще все книжки которые в данный момент находятся у пользователя.
    метод - GET
    адрес - http://localhost:8080//received/books/by/passportS/number/and/series?number={...}&series={...}
    Body пуст.

Для тестирования проекта через postman необходимо установить в Headers: Content-Type - application/json;charset=UTF-8

Перед запуском приложения нужно сделать следущее:
1. Зайти в файл application.properties
2. Указать свою почту и пароль от нее.
3. Указать пароль от своей базы данных PostgresSql.
3. Создать в свой базе данных postgresSql базу данный library

Для запуска проекта необходимо запустить файл applicationLibrary

Спасибо за внимание!